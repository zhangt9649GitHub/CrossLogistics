package com.siruiman.crosslogistics.service;


import com.siruiman.crosslogistics.model.Bag;
import com.siruiman.crosslogistics.model.Car;
import com.siruiman.crosslogistics.model.GoodsDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {

    /**
     * 查询所有的小车
     *
     * @return
     */
    List<Car> selectAll(Car car);

    /**
     * 查询所有的小车个数
     *
     * @return
     */
    int selectCount(Car car);

    /**
     * 小车修改
     *
     * @param car
     */
    void updateCar(Car car);

    /**
     * 根据货物id查询小车信息
     */
    Car selectCarById(Integer goodsId);

    /**
     * 添加小车
     *
     * @param car
     */
    void addCar(Car car);

    /**
     * 根据小车id查询小车明细
     * 货袋等
     *
     * @param carId
     * @return
     */
    Car selectCarDetail(int carId);

    /**
     * 根据货袋id查询货袋里的货物类型等详细信息
     *
     * @param bagId
     * @return
     */
    List<GoodsDetails> selectGoodsDetailsByBagId(int bagId);

    /**
     * 根据小车id查询小车定位
     *
     * @param carId
     * @return
     */
    Car selectLocationByCarId(Integer carId);

    /**
     * 根据货袋id查询出里边的货物个数
     *
     * @param bagId
     * @return
     */
    int selectCountGoodsDetailsByBagId(int bagId);

    /**
     * 获取小车管理列表
     *
     * @param carNumber
     * @param state
     * @return
     */
    List<Car> selectCarInfoList(String carNumber, Integer state, Integer singaporeAreaId, Integer rallyPointId);

    /**
     * 获取小车管理列表总条数
     *
     * @param carNumber
     * @param state
     * @return
     */
    int selectCountCarInfoList(String carNumber, Integer state, Integer singaporeAreaId, Integer rallyPointId);

    /**
     * 根据小车id货车小车详情
     *
     * @param carId
     * @return
     */
    Car selectCarDetailsById(Integer carId);

    /**
     * 添加小车
     *
     * @param car
     */
    void insertCar(Car car);

    /**
     * 获取刚刚插入数据的id
     *
     * @return
     */
    int selectCarLastId();

    /**
     * 根据条件获取小车信息
     *
     * @param carNumber
     * @param state
     * @param bagNumber
     * @return
     */
    List<Car> selectCarList(String carNumber, int state, String bagNumber);

    /**
     * 根据条件获取总条数
     *
     * @param carNumber
     * @param state
     * @param bagNumber
     * @return
     */
    int selectCountCarList(String carNumber, int state, String bagNumber);

    /**
     * 根据小车id将小车删除状态为删除
     *
     * @param carId
     */
    void deleteCarById(int carId);

    /**
     * 模糊查询小车编号
     *
     * @param carNumber
     * @return
     */
    List<Car> selectByCarNumber(String carNumber);

    /**
     * 根据小车编号查询小车状态
     *
     * @param carNumber
     * @return
     */
    int selectCarStateByNum(String carNumber);


}
