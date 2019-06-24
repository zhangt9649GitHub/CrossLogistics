package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.model.dto.AppTaskOrderDto;
import com.siruiman.crosslogistics.model.dto.AppTruckOrderDto;
import com.siruiman.crosslogistics.model.dto.TruckTaskRouteDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AppTruckOrderMapper {
    /**
     * 货车抢单大厅列表(默认)
     * @param appTaskOrderDto
     * @return
     */
    /*List<AppTruckOrder> selectDefaultAppTruckOrderAll(@Param("appTaskOrderDto") AppTaskOrderDto appTaskOrderDto);*/

    /**
     * 货车抢单大厅列表
     * @param appTaskOrderDto
     * @return
     */
    List<AppTruckOrder> selectAppTruckOrderAll(@Param("appTaskOrderDto") AppTaskOrderDto appTaskOrderDto);

    /**
     * 货车抢单大厅列表条数
     * @param appTaskOrderDto
     * @return
     */
    AppTaskOrder count(@Param("appTaskOrderDto") AppTaskOrderDto appTaskOrderDto);

    /**
     * 查询货车订单详情
     * @param taskOrderId
     * @return
     */
    AppTruckOrderDetails selectAppTruckOrderDetails(@Param("taskOrderId") int taskOrderId);

    /**
     * 查询货车订单路线
     * @param taskOrderId
     * @return
     */
    List<TruckTaskRouteDto> selectTruckTaskRoute(@Param("taskOrderId") int taskOrderId);

    /**
     * 查询订单货袋信息
     * @param taskOrderId
     * @return
     */
    List<AppTruckOrderBag> selectAppTruckOrderBagByTruckOrder(@Param("taskOrderId") int taskOrderId);

    /**
     * 查询货袋信息
     * @param bagId
     * @return
     */
    AppTruckOrderBagDetails selectAppTruckOrderBagDetails(@Param("bagId") int bagId);
}
