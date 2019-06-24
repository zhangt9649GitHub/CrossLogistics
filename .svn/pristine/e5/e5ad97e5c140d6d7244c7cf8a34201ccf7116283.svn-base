package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.CarTaskMapper;
import com.siruiman.crosslogistics.model.AppUser;
import com.siruiman.crosslogistics.model.Car;
import com.siruiman.crosslogistics.model.CarTask;
import com.siruiman.crosslogistics.model.RallyPoint;
import com.siruiman.crosslogistics.model.dto.AddCarTaskDto;
import com.siruiman.crosslogistics.service.CarTaskService;
import com.siruiman.crosslogistics.util.RandomCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarTaskServiceImpl implements CarTaskService {
    @Autowired
    private CarTaskMapper carTaskMapper;

    @Override
    public List<CarTask> selectCarTaskAll(String search, int rallyPointId) {
        return carTaskMapper.selectCarTaskAll(search, rallyPointId);
    }

    @Override
    public Integer count(String search) {
        return carTaskMapper.count(search);
    }

    @Override
    public Integer addCarTask(AddCarTaskDto addCarTaskDto) {
        try {
            /*查询当前添加小车模板名称是否存在*/
            Integer selectCarTaskByCarTaskName = carTaskMapper.selectCarTaskByCarTaskName(addCarTaskDto.getCarTaskName());
            if(selectCarTaskByCarTaskName > 0){
                return 2;
            }
            String number = "09" + RandomCodeUtil.getSixRandomCode();
            addCarTaskDto.setCarTaskNumber(number);
            return carTaskMapper.addCarTask(addCarTaskDto);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<AppUser> selectAppUserByCar() {
        try {
            List<AppUser> returnAppUserByCar = new ArrayList<>();
            List<AppUser> selectAppUserByCar = carTaskMapper.selectAppUserByCar();
            /*List<Integer> selectAlreadyAppUser = carTaskMapper.selectAlreadyAppUser();
            if(selectAlreadyAppUser.size() == 0){
                return selectAppUserByCar;
            }
            for(AppUser appUser : selectAppUserByCar){
                int appUserId = appUser.getAppUserId();
                for(int i = 0; i < selectAlreadyAppUser.size(); i++){
                    int userId = selectAlreadyAppUser.get(i);
                    if(appUserId == userId){
                        break;
                    }else{
                        if(i == (selectAlreadyAppUser.size()-1)){
                            returnAppUserByCar.add(appUser);
                        }
                    }
                }
            }*/
            return selectAppUserByCar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public CarTask selectEditCarTask(int carTaskId) {
        return carTaskMapper.selectEditCarTask(carTaskId);
    }

    @Override
    public Integer editCarTask(CarTask carTask) {
        return carTaskMapper.editCarTask(carTask);
    }

    @Override
    public Integer delCarTask(int carTaskId, int adminUid) {
        return carTaskMapper.delCarTask(carTaskId, adminUid);
    }

    @Override
    public Integer editStatus(int carTaskId, int status, int adminUid) {
        return carTaskMapper.editStatus(carTaskId, status, adminUid);
    }

    @Override
    public List<RallyPoint> selectRallyPointAll() {
        try {
            return carTaskMapper.selectRallyPointAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer checkRallyPointCarTask(int rallyPointId) {
        try {
            /*查询当前集结点小车数量*/
            Integer countRallyPointCarNum = carTaskMapper.countRallyPointCarNum(rallyPointId);
            /*查询当前集结点模板数量*/
            Integer countRallyPointCarTaskNum = carTaskMapper.countRallyPointCarTaskNum(rallyPointId);
            if(countRallyPointCarTaskNum >= countRallyPointCarNum){
                return 1;
            }
            return countRallyPointCarTaskNum;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*@Override
    public List<Car> selectCarAll() {
        List<Car> returnCarAll = new ArrayList<>();
        List<Car> selectCarAll = carTaskMapper.selectCarAll();
        List<Integer> selectAlreadyCarId = carTaskMapper.selectAlreadyCarId();
        for(Car car : selectCarAll){
            int carId = car.getCarId();
            for(int i = 0; i < selectAlreadyCarId.size(); i++){
                int alreadyCarId = selectAlreadyCarId.get(i);
                if(carId == alreadyCarId){
                    break;
                }else{
                    if(i == (selectAlreadyCarId.size()-1)){
                        returnCarAll.add(car);
                    }
                }
            }
        }
        return returnCarAll;
    }*/
}
