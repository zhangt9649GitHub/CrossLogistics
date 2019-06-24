package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.TaskTruckOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskTruckOrderService {
    /**
     * 根据用户id获取货车订单编号
     *
     * @param appUserId
     * @return
     */
    TaskTruckOrder selectTaskTruckOrderByUserId(int appUserId);

    /**
     * 根据时间查询今日订单
     *
     * @param createTime
     * @return
     */
    List<TaskTruckOrder> selectTaskTruckOrderByTime(String createTime);

}
