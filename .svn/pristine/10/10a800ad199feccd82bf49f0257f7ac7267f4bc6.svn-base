package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.AppMessage;
import com.siruiman.crosslogistics.model.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {

    /**
     * 根据app用户id获取中文消息中心列表
     *
     * @param category,appUserId
     * @return
     */
    List<AppMessage> selectMessageList(String category, int appUserId);

    /**
     * 根据app用户id获取英文消息中心列表
     *
     * @param category
     * @param appUserId
     * @return
     */
    List<AppMessage> selectEnMessageList(String category, int appUserId);

    /**
     * 根据app用户id获取消息中心列表总行数
     *
     * @param category
     * @param appUserId
     * @return
     */
    int selectCountMessageList(String category, int appUserId);

    /**
     * 根据消息id获取消息详细内容
     *
     * @param messageId
     * @return
     */
    AppMessage selectMessageById(int messageId);

    /**
     * 修改消息状态
     *
     * @param appMessage
     */
    void updateMessageState(AppMessage appMessage);

    /**
     * 根据消息id获取英文消息详细内容
     *
     * @param messageId
     * @return
     */
    AppMessage selectEnMessageById(int messageId);

    /**
     * 创建消息和消息推送
     *
     * @param steps      步骤
     *                   普通用户（steps 1 ：转运货物入库时推送 (steps,appUserId,0,goodsId,0,0,0)
     *                   steps 2 ：小车骑手开启小车锁，提醒此小车中的转运货物的app用户的推送 (steps,0,carId,0,0,0,0);
     *                   steps 3 ：转运货物配送完成的推送(steps,0,0,goodsId,0,0,0)）
     *                   小车（ steps 4 ：小车审核认证通过的推送 (steps,appUserId,0,0,0,0,0)
     *                   steps 5 ：小车骑手预约到订单之后，一小时未处理，小车订单给予此骑手，进行推送 (steps,appUserId,0,carOrderId,0,0)
     *                   steps 6 : 后台将小车订单指派骑手之后，如果订单是当天订单就进行推送 如果推送一小时后骑手仍未开始派送继续推送 （steps appUserId 0 0 carOrderId，0,0）
     *                   steps 7 :  后台确认提现之后，推送提现到账提醒(小车)（steps,appUserId,0,0,0，withdrawId，0）
     *                   steps 8 : 小车佣金到账后的推送提醒（steps appUserId 0 0 carOrderId 0 0）
     *                   steps 9 :  货车司机将货袋放入小车之后，小车与订单绑定  订单有骑手就推送提醒
     *                   如果推送一小时后骑手仍未开始派送继续推送 （steps，0，carId,0,0,0,0）
     *                   ）
     *                   货车 （steps 10：货车审核认证通过的推送 (steps,appUserId,0,0,0,0,0)
     *                   steps 11 : 后台确认提现之后，推送提现到账提醒（货车）（steps,appUserId,0,0,0，withdrawId，0）
     *                   steps 12 : 货车佣金到账后的推送提醒（steps appUserId 0 0 0 0 truckOrderId）
     *                   steps 13 : 货车直接派送货物的订单(切记传值是货车直接派送的货车订单id)，当货车司机点击完成货袋扫描之后，提醒此货车中的转运货物的app用户的推送 (steps,0,0,0,0,0,truckOrderId)
     *                   steps 14 : 货车直接派送货物，货袋直接出库进行派送，提醒此货车中的转运货物的app用户的推送 （steps，0，0,goodsId，0,0,0）
     *                   ）
     * @param appUserId  app用户id
     * @param carId      小车id
     * @param goodsId    货物id
     * @param carOrderId 小车订单id
     * @param withdrawId 提现订单id
     * @return 1.成功 2.失败
     */
    Integer insertMessage(int steps, int appUserId, int carId, int goodsId, int carOrderId, int withdrawId, int truckOrderId);

    /**
     * 创建消息和消息推送
     *
     * @param steps     普通用户（steps 1 : 转运货物配送出现异常的推送 (steps,0,content,goodsId)）
     *                  steps 2 :转运货物入库时为问题件 (steps,appUserId,content,goodsId)）
     *                  小车 （steps 3 ：小车审核认证驳回的推送   (steps,appUserId,content,0)
     *                  steps 4 ：扣除件扣除时减钱推送提醒 （steps appUserId content goodsId）
     *                  货车 （steps 5 ：货车审核认证驳回的推送  (steps,appUserId,content,0)）
     * @param appUserId app用户id
     * @param content   异常内容
     * @param goodsId   货物id
     * @return
     */
    Integer insertAbnormalMessage(int steps, int appUserId, String content, int goodsId);

    /**
     * 添加消息
     *
     * @param message
     */
    void insert(Message message);

    /**
     * 根据订单id查询消息记录
     *
     * @param MessageId
     * @return
     */
    Message selectAllMessageById(int MessageId);

    /**
     * 验重字段
     *
     * @param comment
     * @return
     */
    Message selectMessageComment(String comment);
}
