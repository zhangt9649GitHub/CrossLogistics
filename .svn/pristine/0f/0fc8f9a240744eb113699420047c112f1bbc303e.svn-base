package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.Warehouse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WarehouseService {


    /**
     * 获取所有的仓库
     * @return
     */
    List<Warehouse> getAll(Warehouse warehouse);

    /**
     * 所有的仓库的个数分页使用
     * @return
     */
    int getCountWarehouse(Warehouse warehouse);


    /**
     * 添加仓库
     * @param warehouse
     * @return
     */
    int insert(Warehouse warehouse);


    /**
     * 查询仓库用仓库id
     * @param warehouseId
     * @return
     */
    Warehouse selectById(int warehouseId);


    /**
     * 修改仓库根据id
     * @param warehouse
     */
    void updateWarehouse(Warehouse warehouse);

    /**
     * 查询所有仓库返回仓库名字和id作为下拉选使用
     * @return
     */

    List<Warehouse> getWarehouse();


    /**
     * 仓库逻辑删除
      * @param warehouseId
     */
    void deleteWarehouseByid(int warehouseId);


    /**
     * 根据仓库编号 查询仓库id
     * @param warehouseNumber
     * @return
     */
    int selectWarehouseIdByWarehouseNumber(String warehouseNumber);

    /**
     * 检查名字是否可用
     * @param warehouseId
     * @param warehouseName
     * @return
     */
    boolean checkWarehouseName(int warehouseId, String warehouseName);


    /**
     * 获取所有的仓库名和id
     * @return
     */
    List<Warehouse> selectAllNameAndId();
}
