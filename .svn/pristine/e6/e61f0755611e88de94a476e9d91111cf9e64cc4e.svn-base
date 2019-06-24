package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.model.dto.ProblemPieceDto;
import com.siruiman.crosslogistics.model.dto.SigningDto;

import java.util.List;
import java.util.Map;

public interface CarDeliveryAssistantService {
    /**
     * 查询今天开始的小车订单
     * @param appUserId
     * @return
     */
    List<TaskOrder> selectTaskOrder(int appUserId);

    /**
     * 查询小车集散地
     * @param taskOrderId
     * @return
     */
    RallyPoint selectRallyPoint(int taskOrderId);

    /**
     * 打开小车车锁
     * @param rallyPointId
     * @param lockNumber
     * @returns
     */
    String carUnlock(int rallyPointId, int taskOrderId, String lockNumber);

    /**
     * 查询当前订单需要送几个收货点
     * @param taskOrderId
     * @return
     */
    List<ReceivingPoint> selectZipCode(int taskOrderId);

    /**
     * 查询当前点位下的所有包裹
     * @param taskOrderId
     * @param appUserId
     * @return
     */
    List<PointPackage> selectPointPackageByZipCode(int taskOrderId, int appUserId);

    /**
     * 签收
     * @param signingDto
     * @return
     */
    /*Integer signing(SigningDto signingDto);*/

    /**
     * 修改货物有人签收状态
     * @param goodsId
     */
    void editGoodStatus(int goodsId, SigningDto signingDto);

    /**
     * 修改货物无人签收状态
     * @param signingDto
     */
    void editGoodStatusUnmanned(SigningDto signingDto);

    /**
     * 问题件提交
     * @param problemPieceDto
     */
    void problemSubmission(ProblemPieceDto problemPieceDto);

    /**
     * 修改小车订单状态
     * @param taskOrderId
     */
    Integer editCarOrderStatus(int taskOrderId, int appUserId);

    /**
     * 查询当前小车订单绑定的货袋id
     * @param taskOrderId
     * @return
     */
    Integer selectBagId(int taskOrderId);

    /**
     * 修改货袋完成状态
     * @return
     */
    Integer editBagStatus(int taskOrderId);

    /**
     * 修改小车状态
     * @param taskOrderId
     * @return
     */
    Integer editCarStatus(int taskOrderId);

    /**
     * 查询小车订单进行步骤
     * @param appUserId
     * @param taskOrderId
     * @return
     */
    Map selectCarOrderStep(int appUserId, int taskOrderId);

    /**
     * 查询订单钱数和积分数
     * @param taskOrderId
     * @return
     */
    TruckOrderMoney selectTruckOrderMoney(int taskOrderId);

    /**
     * 存储邮编（app逻辑用）
     * @param taskOrderId
     * @param appUserId
     * @param zipCode
     * @return
     */
    Integer storageZipCode(int taskOrderId, int appUserId, String zipCode);

    /**
     * 根据车锁编号查询小车id
     * @param lockNumber
     * @return
     */
    Integer selectCarIdByLockNumber(String lockNumber);

    /**
     * 查询当前小车订单异常件数量
     * @param taskOrderId
     * @return
     */
    Integer countErrorGoods(int taskOrderId);
}
