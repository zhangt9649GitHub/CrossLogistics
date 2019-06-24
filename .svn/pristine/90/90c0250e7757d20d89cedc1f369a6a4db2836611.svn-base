package com.siruiman.crosslogistics.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GrabOrderMapper {
    /**
     * 修改小车抢单状态
     * @param appUserId
     * @param taskOrderId
     * @return
     */
    Integer editGrabOrderStatus(@Param("appUserId") int appUserId, @Param("taskOrderId") int taskOrderId);

    /**
     * 查询一下当前小车订单是否被抢
     * @param appUserId
     * @param taskOrderId
     * @return
     */
    Integer selectCarGrab(@Param("taskOrderId") int taskOrderId, @Param("appUserId") int appUserId);

    /**
     * 查询一下当前货车订单是否被抢
     * @param appUserId
     * @param taskOrderId
     * @return
     */
    Integer selectTruckGrab(@Param("appUserId") int appUserId, @Param("taskOrderId") int taskOrderId);

    /**
     * 用户抢货车订单
     * @param appUserId
     * @param taskOrderId
     * @return
     */
    Integer truckGrab(@Param("appUserId") int appUserId, @Param("taskOrderId") int taskOrderId);

    /**
     * 查询到了一个小时的订单id
     * @return
     */
    List<Integer> selectCarOrderIds();

    /**
     * 修改所有到一个小时的订单的订单状态
     * @param carOrderIds
     * @return
     */
    Integer carGrab(@Param("carOrderIds") List<Integer> carOrderIds);

    /**
     * 查询当前用户小车认证状态
     * @param appUserId
     * @return
     */
    String selectCarApproveStatus(@Param("appUserId") int appUserId);

    /**
     * 查询当前用户货车认证状态
     * @param appUserId
     * @return
     */
    String selectTruckApproveStatus(@Param("appUserId") int appUserId);

    /**
     * 查询当前用户同一天是否有订单(小车)
     * @param appUserId
     * @param taskOrderId
     * @return
     */
    Integer selectUserTimeIsCarOrder(@Param("appUserId") int appUserId, @Param("taskOrderId") int taskOrderId);

    /**
     * 查询当前用户同一天是否有订单(货车)
     * @param appUserId
     * @param taskOrderId
     * @return
     */
    Integer selectUserTimeIsTruckOrder(@Param("appUserId") int appUserId, @Param("taskOrderId") int taskOrderId);

    /**
     * 根据app用户id查询用户id
     * @param taskOrderId
     * @return
     */
    Integer selectAppUserIdByCarOrder(@Param("taskOrderId") int taskOrderId);
}
