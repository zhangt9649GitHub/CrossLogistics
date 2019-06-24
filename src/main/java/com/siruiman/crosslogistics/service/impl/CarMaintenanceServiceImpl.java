package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.CarMaintenanceMapper;
import com.siruiman.crosslogistics.mapper.CarMapper;
import com.siruiman.crosslogistics.model.Car;
import com.siruiman.crosslogistics.model.CarMaintenance;
import com.siruiman.crosslogistics.service.CarMaintenanceService;
import com.siruiman.crosslogistics.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author 张占伟
 * @date 2018/12/25 16:53
 */
@Service
public class CarMaintenanceServiceImpl implements CarMaintenanceService {

    @Autowired
    private CarMaintenanceMapper carMaintenanceMapper;

    @Autowired
    private  CarMapper carMapper;

    @Override
    public List<CarMaintenance> getCarMaintenanceList(CarMaintenance maintenance) {
        return carMaintenanceMapper.selectAll( maintenance);
    }

    @Override
    public int getCountMaintenance( CarMaintenance maintenance) {
        return carMaintenanceMapper.getCountMaintenance(maintenance);
    }

    @Override
    public CarMaintenance getCarMaintenanceById(int carMaintenanceId) {
        return carMaintenanceMapper.selectCarMaintenanceById(carMaintenanceId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCarMaintenance(CarMaintenance carMaintenance) {
//        维修中
        if (carMaintenance.getState()==2){
            carMaintenance.setCbmServicetime(new Date());
            carMaintenanceMapper.updateCarMaintenance(carMaintenance);
        }
//        维修成功
            Car car = new Car();
            car.setCarId(carMaintenance.getCarId());
            car.setUpdateTime(DateUtil.getDateTime());
        if (carMaintenance.getState()==3){
            carMaintenance.setCbmFinishtime(new Date());
            carMaintenanceMapper.updateCarMaintenanceEnd(carMaintenance);
            car.setState(1);
            car.setCarId(carMaintenance.getCarId());
            carMapper.updateCar(car);
        }
//        维修失败
        if (carMaintenance.getState()==4){
            carMaintenanceMapper.updateCarMaintenanceFail(carMaintenance);
            car.setState(5);
//            小车修改为报废
            carMapper.updateCar(car);
        }

    }

    @Override
    public List<String> getCarMaintenanceNumber() {
        return carMaintenanceMapper.getCarMaintenanceNumber();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void add(CarMaintenance carMaintenance) {
        carMaintenance.setCbmAddtime(new Date());
        carMaintenanceMapper.insert(carMaintenance);
//        修改小车状态为小车维护中
        Car car = new Car();
        car.setUpdateTime(DateUtil.getDateTime());
        car.setState(1);
        car.setCarId(carMaintenance.getCarId());
        car.setBagNumber(carMaintenance.getCarNumber());
        carMapper.updateCar(car);
    }
}
