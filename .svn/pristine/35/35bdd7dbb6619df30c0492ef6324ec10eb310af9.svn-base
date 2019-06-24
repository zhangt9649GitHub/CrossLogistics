package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.model.dto.SigningDto;
import com.sun.imageio.plugins.common.I18N;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CarDeliveryAssistantMapper {
    /**
     * 查询今天开始的小车订单
     * @param appUserId
     * @param createTime
     * @return
     */
    List<TaskOrder> selectTaskOrder(@Param("appUserId") int appUserId, @Param("createTime") String createTime);

    /**
     * 查询小车集散地
     * @param taskOrderId
     * @return
     */
    RallyPoint selectRallyPoint(@Param("taskOrderId") int taskOrderId);

    /**
     * 查询小车集结点id
     * @param taskOrderId
     * @return
     */
    Integer selectRallyPointId(@Param("taskOrderId") int taskOrderId);

    /**
     * 查询当前订单需要送几个收货点
     * @param taskOrderId
     * @return
     */
    List<String> selectZipCode(@Param("taskOrderId") int taskOrderId);

    /**
     * 根据邮编查询大楼经纬度
     * @param zipCode
     * @return
     */
    ReceivingPoint selectLatLng(@Param("zipCode") String zipCode);

    /**
     * 查询该点位货物的收货状态
     * @param taskOrderId
     * @param zipCode
     * @return
     */
    List<CargoInfo> selectCargoInfoByCarOrderAndZipCode(@Param("taskOrderId") int taskOrderId, @Param("zipCode") String zipCode);

    /**
     * 查询当前点位下的所有包裹
     * @param taskOrderId
     * @param zipCode
     * @return
     */
    List<PointPackage> selectPointPackageByZipCode(@Param("taskOrderId") int taskOrderId, @Param("zipCode") String zipCode);

    /**
     * 修改货物有人签收状态
     * @param goodsId
     */
    Integer editGoodStatus(@Param("goodsId") int goodsId);

    /**
     * 修改货物有人签收状态(详情)
     * @param goodsId
     */
    Integer editGoodDetailStatus(@Param("goodsId") int goodsId, @Param("actualName") String actualName, @Param("ufId") int ufId);

    /**
     * 添加货物无人收货图片信息
     * @param signingDto
     */
    Integer addGoodsPic(@Param("signingDto") SigningDto signingDto);

    /**
     * 修改货物无人签收状态
     * @param signingDto
     */
    Integer editGoodsDetailsReceiptStatus(@Param("signingDto") SigningDto signingDto);

    /**
     * 修改货物异常状态
     * @param goodsId
     */
    Integer editGoodsStatusByOdd(@Param("goodsId") int goodsId);

    /**
     * 修改货物详情异常状态和描述
     * @param goodsId
     * @param abnormalText
     */
    Integer editGoodsDetailsStatusByOdd(@Param("goodsId") int goodsId, @Param("abnormalText") String abnormalText);

    /**
     * 修改小车订单状态
     * @param taskOrderId
     */
    Integer editCarOrderStatus(@Param("taskOrderId") int taskOrderId);

    /**
     * 查询当前小车订单绑定的货袋id
     * @param taskOrderId
     * @return
     */
    Integer selectBagId(@Param("taskOrderId") int taskOrderId);

    /**
     * 修改货袋完成状态
     * @param bagId
     * @param state
     * @return
     */
    Integer editBagStatus(@Param("bagId") int bagId, @Param("state") int state);

    /**
     * 查询当前订单的所有包裹
     * @param taskOrderId
     * @return
     */
    List<PointPackage> selectPointPackage(@Param("taskOrderId") int taskOrderId);

    /**
     * 修改小车状态
     * @param taskOrderId
     * @return
     */
    Integer editCarStatus(@Param("taskOrderId") int taskOrderId, @Param("state") int state, @Param("carId") int carId);

    /**
     * 查询当前小车订单进行到第几步
     * @param appUserId
     * @param taskOrderId
     * @return
     */
    Integer selectCarOrderStep(@Param("appUserId") int appUserId, @Param("taskOrderId") int taskOrderId);

    /**
     * 根据订单查询小车id
     * @param taskOrderId
     * @return
     */
    Integer selectCarIdByCarOrder(@Param("taskOrderId") int taskOrderId);

    /**
     *  修改小车订单状态为完成
     * @param taskOrderId
     * @return
     */
    Integer editCarOrderStatusByWC(@Param("taskOrderId") int taskOrderId);

    /**
     * 查询订单钱数和积分数
     * @param taskOrderId
     * @return
     */
    TruckOrderMoney selectTruckOrderMoney(@Param("taskOrderId") int taskOrderId);

    /**
     * 存储邮编（app逻辑用）
     * @param taskOrderId
     * @param appUserId
     * @param zipCode
     * @return
     */
    Integer storageZipCode(@Param("taskOrderId") int taskOrderId, @Param("appUserId") int appUserId, @Param("zipCode") String zipCode);

    /**
     * 查询小车送货助手存储的点位邮编
     * @param taskOrderId
     * @param appUserId
     * @return
     */
    String selectDeliverySsistantRecordZipCode(@Param("taskOrderId") int taskOrderId, @Param("appUserId") int appUserId);

    /**
     * 根据车锁编号查询小车id
     * @param lockNumber
     * @return
     */
    Integer selectCarIdByLockNumber(@Param("lockNumber") String lockNumber);

    /**
     * 查询当前小车订单异常件数量
     * @param taskOrderId
     * @return
     */
    Integer countErrorGoods(@Param("taskOrderId") int taskOrderId);

    /**
     * 查询APP用户的真实姓名
     * @param appUserId
     * @return
     */
    String selectAppUserActualName(@Param("appUserId") int appUserId);

    /**
     * 查询快递单号
     * @param goodsId
     * @return
     */
    String selectDeliveryNumber(@Param("goodsId") int goodsId);

    /**
     * 修改分箱货物 总的那条收货状态和签收时间
     * @param deliveryNumber
     * @return
     */
    Integer editTotalGoodsIsReceiveGoods(@Param("deliveryNumber") String deliveryNumber);
}
