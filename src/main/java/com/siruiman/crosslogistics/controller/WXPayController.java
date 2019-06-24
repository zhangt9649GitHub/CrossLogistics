package com.siruiman.crosslogistics.controller;

import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.siruiman.crosslogistics.config.IWXPayConfig;
import com.siruiman.crosslogistics.enums.ZwCode;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.service.*;
import com.siruiman.crosslogistics.util.*;
import com.sun.net.httpserver.Authenticator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 张占伟
 * @date 2019/1/8 16:34
 */

@RestController
@RequestMapping("wxpay")
@Api(value = "WXPay-API",description = "微信支付-api",tags = {"微信支付"})
public class WXPayController {
    private static final Logger logger = LoggerFactory
            .getLogger(WXPayController.class);
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
    @Autowired
    private WxpayGoodsService wxpayGoodsService;
    /**
     *
     * 步骤1：商户后台收到用户支付单，调用微信支付统一下单接口。参见【统一下单API】。
     *
     * 步骤2：统一下单接口返回正常的prepay_id，再按签名规范重新生成签名后，将数据传输给APP。参与签名的字段名为appid，partnerid，prepayid，noncestr，timestamp，package。注意：package的值格式为Sign=WXPay
     *
     * 步骤3：商户APP调起微信支付。api参见本章节【app端开发步骤说明】
     *
     * 步骤4：商户后台接收支付通知。api参见【支付结果通知API】
     *
     *
     */

    @ApiImplicitParam(name="deliveryNumber", value="快递单号",paramType="query",dataType="String")
    @ApiOperation(value = "app微信支付接口",notes = "app微信支付接口",nickname = "appWXPay",tags={"@张占伟"})
    @RequestMapping(method = RequestMethod.POST,value = "appWXPay")
    public CommonResponse appWXPay(String deliveryNumber,HttpServletRequest request){
//       1.根据订单号查出货物运费
        CommonResponse response = new CommonResponse();
        Goods goods;
        WxpayGoods wxpayGoods;
        String deliveryNumber1 = null;
        try{
            goods = goodsService.selectGoodsAmountByDeliveryNumber(deliveryNumber);
            wxpayGoods =wxpayGoodsService.selectDeliveryNumber(deliveryNumber);
            if(wxpayGoods == null){
                WxpayGoods wxpayGoods1 = new WxpayGoods();
                wxpayGoods1.setDeliveryNumber(deliveryNumber);
                deliveryNumber1 = SmsCodeUtil.getCode()+deliveryNumber;
                wxpayGoods1.setSysDeliveryNumber(deliveryNumber1);
                wxpayGoods1.setIsPayState(1);
                wxpayGoods1.setDealTime(DateUtil.getWXPayExpireTime(600));
                wxpayGoodsService.insert(wxpayGoods1);
            }else if(wxpayGoods.getIsPayState()== 1){
                Date date = new Date();
                Long nowTime = date.getTime();
                String str =wxpayGoods.getDealTime();
                Date dealTime1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
                Long dealTime =dealTime1.getTime();
                if(nowTime >= dealTime){
                    deliveryNumber1 = SmsCodeUtil.getCode()+deliveryNumber;
                    String dealtime =DateUtil.getWXPayExpireTime(600);
                    wxpayGoodsService.update(deliveryNumber1,dealtime,wxpayGoods.getWxpayGdNumberId());
                }else {
                    deliveryNumber1 =wxpayGoods.getSysDeliveryNumber();
                }
            }

        }catch (Exception e){
            response.setCode(ZwCode.ERROR_PAY.getCode());
            response.setMessage(ZwCode.ERROR_PAY.getInfo());
            return response;
        }
        try {
            String pay = goods.getAmount().multiply(new BigDecimal(100)).intValue()+"";
            logger.info(pay);
            String ip = CLientIpUtils.getIp(request);
            Map<String, String> resurlt = WXPayUtils.wxPay(deliveryNumber1, pay, ip);
            logger.info(resurlt.toString());
            HashMap<String, String> map = new HashMap<>(16);
            map.put("appid",IWXPayConfig.APPID);
            map.put("partnerid",IWXPayConfig.MCH_ID);
            map.put("prepayid",resurlt.get("prepay_id"));
            map.put("noncestr",resurlt.get("nonce_str"));
            map.put("timestamp",String.valueOf(WXPayUtil.getCurrentTimestamp()));
            map.put("package","Sign=WXPay");
            logger.info(map.toString());
            String signature = WXPayUtil.generateSignature(map, IWXPayConfig.KEY);
            map.put("sign",signature);
            response.setData(map);
            response.setMessage(ZwCode.SUCCESS_GET_WX_PAY.getInfo());
            response.setCode(ZwCode.SUCCESS_GET_WX_PAY.getCode());
        }catch (Exception e){
            e.printStackTrace();
            response.setMessage(ZwCode.FAIL_GET_WX_PAY.getInfo());
            response.setCode(ZwCode.FAIL_GET_WX_PAY.getCode());
        }
        return response;
    }

