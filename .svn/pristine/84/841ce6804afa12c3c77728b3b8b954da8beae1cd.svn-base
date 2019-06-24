package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.PositionInfo;
import org.springframework.stereotype.Service;

@Service
public interface PositionInfoService {

    /**
     * 根据货车id查询货车实时位置信息
     *
     * @param truckId
     * @return
     */
    PositionInfo selectTruckPosition(Integer truckId);

    /**
     * 获取小车、货车实时定位信息
     *
     * @param positionInfo
     */
    void insertPositionInfo(PositionInfo positionInfo);

    /**
     * 根据小车id查询小车实时位置信息
     *
     * @param carId
     * @return
     */
    PositionInfo selectCarPosition(Integer carId);
}
