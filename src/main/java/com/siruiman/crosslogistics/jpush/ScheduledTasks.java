package com.siruiman.crosslogistics.jpush;

import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.model.dto.AddAppUserDto;
import com.siruiman.crosslogistics.model.dto.TruckTaskDto;
import com.siruiman.crosslogistics.service.*;
import com.siruiman.crosslogistics.util.SmsCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.siruiman.crosslogistics.util.CarJPushClientUtil.pushCarMsg;
import static com.siruiman.crosslogistics.util.RandomCodeUtil.getEightRandomCode;
import static com.siruiman.crosslogistics.util.RandomCodeUtil.getSixRandomCode;
import static com.siruiman.crosslogistics.util.TruckJPushClientUtil.pushTruckMsg;

@Component
@EnableAsync
public class ScheduledTasks {
    private Long newTime;

    private Integer messageId;

    private Integer messagePushId;


    public Long getNewTime() {
        return newTime;
    }

    public void setNewTime(Long newTime) {
        this.newTime = newTime;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getMessagePushId() {
        return messagePushId;
    }

    public void setMessagePushId(Integer messagePushId) {
        this.messagePushId = messagePushId;
    }

    @Autowired
    private TaskTruckOrderService taskTruckOrderService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private MessagePushService messagePushService;
    @Autowired
    private TaskCarOrderService taskCarOrderService;
    @Autowired
    private AppUserWalletStreamService appUserWalletStreamService;
    @Autowired
    private AppUserWalletService appUserWalletService;

    //每天上午的9:00分执行提醒货车司机是否去拉货
    @Scheduled(cron = "0 0 9 ? * *")
    @Async
    public void RemindTheDriver() {
        try{
            //1.查询今日有订单的货车司机的userId和货车任务模板id
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateNowStr = sdf.format(date);
            String createTime = dateNowStr + " 00:00:00";
            List<TaskTruckOrder> taskTruckOrderList = taskTruckOrderService.selectTaskTruckOrderByTime(createTime);
            for (TaskTruckOrder taskTruckOrder : taskTruckOrderList
            ) {
                String comment = "18" + taskTruckOrder.getOrderNumber();
                Message message1 = messageService.selectMessageComment(comment);
                if (message1 == null) {
                    String content = "您今日有订单需完成，订单号为:" + taskTruckOrder.getOrderNumber() + "详情请见APP送货助手";
                    String enContent = "You have to complete the order today, the order number is:" + taskTruckOrder.getOrderNumber() + ", please see the APP Delivery Assistant for details.";
                    Message message = new Message();
                    message.setTitle("订单提醒");
                    if (taskTruckOrder != null && taskTruckOrder.getAppUserId() != null) {
                        message.setAppUserId(taskTruckOrder.getAppUserId());
                        message.setContent(content);
                        message.setType("通知消息");
                        message.setCategory("货车");
                        message.setState("未读");
                        message.setAddTime(new Date());
                        message.setEnTitle("Order reminder");
                        message.setEnContent(enContent);
                        message.setEnType("Notice");
                        message.setComment("18" + taskTruckOrder.getOrderNumber());
                        messageService.insert(message);
                    }
                    if (taskTruckOrder.getAppUserId() != null) {
                        AddAppUserDto addAppUserDto = appUserService.selectEditAppUserDetail(taskTruckOrder.getAppUserId());
                        pushTruckMsg("alias", "订单提醒", "Truck" + addAppUserDto.getNumber(), content + "\r\n" + enContent);

                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //每一分钟执行一次
    @Scheduled(cron = "0 */1 *  * * * ")
    @Async
    public void TimePush() {
        try{
            Date date = new Date();
            String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            Long newNowTime = date.getTime();
            Date time;
            if (newTime == null) {
                boolean flag = true;
                do {
                    MessagePush messagePush = messagePushService.selectMessagePushByTime();
                    if (messagePush == null) {
                        break;
                    }
                    TaskCarOrder taskCarOrder = taskCarOrderService.selectTaskCarOrderById(messagePush.getTaskOrderId());
                    if (taskCarOrder.getState() >= 3) {
                        flag = true;
                        messagePushService.updateById(messagePush.getPushId());
                    } else {
                        flag = false;
                        time = messagePush.getSecondPushTime();
                        messageId = messagePush.getMessageId();
                        messagePushId = messagePush.getPushId();
                        newTime = time.getTime();
                    }
                } while (flag);

            }
            if (newTime != null) {
                while (newNowTime >= newTime) {
                    if (messageId > 0) {
                        Message message = messageService.selectAllMessageById(messageId);
                        message.setState("未读");
                        message.setComment("19" + getEightRandomCode());
                        message.setAddTime(new Date());
                        messageService.insert(message);
                        AddAppUserDto addAppUserDto = appUserService.selectEditAppUserDetail(message.getAppUserId());
                        pushCarMsg("alias", "订单派送提醒", "Car" + addAppUserDto.getNumber(), message.getContent() + "\r\n" + message.getEnContent());
                        if (messagePushId > 0) {
                            messagePushService.updateById(messagePushId);
                        }
                        boolean flag = true;
                        do {
                            MessagePush messagePush = messagePushService.selectMessagePushByTime();
                            if (messagePush == null) {
                                newTime = null;
                                break;
                            }
                            TaskCarOrder taskCarOrder = taskCarOrderService.selectTaskCarOrderById(messagePush.getTaskOrderId());
                            if (taskCarOrder.getState() >= 3) {
                                flag = true;
                                messagePushService.updateById(messagePush.getPushId());
                            } else {
                                flag = false;
                                time = messagePush.getSecondPushTime();
                                messageId = messagePush.getMessageId();
                                messagePushId = messagePush.getPushId();
                                newTime = time.getTime();
                            }
                        } while (flag);
                    }
                    if (newTime == null) {
                        break;
                    }

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Scheduled(cron = "0 0 0 ? * *")
    @Async
    public void ToSettleTheOrder() {
        try{
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());
            now.set(Calendar.DATE, now.get(Calendar.DATE) - 7);
            String time = new SimpleDateFormat("yyyy-MM-dd").format(now.getTime()) + " 00:00:00";
            List<TaskCarOrder> taskCarOrderList = taskCarOrderService.selectTaskCarOrderByTime(time);
            for (TaskCarOrder taskCarOrder : taskCarOrderList
            ) {
                //冻结金额
                BigDecimal freezeAmount = new BigDecimal(0);
                //扣除金额
                BigDecimal deductAmount = new BigDecimal(0);
                //冻结积分
                int freezeIntegral = 0;
                //扣除积分
                int deductIntegral = 0;
                if (taskCarOrder.getState() == 5) {
                    List<AppUserWalletStream> appUserWalletStreamList = appUserWalletStreamService.selectWalletStreamByCarOrderId(taskCarOrder.getTaskOrderId());
                    for (AppUserWalletStream appUserWalletStream : appUserWalletStreamList
                    ) {
                        if (appUserWalletStream.getStreamType() == 2 && appUserWalletStream.getState() == 2) {
                            freezeAmount = appUserWalletStream.getAmount();
                        } else if (appUserWalletStream.getStreamType() == 4 && appUserWalletStream.getState() == 5) {
                            deductAmount = deductAmount.add(appUserWalletStream.getAmount());
                        }
                        if (appUserWalletStream.getStreamType() == 3 && appUserWalletStream.getState() == 2) {
                            freezeIntegral = appUserWalletStream.getIntegral();
                        } else if (appUserWalletStream.getStreamType() == 5 && appUserWalletStream.getState() == 6) {
                            deductIntegral = deductIntegral + appUserWalletStream.getIntegral();
                        }
                    }
                    BigDecimal settlementAmount = freezeAmount.subtract(deductAmount);
                    int settlementIntegral = freezeIntegral - deductIntegral;
                    if (taskCarOrder.getAppUserId() != null) {
                        int result = appUserWalletService.updateCommissionAndIntegral(taskCarOrder.getAppUserId(), settlementAmount, settlementIntegral, "小车", 3, taskCarOrder.getTaskOrderId(), 0);
                        taskCarOrderService.updateState(taskCarOrder.getTaskOrderId());
                  /* if(result ==1){
                    int result2 = messageService.insertMessage(8,taskCarOrder.getAppUserId(),0,0,taskCarOrder.getTaskOrderId(),0,0);
                   }*/
                    }

                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
