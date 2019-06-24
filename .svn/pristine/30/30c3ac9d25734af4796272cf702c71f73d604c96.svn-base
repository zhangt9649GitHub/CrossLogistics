package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.TruckPersonalOrderMapper;
import com.siruiman.crosslogistics.model.AppTruckOrder;
import com.siruiman.crosslogistics.service.TruckPersonalOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TruckPersonalOrderServiceImpl implements TruckPersonalOrderService {
    @Autowired
    private TruckPersonalOrderMapper truckPersonalOrderMapper;

    @Override
    public List<AppTruckOrder> selectAppTruckOrderAll(int appUserId, int orderStatus) {
        try{
            return truckPersonalOrderMapper.selectAppTruckOrderAll(appUserId, orderStatus);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer count(int appUserId, int orderStatus) {
        try{
            return truckPersonalOrderMapper.count(appUserId, orderStatus);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
