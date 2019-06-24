package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.TaskOrderBagMapper;
import com.siruiman.crosslogistics.model.TaskOrderBag;
import com.siruiman.crosslogistics.service.TaskOrderBagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 张占伟
 * @date 2019/1/13 20:39
 */
@Service
public class TaskOrderBagServiceImpl implements TaskOrderBagService {

    @Autowired
    private TaskOrderBagMapper taskOrderBagMapper;


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertTruckBag(int bagId, int taskOrderId) {
        taskOrderBagMapper.insertTruckBag(bagId,taskOrderId);
    }
}
