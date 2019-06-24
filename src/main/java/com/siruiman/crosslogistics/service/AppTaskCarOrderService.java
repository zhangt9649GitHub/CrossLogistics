package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.AppTaskCarOrder;
import com.siruiman.crosslogistics.model.AppTaskCarOrderDetails;

import java.util.List;

public interface AppTaskCarOrderService {
    /**
     * 根据状态查询小车订单列表
     * @param type
     * @return
     */
    List<AppTaskCarOrder> selectAppTaskCarOrderByStatus(int type, int appUserId);

    /**
     * 根据状态查询小车订单列表条数
     * @param type
     * @return
     */
    Integer count(int type, int appUserId);

    /**
     * 小车任务订单详情
     * @param taskOrderId
     * @return
     */
    AppTaskCarOrderDetails selectAppTaskCarOrderDetails(int taskOrderId);
}
