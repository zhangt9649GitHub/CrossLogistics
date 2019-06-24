package com.siruiman.crosslogistics.util;

import com.siruiman.crosslogistics.config.MessageConfig;
import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;
import java.util.Random;


public class SmsCodeUtil {
    private static final Logger logger = LoggerFactory
            .getLogger(SmsCodeUtil.class);

    /**
     *  发送验证码短信api 发送新加坡短信 号码要加国际区号 +65xxxxxxx
     * @param phone +6598076188
     * @param code
     * @return
     */
    public static int singleSend(String phone, String code){
        logger.info("开始发送国外短信");
        long start =System.currentTimeMillis();
        YunpianClient client = new YunpianClient(MessageConfig.APIKEY).init();
        Map<String, String> param = client.newParam(2);
        param.put(YunpianClient.MOBILE, phone);
        param.put(YunpianClient.TEXT, "【HelpAndLove】Verification code : "+code+".  This verification code is only used for authentication. Please do not disclose it to others ");
        Result<SmsSingleSend> result = client.sms().single_send(param);
        long end =System.currentTimeMillis();
        logger.info("一共用时"+(end-start)+"毫秒");
        logger.info(result.getCode()+ result.getMsg()+result.getThrowable()+ result.getData()+result.getDetail());
        return result.getCode();
    }



    /**
     *  发送验证码短信api 发送国内短信
     * @param phone 15893578991 不用加国际区号
     * @param code
     * @return
     */
    public static int chinaSend(String phone, String code){
        logger.info("开始发送国内短信");
        long start =System.currentTimeMillis();
        YunpianClient client = new YunpianClient(MessageConfig.APIKEYTEST).init();
        Map<String, String> param = client.newParam(2);
        param.put(YunpianClient.MOBILE, phone);
        param.put(YunpianClient.TEXT, "【尚潮测试】 您的验证码是"+code );
        Result<SmsSingleSend> result = client.sms().single_send(param);
        long end =System.currentTimeMillis();
        logger.info("一共用时"+(end-start)+"毫秒");
        logger.info(result.getCode()+ result.getMsg()+result.getThrowable()+ result.getData()+result.getDetail());
        return result.getCode();
    }

    /**
     * 生成随机code：四位随机数
     *
     * @return
     */
    public static String getCode() {

        Random random = new Random();

        int code = (int) (random.nextDouble() * (9999 - 1000 + 1)) + 1000;// 获取4位随机数

        String str = String.valueOf(code);
        return str;// 当前时间
    }

    /**
     * 提醒客户货物司机正在派送中(货车模式)
     * @param phone
     * @return
     */
    public static int notifyTruckModel (String phone,String orderNumber){
        logger.info("开始发送国外短信");
        long start =System.currentTimeMillis();
        YunpianClient client = new YunpianClient(MessageConfig.APIKEY).init();
        Map<String, String> param = client.newParam(2);
        param.put(YunpianClient.MOBILE, phone);
        param.put(YunpianClient.TEXT,"【HelpAndLove】Tracking No. : "+orderNumber+"Your shipment has arrived and scheduled for delivery. Kindly be prepared to be expecting any calls from our courier. Customer Service: xxxx-xxxx SMS/Whatsapp: xxxx-xxxx");
        Result<SmsSingleSend> result = client.sms().single_send(param);
        long end =System.currentTimeMillis();
        logger.info("一共用时"+(end-start)+"毫秒");
        logger.info(result.getCode()+ result.getMsg()+result.getThrowable()+ result.getData()+result.getDetail());
        return result.getCode();
    }


    /**
     * 货物到仓发起转运通知
     * @param phone
     * @return
     */
    public static int notifyTransfer (String phone){
        logger.info("开始发送国外短信");
        long start =System.currentTimeMillis();
        YunpianClient client = new YunpianClient(MessageConfig.APIKEY).init();
        Map<String, String> param = client.newParam(2);
        param.put(YunpianClient.MOBILE, phone);
        param.put(YunpianClient.TEXT,"【HelpAndLove】Your package has arrived at the warehouse, you can go to the app to initiate the transfer");
        Result<SmsSingleSend> result = client.sms().single_send(param);
        long end =System.currentTimeMillis();
        logger.info("一共用时"+(end-start)+"毫秒");
        logger.info(result.getCode()+ result.getMsg()+result.getThrowable()+ result.getData()+result.getDetail());
        return result.getCode();
    }


    /**
     * 包裹配送中提醒
     * @param phone
     * @param number
     * @return
     */
    public static int notifyDeliveryStart (String phone,String number){
        logger.info("开始发送国外短信");
        long start =System.currentTimeMillis();
        YunpianClient client = new YunpianClient(MessageConfig.APIKEY).init();
        Map<String, String> param = client.newParam(2);
        param.put(YunpianClient.MOBILE, phone);
        param.put(YunpianClient.TEXT,"【HelpAndLove】Your package  is being delivered, please check it in time, single number:"+number);
        Result<SmsSingleSend> result = client.sms().single_send(param);
        long end =System.currentTimeMillis();
        logger.info("一共用时"+(end-start)+"毫秒");
        logger.info(result.getCode()+ result.getMsg()+result.getThrowable()+ result.getData()+result.getDetail());
        return result.getCode();
    }


