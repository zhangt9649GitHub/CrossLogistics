package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.*;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.service.MessageService;
import com.siruiman.crosslogistics.service.WithdrawApplicationService;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.siruiman.crosslogistics.util.CarJPushClientUtil.pushCarMsg;
import static com.siruiman.crosslogistics.util.RandomCodeUtil.getEightRandomCode;
import static com.siruiman.crosslogistics.util.RandomCodeUtil.getSixRandomCode;
import static com.siruiman.crosslogistics.util.TruckJPushClientUtil.pushTruckMsg;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private AppUserMapper appUserMapper;
    @Autowired
    private BagMapper bagMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsDetailsMapper goodsDetailsMapper;
    @Autowired
    private TaskCarOrderMapper taskCarOrderMapper;
    @Autowired
    private WithdrawApplicationMapper withdrawApplicationMapper;
    @Autowired
    private TaskTruckOrderMapper taskTruckOrderMapper;
    @Autowired
    private MessagePushMapper messagePushMapper;
    @Autowired
    private GoodsWarningMapper goodsWarningMapper;
    @Autowired
    private TaskOrderBagMapper taskOrderBagMapper;

    @Override
    public List<AppMessage> selectMessageList(String category, int appUserId) {
        try {
            return messageMapper.selectMessageList(category, appUserId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<AppMessage> selectEnMessageList(String category, int appUserId) {
        try {
            return messageMapper.selectEnMessageList(category, appUserId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int selectCountMessageList(String category, int appUserId) {
        try {
            return messageMapper.selectCountMessageList(category, appUserId);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public AppMessage selectMessageById(int messageId) {
        try {
            return messageMapper.selectMessageById(messageId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateMessageState(AppMessage appMessage) {
        messageMapper.updateZhMessage(appMessage);
    }

    @Override
    public AppMessage selectEnMessageById(int messageId) {
        try {
            return messageMapper.selectEnMessageById(messageId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer insertMessage(int steps, int appUserId, int carId, int goodsId, int carOrderId, int withdrawId, int truckOrderId) {
        Message message = new Message();
        AppUser appUser = new AppUser();
        Goods goods = new Goods();
        TaskCarOrder taskCarOrder = new TaskCarOrder();
        TaskTruckOrder taskTruckOrder = new TaskTruckOrder();
        WithdrawApplication withdrawApplication = new WithdrawApplication();
        List<TaskOrderBag> taskOrderBagList = new ArrayList<>();
        if (appUserId > 0) {
            appUser = appUserMapper.selectAppUserByUserId(appUserId);
        }
        if (goodsId > 0) {
            goods = goodsMapper.selectGoodsById(goodsId);
        }
        if (carOrderId > 0) {
            taskCarOrder = taskCarOrderMapper.selectByPrimaryKey(carOrderId);
        }
        if (withdrawId > 0) {
            withdrawApplication = withdrawApplicationMapper.selectByPrimaryKey(withdrawId);
        }
        if (truckOrderId > 0) {
            taskTruckOrder = taskTruckOrderMapper.selectByPrimaryKey(truckOrderId);
            taskOrderBagList = taskOrderBagMapper.selectBagsBytruckOrderId(truckOrderId);
        }
        if (steps == 1) {
            String comment = "1" + goods.getDeliveryNumber();
            Message message1 = messageMapper.selectMessageComment(comment);
            if (message1 == null) {
                String content = "您的转运包裹已到仓库，可以发起包裹转运了";
                String enContent = "Your transshipment parcel has arrived at the warehouse and can be used to initiate parcel transfer.";
                message.setAppUserId(appUserId);
                message.setTitle("包裹已到仓");
                message.setContent(content);
                message.setType("通知消息");
                message.setCategory("普通用户");
                message.setState("未读");
                message.setAddTime(new Date());
                message.setEnTitle("Parcel has arrived");
                message.setEnContent(enContent);
                message.setEnType("Notice");
                message.setComment("1" + goods.getDeliveryNumber());
                messageMapper.insert(message);
                if (appUser != null && appUser.getNumber() != null) {
                    pushCarMsg("alias", "包裹已到仓", "Car" + appUser.getNumber(), content + "\r\n" + enContent);
                }
            }
            return 1;
        } else if (steps == 2) {
            List<Bag> bagList = bagMapper.selectBagByCarId(carId);
            if (bagList.size() == 1) {
                for (Bag bag : bagList
                ) {
                    List<Goods> goodsList = goodsMapper.selectCarGoodsList(bag.getBagId());
                    for (Goods goods1 : goodsList
                    ) {
                        if (goods1.getFrom().equals("转运订单") && !(goods1.getTransportType().equals("组合子转运"))) {
                            String comment = "2" + goods1.getDeliveryNumber();
                            Message message1 = messageMapper.selectMessageComment(comment);
                            if (message1 == null) {
                                String content = "您的包裹(快递单号为:" + goods1.getDeliveryNumber() + ")正在派送中，请您注意查收";
                                String enContent = "Your parcel (tracking number :" + goods1.getDeliveryNumber() + ") is being dispatched, please pay attention to check.";
                                message.setAppUserId(goods1.getAppUserId());
                                message.setTitle("包裹派送中");
                                message.setContent(content);
                                message.setType("通知消息");
                                message.setCategory("普通用户");
                                message.setState("未读");
                                message.setAddTime(new Date());
                                message.setEnTitle("Parcel delivery");
                                message.setEnContent(enContent);
                                message.setEnType("Notice");
                                message.setComment("2" + goods1.getDeliveryNumber());
                                messageMapper.insert(message);
                                AppUser appUser1 = appUserMapper.selectAppUserByUserId(goods1.getAppUserId());
                                pushCarMsg("alias", "包裹派送中", "Car" + appUser1.getNumber(), content + "\r\n" + enContent);
                            }

                        }
                    }
                }
                return 1;
            } else {
                return 2;
            }
        } else if (steps == 3) {
            if (goods.getFrom().equals("转运订单") && !(goods.getTransportType().equals("组合子转运"))) {
                GoodsDetails goodsDetails = new GoodsDetails();
                String goodState = "已完成";
                goodsDetails.setGoodState(goodState);
                goodsDetails.setUpdateTime(new Date());
                goodsDetails.setGoodsId(goodsId);
                goodsDetailsMapper.updateGoodsState(goodsDetails);
                if (goods.getIsReceiveGoods() == 1) {
                    String comment = "3" + goods.getDeliveryNumber();
                    Message message1 = messageMapper.selectMessageComment(comment);
                    if (message1 == null) {
                        String content = "您的包裹(快递单号为:" + goods.getDeliveryNumber() + ")已签收，期待再次为您服务";
                        String enContent = "Your parcel (tracking number :" + goods.getDeliveryNumber() + ") has been received. Looking forward to serving you again";
                        message.setAppUserId(goods.getAppUserId());
                        message.setTitle("包裹已签收");
                        message.setContent(content);
                        message.setType("通知消息");
                        message.setCategory("普通用户");
                        message.setState("未读");
                        message.setAddTime(new Date());
                        message.setEnTitle("Parcel signing success");
                        message.setEnContent(enContent);
                        message.setEnType("Notice");
                        message.setComment("3" + goods.getDeliveryNumber());
                        messageMapper.insert(message);
                        AppUser appUser1 = appUserMapper.selectAppUserByUserId(goods.getAppUserId());
                        pushCarMsg("alias", "包裹已签收", "Car" + appUser1.getNumber(), content + "\r\n" + enContent);
                    }
                }
            }
            return 1;
        } else if (steps == 4) {
            String comment = "4" + appUserId;
            Message message1 = messageMapper.selectMessageComment(comment);
            if (message1 == null) {
                String content = "恭喜您！您的小车审核认证已通过";
                String enContent = "Congratulations! Your car review certification has passed";
                message.setAppUserId(appUserId);
                message.setTitle("小车审核认证已通过");
                message.setContent(content);
                message.setType("通知消息");
                message.setCategory("小车");
                message.setState("未读");
                message.setAddTime(new Date());
                message.setEnTitle("Car audit certification has passed");
                message.setEnContent(enContent);
                message.setEnType("Notice");
                message.setComment("4" + appUserId);
                messageMapper.insert(message);
                pushCarMsg("alias", "小车审核认证已通过", "Car" + appUser.getNumber(), content + "\r\n" + enContent);
            }
            return 1;
        } else if (steps == 5) {
            if (taskCarOrder.getAppUserId() != null) {
                String comment = "5" + taskCarOrder.getOrderNumber() + appUserId;
                Message message1 = messageMapper.selectMessageComment(comment);
                if (message1 == null) {
                    String content = "恭喜您! 抢单成功,订单号为:" + taskCarOrder.getOrderNumber() + "请及时进行派送";
                    String enContent = "Congratulations! The order is successful, the order number is: " + taskCarOrder.getOrderNumber() + ", please send it in time.";
                    message.setAppUserId(taskCarOrder.getAppUserId());
                    message.setTitle("抢单成功");
                    message.setContent(content);
                    message.setType("通知消息");
                    message.setCategory("小车");
                    message.setState("未读");
                    message.setAddTime(new Date());
                    message.setEnTitle("Successfully grabbed the order");
                    message.setEnContent(enContent);
                    message.setEnType("Notice");
                    message.setComment("5" + taskCarOrder.getOrderNumber() + taskCarOrder.getAppUserId());
                    messageMapper.insert(message);
                    AppUser appUser1 = appUserMapper.selectAppUserByUserId(taskCarOrder.getAppUserId());
                    pushCarMsg("alias", "抢单成功", "Car" + appUser1.getNumber(), content + "\r\n" + enContent);
                    String time = new SimpleDateFormat("yyyy-MM-dd").format(taskCarOrder.getCreateTime());
                    String newtime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                    if (time.equals(newtime) && taskCarOrder.getBagId() != null) {
                        String comment1 = "9" + taskCarOrder.getOrderNumber() + taskCarOrder.getAppUserId();
                        Message message2 = messageMapper.selectMessageComment(comment1);
                        if (message2 == null) {
                            String content1 = "您的订单(订单号为:" + taskCarOrder.getOrderNumber() + ")可以开始派送，具体详情请查看送货助手";
                            String enContent1 = "Your order (order number: " + taskCarOrder.getOrderNumber() + ") can be sent. For details, please see the delivery assistant.";
                            message.setAppUserId(taskCarOrder.getAppUserId());
                            message.setTitle("订单派送提醒");
                            message.setContent(content1);
                            message.setType("通知消息");
                            message.setCategory("小车");
                            message.setState("未读");
                            message.setAddTime(new Date());
                            message.setEnTitle("Order delivery reminder");
                            message.setEnContent(enContent1);
                            message.setEnType("Notice");
                            message.setComment("9" + taskCarOrder.getOrderNumber() + taskCarOrder.getAppUserId());
                            messageMapper.insert(message);
                            AppUser appUser2 = appUserMapper.selectAppUserByUserId(taskCarOrder.getAppUserId());
                            pushCarMsg("alias", "订单派送提醒", "Car" + appUser2.getNumber(), content1 + "\r\n" + enContent1);
                            MessagePush messagePush = new MessagePush();
                            messagePush.setAppUserId(taskCarOrder.getAppUserId());
                            messagePush.setTaskOrderId(taskCarOrder.getTaskOrderId());
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
                return 1;
            }
        } else if (steps == 6) {
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateNowStr = sdf.format(d);
            String createTime = sdf.format(taskCarOrder.getCreateTime());
            if (dateNowStr.equals(createTime) && taskCarOrder.getCarId() != null) {
                String comment = "6" + taskCarOrder.getOrderNumber() + appUserId;
                Message message1 = messageMapper.selectMessageComment(comment);
                if (message1 == null) {
                    String content = "您今天有一条新的小车订单，订单号为:" + taskCarOrder.getOrderNumber() + "可以开始派送，具体详情请查看送货助手";
                    String enContent = "You have a new car order today, the order number is: " + taskCarOrder.getOrderNumber() + " can start to send, please see the delivery assistant for details.";
                    message.setAppUserId(appUserId);
                    message.setTitle("订单派送提醒");
                    message.setContent(content);
                    message.setType("通知消息");
                    message.setCategory("小车");
                    message.setState("未读");
                    message.setAddTime(new Date());
                    message.setEnTitle("Order delivery reminder");
                    message.setEnContent(enContent);
                    message.setEnType("Notice");
                    message.setComment("6" + taskCarOrder.getOrderNumber() + appUserId);
                    messageMapper.insert(message);
                    pushCarMsg("alias", "订单派送提醒", "Car" + appUser.getNumber(), content + "\r\n" + enContent);
                    MessagePush messagePush = new MessagePush();
                    messagePush.setAppUserId(appUserId);
                    messagePush.setTaskOrderId(carOrderId);
                    messagePush.setFristPushTime(message.getAddTime());
                    long currentTime = System.currentTimeMillis() + 60 * 60 * 1000;
                    Date date = new Date(currentTime);
                    messagePush.setSecondPushTime(date);
                    messagePush.setIsPush(1);
                    int MessageId = messageMapper.selectLastId();
                    messagePush.setMessageId(MessageId);
                    messagePushMapper.insert(messagePush);
                }
                return 1;
            }
        } else if (steps == 7) {
            String comment = "7" + withdrawApplication.getWithdrawOrderNumber();
            Message message1 = messageMapper.selectMessageComment(comment);
            if (message1 == null) {
                String content = "提现到账成功，订单号为:" + withdrawApplication.getWithdrawOrderNumber() + "，具体详情请查看提现记录";
                String enContent = "The withdrawal is successful, the order number is: " + withdrawApplication.getWithdrawOrderNumber() + "，Please see the withdrawal record for details.";
                message.setAppUserId(appUserId);
                message.setTitle("提现到账成功");
                message.setContent(content);
                message.setType("通知消息");
                message.setCategory("小车");
                message.setState("未读");
                message.setAddTime(new Date());
                message.setEnTitle("Cash withdrawal successful");
                message.setEnContent(enContent);
                message.setEnType("Notice");
                message.setComment("7" + withdrawApplication.getWithdrawOrderNumber());
                messageMapper.insert(message);
                pushCarMsg("alias", "提现到账成功", "Car" + appUser.getNumber(), content + "\r\n" + enContent);
            }
            return 1;
        } else if (steps == 8) {
            String comment = "8" + taskCarOrder.getOrderNumber();
            Message message1 = messageMapper.selectMessageComment(comment);
            if (message1 == null) {
                String content = "佣金到账成功，订单号为:" + taskCarOrder.getOrderNumber() + "，具体详情请查看交易记录";
                String enContent = "The commission is successfully received. The order number is: " + taskCarOrder.getOrderNumber() + ". Please check the transaction record for details.";
                message.setAppUserId(appUserId);
                message.setTitle("佣金到账成功");
                message.setContent(content);
                message.setType("通知消息");
                message.setCategory("小车");
                message.setState("未读");
                message.setAddTime(new Date());
                message.setEnTitle("Commission arrived successfully");
                message.setEnContent(enContent);
                message.setEnType("Notice");
                message.setComment("8" + taskCarOrder.getOrderNumber());
                messageMapper.insert(message);
                pushCarMsg("alias", "佣金到账成功", "Car" + appUser.getNumber(), content + "\r\n" + enContent);
            }
            return 1;
        } else if (steps == 9) {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateNowStr = sdf.format(date);
            String createTime = dateNowStr + " 00:00:00";
            TaskCarOrder taskCarOrder1 = taskCarOrderMapper.selectTaskCarOrderByCarIdAndTime(carId, createTime);
            if (taskCarOrder1 != null && taskCarOrder1.getAppUserId() != null && taskCarOrder1.getAppUserId() > 0 && taskCarOrder1.getTaskOrderId() > 0 && taskCarOrder1.getState() == 2) {
                String comment = "9" + taskCarOrder1.getOrderNumber() + taskCarOrder1.getAppUserId();
                Message message1 = messageMapper.selectMessageComment(comment);
                if (message1 == null) {
                    String content = "您的订单(订单号为:" + taskCarOrder1.getOrderNumber() + ")可以开始派送，具体详情请查看送货助手";
                    String enContent = "Your order (order number: " + taskCarOrder1.getOrderNumber() + ") can be sent. For details, please see the delivery assistant.";
                    message.setAppUserId(taskCarOrder1.getAppUserId());
                    message.setTitle("订单派送提醒");
                    message.setContent(content);
                    message.setType("通知消息");
                    message.setCategory("小车");
                    message.setState("未读");
                    message.setAddTime(new Date());
                    message.setEnTitle("Order delivery reminder");
                    message.setEnContent(enContent);
                    message.setEnType("Notice");
                    message.setComment("9" + taskCarOrder1.getOrderNumber() + taskCarOrder1.getAppUserId());
                    messageMapper.insert(message);
                    AppUser appUser1 = appUserMapper.selectAppUserByUserId(taskCarOrder1.getAppUserId());
                    pushCarMsg("alias", "订单派送提醒", "Car" + appUser1.getNumber(), content + "\r\n" + enContent);
                    MessagePush messagePush = new MessagePush();
                    messagePush.setAppUserId(taskCarOrder1.getAppUserId());
                    messagePush.setTaskOrderId(taskCarOrder1.getTaskOrderId());
                    messagePush.setFristPushTime(message.getAddTime());
                    long currentTime = System.currentTimeMillis() + 60 * 60 * 1000;
                    Date date1 = new Date(currentTime);
                    messagePush.setSecondPushTime(date1);
                    messagePush.setIsPush(1);
                    int MessageId = messageMapper.selectLastId();
                    messagePush.setMessageId(MessageId);
                    messagePushMapper.insert(messagePush);
                }
            }
            return 1;
        } else if (steps == 10) {
            String comment = "10" + appUserId;
            Message message1 = messageMapper.selectMessageComment(comment);
            if (message1 == null) {
                String content = "恭喜您！您的货车审核认证已通过";
                String enContent = "Congratulations! Your truck review certification has passed";
                message.setAppUserId(appUserId);
                message.setTitle("货车审核认证已通过");
                message.setContent(content);
                message.setType("通知消息");
                message.setCategory("货车");
                message.setState("未读");
                message.setAddTime(new Date());
                message.setEnTitle("Truck audit certification has passed");
                message.setEnContent(enContent);
                message.setEnType("Notice");
                message.setComment("10" + appUserId);
                messageMapper.insert(message);
                pushTruckMsg("alias", "货车审核认证已通过", "Truck" + appUser.getNumber(), content + "\r\n" + enContent);
            }
            return 1;
        } else if (steps == 11) {
            String comment = "11" + withdrawApplication.getWithdrawOrderNumber();
            Message message1 = messageMapper.selectMessageComment(comment);
            if (message1 == null) {
                String content = "提现到账成功，订单号为:" + withdrawApplication.getWithdrawOrderNumber() + "，具体详情请查看提现记录";
                String enContent = "The withdrawal is successful, the order number is: " + withdrawApplication.getWithdrawOrderNumber() + "，Please see the withdrawal record for details.";
                message.setAppUserId(appUserId);
                message.setTitle("提现到账成功");
                message.setContent(content);
                message.setType("通知消息");
                message.setCategory("货车");
                message.setState("未读");
                message.setAddTime(new Date());
                message.setEnTitle("Cash withdrawal successful");
                message.setEnContent(enContent);
                message.setEnType("Notice");
                message.setComment("11" + withdrawApplication.getWithdrawOrderNumber());
                messageMapper.insert(message);
                pushTruckMsg("alias", "提现到账成功", "Truck" + appUser.getNumber(), content + "\r\n" + enContent);
            }
            return 1;
        } else if (steps == 12) {
            String comment = "12" + taskTruckOrder.getOrderNumber();
            Message message1 = messageMapper.selectMessageComment(comment);
            if (message1 == null) {
                String content = "佣金到账成功，订单号为:" + taskTruckOrder.getOrderNumber() + "，具体详情请查看交易记录";
                String enContent = "The commission is successfully received. The order number is: " + taskTruckOrder.getOrderNumber() + ". Please check the transaction record for details.";
                message.setAppUserId(appUserId);
                message.setTitle("佣金到账成功");
                message.setContent(content);
                message.setType("通知消息");
                message.setCategory("货车");
                message.setState("未读");
                message.setAddTime(new Date());
                message.setEnTitle("Commission arrived successfully");
                message.setEnContent(enContent);
                message.setEnType("Notice");
                message.setComment("12" + taskTruckOrder.getOrderNumber());
                messageMapper.insert(message);
                pushTruckMsg("alias", "佣金到账成功", "Truck" + appUser.getNumber(), content + "\r\n" + enContent);
            }
            return 1;
        } else if (steps == 13) {
            List<Goods> goodsList1 = new ArrayList<>();
            if (taskOrderBagList.size() > 0) {
                for (TaskOrderBag taskOrderBag : taskOrderBagList
                ) {
                    if (taskOrderBag.getBagId() != null) {
                        List<Goods> goodsList = goodsMapper.selectCarGoodsList(taskOrderBag.getBagId());
                        for (Goods goods1 : goodsList
                        ) {
                            goodsList1.add(goods1);
                        }
                    }
                }
            }
            if (goodsList1.size() > 0) {
                for (Goods goods1 : goodsList1
                ) {
                    if (goods1.getFrom().equals("转运订单") && !(goods1.getTransportType().equals("组合子转运"))) {
                        String comment = "13" + goods1.getDeliveryNumber();
                        Message message1 = messageMapper.selectMessageComment(comment);
                        if (message1 == null) {
                            String content = "您的包裹(快递单号为:" + goods1.getDeliveryNumber() + ")正在派送中，请您注意查收";
                            String enContent = "Your parcel (tracking number :" + goods1.getDeliveryNumber() + ") is being dispatched, please pay attention to check.";
                            message.setAppUserId(goods1.getAppUserId());
                            message.setTitle("包裹派送中");
                            message.setContent(content);
                            message.setType("通知消息");
                            message.setCategory("普通用户");
                            message.setState("未读");
                            message.setAddTime(new Date());
                            message.setEnTitle("Parcel delivery");
                            message.setEnContent(enContent);
                            message.setEnType("Notice");
                            message.setComment("13" + goods1.getDeliveryNumber());
                            messageMapper.insert(message);
                            AppUser appUser1 = appUserMapper.selectAppUserByUserId(goods1.getAppUserId());
                            pushCarMsg("alias", "包裹派送中", "Car" + appUser1.getNumber(), content + "\r\n" + enContent);
                        }
                    }
                }
            }
            return 1;
        } else if (steps == 14) {
            if (goods.getFrom().equals("转运订单") && !(goods.getTransportType().equals("组合子转运"))) {
                String comment = "14" + goods.getDeliveryNumber();
                Message message1 = messageMapper.selectMessageComment(comment);
                if (message1 == null) {
                    String content = "您的包裹(快递单号为:" + goods.getDeliveryNumber() + ")正在派送中，请您注意查收";
                    String enContent = "Your parcel (tracking number :" + goods.getDeliveryNumber() + ") is being dispatched, please pay attention to check.";
                    message.setAppUserId(goods.getAppUserId());
                    message.setTitle("包裹派送中");
                    message.setContent(content);
                    message.setType("通知消息");
                    message.setCategory("普通用户");
                    message.setState("未读");
                    message.setAddTime(new Date());
                    message.setEnTitle("Parcel delivery");
                    message.setEnContent(enContent);
                    message.setEnType("Notice");
                    message.setComment("14" + goods.getDeliveryNumber());
                    messageMapper.insert(message);
                    AppUser appUser1 = appUserMapper.selectAppUserByUserId(goods.getAppUserId());
                    pushCarMsg("alias", "包裹派送中", "Car" + appUser1.getNumber(), content + "\r\n" + enContent);
                }
            }
            return 1;
        }
        return 2;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer insertAbnormalMessage(int steps, int appUserId, String content, int goodsId) {
        Message message = new Message();
        AppUser appUser = new AppUser();
        Goods goods = new Goods();
        if (appUserId > 0) {
            appUser = appUserMapper.selectAppUserByUserId(appUserId);
        }
        if (goodsId > 0) {
            goods = goodsMapper.selectGoodsById(goodsId);
        }
        if (steps == 1) {
            if (goods.getFrom().equals("转运订单") && !(goods.getTransportType().equals("组合子转运"))) {
                GoodsWarning goodsWarning = goodsWarningMapper.selectGoodsWarningByGoodsId(goods.getGoodsId());
                String comment = "13" + goodsWarning.getGoodsWarningId();
                Message message1 = messageMapper.selectMessageComment(comment);
                if (message1 == null) {
                    message.setAppUserId(goods.getAppUserId());
                    message.setTitle("异常件无法派送");
                    message.setContent(content);
                    message.setType("通知消息");
                    message.setCategory("普通用户");
                    message.setState("未读");
                    message.setAddTime(new Date());
                    message.setEnTitle("Dispatch exception");
                    message.setEnContent(content);
                    message.setEnType("Notice");
                    message.setComment("13" + goodsWarning.getGoodsWarningId());
                    messageMapper.insert(message);
                    AppUser appUser1 = appUserMapper.selectAppUserByUserId(goods.getAppUserId());
                    pushCarMsg("alias", "异常件无法派送", "Car" + appUser1.getNumber(), content);
                }
            }
            return 1;
        } else if (steps == 2) {
            GoodsWarning goodsWarning = goodsWarningMapper.selectGoodsWarningByGoodsId(goods.getGoodsId());
            String comment = "14" + goodsWarning.getGoodsWarningId();
            Message message1 = messageMapper.selectMessageComment(comment);
            if (message1 == null) {
                message.setAppUserId(appUserId);
                message.setTitle("异常件无法入库");
                message.setContent(content);
                message.setType("通知消息");
                message.setCategory("普通用户");
                message.setState("未读");
                message.setAddTime(new Date());
                message.setEnTitle("Inbound exception");
                message.setEnContent(content);
                message.setEnType("Notice");
                message.setComment("14" + goodsWarning.getGoodsWarningId());
                messageMapper.insert(message);
                pushCarMsg("alias", "异常件无法入库", "Car" + appUser.getNumber(), content);
            }
            return 1;
        } else if (steps == 3) {
            message.setAppUserId(appUserId);
            message.setTitle("小车审核认证已驳回");
            message.setContent(content);
            message.setType("通知消息");
            message.setCategory("小车");
            message.setState("未读");
            message.setAddTime(new Date());
            message.setEnTitle("Car audit certification has been rejected");
            message.setEnContent(content);
            message.setEnType("Notice");
            message.setComment("15" + getEightRandomCode());
            messageMapper.insert(message);
            pushCarMsg("alias", "小车审核认证已驳回", "Car" + appUser.getNumber(), content);
            return 1;
        } else if (steps == 4) {
            String comment = "16" + goodsId;
            Message message1 = messageMapper.selectMessageComment(comment);
            if (message1 == null) {
                message.setAppUserId(appUserId);
                message.setTitle("佣金扣除");
                message.setContent(content);
                message.setType("通知消息");
                message.setCategory("小车");
                message.setState("未读");
                message.setAddTime(new Date());
                message.setEnTitle("Commission deduction");
                message.setEnContent(content);
                message.setEnType("Notice");
                message.setComment("16" + goodsId);
                messageMapper.insert(message);
                pushCarMsg("alias", "佣金扣除", "Car" + appUser.getNumber(), content);
            }
            return 1;
        } else if (steps == 5) {
            message.setAppUserId(appUserId);
            message.setTitle("货车审核认证已驳回");
            message.setContent(content);
            message.setType("通知消息");
            message.setCategory("货车");
            message.setState("未读");
            message.setAddTime(new Date());
            message.setEnTitle("Truck audit certification has been rejected");
            message.setEnContent(content);
            message.setEnType("Notice");
            message.setComment("17" + getEightRandomCode());
            messageMapper.insert(message);
            pushTruckMsg("alias", "货车审核认证已驳回", "Truck" + appUser.getNumber(), content);
            return 1;
        }
        return 2;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insert(Message message) {
        messageMapper.insert(message);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Message selectAllMessageById(int MessageId) {
            return messageMapper.selectByPrimaryKey(MessageId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Message selectMessageComment(String comment) {
            return messageMapper.selectMessageComment(comment);
    }

}
