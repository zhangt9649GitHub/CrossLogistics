package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.TaskOrder;
import com.siruiman.crosslogistics.model.TaskOrderDetails;
import com.siruiman.crosslogistics.model.dto.AppTaskOrderDto;

import java.util.List;

public interface AppTaskOrderService {
    /**
     * App任务订单列表
     * @param appTaskOrderDto
     * @return
     */
    List<TaskOrder> selectTaskOrderAll(AppTaskOrderDto appTaskOrderDto);

    /**
     * App任务订单列表条数
     * @param appTaskOrderDto
     * @return
     */
    Integer count(AppTaskOrderDto appTaskOrderDto);

    /**
     * 查询订单详情
     * @param taskOrderId
     * @return
     */
    TaskOrderDetails selectTaskOrderDetails(int taskOrderId);
}
