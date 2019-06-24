package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.*;
import com.siruiman.crosslogistics.model.AppUser;
import com.siruiman.crosslogistics.model.Message;
import com.siruiman.crosslogistics.model.MessagePush;
import com.siruiman.crosslogistics.model.TaskCarOrder;
import com.siruiman.crosslogistics.service.GrabOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.siruiman.crosslogistics.util.CarJPushClientUtil.pushCarMsg;

@Service
public class GrabOrderServiceImpl implements GrabOrderService {

    private static final Logger logger = LoggerFactory
            .getLogger(GrabOrderServiceImpl.class);
    @Autowired
    private GrabOrderMapper grabOrderMapper;
    @Autowired
    private TaskCarOrderMapper taskCarOrderMapper;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private AppUserMapper appUserMapper;
    @Autowired
    private MessagePushMapper messagePushMapper;

    @Override
    @Transactional
    public Integer carGrab(int taskOrderId, int appUserId) {
            /*查询当前用户小车认证状态*/
            String carApproveStatus = grabOrderMapper.selectCarApproveStatus(appUserId);
            if(!carApproveStatus.equals("已认证")){
                return 3;
            }
            /*查询当前用户同一天是否有订单*/
            /*Integer selectUserTimeIsCarOrder = grabOrderMapper.selectUserTimeIsCarOrder(appUserId, taskOrderId);
            if(selectUserTimeIsCarOrder != 0){
                return 4;
            }*/
            /*查询是否有用户已经抢了这单*/
            Integer countCarGrab = grabOrderMapper.selectCarGrab(taskOrderId, appUserId);
            if(countCarGrab != null){
                return 2;
            }
            /*修改小车抢单状态*/
            Integer editGrabOrderStatus = grabOrderMapper.editGrabOrderStatus(appUserId, taskOrderId);
            return editGrabOrderStatus;

    }

    @Override
    @Transactional
    public Integer truckGrab(int appUserId, int taskOrderId) {
            /*查询当前用户是否认证货车司机*/
            String truckApproveStatus = grabOrderMapper.selectTruckApproveStatus(appUserId);
            if(!truckApproveStatus.equals("已认证")){
                return 3;
            }
            /*查询当前用户同一天是否有订单*/
            Integer selectUserTimeIsTruckOrder = grabOrderMapper.selectUserTimeIsTruckOrder(appUserId, taskOrderId);
            if(selectUserTimeIsTruckOrder != 0){
                return 4;
            }
            /*查询是否有用户已经抢了这单*/
            Integer selectTruckGrab = grabOrderMapper.selectTruckGrab(appUserId, taskOrderId);
            if(selectTruckGrab != null){
                return 2;
            }
            Integer truckGrab = grabOrderMapper.truckGrab(appUserId, taskOrderId);
            return truckGrab;
    }

    @Override
    @Transactional
    public void editCarOrder() {
            /*查询到了一个小时的所有小车订单*/
            List<Integer> selectCarOrderIds = grabOrderMapper.selectCarOrderIds();
            if(selectCarOrderIds.size() != 0){
                logger.info("开始生成小车订单：" + new Date());
                Integer carGrab = grabOrderMapper.carGrab(selectCarOrderIds);
                for(int i = 0; i < selectCarOrderIds.size(); i++){
                    int taskOrderId = selectCarOrderIds.get(i);
                    /*根据订单id查询app用户id*/
                    Integer appUserId = grabOrderMapper.selectAppUserIdByCarOrder(taskOrderId);
                    TaskCarOrder taskCarOrder =taskCarOrderMapper.selectByPrimaryKey(taskOrderId);
                    String comment = "5"+taskCarOrder.getOrderNumber()+appUserId;
                    Message message1 =messageMapper.selectMessageComment(comment);
                    if(message1==null) {
                        String content = "恭喜您! 抢单成功,订单号为:" + taskCarOrder.getOrderNumber() + "请及时进行派送";
                        String enContent = "Congratulations! The order is successful, the order number is: " + taskCarOrder.getOrderNumber() + ", please send it in time.";
                        Message message = new Message();
                        message.setAppUserId(appUserId);
                        message.setTitle("抢单成功");
                        message.setContent(content);
                        message.setType("通知消息");
                        message.setCategory("小车");
                        message.setState("未读");
                        message.setAddTime(new Date());
                        message.setEnTitle("Successfully grabbed the order");
                        message.setEnContent(enContent);
                        message.setEnType("Notice");
                        message.setComment("5" + taskCarOrder.getOrderNumber()+appUserId);
                        messageMapper.insert(message);
                        AppUser appUser1 = appUserMapper.selectAppUserByUserId(appUserId);
                        pushCarMsg("alias", "抢单成功", "Car" + appUser1.getNumber(), content + "\r\n" + enContent);
                        String time = new SimpleDateFormat("yyyy-MM-dd").format(taskCarOrder.getCreateTime());
                        String newtime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                        if(time.equals(newtime)&&taskCarOrder.getBagId()!=null){
                            String comment1 = "9"+taskCarOrder.getOrderNumber()+appUserId;
                            Message message2 =messageMapper.selectMessageComment(comment1);
                            if(message2==null) {
                                String content1 = "您的订单(订单号为:" + taskCarOrder.getOrderNumber() + ")可以开始派送，具体详情请查看送货助手";
                                String enContent1 = "Your order (order number: " + taskCarOrder.getOrderNumber() + ") can be sent. For details, please see the delivery assistant.";
                                message.setAppUserId(appUserId);
                                message.setTitle("订单派送提醒");
                                message.setContent(content1);
                                message.setType("通知消息");
                                message.setCategory("小车");
                                message.setState("未读");
                                message.setAddTime(new Date());
                                message.setEnTitle("Order delivery reminder");
                                message.setEnContent(enContent1);
                                message.setEnType("Notice");
                                message.setComment("9"+taskCarOrder.getOrderNumber()+appUserId);
                                messageMapper.insert(message);
                                AppUser appUser2 = appUserMapper.selectAppUserByUserId(appUserId);
                                pushCarMsg("alias", "订单派送提醒", "Car" + appUser2.getNumber(), content1 + "\r\n" + enContent1);
                                MessagePush messagePush = new MessagePush();
                                messagePush.setAppUserId(appUserId);
                                messagePush.setTaskOrderId(taskOrderId);
                                messagePush.setFristPushTime(message.getAddTime());
                                long currentTime = System.currentTimeMillis() + 60 * 60 * 1000;
                                Date date = new Date(currentTime);
                                messagePush.setSecondPushTime(date);
                                messagePush.setIsPush(1);
                                int MessageId = messageMapper.selectLastId();
                                messagePush.setMessageId(MessageId);
                                messagePushMapper.insert(messagePush);
                            }
                        }
                    }
                }

            }

    }
}
