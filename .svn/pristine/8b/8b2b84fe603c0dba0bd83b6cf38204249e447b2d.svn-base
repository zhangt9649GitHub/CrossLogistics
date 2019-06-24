package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.model.dto.TruckOrderDto;

import java.util.List;

public interface CarOrderService {
    /**
     * App任务订单列表
     * @param truckOrderDto
     * @return
     */
    List<TaskOrder> selectTaskOrderAll(TruckOrderDto truckOrderDto);

    /**
     * App任务订单列表条数
     * @param truckOrderDto
     * @return
     */
    Integer countTaskOrderAll(TruckOrderDto truckOrderDto);

    /**
     * 根据区域查询小车集结点
     * @param singaporeAreaId
     * @return
     */
    List<RallyPoint> selectRallyPointBySingaporeArea(int singaporeAreaId);

    /**
     * 修改订单派送人
     * @param taskOrderId
     * @param appUserId
     * @return
     */
    Integer editCarOrderUser(int taskOrderId, int appUserId);

    /**
     * 查询所有未在派送的小车用户
     * @return
     */
    List<AppUser> selectAppUser(String createTime);

    /**
     * 根据订单id查询当前订单的派送人信息
     * @param taskOrderId
     * @return
     */
    AppUser selectAppUser(int taskOrderId);

    /**
     * 根据订单id查询当前订单送货小车
     * @param taskOrderId
     * @return
     */
    Car selectCarByCarOrder(int taskOrderId);

    /**
     * 查询当前时间没有订单的小车
     * @return
     */
    List<Car> selectCarNoCarOrder(String createTime);

    /**
     * 修改订单送货小车
     * @param taskOrderId
     * @param newCarId
     * @return
     */
    Integer editCarOrderCar(int taskOrderId, int newCarId);

    /**
     * 根据订单id查询订单时间
     * @param taskOrderId
     * @return
     */
    String selectCarOrderTime(int taskOrderId);



    /**
     * 根据时间查出所有小车订单 张占伟
     * @param date
     * @return
     */
    int selectCountOrderByTime(String date);

    /**
     * 根据时间查出完成小车订单个数  张占伟
     * @param date
     * @return
     */
    int selectCountFinishOrderByTime(String date);


    /**
     *  获取某位骑手获取某段时间所有的订单
     * @param startDate
     * @param endDate
     * @param appUserId
     * @return
     */
    List<AppCarOrder> getAppUserCarStatistics(String startDate, String endDate, int appUserId);
}
