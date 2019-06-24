package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.CarLockMapper;
import com.siruiman.crosslogistics.model.CarLock;
import com.siruiman.crosslogistics.model.dto.CarLockDto;
import com.siruiman.crosslogistics.service.CarLockService;
import com.siruiman.crosslogistics.util.LockUtils;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CarLockServiceImpl implements CarLockService {

    @Autowired
    private CarLockMapper carLockMapper;
    @Override
    public List<CarLock> selectCarLockByCarId(int carId) {
        try{
            return carLockMapper.selectCarLockByCarId(carId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insertCarLock(CarLock carLock) {
        try{
             carLockMapper.insert(carLock);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public CarLock selectCarLockByLockNumber(String lockNumber) {
        try{
            return carLockMapper.selectCarLockByLockNumber(lockNumber);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateCarLock(CarLock carLock) {
        try{
            carLockMapper.updateByPrimaryKey(carLock);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<CarLock> selectCarLock(int carId, int lockPosition) {
        try{
            return carLockMapper.selectCarLock(carId,lockPosition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CarLock selectCarLockById(int lockId) {
        try{
            return carLockMapper.selectByPrimaryKey(lockId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateUnLockState(CarLock lock) {
        carLockMapper.updateUnLockState(lock);
    }

    @Override
    public List<CarLock> selectVacancyCarLock(int lockPostion, byte status) {
        try{
            return carLockMapper.selectVacancyCarLock(lockPostion,status);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CarLock selectCarIdByLockNumber(String lockNumber) {
        try{
            return carLockMapper.selectCarIdByLockNumber(lockNumber);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateLockLatLng(CarLock lock) {
        carLockMapper.updateLockLatLng(lock);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateLockStationNum(CarLock lock) {
        carLockMapper.updateLockStationNum(lock);
    }

    @Override
    public List<CarLock> getList(String lockNumber, Byte status) {
        return carLockMapper.getList(lockNumber,status);
    }

    @Override
    public int getCount(String lockNumber, Byte status) {
        return carLockMapper.getCount(lockNumber,status);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateCarNumberAndPosition(CarLock carLock) {
        carLockMapper.updateCarNumberAndPosition(carLock);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void scrapLock(Integer lockId) {
        carLockMapper.scrapLock(lockId,new Date());

    }

    @Override
    public void repairLock(Integer lockId) {
        carLockMapper.repairLock(lockId,new Date());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void add(CarLockDto lockDto) {
        carLockMapper.addLock(lockDto);
    }

    @Override
    public boolean checkNumber(String lockNumber,Integer lockId) {
        if (lockId!=0){
            try {
                CarLock lock =    carLockMapper.selectCarLockByLockNumberORId(lockNumber,lockId);

                if(lock!=null){
                    return true;
                }else{
                    return false;
                }
            }catch (Exception e){
                return false;
            }
        } else{
            try {
                CarLock lock =    carLockMapper.selectCarLockByLockNumber(lockNumber);
                if(lock!=null){
                    return false;
                }else{
                    return true;
                }
            }catch (Exception e){
                return false;
            }
        }
    }

    @Override
    public void unLock(String lockNumber)throws Exception {
        LockUtils.unLock(lockNumber);
    }
}
