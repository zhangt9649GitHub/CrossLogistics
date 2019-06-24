package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.TruckDriverOrderMapper;
import com.siruiman.crosslogistics.model.dto.TruckDriverOrder;
import com.siruiman.crosslogistics.service.TruckDriverOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 张占伟
 * @date 2019/1/9 15:47
 */
@Service
public class TruckDriverOrderServiceImpl implements TruckDriverOrderService {

    @Autowired
    private TruckDriverOrderMapper truckDriverOrderMapper;

    @Override
    public TruckDriverOrder selectByUserId(int appUserId,String date) {
        return truckDriverOrderMapper.selectByUserId(appUserId,date);
    }


    @Override
    public int selectTruckOrderIdByUId(int appUserId, String date) {
        return truckDriverOrderMapper.selectTruckOrderIdByUId(appUserId,date);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateTruckOrderState(TruckDriverOrder order) {
        truckDriverOrderMapper.updateTruckOrderState(order);
    }


    @Override
    public List<Integer> selectRallyPointIds(int taskOrderId) {
        return truckDriverOrderMapper.selectRallyPointIds(taskOrderId);
    }

    @Override
    public int selectCountOrderByTime(String date) {
        return truckDriverOrderMapper.selectCountOrderByTime(date);
    }

    @Override
    public int selectCountFinishOrderByTime(String date) {
        return truckDriverOrderMapper.selectCountOrderByTime(date);
    }

    @Override
    public TruckDriverOrder getUserByUID(int appUserId) {
        return truckDriverOrderMapper.getUserByUID(appUserId);
    }
}
