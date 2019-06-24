package com.siruiman.crosslogistics.util;

import com.siruiman.crosslogistics.service.GenerateCarordersService;
import com.siruiman.crosslogistics.service.GenerateTruckOrdersService;
import com.siruiman.crosslogistics.service.GrabOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
@EnableAsync
public class ScheduledConfig{
    public final static long TEN_Minute =  60 * 1000;

    private static final Logger logger = LoggerFactory
            .getLogger(ScheduledConfig.class);


    @Autowired
    private GenerateCarordersService generateCarordersService;
    @Autowired
    private GenerateTruckOrdersService generateTruckOrdersService;
    @Autowired
    private GrabOrderService grabOrderService;

    @Scheduled(cron = "0 0 1 * * ? ")
    @Async
    public void generateCarorders(){
        try{
            logger.info("开始生成小车订单：" + new Date());
            Integer generateCarorders = generateCarordersService.generateCarorders();
            logger.info("生成小车订单结束：" + generateCarorders + new Date());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Scheduled(cron = "0 0 1 * * ? ")
    @Async
    public void generateTruckOrders(){
        try{
            logger.info("开始生成货车订单：" + new Date());
            Integer generateTruckOrders = generateTruckOrdersService.generateTruckOrders();
            logger.info("生成货车订单结束：" + generateTruckOrders + new Date());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Scheduled(fixedDelay=TEN_Minute)
    @Async
    public void editCarOrder(){
        try{
            logger.info("开始处理已预约超时订单：" + new Date());
            grabOrderService.editCarOrder();
            logger.info("处理已预约超时订单结束：" + new Date());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
