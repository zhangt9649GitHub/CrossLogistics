package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.CarMapper;
import com.siruiman.crosslogistics.mapper.GoodsDetailsMapper;
import com.siruiman.crosslogistics.model.Bag;
import com.siruiman.crosslogistics.model.Car;
import com.siruiman.crosslogistics.model.GoodsDetails;
import com.siruiman.crosslogistics.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 张占伟
 * @date 2018/12/21
 */
@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarMapper carMapper;
    @Autowired
    private GoodsDetailsMapper goodsDetailsMapper;

    @Override
    public int selectCount(Car car) {

        return carMapper.selectCountCar(car);
    }

    @Override
    public List<Car> selectAll(Car car) {

        return carMapper.selectAll(car);
    }

    @Override
    public void updateCar(Car car) {

        carMapper.updateByPrimaryKey(car);
    }

    @Override
    public Car selectCarById(Integer goodsId) {
        try {
            return carMapper.selectCarById(goodsId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addCar(Car car) {
        carMapper.insert(car);
    }


    @Override
    public Car selectCarDetail(int carId) {
        return carMapper.selectCarDetail(carId);
    }

    @Override
    public List<GoodsDetails> selectGoodsDetailsByBagId(int bagId) {
        return goodsDetailsMapper.selectGoodsDetailsByBagID(bagId);
    }


    @Override
    public Car selectLocationByCarId(Integer carId) {
        return carMapper.selectByPrimaryKey(carId);
    }

    @Override
    public int selectCountGoodsDetailsByBagId(int bagId) {
        return goodsDetailsMapper.selectCountGoodsDetailsByBagID(bagId);
    }

    @Override
    public List<Car> selectCarInfoList(String carNumber, Integer state, Integer singaporeAreaId, Integer rallyPointId) {
        try {
            return carMapper.selectCarInfoList(carNumber, state, singaporeAreaId, rallyPointId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int selectCountCarInfoList(String carNumber, Integer state, Integer singaporeAreaId, Integer rallyPointId) {
        try {
            return carMapper.selectCountCarInfoList(carNumber, state, singaporeAreaId, rallyPointId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Car selectCarDetailsById(Integer carId) {
        try {
            return carMapper.selectCarDetailsById(carId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertCar(Car car) {
        carMapper.insert(car);

    }

    @Override
    public int selectCarLastId() {
        try {
            return carMapper.selectCarLastId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Car> selectCarList(String carNumber, int state, String bagNumber) {
        try {
            return carMapper.selectCarList(carNumber, state, bagNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int selectCountCarList(String carNumber, int state, String bagNumber) {
        try {
            return carMapper.selectCountCarList(carNumber, state, bagNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteCarById(int carId) {
        Car car = carMapper.selectByPrimaryKey(carId);
        car.setDisableState(2);
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        car.setUpdateTime(dateStr);
        carMapper.updateByPrimaryKey(car);
    }


    @Override
    public List<Car> selectByCarNumber(String carNumber) {
        return carMapper.selectByCarNumber(carNumber);
    }

    @Override
    public int selectCarStateByNum(String carNumber) {
        return carMapper.selectCarStateByNum(carNumber);
    }
}