    //  此接口都可以访问不能加权限
    @ApiOperation(value = "app微信异步回调接口",notes = "app微信异步回调接口",nickname = "notifyWXPayResults",tags={"@张占伟"})
    @RequestMapping(method = RequestMethod.POST,value = "notifywxpayResults")
    public void notifyWXPayResults(HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
//      微信支付与支付宝不同,会一直重复发送需验证该条通知是否处理,避免重复处理
        // 读取参数
        PrintWriter writer = response.getWriter();
        InputStream inputStream = request.getInputStream();
        StringBuffer sb = new StringBuffer();
        String s;
        BufferedReader in = new BufferedReader(new InputStreamReader(
                inputStream, "UTF-8"));
        while ((s = in.readLine()) != null) {
            sb.append(s);
        }
        in.close();
        inputStream.close();
        logger.info(sb.toString());
        Map<String, String> map = WXPayUtil.xmlToMap(sb.toString());
//         支付结果为Success
        logger.info(map.toString());
        if ("SUCCESS".equals(map.get("result_code"))&&"SUCCESS".equals(map.get("return_code"))){
//            验证签名
            logger.info("正在验证微信支付签名");
            boolean valid = WXPayUtil.isSignatureValid(map, IWXPayConfig.KEY);
            if (!valid){
                String noticeStr = WXPayUtils.setXML("false", "fall");
                logger.error("微信验签失败");
                writer.print(noticeStr);
                writer.flush();
            }
            if(valid){
                logger.info("微信支付签名验证成功");
                String sysDeliveryNumber = map.get("out_trade_no");
                WxpayGoods wxpayGoods = wxpayGoodsService.selectdeliveryNumberBysysDeliveryNumber(sysDeliveryNumber);
                String deliveryNumber =wxpayGoods.getDeliveryNumber();
                wxpayGoodsService.updateIsPayState(wxpayGoods.getWxpayGdNumberId());
                Goods goods = goodsService.selectGoodsAmountByDeliveryNumber(deliveryNumber);
                //                 根据goodsid查出用户id
                int uId = goodsDetailsService.selectAPPUIdByGdId(goods.getGoodsId());
                GoodsDetails goodsDetails = goodsDetailsService.selectGooodsPayStateByNOAndUID(deliveryNumber,uId);
                if ("已支付".equals(goodsDetails.getPaymentStatus())){
                    String noticeStr = WXPayUtils.setXML("SUCCESS", "OK");
                    logger.info("微信支付结果回馈" + noticeStr);
                    writer.print(noticeStr);
                    writer.flush();
                }else{
                    double amount = goods.getAmount().doubleValue();
                    double amountmark = amount * 100;//换算为分
//              比对appid和mchid
                    if(IWXPayConfig.APPID.equals( map.get("appid"))&&IWXPayConfig.MCH_ID.equals(map.get("mch_id"))) {
//                    对比金额
                        if (Double.parseDouble(map.get("total_fee")) == amountmark) {
//                      修改支付状态
                            GoodsDetails details = new GoodsDetails();
                            details.setGoodsId(goods.getGoodsId());
                            details.setUpdateTime(new Date());
                            details.setGoodState("待集运");
                            details.setPayWay("微信支付");
                            details.setPaymentStatus("已支付");
                            goods.setCreateTime(new Date());
                            AppUserWalletStream appUserWalletStream = new AppUserWalletStream();
                            appUserWalletStream.setGoodsId(goods.getGoodsId());
                            appUserWalletStream.setAmount(new BigDecimal(amount));
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
                            String totalFee = map.get("total_fee");
                            BigDecimal mount = new BigDecimal(totalFee);
                            BigDecimal divide = mount.divide(new BigDecimal("100"), 2, BigDecimal.ROUND_UP);
                            flow.setAmount(divide);
                            flow.setUnit(1);
                            flow.setFinanceMoneyType("订单支付");
                            flow.setSerialNumber(deliveryNumber);
                            flow.setFinanceObject(name);
                            try{
                                goodsService.updateGoodsCreate(goods);
                                goodsDetailsService.updateGoodsPayState(details);
                                appUserWalletStreamService.insertUserPay(appUserWalletStream);
                                logisticInfoService.insertLogisticInfo(info);
                                financeMoneyFlowService.insert(flow);
                                String noticeStr = WXPayUtils.setXML("SUCCESS", "OK");
                                logger.info("微信支付结果回馈" + noticeStr);
                                writer.print(noticeStr);
                                writer.flush();
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }

    }




}
