package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.GoodsDetailsMapper;
import com.siruiman.crosslogistics.mapper.TaskOrderBagMapper;
import com.siruiman.crosslogistics.mapper.TaskTruckOrderMapper;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.service.AsyncService;
import com.siruiman.crosslogistics.util.SmsCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsyncServiceImpl implements AsyncService {
    private static final Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);
    @Autowired
    private TaskTruckOrderMapper taskTruckOrderMapper;
    @Autowired
    private TaskOrderBagMapper taskOrderBagMapper;
    @Autowired
    private GoodsDetailsMapper goodsDetailsMapper;

    @Async
    @Override
    public void executeAsync(int appUserId) {
        logger.info("start executeAsync");
        try {
           TaskTruckOrder taskTruckOrder = taskTruckOrderMapper.selectTruckOrderByAppUserId(appUserId);
           if(taskTruckOrder!=null&&taskTruckOrder.getTaskOrderId()!=null){
               List<TaskBagOrder> taskBagOrderList = taskOrderBagMapper.selectBagsByOrderId(taskTruckOrder.getTaskOrderId());
               if(taskBagOrderList.size()>0){
                   for (TaskBagOrder taskBagOrder:taskBagOrderList
                   ) {
                       List<GoodsDetails> goodsDetailsList = goodsDetailsMapper.selectGoodsDetailsByBagId(taskBagOrder.getBagId());
                       for (GoodsDetails goodsDetails:goodsDetailsList
                       ) {
                           String receiptContactMobile =goodsDetails.getReceiptContactMobile();
                           if(receiptContactMobile.length()==8){
                               SmsCodeUtil.notifyTruckModel("+65"+receiptContactMobile,goodsDetails.getDeliveryNumber());
                           }else if (receiptContactMobile.length()==10 && receiptContactMobile.substring(0,2).equals("65")){
                               SmsCodeUtil.notifyTruckModel("+"+receiptContactMobile,goodsDetails.getDeliveryNumber());
                           }else if (receiptContactMobile.length()==11 && receiptContactMobile.substring(0,3).equals("+65")){
                               SmsCodeUtil.notifyTruckModel(receiptContactMobile,goodsDetails.getDeliveryNumber());
                           }else if (receiptContactMobile.length()>12 && receiptContactMobile.contains("/")){
                               String [] strs = receiptContactMobile.split("/");
                               if(strs[0].length()==8 && strs[1].length()==8){
                                   SmsCodeUtil.notifyTruckModel("+65"+strs[0],goodsDetails.getDeliveryNumber());
                                   SmsCodeUtil.notifyTruckModel("+65"+strs[1],goodsDetails.getDeliveryNumber());
                               }

                           }

                       }
                   }
               }
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("end executeAsync");
    }


}
