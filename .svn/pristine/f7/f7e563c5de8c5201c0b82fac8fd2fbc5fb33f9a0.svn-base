package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.AppTruckOrder;
import com.siruiman.crosslogistics.model.AppUser;
import com.siruiman.crosslogistics.model.dto.TruckOrderDto;

import java.util.List;

public interface TruckOrderService {
    /**
     * 货车抢单大厅列表
     * @param truckOrderDto
     * @return
     */
    List<AppTruckOrder> selectAppTruckOrderAll(TruckOrderDto truckOrderDto);

    /**
     * 货车抢单大厅列表条数
     * @param truckOrderDto
     * @return
     */
    Integer countAppTruckOrderAll(TruckOrderDto truckOrderDto);

    /**
     * 查询货车送货司机
     * @param taskOrderId
     * @return
     */
    AppUser selectAppUser(int taskOrderId);

    /**
     * 查询所有未在派送的货车用户
     * @return
     */
    List<AppUser> selectTruckAppUser(String createTime);

    /**
     * 修改货车订单送货人
     * @param taskOrderId
     * @param newAppUserId
     * @return
     */
    Integer editTruckOrderDriver(int taskOrderId, int newAppUserId);

    /**
     * 查出 某个货车司机某段时间的全部订单
     * @param startDate
     * @param endDate
     * @param appUserId
     * @return
     */
    List<AppTruckOrder> getAppUserTruckStatistics(String startDate, String endDate, int appUserId);
}
