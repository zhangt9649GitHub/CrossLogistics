package com.siruiman.crosslogistics.runner;

import com.siruiman.crosslogistics.config.LockConfig;
import com.siruiman.crosslogistics.mqttclient.MqttConnection;
import com.siruiman.crosslogistics.service.ConfigService;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * @author 张占伟
 * @date 2019/1/15 14:06
 */
@Component
public class MyApplicationRunner implements ApplicationRunner {
    private static final Logger logger = LoggerFactory
            .getLogger(MyApplicationRunner.class);
    @Autowired
    private ConfigService configService;

    @Override
    public void run(ApplicationArguments args) {
        try {
//            初始化加载系统配置
            configService.loadConfig();
        }catch (Exception e){
            e.printStackTrace();
            logger.error("系统配置失败");
        }
    }
}
