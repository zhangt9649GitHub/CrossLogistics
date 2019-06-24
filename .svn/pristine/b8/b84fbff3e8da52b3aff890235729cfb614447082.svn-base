package com.siruiman.crosslogistics.service;


import com.siruiman.crosslogistics.model.WarehousePositions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WarehousePositionService {


    /**
     *
     * 根据仓库id查询仓库所有仓位
     *
     */
    List<WarehousePositions> getAll(int warehouseId);

    /**
     * 根据仓库id查询所有仓位个数
     * @param warehouseId
     * @return
     */
    int getCountByWarehouseId(int warehouseId);

    /**
     * 根据仓位id查询出仓位
     * @param wpId
     * @return
     */
    WarehousePositions getByID(int wpId);

    /**
     * x修改仓位
     * @param warehousePositions
     */
    void update(WarehousePositions warehousePositions);

    /**
     * 新增仓位
     * @param warehousePositions
     * @return
     */
    int insert(WarehousePositions warehousePositions);

    /**
     * 查询出所有的仓位和他所在的仓库名字
     * @return
     */
    List<WarehousePositions> selectAll(WarehousePositions positions);

    /**
     * 查询出所有的仓位的个数用来分页
     * @return
     */
    int selectCount(WarehousePositions positions);

    /**
     * 根据仓位id做逻辑删除
     * @param wpId
     */
    void deleteById(int wpId);

    /**
     *根据仓位编号查询仓位
     */
    WarehousePositions selectWarehousePositionsbyWpNumber(String wpNumber);

    /**
     * 查询仓位id根据仓库编号
     * @param wpNumber
     * @return
     */
    int selectWpIdByWpNumber(String wpNumber);

    /**
     * 根据区域id获取仓位
     * @param saId
     * @return
     */
    WarehousePositions selectWarehousePositionsbySaId(Integer saId);

    /**
     * 删除一个仓库下的所有仓位
     * @param warehouseId
     */
    void deleteByWarehouseId(int warehouseId);
}
