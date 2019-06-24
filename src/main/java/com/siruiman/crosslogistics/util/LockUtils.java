package com.siruiman.crosslogistics.util;

import com.siruiman.crosslogistics.mqttclient.MqttConnection;
import com.siruiman.crosslogistics.mqttclient.PushCallBack;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;

/**
 * @author 张占伟
 * @date 2019/1/16 17:07
 *  小车开锁工具类
 */
public class LockUtils {
    private static final Logger logger = LoggerFactory
            .getLogger(LockUtils.class);

    /**
     * 小车开锁传递锁编号
     * @param lockNumber
     * @return
     */
    public static void unLock(String lockNumber)throws Exception{
        MqttClient client = MqttConnection.client;
        long millis = System.currentTimeMillis();
        String payload ="c=O&p="+millis;
        logger.info("正在发起开锁命令");
        client.publish(lockNumber,payload.getBytes("UTF-8"),2,false);
        client.setCallback(PushCallBack.getInStance());
        logger.info("发起开锁命令成功");

    }

}
