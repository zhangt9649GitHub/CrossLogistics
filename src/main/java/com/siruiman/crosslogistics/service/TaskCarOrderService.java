package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.TaskCarOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskCarOrderService {
    /**
     * 根据小车id获取订单号
     *
     * @param carId
     * @param state
     * @return
     */
    TaskCarOrder selectAppTaskCarOrderByCarId(int carId, int state);

    /**
     * 根据小车订单id获取小车id
     *
     * @param taskOrderId
     * @return
     */
    TaskCarOrder selectCarIdById(int taskOrderId);

    /**
     * 根据小车id和时间确认小车是否还存有订单
     *
     * @param carId
     * @param date
     * @return
     */
    List<TaskCarOrder> selectTaskCarOrderByCarId(Integer carId, String date);

    /**
     * 根据小车任务订单id查询此订单的状态
     *
     * @param taskOrderId
     * @return
     */
    TaskCarOrder selectTaskCarOrderById(int taskOrderId);

    /**
     * 根据时间查询所有订单
     *
     * @param time
     * @return
     */
    List<TaskCarOrder> selectTaskCarOrderByTime(String time);

    /**
     * 变更订单的状态为已结算
     */
    void updateState(int taskOrderId);
}
