package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface TruckDeliveryAssistantMapper {
    /**
     * 查询当天要送的订单
     * @param appUserId
     * @param createTime
     * @return
     */
    List<AppTruckOrder> selectAppTruckOrder(@Param("appUserId") int appUserId, @Param("createTime") String createTime);

    /**
     * 修改货车订单状态
     * @param taskOrderId
     * @return
     */
    Integer editTruckOrderStatus(@Param("taskOrderId") int taskOrderId, @Param("orderType") int orderType);

    /**
     * 修改货车使用状态
     * @param appUserId
     * @param state 1使用 2空闲
     * @return
     */
    Integer editTruckStatus(@Param("appUserId") int appUserId, @Param("state") int state);

    /**
     * 查询当前订单仓库的经纬度
     * @param taskOrderId
     * @return
     */
    Warehouse selectWarehouseLatLng(@Param("taskOrderId") int taskOrderId);

    /**
     * 查询送货司机编号
     * @param appUserId
     * @return
     */
    String selectTruckNumber(@Param("appUserId") int appUserId);

    /**
     * 插入步骤
     * @param appUserId
     * @param taskOrderId
     * @return
     */
    Integer addDeliverySsistantRecord(@Param("appUserId") int appUserId, @Param("taskOrderId") int taskOrderId, @Param("orderType") int orderType);

    /**
     * 查询当前订单是否已经插入步骤
     * @param appUserId
     * @param taskOrderId
     * @param orderType
     * @return
     */
    Integer countDeliverySsistantRecord(@Param("appUserId") int appUserId, @Param("taskOrderId") int taskOrderId, @Param("orderType") int orderType);

    /**
     * 根据货袋编号查询货袋id
     * @param bagNumber
     * @return
     */
    Integer selectBagId(@Param("bagNumber") String bagNumber);

    /**
     * 绑定货袋和货车订单
     * @param bagId
     * @param taskOrderId
     * @return
     */
    Integer truckOrderBindingBag(@Param("bagId") int bagId, @Param("taskOrderId") int taskOrderId);

    /**
     * 查询是否已经绑定
     * @param bagId
     * @param taskOrderId
     * @return
     */
    Integer selectBindingBag(@Param("bagId") int bagId, @Param("taskOrderId") int taskOrderId);

    /**
     * 修改当前所到步骤
     * @param appUserId
     * @param taskOrderId
     * @param step
     * @return
     */
    Integer editStep(@Param("appUserId") int appUserId, @Param("taskOrderId") int taskOrderId, @Param("orderType") int orderType, @Param("step") int step);

    /**
     * 查询货车订单送货路线
     * @param taskOrderId
     * @return
     */
    List<TruckTaskRoute> selectTruckOrderRoute(@Param("taskOrderId") int taskOrderId);

    /**
     * 根据集结点id查询货车订单送货路线
     * @param taskOrderId
     * @return
     */
    TruckTaskRoute selectTruckOrderRouteByrallyPointId(@Param("taskOrderId") int taskOrderId, @Param("rallyPointId") int rallyPointId);

    /**
     * 查询货车所有路线点位数量
     * @param taskOrderId
     * @return
     */
    Integer countTruckOrderRoute(@Param("taskOrderId") int taskOrderId);

    /**
     * 根据车锁编号查询小车集结点id
     * @param lockNumber
     * @return
     */
    Integer selectRallyPointId(@Param("lockNumber") String lockNumber);

    /**
     * 根据货物快递单号查询货物id
     * @param deliveryNumber
     * @return
     */
    Integer selectGoodsId(@Param("deliveryNumber") String deliveryNumber);

    /**
     * 问题件和货车司机绑定
     * @param appUserId
     * @param goodsId
     * @return
     */
    Integer bindingAppUserAndVeryGoods(@Param("appUserId") int appUserId, @Param("goodsId") int goodsId, @Param("carId") int carId, @Param("createTime") String createTime);

    /**
     * 根据车锁编号查询小车id
     * @param lockNumber
     * @return
     */
    Integer selectCarId(@Param("lockNumber") String lockNumber);

    /**
     * 根据小车id和当前时间查询今天小车的小车订单id
     * @param carId
     * @param createTime
     * @return
     */
    Integer selectCarOrderId(@Param("carId") int carId, @Param("createTime") String createTime);

    /**
     * 绑定小车订单和货袋
     * @param carId
     * @param bagId
     * @return
     */
    Integer bindingCarBag(@Param("bagId") int bagId, @Param("carId") int carId);

    /**
     * 当前路线集结点已完成
     * @param todrId
     * @return
     */
    Integer editTruckOrderRouteStatus(@Param("todrId") int todrId);

    /**
     * 查询当前到哪一步
     * @param appUserId
     * @param taskOrderId
     * @return
     */
    Integer selectStep(@Param("appUserId") int appUserId, @Param("taskOrderId") int taskOrderId);

    /**
     * 查询已绑定货袋信息
     * @param taskOrderId
     * @return
     */
    List<String> selectTruckOrderBags(@Param("taskOrderId") int taskOrderId);

    /**
     * 查询今天这个小车和货车司机所绑定的问题件
     * @param appUserId
     * @param carId
     * @param createTime
     * @return
     */
    List<String> selectVeryGoods(@Param("appUserId") int appUserId, @Param("carId") int carId, @Param("createTime") String createTime);

    /**
     * 根据小车订单id查询绑定的货袋信息
     * @param carOrderId
     * @return
     */
    String selectCarOrderBagNum(@Param("carOrderId") int carOrderId);

    /**
     * 货车订单完成
     * @param taskOrderId
     * @return
     */
    Integer truckOrderComplete(@Param("taskOrderId") int taskOrderId);

    /**
     * 查询小车使用状态
     * @param lockNumber
     * @return
     */
    Integer selectCarState(@Param("lockNumber") String lockNumber);

    /**
     * 修改小车状态-派送中
     * @param carId
     * @return
     */
    Integer editCarStatus(@Param("carId") int carId);

    /**
     * 修改货袋状态为
     * @param bagId
     * @param state 货袋状态:1为损毁2为打包3为入库4,货袋出库,5,货袋送出6大货车运载中,7为小车运载中,8为送货完成,9为送货完但内有异常件
     * @return
     */
    Integer editBagStatus(@Param("bagId") int bagId, @Param("state") int state);

    /**
     * 查询当前订单 送货路线当前集结点 需要送的货袋
     * @param taskOrderId
     * @param rallyPointId
     * @return
     */
    List<RallyPointBags> selectRallyPointBags(@Param("taskOrderId") int taskOrderId, @Param("rallyPointId") int rallyPointId);

    /**
     * 修改车锁状态
     * @param lockNumber
     * @return
     */
    Integer editCarLockStatus(@Param("lockNumber") String lockNumber, @Param("state") int state);

    /**
     * 根据货袋查询当前货袋属于哪个小车集结点
     * @param bagId
     * @return
     */
    Integer selectRallyPointIdByBagId(@Param("bagId") int bagId);

    /**
     * 根据车锁查询小车和小车集结点
     * @param lockNumber
     * @return
     */
    RallyPointIdAndCarId selectRallyPointIdAndCarId(@Param("lockNumber") String lockNumber);

    /**
     * 查询今天 当前集结点的订单 抢单时间
     * @param createTime
     * @param rallyPointId
     * @return
     */
    List<CarOrderTime> selectCarOrderTime(@Param("createTime") String createTime, @Param("rallyPointId") int rallyPointId);

    /**
     * 根据小车id查询小车所属集结点id
     * @param carId
     * @return
     */
    Integer selectRallyPointIdByCarId(@Param("carId") int carId);

    /**
     * 绑定小车订单和小车
     * @param taskOrderId
     * @param carId
     * @return
     */
    Integer bindingCarOrderAndCar(@Param("taskOrderId") int taskOrderId, @Param("carId") int carId, @Param("orderMoney") double orderMoney, @Param("bagId") int bagId);

    /**
     * 查询今天 当前集结点的订单 抢单时间
     * @param rallyPointId
     * @return
     */
    List<CarOrderTime> selectCarOrderTimeByCarTask(@Param("rallyPointId") int rallyPointId,@Param ("createTime") String createTime);

    /**
     * 根据小车模板id和当前时间查询小车订单id
     * @param carTaskId
     * @param createTime
     * @return
     */
    Integer selectCarOrderIdByCarTask(@Param("carTaskId") int carTaskId, @Param("createTime") String createTime);

    /**
     * 查询当前集结点今天的订单
     * @param rallyPointId
     * @param createTime
     * @return
     */
    List<CarOrderTime> selectCarOrderNOTaskGrab(@Param("rallyPointId") int rallyPointId, @Param("createTime") String createTime);

    /**
     * 根据货袋id查询货物数量
     * @param bagId
     * @return
     */
    Integer selectGoodsNum(@Param("bagId") int bagId);

    /**
     * 根据货车订单id查询这个订单钱数和几分数
     * @param taskOrderId
     * @return
     */
    TruckOrderMoney selectTruckOrderMoney(@Param("taskOrderId") int taskOrderId);

    /**
     * 查询是否有异常件需要送回仓库
     * @param appUserId
     * @return
     */
    List<TruckProblemPiece>  selectIsAbnormal(@Param("appUserId") int appUserId);

    /**
     * 查询当前订单所绑定的异常件
     * @param appUserId
     * @return
     */
    List<String> selectVeryGoodsByTruck(@Param("appUserId") int appUserId);

    /**
     * 查询当前货车异常件是否处理完成
     * @param appUserId
     * @return
     */
    Integer selectVeryGoodsIsSolve(@Param("appUserId") int appUserId, @Param("createTime") String createTime);

    /**
     * 查询当前货车订单有多少个货袋
     * @param taskOrderId
     * @return
     */
    Integer selectTruckOrderBagsNum(@Param("taskOrderId") int taskOrderId);

    /**
     * 修改货车订单钱数
     * @param taskOrderId
     * @param money
     * @return
     */
    Integer editTruckOrderMoney(@Param("taskOrderId") int taskOrderId, @Param("money") double money);

    /**
     * 存小车车厢锁编号 逻辑用(步骤表)
     * @param appUserId
     * @param taskOrderId
     * @param lockNumber
     * @return
     */
    Integer storageLockNumber(@Param("lockNumber") String lockNumber, @Param("appUserId") int appUserId, @Param("taskOrderId") int taskOrderId, @Param("bagNumber") String bagNumber,
                              @Param("todrId") int todrId, @Param("rallyPointId") int rallyPointId);

    /**
     * 查询步骤存的车锁编号
     * @param appUserId
     * @param taskOrderId
     * @return
     */
    TruckDeliveryAssStep selectLockNumber(@Param("appUserId") int appUserId, @Param("taskOrderId") int taskOrderId);

    /**
     * 查询订单状态
     * @param taskOrderId
     * @return
     */
    Integer selectTruckOrderStatus(@Param("taskOrderId") int taskOrderId);

    /**
     * 查询当前货车订单所有货物邮编
     * @param taskOrderId
     * @return
     */
    List<String> selectZipCode(@Param("taskOrderId") int taskOrderId);

    /**
     * 查询当前点位下的所有货物
     * @param taskOrderId
     * @param appUserId
     * @param zipCode
     * @return
     */
    List<PointPackage> selectPointPackageByZipCode(@Param("taskOrderId") int taskOrderId, @Param("appUserId") int appUserId, @Param("zipCode") String zipCode);

    /**
     * 查询当前货车今天的异常件
     * @param appUserId
     * @param createTime
     * @return
     */
    List<String> selectTruckUserBudingErrorGoods(@Param("appUserId") int appUserId, @Param("createTime") String createTime);

    /**
     * 查询车锁编号
     * @param lockNumber
     * @return
     */
    Integer selectCarLockState(@Param("lockNumber") String lockNumber);

    /**
     * 查询当前小车是否已经绑定货袋
     * @param carId
     * @return
     */
    Integer selectCarBudingBag(@Param("carId") int carId);

    /**
     * 查询货车订单当前集结点的货
     * @param taskOrderId
     * @param zipCode
     * @return
     */
    List<CargoInfo> selectTruckOrderBagByZipCode(@Param("taskOrderId") int taskOrderId, @Param("zipCode") String zipCode);

    /**
     * 根据货物id查询货袋id
     * @param goodsId
     * @return
     */
    Integer selectBagIdByGoodsId(@Param("goodsId") int goodsId);

    /**
     * 查询当前货车订单订单类型
     * @param taskOrderId
     * @return
     */
    Integer selectOrderType(@Param("taskOrderId") int taskOrderId);

    /**
     * 根据快递单号查询 货物箱数
     * @param deliveryNumber
     * @return
     */
    PointPackage selectTotalGoods(@Param("deliveryNumber") String deliveryNumber);

    /**
     * 查询分箱总单 是否已经签收
     * @param deliveryNumber
     * @return
     */
    Integer selectIsReceiveGoods(@Param("deliveryNumber") String deliveryNumber);

    /**
     * 简易APP 查询个人所有货物
     * @return
     */
    List<PeopleGoods> selectPeopleGoodsBySimple(@Param("deliveryNumber") String deliveryNumber);

    /**
     * 货车送货 查询个人所有货物
     * @return
     */
    List<PeopleGoods> selectPeopleGoodsByTruckNormal(@Param("taskOrderId") int taskOrderId, @Param("deliveryNumber") String deliveryNumber, @Param("zipCode") String zipCode);

    /**
     * 小车送货 查询个人所有货物
     * @return
     */
    List<PeopleGoods> selectPeopleGoodsByCarNormal(@Param("taskOrderId") int taskOrderId, @Param("deliveryNumber") String deliveryNumber, @Param("zipCode") String zipCode);

    /**
     * 根据货车id 和小车id 还有订单状态查询是否已经匹配小车订单
     * @param bagId
     * @param carId
     * @return
     */
    Integer selectCountCarOrder(@Param("bagId") int bagId,@Param("carId") int carId);

    /**
     * 根据货物id查询货物异常件表里有无此货物
     * @param goodsId
     * @return
     */
    TruckProblemPiece selectGoodsIdByCarId(int goodsId);

    /**
     * 查询当前时间此集结点还有无订单
     * @param rallyPointId
     * @param createTime
     * @return
     */
    List<TaskCarOrder> selectCarOrderByRallyId(@Param("rallyPointId") Integer rallyPointId,@Param("createTime") String createTime);

    /**
     * 根据货车订单id查询步骤
     * @param taskOrderId
     * @return
     */
    TruckDeliveryAssistant selectTruckOrderById(@Param("taskOrderId") int taskOrderId);
}
