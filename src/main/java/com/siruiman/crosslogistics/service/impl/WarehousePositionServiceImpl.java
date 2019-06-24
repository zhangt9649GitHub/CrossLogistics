package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.WarehousePositionsMapper;
import com.siruiman.crosslogistics.model.WarehousePositions;
import com.siruiman.crosslogistics.service.WarehousePositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class WarehousePositionServiceImpl implements WarehousePositionService {


    @Override
    public void deleteById(int wpId) {
        warehousePositionsMapper.deleteById(wpId);
    }



    @Autowired
    private WarehousePositionsMapper warehousePositionsMapper;

    @Override
    public void update(WarehousePositions warehousePositions) {
        warehousePositionsMapper.updateByPrimaryKey(warehousePositions);
    }

    @Override
    public int insert(WarehousePositions warehousePositions) {
        return warehousePositionsMapper.insert(warehousePositions);
    }

    @Override
    public WarehousePositions getByID(int wpId) {
        return warehousePositionsMapper.selectByPrimaryKey(wpId);
    }

    @Override
    public List<WarehousePositions> getAll(int warehouseId) {
        return warehousePositionsMapper.selectAllByWarehouseId(  warehouseId );
    }

    @Override
    public List<WarehousePositions> selectAll(WarehousePositions positions) {
        return warehousePositionsMapper.selectAll(positions);
    }

    @Override
    public int selectCount(WarehousePositions positions) {
        return warehousePositionsMapper.selectCount(positions);
    }

    @Override
    public int getCountByWarehouseId(int warehouseId) {
        return warehousePositionsMapper.selectCountByWarehouseId( warehouseId);
    }

    /**
     * 根据仓位编号查询仓位
     * @param wpNumber
     * @return
     * @author 郝腾
     */
    @Override
    public WarehousePositions selectWarehousePositionsbyWpNumber(String wpNumber) {
        try{
            return warehousePositionsMapper.selectWarehousePositionsbyWpNumber(wpNumber);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int selectWpIdByWpNumber(String wpNumber) {
        if (wpNumber!=null&&wpNumber!="") {
            return warehousePositionsMapper.selectWpIdByWpNumber(wpNumber);
        }else {
            return 0;
        }
    }

    @Override
    public WarehousePositions selectWarehousePositionsbySaId(Integer saId) {
        try{
            return warehousePositionsMapper.selectWarehousePositionsbySaId(saId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteByWarehouseId(int warehouseId) {
        warehousePositionsMapper.deleteByWarehouseId(warehouseId);
    }
}
