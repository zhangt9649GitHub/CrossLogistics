package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.AppTruckOrder;
import com.siruiman.crosslogistics.model.AppUser;
import com.siruiman.crosslogistics.model.dto.TruckOrderDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TruckOrderMapper {
    /**
     * 货车抢单大厅列表
     * @param truckOrderDto
     * @return
     */
    List<AppTruckOrder> selectAppTruckOrderAll(@Param("truckOrderDto") TruckOrderDto truckOrderDto);

    /**
     * 货车抢单大厅列表条数
     * @param truckOrderDto
     * @return
     */
    Integer countAppTruckOrderAll(@Param("truckOrderDto") TruckOrderDto truckOrderDto);

    /**
     * 查询货车送货司机
     * @param taskOrderId
     * @return
     */
    AppUser selectAppUser(@Param("taskOrderId") int taskOrderId);

    /**
     * 查询所有的货车用户
     * @return
     */
    List<AppUser> selectTruckAppUser();

    /**
     * 查询当前时间该用户是否存在订单
     * @param appUserId
     * @param createTime
     * @return
     */
    Integer selectCarOrderByTruckUser(@Param("appUserId") int appUserId, @Param("createTime") String createTime);

    /**
     * 修改货车订单送货人
     * @param taskOrderId
     * @param newAppUserId
     * @return
     */
    Integer editTruckOrderDriver(@Param("taskOrderId") int taskOrderId, @Param("newAppUserId") int newAppUserId, @Param("state") int state);

    /**
     * 查询当前订单的订单时间
     * @param taskOrderId
     * @return
     */
    String selectCarOrderTime(@Param("taskOrderId") int taskOrderId);


    /**
     * 查出 某个货车司机某段时间的全部订单
     * @param startDate
     * @param endDate
     * @param appUserId
     * @return
     */

    List<AppTruckOrder> getAppUserTruckStatistics(@Param("startDate")String startDate,
                                                  @Param("endDate") String endDate, @Param("appUserId")int appUserId);
}
