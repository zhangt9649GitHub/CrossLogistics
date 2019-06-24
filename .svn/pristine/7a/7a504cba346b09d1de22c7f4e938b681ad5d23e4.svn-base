package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.PositionInfoMapper;
import com.siruiman.crosslogistics.model.PositionInfo;
import com.siruiman.crosslogistics.service.PositionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PositionInfoServiceImpl implements PositionInfoService {

    @Autowired
    private PositionInfoMapper positionInfoMapper;
    @Override
    public PositionInfo selectTruckPosition(Integer truckId) {
        try{
            return positionInfoMapper.selectTruckPosition(truckId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void insertPositionInfo(PositionInfo positionInfo) {
            positionInfoMapper.insert(positionInfo);
    }

    @Override
    public PositionInfo selectCarPosition(Integer carId) {
        try{
            return positionInfoMapper.selectCarPosition(carId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
