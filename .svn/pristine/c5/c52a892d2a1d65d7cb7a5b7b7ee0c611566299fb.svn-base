package com.siruiman.crosslogistics.util;


import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.siruiman.crosslogistics.config.IWXPayConfig;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;

/**
 *
 * 微信支付工具类
 * @author 张占伟
 * @date 2019/1/8 16:36
 */
public class WXPayUtils {

    /**
     * 微信支付请求
     * @param out_trade_no 订单号
     * @param total_fee 金额单位分int100
     * @return
     * @throws Exception
     */
    public static Map<String, String> wxPay(String out_trade_no,String total_fee,String spbill_create_ip) throws Exception {

        Map<String,String> parameters = new HashMap<>(16);
        parameters.put("appid", IWXPayConfig.APPID);
        parameters.put("mch_id", IWXPayConfig.MCH_ID);
        parameters.put("nonce_str", WXPayUtil.generateNonceStr());
        parameters.put("body", "物流支付订单");//
        parameters.put("out_trade_no", out_trade_no);//订单编号
        parameters.put("total_fee", total_fee);//订单金额
        parameters.put("notify_url", IWXPayConfig.NOTIFY_URL);//异步通知服务器路径
        parameters.put("trade_type", IWXPayConfig.TRADE_TYPE);//app支付
        parameters.put("spbill_create_ip",spbill_create_ip);//客户端IP地址
        parameters.put("time_start",DateUtil.getWXPayStartTime());
        parameters.put("time_expire", DateUtil.getWXPayExpireTime(600));
        String signvalue = WXPayUtil.generateSignature(parameters, IWXPayConfig.KEY,WXPayConstants.SignType.MD5);//默认为MD5加密
        parameters.put("sign",signvalue);
//        转换为xml格式
        String requestDataXml = WXPayUtil.mapToXml(parameters);
        String responseDataXml = doPostByXml(requestDataXml);
        Map<String, String> map = WXPayUtil.xmlToMap(responseDataXml);
        return map;
    }

    /**
     * 微信支付发起post请求xml
     * @param requestDataXml
     * @return
     */
    public static String doPostByXml( String requestDataXml)  {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(IWXPayConfig.WXPAY_URL);
        RequestConfig config =  RequestConfig.custom()
                .setConnectTimeout(15000)   //设置连接服务器主机时间
                .setConnectionRequestTimeout(60000) //连接请求超时时间
                .setSocketTimeout(60000)    //读取响应超时时间
                .build();
        httpPost.setConfig(config);
//        将上传对象保存到entity属性中
        httpPost.setEntity(new StringEntity(requestDataXml,"UTF-8"));
//        设置请求头
        httpPost.setHeader("Content-Type","text/xml");
//        发送请求
        String results="";
        try {
            HttpResponse response = client.execute(httpPost);
            HttpEntity entity = response.getEntity();
            results = EntityUtils.toString(entity,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

    /**
     * 微信支付返回数据xml格式
     * @param return_code
     * @param return_msg
     * @return
     */
    public static String setXML(String return_code, String return_msg) {
        return "<xml><return_code><![CDATA[" + return_code + "]]></return_code><return_msg><![CDATA[" + return_msg + "]]></return_msg></xml>";
    }
}
