package com.siruiman.crosslogistics.service.impl;


import com.siruiman.crosslogistics.mapper.TaskCarOrderMapper;
import com.siruiman.crosslogistics.model.TaskCarOrder;
import com.siruiman.crosslogistics.service.TaskCarOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskCarOrderServiceImpl implements TaskCarOrderService {
    @Autowired
    private TaskCarOrderMapper taskCarOrderMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public TaskCarOrder selectAppTaskCarOrderByCarId(int carId, int state) {
            return taskCarOrderMapper.selectAppTaskCarOrderByCarId(carId, state);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public TaskCarOrder selectCarIdById(int taskOrderId) {
            return taskCarOrderMapper.selectByPrimaryKey(taskOrderId);
    }

    @Override
    @Transactional( rollbackFor = Exception.class)
    public List<TaskCarOrder> selectTaskCarOrderByCarId(Integer carId, String date) {
            return taskCarOrderMapper.selectTaskCarOrderByCarId(carId, date);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TaskCarOrder selectTaskCarOrderById(int taskOrderId) {
            return taskCarOrderMapper.selectByPrimaryKey(taskOrderId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<TaskCarOrder> selectTaskCarOrderByTime(String time) {
            return taskCarOrderMapper.selectTaskCarOrderByTime(time);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateState(int taskOrderId) {
            taskCarOrderMapper.updateState(taskOrderId);
    }
}
