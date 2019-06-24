package com.siruiman.crosslogistics.mqttclient;

import com.siruiman.crosslogistics.model.CarLock;
import com.siruiman.crosslogistics.service.CarLockService;
import com.siruiman.crosslogistics.util.ContextUtils;
import com.siruiman.crosslogistics.util.StaticConfigUtil;
import org.eclipse.paho.client.mqttv3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author 张占伟
 * @date 2019/1/14 16:53
 * 接收服务端消息回调类
 */
public class PushCallBack implements MqttCallback {

    private static final Logger logger = LoggerFactory
            .getLogger(PushCallBack.class);

    private static volatile PushCallBack pushCallBack;

    private PushCallBack() {
        super();
    }

    @Override
    public void connectionLost(Throwable cause) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
        String payload = new String(message.getPayload(),Charset.forName("UTF-8"));
        logger.info("topic="+topic+"&&payload="+payload);
        CarLockService carLockService = ContextUtils.getBean(CarLockService.class);
        CarLock lock = new CarLock();
        lock.setUpdateTime(new Date());
        String[] split = payload.split("&");
        String lockNumber = split[1].substring(2);
        if(topic.equals("bike_c")){
            lock.setLockNumber(lockNumber);
            if (payload.contains("c=NL")){
                logger.info("闭锁成功");
                StaticConfigUtil.lockState.put(lockNumber,"2");
                lock.setState(new Byte("2"));
                carLockService.updateUnLockState(lock);
            }
            if (payload.contains("c=OR")&&payload.contains("p=0")){
                lock.setState(new Byte("1"));

                try {
                    carLockService.updateUnLockState(lock);
                }catch (Exception e){
                    e.printStackTrace();
                }
                StaticConfigUtil.lockState.put(lockNumber,"1");
            }
        }
            else  if (topic.equals("bike_i")){
//            返回信息为cell类型
            if (payload.contains("c=1")){
                lock.setLockNumber(lockNumber);
                //分割字符串获得
                String[] values = split[2].substring(2).split(",");
                lock.setElectricity(values[0]);
                lock.setLac(values[5]);
                lock.setBaseStationNumber(values[6]);
                lock.setSign(values[7]);
                if(values[1].equals("0")){
                    lock.setState(new Byte("2"));
                    StaticConfigUtil.lockState.put(lockNumber,"2");
                }
                if (values[1].equals("1")){
                    lock.setState(new Byte("1"));
                    StaticConfigUtil.lockState.put(lockNumber,"1");
                }
                try {
                    carLockService.updateLockStationNum(lock);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
//             如果返回的是wgs84数据
            if(payload.contains("c=0")){
                //分割字符串获得
                String[] values = split[2].substring(2).split(",");

                if(values[1].equals("0")){
                    StaticConfigUtil.lockState.put(lockNumber,"2");
                    lock.setState(new Byte("2"));
                }
                if (values[1].equals("1")){
                    StaticConfigUtil.lockState.put(lockNumber,"1");
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

    public static PushCallBack getInStance(){
        if (pushCallBack==null){
//            单列加锁保证线程安全双重判断
            synchronized (MqttConnection.class){
                if (pushCallBack==null){
                    pushCallBack = new PushCallBack();
                }
            }
        }
        return pushCallBack;
    }


    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }
}
