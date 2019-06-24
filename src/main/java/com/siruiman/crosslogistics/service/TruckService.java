package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.Truck;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TruckService {
    /**
     * 根据货物id查询货车信息
     *
     * @param goodsId
     * @return
     */
    Truck selectTruckById(Integer goodsId);

    /**
     * 获取货车列表
     *
     * @param truck
     * @return
     */
    List<Truck> selectTruckList(Truck truck);

    /**
     * 获取货车列表总行数
     *
     * @param truck
     * @return
     */
    int selectCountTruckList(Truck truck);

    /**
     * 根据货车id获取货车详情
     *
     * @param truckId
     * @return
     */
    Truck selectTruckDetailsById(Integer truckId);


    /**
     * 查询货车id
     *
     * @param appUserId
     * @return
     */
    int selectTruckIdByUId(int appUserId);

    /**
     * 根据牌照获取货车信息
     *
     * @param licensePlate
     * @return
     */
    Truck selectTruckDetailsByBag(String licensePlate);

    /**
     * 修改货车信息
     *
     * @param truck
     */
    void updateTruck(Truck truck);

}
