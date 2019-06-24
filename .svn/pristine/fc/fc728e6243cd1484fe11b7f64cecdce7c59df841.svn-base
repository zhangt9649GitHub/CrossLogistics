package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.Truck;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface TruckMapper {

    int deleteByPrimaryKey(Integer truckId);


    int insert(Truck record);


    Truck selectByPrimaryKey(Integer truckId);


    List<Truck> selectAll();


    int updateByPrimaryKey(Truck record);

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
     * @param map
     * @return
     */
    List<Truck> selectTruckList(HashMap<String, Object> map);

    /**
     * 获取货车列表总行数
     *
     * @param map
     * @return
     */
    int selectCountTruckList(HashMap<String, Object> map);

    /**
     * 根据货袋id查询货袋所关联大货车详情
     * 张占伟
     *
     * @param bagId
     * @return
     */
    Truck selectBagDetailedOfTruckById(@Param("bagId") int bagId);

    /**
     * 根据货车id获取货车详情
     *
     * @param truckId
     * @return
     */
    Truck selectTruckDetailsById(@Param("truckId") Integer truckId);


    /**
     * 查询大货车id
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
    Truck selectTruckDetailsByBag(@Param("licensePlate") String licensePlate);

}