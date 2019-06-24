package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.model.dto.TruckOrderDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CarOrderMapper {
    /**
     * App任务订单列表
     * @param truckOrderDto
     * @return
     */
    List<TaskOrder> selectTaskOrderAll(@Param("truckOrderDto") TruckOrderDto truckOrderDto);

    /**
     * App任务订单列表条数
     * @param truckOrderDto
     * @return
     */
    Integer countTaskOrderAll(@Param("truckOrderDto") TruckOrderDto truckOrderDto);

    /**
     * 根据区域查询小车集结点
     * @param singaporeAreaId
     * @return
     */
    List<RallyPoint> selectRallyPointBySingaporeArea(@Param("singaporeAreaId") int singaporeAreaId);

    /**
     * 修改订单派送人
     * @param taskOrderId
     * @param appUserId
     * @return
     */
    Integer editCarOrderUser(@Param("taskOrderId") int taskOrderId, @Param("appUserId") int appUserId, @Param("state") int state);

    /**
     * 查询所有小车用户
     * @return
     */
    List<AppUser> selectAppUser();

    /**
     * 查询当前用户在某一时间是否有订单
     * @param appUserId
     * @param createTime
     * @return
     */
    Integer selectCarOrderByUser(@Param("appUserId") int appUserId, @Param("createTime") String createTime);

    /**
     * 根据订单id查询当前订单的派送人信息
     * @param taskOrderId
     * @return
     */
    AppUser selectAppUserByCarOrderId(@Param("taskOrderId") int taskOrderId);

    /**
     * 根据订单id查询当前订单送货小车
     * @param taskOrderId
     * @return
     */
    Car selectCarByCarOrder(@Param("taskOrderId") int taskOrderId);

    /**
     * 查询所有小车
     * @return
     */
    List<Car> selectCarAll();

    /**
     * 根据订单时间这个小车是否存在订单
     * @param carId
     * @param createTime
     * @return
     */
    Integer selectCarOrderByCar(@Param("carId") int carId, @Param("createTime") String createTime);

    /**
     * 根据小车订单id查询当前订单所绑定的货袋
     * @param taskOrderId
     * @return
     */
    Integer selectBagIdByCarOrder(@Param("taskOrderId") int taskOrderId);

    /**
     * 修改当前订单所绑定小车
     * @param taskOrderId
     * @param newCarId
     * @return
     */
    Integer editCarOrderCarId(@Param("taskOrderId") int taskOrderId, @Param("newCarId") int newCarId);

    /**
     * 修改货袋绑定小车
     * @param bagId
     * @return
     */
    Integer editBagCarId(@Param("bagId") int bagId, @Param("newCarId") int newCarId);

    /**
     * 查询当前订单状态
     * @param taskOrderId
     * @return
     */
    Integer selectCarOrderStatus(@Param("taskOrderId") int taskOrderId);

    /**
     * 根据订单id查询订单时间
     * @param taskOrderId
     * @return
     */
    String selectCarOrderTime(@Param("taskOrderId") int taskOrderId);

    /**
     * 根据时间查询当天所有订单
     *     2019-03-21 新增统计订单功能
     * @param date
     * @return
     */
    int selectCountOrderByTime(@Param("date")String date);

    /**
     * 根据时间查询当天订单完成个数
     * 2019-03-21 新增统计订单功能
     * @param date
     * @return
     */
    int selectCountFinishOrderByTime(@Param("date")String date);

    /**
     * 查出 某个货车司机某段时间的全部订单
     * @param startDate
     * @param endDate
     * @param appUserId
     * @return
     */
    List<AppCarOrder> getAppUserCarStatistics(@Param("startDate")String startDate,
                                                  @Param("endDate") String endDate, @Param("appUserId")int appUserId);
}
