package com.siruiman.crosslogistics.controller;


import com.alibaba.fastjson.JSONObject;
import com.siruiman.crosslogistics.config.NETSPayConfig;
import com.siruiman.crosslogistics.enums.ZwCode;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.service.*;
import com.siruiman.crosslogistics.util.CLientIpUtils;
import com.siruiman.crosslogistics.util.DateUtil;
import com.siruiman.crosslogistics.util.NETSPayUtil;
import com.siruiman.crosslogistics.util.StaticConfigUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import static java.math.BigDecimal.ROUND_HALF_UP;

/**
 * @author 张占伟
 * @date 2019/2/28 10:05
 * nets支付接口
 */
@RestController
@RequestMapping("netsPay")
@Api(value="NETS-Pay",description = "NetsPay-API",tags={"nets支付Api"})
public class NETSPayController {
    private static final Logger log = LoggerFactory
            .getLogger(NETSPayController.class);
    @Autowired
    private GoodsDetailsService goodsDetailsService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private AppUserWalletStreamService appUserWalletStreamService;

    @Autowired
    private LogisticInfoService logisticInfoService;

    @Autowired
    private AppUserAddressService appUserAddressService;

    @Autowired
    private FinanceMoneyFlowService financeMoneyFlowService;


    @ApiOperation(value = "AppNETS支付接口",notes = "appNETS支付接口",nickname = "appNETS支付接口",tags={"@张占伟"})
    @RequestMapping(method = RequestMethod.POST,value = "appNETSPay")
    public CommonResponse appNETSPay(@Validated @RequestParam String deliveryNumber,@Validated @RequestParam String mobileOs, HttpServletRequest request) {
//       1.根据订单号查出货物运费
        CommonResponse response = new CommonResponse();
        Goods goods;
        try {
            goods = goodsService.selectGoodsAmountByDeliveryNumber(deliveryNumber);
        }catch (Exception e){
            response.setCode(ZwCode.ERROR_PAY.getCode());
            response.setMessage(ZwCode.ERROR_PAY.getInfo());
            log.info("查询货物运费失败");
            return response;
        }
//        计算公式 金额(分)等于人民币元*100/汇率 取整
        BigDecimal txnAmount = goods.getAmount().multiply(new BigDecimal("100"))
                .divide(StaticConfigUtil.SGDtoCNYExchangeFrate,0,ROUND_HALF_UP);

        NetsReqModel model = new NetsReqModel();
        log.info("正在生成签名nets支付");
        try {
            String txnReq = NETSPayUtil.getTxnReq(txnAmount+"",mobileOs,deliveryNumber,CLientIpUtils.getIp(request));
            String mac = NETSPayUtil.generateSignature(txnReq, NETSPayConfig.KEYSECRET);
            model.setApiKey(NETSPayConfig.APIKEY);
            model.setMac(mac);
            model.setTxnReq(txnReq);
            response.setCode(ZwCode.SUCCESS_GET.getCode());
            response.setMessage(ZwCode.SUCCESS_GET.getInfo());
            response.setData(model);
            log.info("生成签名nets支付成功");
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(ZwCode.FAIL_GET.getInfo());
            response.setCode(ZwCode.FAIL_GET.getCode());
        }
        return response;
    }



    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/s2sTxnEnd", method =
            RequestMethod.POST)
    public ResponseEntity<Void> receiveS2STxnEnd(@RequestBody String txnRes, HttpServletRequest
            request) {
        //json message received as string
        log.debug("MERCHANT APP : in receiveS2STxnEnd :" + txnRes);
        String ip = CLientIpUtils.getIp(request);
        log.info(ip);
//        todo 换为正式的ip 两个 或的关系 ||
        if (!NETSPayConfig.NETS_IP.equals(ip)){
            log.error("警告!不是NETS的通知");
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        try {
            //generate mac
            String generatedHmac = NETSPayUtil.generateSignature(txnRes, NETSPayConfig.KEYSECRET);
            String macFromGW = request.getHeader("hmac");
            log.info("MERCHANT APP : header hmac received :" + macFromGW);
            log.info("MERCHANT APP : header hmac generated :" + generatedHmac);
//            验证签名 忽略大小写
            if(generatedHmac.equalsIgnoreCase(macFromGW)){
//          parse message
                JSONObject obj = JSONObject.parseObject(txnRes);
                JSONObject msg = (JSONObject) obj.get("msg");
                if ("0".equals(msg.getString("netsTxnStatus"))
                        &&NETSPayConfig.UMID.equals(msg.get("netsMid"))){
                    log.info("效验成功");
                    String deliveryNumber = msg.getString("tid");
                    BigDecimal amount =new BigDecimal(msg.getString("netsAmountDeducted"));
                    Goods goods = goodsService.selectGoodsAmountByDeliveryNumber(deliveryNumber);
                    //                 根据goodsid查出用户id
                    int uId = goodsDetailsService.selectAPPUIdByGdId(goods.getGoodsId());
                    GoodsDetails goodsDetails = goodsDetailsService.selectGooodsPayStateByNOAndUID(deliveryNumber,uId);
                    if ("已支付".equals(goodsDetails.getPaymentStatus())){
                        return new ResponseEntity<Void>(HttpStatus.OK);
                    }
                    else{
//                        未支付 修改支付结果为已支付
                            GoodsDetails details = new GoodsDetails();
                            details.setGoodsId(goods.getGoodsId());
                            details.setUpdateTime(new Date());
                            details.setGoodState("待集运");
                            details.setPayWay("Nets支付");
                            details.setPaymentStatus("已支付");
                            goods.setCreateTime(new Date());
                            AppUserWalletStream appUserWalletStream = new AppUserWalletStream();
                            appUserWalletStream.setGoodsId(goods.getGoodsId());
                            appUserWalletStream.setAmount(goods.getAmount());
                            //新增流水表单位 人民币1
                            appUserWalletStream.setUnit(1);
                            appUserWalletStream.setAddTime(new Date());
                            appUserWalletStream.setStreamType((byte) 6);
                            appUserWalletStream.setUserType("普通用户");
                            appUserWalletStream.setAppUserId(uId);
                            appUserWalletStream.setStreamText("转运订单支付");
                            appUserWalletStream.setState((byte) 7);
                            LogisticInfo info = new LogisticInfo();
                            info.setCreateTime(new Date());
                            info.setOperateComment("订单号为"+deliveryNumber+"发起转运");
                            info.setGoodsId(goods.getGoodsId());
                            info.setAppUserId(uId);
                            info.setOperateType("货物发起转运");
                            info.setOperateResult("货物发起转运成功");
                            info.setGoodsId(goods.getGoodsId());
                            info.setAppUserId(uId);

                            String name = appUserAddressService.selectAppUserNameByUId(uId);
                            FinanceMoneyFlow flow = new FinanceMoneyFlow();
                            flow.setCreateTime(DateUtil.getDateTime());
                            flow.setAmount(goods.getAmount());
                            flow.setFinanceMoneyType("订单支付");
                            flow.setSerialNumber(deliveryNumber);
                            flow.setFinanceObject(name);
                            flow.setUnit(1);
//                            返回时新币
                           /* if("SGD".equals(msg.getString("currencyCode"))){
                                flow.setUnit(3);
                            }*/

//                              返回是美元
                          /*  if ("USD".equals(msg.getString("currencyCode"))){
                                flow.setUnit(2);
                            }*/

                            goodsService.updateGoodsCreate(goods);
                            goodsDetailsService.updateGoodsPayState(details);
                            appUserWalletStreamService.insertUserPay(appUserWalletStream);
                            logisticInfoService.insertLogisticInfo(info);
                            financeMoneyFlowService.insert(flow);
                    }
                }
//          Please handle success or failure response code
            }
            else{
                log.error("验签失败");
//          handle exception flow
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
