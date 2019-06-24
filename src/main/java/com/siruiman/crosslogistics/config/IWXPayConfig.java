package com.siruiman.crosslogistics.config;

/**
 * 微信支付配置
 * @author 张占伟
 * @date 2019/1/4 20:44
 */
public class IWXPayConfig   {
//     应用ID
    public  static final String APPID ="wxe5f6b91d62ba5826";
//     public static String APPID ="wx71790eeff9b66024";
    //微信异步通知路径
    public static final String NOTIFY_URL="http://kjwl.helplove.com.sg/wxpay/notifywxpayResults";
    //交易类型
    public static final String TRADE_TYPE ="APP";
    public static String APP_SECRET ="2cc443301301ba94a7c280bf873e1033";// 服务号的应用密钥
    // 商户号
    public static final String MCH_ID ="1526228501";
//    public static String MCH_ID ="1523984001";
//  密钥
    public static final String KEY="kjwl123456Abc6aa163006efbf200808";
//    public static String KEY="8KJWLy8gkDJ4c4ERKYG9y9aC4jwsrmtj";
//  微信支付证书
    public static String CERT_PATH ="";
 // 微信支付接口请求地址
    public static final String WXPAY_URL="https://api.mch.weixin.qq.com/pay/unifiedorder";



}
