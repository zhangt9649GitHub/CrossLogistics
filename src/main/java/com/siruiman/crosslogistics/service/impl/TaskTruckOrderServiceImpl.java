package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.TaskTruckOrderMapper;
import com.siruiman.crosslogistics.model.TaskTruckOrder;
import com.siruiman.crosslogistics.service.TaskTruckOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TaskTruckOrderServiceImpl implements TaskTruckOrderService {
    @Autowired
    private TaskTruckOrderMapper taskTruckOrderMapper;

    @Override
    public TaskTruckOrder selectTaskTruckOrderByUserId(int appUserId) {
        try {
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateNowStr = sdf.format(d);
            String createTime = dateNowStr + " 00:00:00";
            return taskTruckOrderMapper.selectTaskTruckOrderByUserId(appUserId, createTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<TaskTruckOrder> selectTaskTruckOrderByTime(String createTime) {
            return taskTruckOrderMapper.selectTaskTruckOrderByTime(createTime);

    }
}
