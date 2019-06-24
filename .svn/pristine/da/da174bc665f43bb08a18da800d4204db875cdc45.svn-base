package com.siruiman.crosslogistics.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.siruiman.crosslogistics.config.AliPayConfig;
import com.siruiman.crosslogistics.enums.ZwCode;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.service.*;
import com.siruiman.crosslogistics.util.AliPayUtil;
import com.siruiman.crosslogistics.util.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author 张占伟
 * @date 2019/1/6 11:12
 */
@RestController
@RequestMapping("alipay")
@Api(value = "AliPay-API",description = "支付宝支付-api",tags = {"支付宝支付"})
public class AliPayController {
    private static final Logger logger = LoggerFactory
            .getLogger(AliPayController.class);
    @Autowired
    private GoodsDetailsService goodsDetailsService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private LogisticInfoService logisticInfoService;



    @Autowired
    private AppUserWalletStreamService appUserWalletStreamService;

    @Autowired
    private AppUserAddressService appUserAddressService;

    @Autowired
    private FinanceMoneyFlowService financeMoneyFlowService;

    @ApiImplicitParam(name="deliveryNumber", value="快递单号",paramType="query",dataType="String")
    @ApiOperation(value = "app支付宝支付接口",notes = "aliPay",nickname = "aliPay",tags={"@张占伟"})
    @RequestMapping(method = RequestMethod.POST,value = "aliPay")
    public CommonResponse appAliPay(String deliveryNumber){
        logger.info("App支付支付宝支付");
        CommonResponse response = new CommonResponse();
        Goods goods;
        try{
           goods = goodsService.selectGoodsAmountByDeliveryNumber(deliveryNumber);
        }catch (Exception e){
            logger.error("查询货物运费失败");
            response.setCode(ZwCode.ERROR_PAY.getCode());
            response.setMessage(ZwCode.ERROR_PAY.getInfo());
            return response;
        }
        try {
        String results = AliPayUtil.alipay(deliveryNumber, goods.getAmount().toString(), "HelpAndLove", "快递订单支付");
        if(results!=null&&results!=""){
            response.setCode(ZwCode.SUCCESS_GET.getCode());
            response.setMessage(ZwCode.SUCCESS_GET.getInfo());
            response.setData(results);
            logger.info("支付宝接口请求成功");
        }
        else {
            logger.error("支付宝接口请求失败");
            response.setCode(ZwCode.FAIL_GET.getCode());
            response.setMessage(ZwCode.FAIL_GET.getInfo());
        }
        }catch (Exception e){
            e.printStackTrace();
            }
        return response;
    }


//  此接口都可以访问不能加权限
    @ApiOperation(value = "app支付宝异步回调接口(app不用对接)",notes = "app不用对接",nickname = "notifyAliPayResults",tags={"@张占伟"})
    @RequestMapping(method = RequestMethod.POST,value = "notifyAliPayResults")
    public void notifyAliPayResults(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("获取支付宝异步回调结果");
//      获得支付结果验证顺序 将支付结果转化为map集合
        PrintWriter writer = response.getWriter();
        Map<String, String> params = new HashMap<>(16);
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++)
            {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。

            params.put(name, valueStr);

        }
        // 切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
//          效验支付宝公钥
        boolean flag = true;
        try {
            logger.info("支付宝签名验证");
            flag = AlipaySignature.rsaCheckV1(params,AliPayConfig.ALIPAY_PUBLIC_KEY,
                    "utf-8", "RSA2");
        } catch (AlipayApiException e) {
            logger.error("警告:支付宝签名验证失败");
//            验证失败不是支付宝发来的异步请求(即第三方抓包工具)
            e.printStackTrace();
            writer.print("FALSE");
            writer.flush();
        }
//        支付宝签名验证失败
        if(!flag){
            logger.error("警告:支付宝签名验证失败");
            writer.print("FALSE");
            writer.flush();
        }
//        验证成功
        if(flag){
//            验证支付状态
            logger.info("支付宝签名验证成功");
            String  deliveryNumber = params.get("out_trade_no");//获取支付订单号
            String receiptAmount = params.get("receipt_amount");//实际收款金额
             logger.info("支付宝实际收款金额"+receiptAmount);
            double alipayAmount = Double.parseDouble(receiptAmount);
//            查出货物应付价格
            Goods goods = goodsService.selectGoodsAmountByDeliveryNumber(deliveryNumber);
            //                 根据goodsid查出用户id
            int uId = goodsDetailsService.selectAPPUIdByGdId(goods.getGoodsId());
            //          查出该条订单有无处理
            GoodsDetails goodsDetails = goodsDetailsService.selectGooodsPayStateByNOAndUID(deliveryNumber,uId);
            if ("已支付".equals(goodsDetails.getPaymentStatus())){
                logger.info("订单编号为:"+deliveryNumber+"已处理");
                writer.print("success");
                writer.flush();
            }else{
            double amount = goods.getAmount().doubleValue();
             logger.info("货物运费应收金额"+amount);
//            实际付款价格
                if (amount==alipayAmount) {
                    String  tradeStatus =    params.get("trade_status");//获取交易状态
                    GoodsDetails details = new GoodsDetails();
                    details.setGoodsId(goods.getGoodsId());
                    details.setUpdateTime(new Date());
                    details.setGoodState("待集运");
                    details.setPayWay("支付宝支付");
                    details.setPaymentStatus("已支付");
                    goods.setCreateTime(new Date());
//                添加流水记录表
                    AppUserWalletStream appUserWalletStream = new AppUserWalletStream();
                    appUserWalletStream.setGoodsId(goods.getGoodsId());
                    appUserWalletStream.setAmount(new  BigDecimal(receiptAmount));
                    //新增流水表单位 人民币1
                    appUserWalletStream.setUnit(1);
                    appUserWalletStream.setAddTime(new Date());
                    appUserWalletStream.setStreamType((byte)6);
                    appUserWalletStream.setUserType("普通用户");
                    appUserWalletStream.setAppUserId(uId);
                    appUserWalletStream.setStreamText("转运订单支付");
                    appUserWalletStream.setState((byte) 7);
                    LogisticInfo info = new LogisticInfo();
                    info.setCreateTime(new Date());
                    info.setOperateComment("订单号为"+deliveryNumber+"发起转运");
                    info.setGoodsId(goods.getGoodsId());
                    info.setOperateType("货物发起转运");
                    info.setOperateResult("货物发起转运成功");
                    info.setAppUserId(uId);
                    info.setGoodsId(goods.getGoodsId());
                    info.setAppUserId(uId);
//                    查出用户名
                    String name = appUserAddressService.selectAppUserNameByUId(uId);
                    FinanceMoneyFlow flow = new FinanceMoneyFlow();
                    flow.setCreateTime(DateUtil.getDateTime());
                    flow.setAmount(new BigDecimal(receiptAmount));
                    flow.setSerialNumber(deliveryNumber);
                    flow.setFinanceObject(name);
                    flow.setUnit(1);
                    flow.setFinanceMoneyType("订单支付");
                    try {
                        switch (tradeStatus) // 判断交易结果
                        {
                            case "TRADE_FINISHED": // 完成
                                goodsService.updateGoodsCreate(goods);
                                goodsDetailsService.updateGoodsPayState(details);
                                appUserWalletStreamService.insertUserPay(appUserWalletStream);
                                logisticInfoService.insertLogisticInfo(info);
                                financeMoneyFlowService.insert(flow);
                                break;
                            case "TRADE_SUCCESS": // 完成
                                goodsService.updateGoodsCreate(goods);
                                goodsDetailsService.updateGoodsPayState(details);
                                appUserWalletStreamService.insertUserPay(appUserWalletStream);
                                logisticInfoService.insertLogisticInfo(info);
                                financeMoneyFlowService.insert(flow);
                                break;
                            case "WAIT_BUYER_PAY": // 待支付
                                break;
                            case "TRADE_CLOSED": // 交易关闭
                                break;
                            default:
                                break;
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                writer.print("success");
                writer.flush();
            }
        }
      //给支付宝一个回馈结果否则会重复发送
    }

    @ApiOperation(value = "支付结果",notes = "货物运费支付结果接口",nickname = "getPayResults",tags={"@张占伟"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="appUserId", value="app用户id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="deliveryNumber", value="快递单号",paramType="query",dataType="String")
    })
    @RequestMapping(value ="getPayResults",method = RequestMethod.POST)
    public CommonResponse getPayResults( String deliveryNumber,int appUserId ){
        CommonResponse response = new CommonResponse();
        try {
            GoodsDetails goodsDetails = goodsDetailsService.selectGooodsPayStateByNOAndUID(deliveryNumber,appUserId);
            if (goodsDetails!=null){
                String status = goodsDetails.getPaymentStatus();
                if ("已支付".equals(status)){
                    response.setCode(ZwCode.SUCCESS_PAYMENT.getCode());
                    response.setMessage(ZwCode.SUCCESS_PAYMENT.getInfo());
                }
                else {
                    response.setCode(ZwCode.NOT_PAYMENT.getCode());
                    response.setMessage(ZwCode.NOT_PAYMENT.getInfo());
                }
            }else {
                response.setCode(ZwCode.FAIL_GET.getCode());
                response.setMessage(ZwCode.FAIL_GET.getInfo());
            }
        }catch (Exception e){
            e.printStackTrace();
            response.setCode(ZwCode.FAIL_GET.getCode());
            response.setMessage(ZwCode.FAIL_GET.getInfo());
        }
        return response;
    }

}
