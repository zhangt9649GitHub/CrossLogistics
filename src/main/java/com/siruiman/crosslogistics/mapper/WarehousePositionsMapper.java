package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.WarehousePositions;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface WarehousePositionsMapper {

    int deleteByPrimaryKey(Integer wpId);

    int insert(WarehousePositions record);

    WarehousePositions selectByPrimaryKey(Integer wpId);

    List<WarehousePositions> selectAllByWarehouseId(int warehouseId);

    int updateByPrimaryKey(WarehousePositions record);



//    查询一个仓库所有仓位个数
    int selectCountByWarehouseId(int warehouseId);

    List<WarehousePositions> selectAll(WarehousePositions positions);

    int selectCount(WarehousePositions positions);

    void deleteById(int wpId);

    /**
     * 根据仓位编号查询仓位
     * @param wpNumber
     * @return
     * @author 郝腾
     */
    WarehousePositions selectWarehousePositionsbyWpNumber(@Param("wpNumber") String wpNumber);

    /**
     * 查询仓位id根据仓位编号
     * @param wpNumber
     * @return
     */
    int selectWpIdByWpNumber(String wpNumber);

    /**
     * 根据区域id获取仓位
     * @param saId
     * @return
     */
    WarehousePositions selectWarehousePositionsbySaId(@Param("saId") Integer saId);

    /**
     * 删除一个仓库下所有的仓位
     * @param warehouseId
     */
    void deleteByWarehouseId(int warehouseId);
}