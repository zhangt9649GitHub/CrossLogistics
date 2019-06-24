package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.CarMaintenance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CarMaintenanceMapper {

    int deleteByPrimaryKey(Integer carMaintenanceId);


    int insert(CarMaintenance record);


    CarMaintenance selectByPrimaryKey(Integer carMaintenanceId);


    List<CarMaintenance> selectAll(CarMaintenance maintenance);


    Integer getCountMaintenance(CarMaintenance maintenance);

    CarMaintenance selectCarMaintenanceById(int carMaintenanceId);

    void updateCarMaintenance(@Param(value = "carMaintenance") CarMaintenance carMaintenance);

    List<String> getCarMaintenanceNumber();

    /**
     * 维修成功
     * @param carMaintenance
     */
    void updateCarMaintenanceEnd(@Param(value = "carMaintenance")CarMaintenance carMaintenance);

    /**
     * 小车报废维修失败
     * @param carMaintenance
     */
    void updateCarMaintenanceFail(@Param(value = "carMaintenance")CarMaintenance carMaintenance);
}