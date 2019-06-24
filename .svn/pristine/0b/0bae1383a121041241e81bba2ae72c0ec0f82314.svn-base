package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.AppTruckOrder;
import com.siruiman.crosslogistics.model.AppTruckOrderBagDetails;
import com.siruiman.crosslogistics.model.AppTruckOrderDetails;
import com.siruiman.crosslogistics.model.dto.AppTaskOrderDto;
import java.util.List;

public interface AppTruckOrderService {
    /**
     * 货车抢单大厅列表
     * @param appTruckOrderDto
     * @return
     */
    List<AppTruckOrder> selectAppTruckOrderAll(AppTaskOrderDto appTruckOrderDto);

    /**
     * 货车抢单大厅列表条数
     * @param appTruckOrderDto
     * @return
     */
    Integer count(AppTaskOrderDto appTruckOrderDto);

    /**
     * 查询货车订单详情
     * @param taskOrderId
     * @return
     */
    AppTruckOrderDetails selectAppTruckOrderDetails(int taskOrderId);

    /**
     * 查询货袋信息
     * @param bagId
     * @return
     */
    AppTruckOrderBagDetails selectAppTruckOrderBagDetails(int bagId);
}
