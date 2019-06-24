package com.siruiman.crosslogistics.util;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.siruiman.crosslogistics.config.AliPayConfig;

/**
 * @author 张占伟
 * @date 2019/1/6 11:54
 * 支付宝支付工具类
 */
public class AliPayUtil {

    /**
     *  支付宝支付返回 ios 安卓签名
     * @param outTradeNo  快递订单号 (必填)
     * @param totalAmount 付款金额 (必填)
     * @param subject 订单名称(必填)
     * @param body  商品描述(可以为空)
     * @return
     */
    public static String alipay(String outTradeNo,String totalAmount,String subject,String body){
//        参数列表1 // 请求网关地址#, 商户appid#,应用私钥#,返回数据格式,编码格式,支付宝公钥,签名类型
        AlipayClient alipayClient = new DefaultAlipayClient(AliPayConfig.URL,AliPayConfig.APPID,
                AliPayConfig.RSA_PRIVATE_KEY,AliPayConfig.FORMAT,AliPayConfig.CHARSET,AliPayConfig.ALIPAY_PUBLIC_KEY,AliPayConfig.SIGNTYPE);
//      设置请求参数
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
//        服务器异步通知页面路径
        request.setNotifyUrl(AliPayConfig.NOTIFY_URL);
        request.setBizContent("{\"out_trade_no\":\""+ outTradeNo +"\","
                + "\"total_amount\":\""+ totalAmount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"timeout_express\":\""+ "10m"+"\","  //订单支付时间
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        try {
            String result;
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            if (response.isSuccess()){

            }
            result = response.getBody();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
