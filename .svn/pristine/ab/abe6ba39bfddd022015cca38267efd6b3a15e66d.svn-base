package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.TaskOrder;
import com.siruiman.crosslogistics.model.TaskOrderDetail;
import com.siruiman.crosslogistics.model.dto.TaskOrderDto;

import java.util.List;

public interface TaskOrderService {
    /**
     * 任务订单列表
     * @return
     */
    List<TaskOrder> selectTaskOrderAll(TaskOrderDto taskOrderDto);

    /**
     * 任务订单条数
     * @return
     */
    Integer count(TaskOrderDto taskOrderDto);

    /**
     * 查询任务订单详情
     * @param taskOrderId
     * @return
     */
    TaskOrderDetail selectTaskOrderDetail(int taskOrderId);
}