    /**
     * 包裹配送 完成提醒
     * @param phone
     * @param number
     * @return
     */
    public static int notifyDeliveryFinish (String phone,String number){
        logger.info("开始发送国外短信");
        long start =System.currentTimeMillis();
        YunpianClient client = new YunpianClient(MessageConfig.APIKEY).init();
        Map<String, String> param = client.newParam(2);
        param.put(YunpianClient.MOBILE, phone);
        param.put(YunpianClient.TEXT,"【HelpAndLove】Your package has been signed. For help, please login for details, single number:"+number);
        Result<SmsSingleSend> result = client.sms().single_send(param);
        long end =System.currentTimeMillis();
        logger.info("一共用时"+(end-start)+"毫秒");
        logger.info(result.getCode()+ result.getMsg()+result.getThrowable()+ result.getData()+result.getDetail());
        return result.getCode();
    }

    /**
     * 提现成功提醒
     * @param phone
     * @return
     */
    public static int notifywithdrawFinish (String phone,String amount){
        logger.info("开始发送国内短信");
        long start =System.currentTimeMillis();
        YunpianClient client = new YunpianClient(MessageConfig.APIKEYTEST).init();
        Map<String, String> param = client.newParam(2);
        param.put(YunpianClient.MOBILE, phone);
        param.put(YunpianClient.TEXT,"【尚潮测试】尊敬的用户，您的帐号成功提现"+amount+"元，如有疑问请联系客服。");
        Result<SmsSingleSend> result = client.sms().single_send(param);
        long end =System.currentTimeMillis();
        logger.info("一共用时"+(end-start)+"毫秒");
        logger.info(result.getCode()+ result.getMsg()+result.getThrowable()+ result.getData()+result.getDetail());
        return result.getCode();
    }

    /**
     * 货车审核成功
     * @param phone
     * @return
     */
    public static int notifyTruckCertificationFinish (String phone){
        logger.info("开始发送国内短信");
        long start =System.currentTimeMillis();
        YunpianClient client = new YunpianClient(MessageConfig.APIKEYTEST).init();
        Map<String, String> param = client.newParam(2);
        param.put(YunpianClient.MOBILE, phone);
        param.put(YunpianClient.TEXT,"【尚潮测试】尊敬的用户，您的货车审核认证已通过，赶快抢单吧。");
        Result<SmsSingleSend> result = client.sms().single_send(param);
        long end =System.currentTimeMillis();
        logger.info("一共用时"+(end-start)+"毫秒");
        logger.info(result.getCode()+ result.getMsg()+result.getThrowable()+ result.getData()+result.getDetail());
        return result.getCode();
    }

    /**
     * 货车审核被驳回
     * @param phone
     * @return
     */
    public static int notifyTruckCertificationReject (String phone){
        logger.info("开始发送国内短信");
        long start =System.currentTimeMillis();
        YunpianClient client = new YunpianClient(MessageConfig.APIKEYTEST).init();
        Map<String, String> param = client.newParam(2);
        param.put(YunpianClient.MOBILE, phone);
        param.put(YunpianClient.TEXT,"【尚潮测试】尊敬的用户，您的货车审核认证被驳回，如有疑问请联系客服。");
        Result<SmsSingleSend> result = client.sms().single_send(param);
        long end =System.currentTimeMillis();
        logger.info("一共用时"+(end-start)+"毫秒");
        logger.info(result.getCode()+ result.getMsg()+result.getThrowable()+ result.getData()+result.getDetail());
        return result.getCode();
    }


    /**
     * 小车审核成功
     * @param phone
     * @return
     */
    public static int notifyCarCertificationFinish (String phone){
        logger.info("开始发送国内短信");
        long start =System.currentTimeMillis();
        YunpianClient client = new YunpianClient(MessageConfig.APIKEYTEST).init();
        Map<String, String> param = client.newParam(2);
        param.put(YunpianClient.MOBILE, phone);
        param.put(YunpianClient.TEXT,"【尚潮测试】尊敬的用户，您的小车审核认证已通过，赶快抢单吧。");
        Result<SmsSingleSend> result = client.sms().single_send(param);
        long end =System.currentTimeMillis();
        logger.info("一共用时"+(end-start)+"毫秒");
        logger.info(result.getCode()+ result.getMsg()+result.getThrowable()+ result.getData()+result.getDetail());
        return result.getCode();
    }

    /**
     * 小车审核被驳回
     * @param phone
     * @return
     */
    public static int notifyCarCertificationReject (String phone){
        logger.info("开始发送国内短信");
        long start =System.currentTimeMillis();
        YunpianClient client = new YunpianClient(MessageConfig.APIKEYTEST).init();
        Map<String, String> param = client.newParam(2);
        param.put(YunpianClient.MOBILE, phone);
        param.put(YunpianClient.TEXT,"【尚潮测试】尊敬的用户，您的小车审核认证被驳回，如有疑问请联系客服。");
        Result<SmsSingleSend> result = client.sms().single_send(param);
        long end =System.currentTimeMillis();
        logger.info("一共用时"+(end-start)+"毫秒");
        logger.info(result.getCode()+ result.getMsg()+result.getThrowable()+ result.getData()+result.getDetail());
        return result.getCode();
    }


    /**
     * 提醒订单处理
     * @param phone
     * @return
     */
    public static int notifyOrderProcess (String phone){
        logger.info("开始发送国内短信");
        long start =System.currentTimeMillis();
        YunpianClient client = new YunpianClient(MessageConfig.APIKEYTEST).init();
        Map<String, String> param = client.newParam(2);
        param.put(YunpianClient.MOBILE, phone);
        param.put(YunpianClient.TEXT,"【尚潮测试】尊敬的用户，您今天有订单需要处理，请及时处理 ");
        Result<SmsSingleSend> result = client.sms().single_send(param);
        long end =System.currentTimeMillis();
        logger.info("一共用时"+(end-start)+"毫秒");
        logger.info(result.getCode()+ result.getMsg()+result.getThrowable()+ result.getData()+result.getDetail());
        return result.getCode();
    }


}
