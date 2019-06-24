package com.siruiman.crosslogistics.mqttclient;

import com.siruiman.crosslogistics.model.CarLock;
import com.siruiman.crosslogistics.service.CarLockService;
import com.siruiman.crosslogistics.util.ContextUtils;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author 张占伟
 * @date 2019/1/22 11:04
 *
 * 回执处理消息为小车锁开启专用
 */
public class UnLockCallback implements MqttCallback {

    private static final Logger logger = LoggerFactory
            .getLogger(UnLockCallback.class);

    private static volatile UnLockCallback unLockCallback;

    private UnLockCallback() {
    }

    public static UnLockCallback getInStance(){

        if (unLockCallback==null){
    //            单列加锁保证线程安全双重判断

            synchronized (MqttConnection.class){

                if (unLockCallback==null){
                    unLockCallback = new UnLockCallback();
                }
            }
        }
        return unLockCallback;
    }


    @Override
    public void connectionLost(Throwable throwable) {
        logger.warn("客户端关闭,将在30秒后重新连接");
        while(true) {
            try {
                Thread.sleep(30000);
                MqttConnection.connect();
                logger.warn("连接成功跳出循环");
//                连接成功跳出循环
                break;
            } catch (Exception e) {
//                连接失败继续
                continue;
            }
        }
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
        String payload = new String(message.getPayload(),Charset.forName("UTF-8"));
        logger.info("topic="+topic+"&&"+"&&payload="+payload);
        CarLockService carLockService = ContextUtils.getBean(CarLockService.class);
        CarLock lock = new CarLock();
        String[] split = payload.split("&");
        String lockNumber = split[1].substring(2);
        lock.setUpdateTime(new Date());
        if ("bike_i".equals(topic)){
//            返回信息为cell类型
            if (payload.contains("c=1")){
                lock.setLockNumber(lockNumber);
                String[] values = split[2].substring(2).split(",");//分割字符串获得
                lock.setElectricity(values[0]);
                lock.setLac(values[5]);
                lock.setBaseStationNumber(values[6]);
                lock.setSign(values[7]);
                if("0".equals(values[1])){
                    lock.setState(new Byte("2"));
                }
                if ("1".equals(values[1])){
                    lock.setState(new Byte("1"));
                }
                try {
                    carLockService.updateLockStationNum(lock);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
//             如果返回的是wgs84数据
            if(payload.contains("c=0")){
                String[] values = split[2].substring(2).split(",");//分割字符串获得

                if("0".equals(values[1])){
                    lock.setState(new Byte("2"));
                }
                if ("1".equals(values[1])){
                    lock.setState(new Byte("1"));
                }
                lock.setLng(values[2]);
                lock.setLat(values[3]);
                try {
                    carLockService.updateLockLatLng(lock);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
