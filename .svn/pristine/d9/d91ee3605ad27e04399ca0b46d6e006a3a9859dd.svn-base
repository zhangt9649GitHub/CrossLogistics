package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.WarehouseMapper;
import com.siruiman.crosslogistics.model.Warehouse;
import com.siruiman.crosslogistics.service.WarehouseService;
import org.apache.ibatis.binding.BindingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class WarehouseServiceImpl  implements WarehouseService {

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Override
    public List<Warehouse> getWarehouse() {
        return warehouseMapper.selectWarehouse();
    }

    @Override
    public void updateWarehouse(Warehouse warehouse) {
        warehouseMapper.updateByPrimaryKey(warehouse);
    }

    @Override
    public int getCountWarehouse(Warehouse warehouse) {
        return warehouseMapper.getCountWarehouse(warehouse);
    }

    @Override
    public Warehouse selectById(int warehouseId) {
        return warehouseMapper.selectByPrimaryKey(warehouseId);
    }

    @Override
    public int insert(Warehouse warehouse) {
        return warehouseMapper.insert(warehouse);
    }

    @Override
    public List<Warehouse> getAll(Warehouse warehouse) {
        return warehouseMapper.selectAll(warehouse );
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteWarehouseByid(int warehouseId) {
        warehouseMapper.deleteById(warehouseId);
    }

    @Override
    public int selectWarehouseIdByWarehouseNumber(String warehouseNumber) {
        return warehouseMapper.selectWarehouseIdByWarehouseNumber(warehouseNumber);
    }

    @Override
    public boolean checkWarehouseName(int warehouseId, String warehouseName) {
        //        如果id存在的情况下
        if (warehouseId > 0) {

            try {
                int id = warehouseMapper.selectIdByWarehouseName(warehouseName);
//                没有修改名字就可以使用
                if (id == warehouseId) {
                    return true;
                }
//                如果名字存在查出来id与本身id不同就不可用
                if (id!=warehouseId){
                    return false;
                }
                //查出来为空就可以使用
            } catch (BindingException e) {
                return true;
            }
        } else {
//            id不存在查询
            try {
                warehouseMapper.selectIdByWarehouseName(warehouseName);
//            查不出来就可用
            } catch (BindingException e) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Warehouse> selectAllNameAndId() {
        return warehouseMapper.selectAllNameAndId();
    }
}
