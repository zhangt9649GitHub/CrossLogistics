package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.TruckMapper;
import com.siruiman.crosslogistics.model.Truck;

import com.siruiman.crosslogistics.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
public class TruckServiceImpl implements TruckService {
    @Autowired
    private TruckMapper truckMapper;

    @Override
    public Truck selectTruckById(Integer goodsId) {
        try {
            return truckMapper.selectTruckById(goodsId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Truck> selectTruckList(Truck truck) {
        try {
            HashMap<String, Object> map = new HashMap<>();
            map.put("state", truck.getState());
            map.put("licensePlate", truck.getLicensePlate());
            map.put("mobile", truck.getMobile());
            return truckMapper.selectTruckList(map);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int selectCountTruckList(Truck truck) {
        try {
            HashMap<String, Object> map = new HashMap<>();
            map.put("state", truck.getState());
            map.put("licensePlate", truck.getLicensePlate());
            map.put("mobile", truck.getMobile());
            return truckMapper.selectCountTruckList(map);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Truck selectTruckDetailsById(Integer truckId) {
        try {
            return truckMapper.selectTruckDetailsById(truckId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public int selectTruckIdByUId(int appUserId) {
        return truckMapper.selectTruckIdByUId(appUserId);
    }

    @Override
    public Truck selectTruckDetailsByBag(String licensePlate) {
        try {
            return truckMapper.selectTruckDetailsByBag(licensePlate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateTruck(Truck truck) {
        truckMapper.updateByPrimaryKey(truck);
    }
}
