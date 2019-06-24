package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.Warehouse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface WarehouseMapper {
    int deleteByPrimaryKey(Integer warehouseId);

    int insert(Warehouse record);

    Warehouse selectByPrimaryKey(Integer warehouseId);

    List<Warehouse> selectAll(Warehouse warehouse);

    int updateByPrimaryKey(Warehouse record);

//    查询所有的仓库个数
    int getCountWarehouse(Warehouse warehouse);

    List<Warehouse> selectWarehouse();

    void deleteById(int warehouseId);

    /**
     * 查询仓库id根据仓库编号
     * @param warehouseNumber
     * @return
     */
    int selectWarehouseIdByWarehouseNumber(@Param("warehouseNumber") String warehouseNumber);

    /**
     * 根据仓库名字查出仓库id
     * @param warehouseName
     * @return
     */
    int selectIdByWarehouseName(String warehouseName);

    /**
     * 查出所有的仓库名和id
     * @return
     */
    List<Warehouse> selectAllNameAndId();
}