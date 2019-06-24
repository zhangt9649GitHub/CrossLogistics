package com.siruiman.crosslogistics.util;

import com.siruiman.crosslogistics.config.NETSPayConfig;
import org.apache.commons.lang.math.RandomUtils;
import sun.applet.resources.MsgAppletViewer;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * nets 支付工具类
 * @author 张占伟
 * @date 2019/2/28 9:13
 */
public class NETSPayUtil {


    /**
     * 生成签名
     * @param txnReq
     * @param secretKey
     * @return
     * @throws Exception
     */
    public static String generateSignature(String txnReq,String secretKey) throws Exception{
        String concatPayloadAndSecretKey = txnReq + secretKey;
        String hmac =
                encodeBase64(hashSHA256ToBytes(concatPayloadAndSecretKey.getBytes()));
        return hmac;
    }


    public static byte[] hashSHA256ToBytes(byte[] input) throws Exception {
        byte[] byteData = null;
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(input);
        byteData = md.digest();
        return byteData;
    }



    public static String encodeBase64(byte[] data) {
        return DatatypeConverter.printBase64Binary(data);
    }

    /**
     *
     * 生成json字符串 获得请求参数
     *"txnAmount":"1000",<replace>
     * o "merchantTxnRef":"2017060510365198",<replace>
     * o "b2sTxnEndURL":"https://sit2.enets.sg/MerchantApp/sim/b2sTxnEndURL.jsp",<replace>
     * o "s2sTxnEndURL":"https://sit2.enets.sg/MerchantApp/rest/s2sTxnEnd",<replace>
     * o "netsMid":"UMID_887770001",<replace>
     * o "merchantTxnDtm":"20170605 10:36:51.989",<replace>
     * o
     * o "mobileOs":"ANDROID"<default value1>
     * o "submissionMode":"B",<default value1>
     * o "paymentType":"SALE",<default value1>
     * o "paymentMode":"",<default value1>
     * o "clientType":"S",<default value1>
     * o
     * o "currencyCode":"SGD",<default value2>
     * o "merchantTimeZone":"+8:00",<default value2>
     * o "language":"en", <default value2>
     * "netsMidIndicator":"U",
     * @param txnAmount 金额
     * @param mobileOs 操作系统
     * @param ipAddress 客户端ip地址
     *
     * @param deliveryNumber 快递单号
     * @return msg
     */

    public static String getTxnReq(String txnAmount,String mobileOs,String deliveryNumber,String ipAddress){
        String[] arr = new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q"
                ,"r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M"
                ,"N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        Random r = new Random();
//      订单后添加两个字符防止用户退出不能重新支付
        String merchantTxnRef = deliveryNumber+arr[ r.nextInt(arr.length)]+arr[ r.nextInt(arr.length)];
        String txnReq = "{\"ss\":\"1\",\"msg\":{\"netsMid\":\""
                +NETSPayConfig.UMID+"\",\"tid\":\""+deliveryNumber+"\",\"submissionMode\":\"B\",\"txnAmount\":\""
                +txnAmount+"\",\"merchantTxnRef\":\""
                +merchantTxnRef+"\",\"merchantTxnDtm\":\""
                +DateUtil.getmerchantTxnDtm()+"\",\"paymentType\":\"SALE\",\"currencyCode\":\"SGD\",\"paymentMode\":\"QR\",\"merchantTimeZone\":\"+8:00\",\"b2sTxnEndURL\":\"https://sit2.enets.sg/MerchantApp/sim/b2sTxnEndURL.jsp\",\"b2sTxnEndURLParam\":\"\",\"s2sTxnEndURL\":\""
                +NETSPayConfig.s2sTxnEndURL+"\",\"s2sTxnEndURLParam\":\"\",\"clientType\":\"S\",\"supMsg\":\"\",\"netsMidIndicator\":\"U\",\"ipAddress\":\""
                +ipAddress+"\",\"language\":\"en\",\"mobileOs\":\""+mobileOs+"\"}}";
        return txnReq;

    }

}
