package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.model.dto.ProblemPieceDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TruckDeliveryAssistantService {
    /**
     * 查询当天要送的订单
     * @param appUserId
     * @return
     */
    List<AppTruckOrder> selectAppTruckOrder(int appUserId);

    /**
     * 修改货车订单状态
     * @param taskOrderId
     * @return
     */
    Integer editTruckOrderStatus(int taskOrderId, int appUserId, int orderType);

    /**
     * 查询当前订单仓库的经纬度
     * @param taskOrderId
     * @return
     */
    Warehouse selectWarehouseLatLng(int taskOrderId);

    /**
     * 查询送货司机编号
     * @param appUserId
     * @return
     */
    String selectTruckNumber(int appUserId);

    /**
     * 插入步骤
     * @param appUserId
     * @param taskOrderId
     * @return
     */
    Integer addDeliverySsistantRecord(int appUserId, int taskOrderId, int orderType);

    /**
     * 绑定货袋和货车
     * @param bagNumber
     * @return
     */
    Integer truckOrderBindingBag(String bagNumber, int taskOrderId);

    /**
     * 修改当前所到步骤
     * @param appUserId
     * @param taskOrderId
     * @param step
     * @return
     */
    Integer editStep(int appUserId, int taskOrderId, int orderType, int step);

    /**
     * 查询货车订单送货路线
     * @param taskOrderId
     * @return
     */
    List<TruckTaskRoute> selectTruckOrderRoute(int taskOrderId);

    /**
     * 查询货车所有路线点位数量
     * @param taskOrderId
     * @return
     */
    Integer countTruckOrderRoute(int taskOrderId);

    /**
     * 扫开小车车厢
     * @param appUserId
     * @param lockNumber
     * @return
     */
    String carUnlock(int appUserId, int taskOrderId, String lockNumber);

    /**
     * 绑定问题件和货车司机
     * @param deliveryNumber
     * @param appUserId
     * @return
     */
    Integer bindingAppUserAndVeryGoods(String deliveryNumber, int appUserId, String lockNumber);

    /**
     * 绑定货袋和小车
     * @param bagNumber
     * @param lockNumber
     * @return
     */
    Integer bindingCarBag(String bagNumber, String lockNumber, int appUserId, int taskOrderId, int todrId, int rallyPointId);

    /**
     * 当前路线集结点已完成
     * @param todrId
     * @return
     */
    Integer editTruckOrderRouteStatus(int todrId);

    /**
     * 关闭小车车厢
     * @param lockNumber
     * @return
     */
    Integer carCloseLock(String lockNumber, int taskOrderId);

    /**
     * 查询中途退出后到达第几步
     * @param appUserId
     * @param taskOrderId
     * @return
     */
    Map selectStep(int appUserId, int taskOrderId);

    /**
     * 货车订单完成
     * @param taskOrderId
     * @return
     */
    Integer truckOrderComplete(int taskOrderId, int appUserId);

    /**
     * 根据货袋编号查询货袋id
     * @param bagNumber
     * @return
     */
    Integer selectBagId(String bagNumber);

    /**
     * 查询当前订单 送货路线当前集结点 需要送的货袋
     * @param taskOrderId
     * @param rallyPointId
     * @return
     */
    List<RallyPointBags> selectRallyPointBags(int taskOrderId, int rallyPointId);

    /**
     * 查询小车使用状态
     * @param lockNumber
     * @return
     */
    Integer selectCarState(String lockNumber);

    /**
     * 绑定小车订单和小车
     * @param lockNumber
     * @return
     */
    Integer bindingCarOrderBag(String lockNumber, String bagNumber);

    /**
     * 根据货车订单id查询这个订单钱数和几分数
     * @param taskOrderId
     * @return
     */
    TruckOrderMoney selectTruckOrderMoney(int taskOrderId);

    /**
     * 查询是否有异常件需要送回仓库
     * @param appUserId
     * @return
     */
    Integer selectIsAbnormal(int appUserId, int taskOrderId);

    /**
     * 查询当前订单所绑定的异常件
     * @return
     */
    List<String> selectVeryGoodsByTruck(int appUserId);

    /**
     * 修改货车订单钱数
     * @param taskOrderId
     * @return
     */
    Integer editTruckOrderMoney(int taskOrderId);

    /**
     * 查询货车订单所有货物送货点
     * @param taskOrderId
     * @return
     */
    List<ReceivingPoint> selectTruckOrderBagZipCode(int taskOrderId);

    /**
     * 查询当前点位下的所有包裹
     * @return
     */
    List<PointPackage> selectPointPackageByZipCode(int taskOrderId, int appUserId, String zipCode);

    /**
     * 货车直接送货-绑定货车司机和异常件
     * @param problemPieceDto
     * @return
     */
    Integer bindingAppUserAndVeryGoodsTwo(ProblemPieceDto problemPieceDto);

    /**
     * 查询货车送货步骤
     * @param appUserId
     * @param taskOrderId
     * @return
     */
    Map selectTruckDeliveryStep(int appUserId, int taskOrderId);

    /**
     * 根据货物id查询货袋id
     * @param goodsId
     * @return
     */
    Integer selectBagIdByGoodsId(int goodsId);

    /**
     * 查询个人所有货物
     * @param taskOrderId
     * @param deliveryNumber
     * @param simpleOrNormal
     * @return
     */
    List<PeopleGoods> selectPeopleGoodsAll(int taskOrderId, String deliveryNumber, int simpleOrNormal, String zipCode);

    /**
     * 查询货物司机问题件是否完成装车
     * @param lockNumber
     * @return
     */
    Integer loadingGoodsWarning(String lockNumber);
}
