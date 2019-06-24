package com.siruiman.crosslogistics.service;


import com.siruiman.crosslogistics.model.CarMaintenance;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 张占伟
 * @date 2018/12/25 16:49
 */
@Service
public interface CarMaintenanceService {

    /**
     * 获取小车维修列表
     * @return
     */
    List<CarMaintenance> getCarMaintenanceList( CarMaintenance maintenance);

    /**
     * 获取小车维修个数
     *      分页
     * @return
     */
    int getCountMaintenance(CarMaintenance maintenance);

    /**
     * 获取小车维修详情根据维护id
     * @param carMaintenanceId
     * @return
     */
    CarMaintenance getCarMaintenanceById(int carMaintenanceId);

    /**
     * 修改修车维修状态
     * @param carMaintenance
     */
    void updateCarMaintenance(CarMaintenance carMaintenance);


    /**
     * 获取需要维护的小车编号集合
     * @return
     */
    List<String> getCarMaintenanceNumber();

    /**
     * 添加小车保修
     * @param carMaintenance
     */
    void add(CarMaintenance carMaintenance);
}
