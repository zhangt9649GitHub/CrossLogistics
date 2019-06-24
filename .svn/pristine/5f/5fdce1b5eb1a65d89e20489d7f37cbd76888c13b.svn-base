package com.siruiman.crosslogistics.mqttclient;

import com.github.wxpay.sdk.WXPayUtil;
import com.siruiman.crosslogistics.config.LockConfig;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 张占伟
 * @date 2019/1/21 19:00
 * mqtt 创建mqtt客户端连接
 */
public class MqttConnection {
    public static MqttClient client;
    private static MqttConnectOptions options;

    private static volatile MqttConnection mqttConnection;
    private static final Logger logger = LoggerFactory
            .getLogger(MqttConnection.class);
    public MqttClient getClient() {
        return client;
    }
    static {
        try {
            if(null == client) {
                //host为主机名，clientid即连接MQTT的客户端ID，一般以唯一标识符表示，
                // MemoryPersistence设置clientid的保存形式，默认为以内存保存
                client = new MqttClient(LockConfig.HOST, LockConfig.CLIENT_ID+WXPayUtil.getCurrentTimestamp(), new MemoryPersistence());
            }
            //获取连接配置
            MqttConnectOptions options = getOptions();
            client.connect(options);
            String[] topicFilters ={LockConfig.TOPIC1,LockConfig.TOPIC2};
            int[] qos={1,1};
            client.subscribe(topicFilters,qos);
            client.setCallback(UnLockCallback.getInStance());
            logger.info("客户端连接成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized boolean connect() {
        try {
            if(null == client) {
                //host为主机名，clientid即连接MQTT的客户端ID，一般以唯一标识符表示，
                // MemoryPersistence设置clientid的保存形式，默认为以内存保存
                client = new MqttClient(LockConfig.HOST, LockConfig.CLIENT_ID+WXPayUtil.getCurrentTimestamp(), new MemoryPersistence());
            }
            //获取连接配置
            MqttConnectOptions options = getOptions();
            client.connect(options);
            String[] topicFilters ={LockConfig.TOPIC1,LockConfig.TOPIC2};
            int[] qos={1,1};
            client.subscribe(topicFilters,qos);
            client.setCallback(UnLockCallback.getInStance());
            logger.info("客户端连接成功");
            return isConnected();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean isConnected(){
        try {
            client.disconnect();
            client = null;
        } catch (MqttException me) {
            logger.info("reason " + me.getReasonCode());
            logger.info("msg " + me.getMessage());
            logger.info("loc " + me.getLocalizedMessage());
            logger.info("cause " + me.getCause());
            logger.info("excep " + me);
            me.printStackTrace();
            return false;
        }
        return true;
    }

    public static MqttConnectOptions getOptions() {
        logger.info("加载客户端配置");
        // MQTT的连接设置
        options = new MqttConnectOptions();
        // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，设置为true表示每次连接到服务器都以新的身份连接
        options.setCleanSession(false);
        // 设置连接的用户名
        options.setUserName(LockConfig.USERNAME);
        // 设置连接的密码
        options.setPassword(LockConfig.PASSWORD.toCharArray());
        // 设置超时时间 单位为秒
        options.setConnectionTimeout(800);
        // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
        options.setKeepAliveInterval(200);
        return options;
    }

    private MqttConnection() {
        super();
    }

    public static MqttConnection getInstance(){
        if (mqttConnection==null){
//            单列加锁保证线程安全双重判断
            synchronized (MqttConnection.class){
                if (mqttConnection==null){
                     mqttConnection = new MqttConnection();
                }
            }
        }
        return mqttConnection;
    }
}
