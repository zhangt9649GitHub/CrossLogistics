package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.PositionInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PositionInfoMapper {

    int deleteByPrimaryKey(Integer piId);


    int insert(PositionInfo record);


    PositionInfo selectByPrimaryKey(Integer piId);


    List<PositionInfo> selectAll();


    int updateByPrimaryKey(PositionInfo record);

    /**
     * 根据货车id查询货车实时位置信息
     *
     * @param truckId
     * @return
     */
    PositionInfo selectTruckPosition(@Param("truckId") Integer truckId);

    /**
     * 根据小车id查询小车实时位置信息
     *
     * @param carId
     * @return
     */
    PositionInfo selectCarPosition(@Param("carId") Integer carId);

}