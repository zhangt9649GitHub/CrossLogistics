package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.TaskOrderMapper;
import com.siruiman.crosslogistics.model.TaskOrder;
import com.siruiman.crosslogistics.model.TaskOrderDetail;
import com.siruiman.crosslogistics.model.dto.TaskOrderDto;
import com.siruiman.crosslogistics.service.TaskOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskOrderServiceImpl implements TaskOrderService {
    @Autowired
    private TaskOrderMapper taskOrderMapper;

    @Override
    public List<TaskOrder> selectTaskOrderAll(TaskOrderDto taskOrderDto) {
        try{
            return taskOrderMapper.selectTaskOrderAll(taskOrderDto);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer count(TaskOrderDto taskOrderDto) {
        try{
            return taskOrderMapper.count(taskOrderDto);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public TaskOrderDetail selectTaskOrderDetail(int taskOrderId) {
        try{
            return taskOrderMapper.selectTaskOrderDetail(taskOrderId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
