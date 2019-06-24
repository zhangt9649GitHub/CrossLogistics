package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.model.dto.EditTruckTaskDto;
import com.siruiman.crosslogistics.model.dto.TruckTaskDto;

import java.util.List;

public interface TruckTaskService {
    /**
     * 货车任务模板列表
     * @param search
     * @return
     */
    List<TruckTask> selectTruckTaskAll(String search);

    /**
     * 列表条数
     * @param search
     * @return
     */
    Integer count(String search);

    /**
     * 添加货车任务
     * @param truckTaskDto
     * @return
     */
    Integer addTruckTask(TruckTaskDto truckTaskDto);

    /**
     * 查询区域列表
     * @return
     */
    List<SingaporeArea> selectSingaporeAreaAll();

    /**
     * 根据区域id查询当前区域所有小车集结点
     * @param singaporeAreaId
     * @return
     */
    List<RallyPoint> selectRallyPointBySA(int singaporeAreaId);

    /**
     * 查询所有货车司机
     * @return
     */
    List<AppUser> selectAppUserByTruck();

    /**
     * 查询所有仓库
     * @return
     */
    List<Warehouse> selectWarehouseAll();

    /**
     * 删除货车任务模板
     * @param truckTaskId
     * @return
     */
    Integer deleteTruckTask(int truckTaskId);

    /**
     * 查询模板详情
     * @param truckTaskId
     * @return
     */
    TruckTaskDetails truckTaskDetails(int truckTaskId);

    /**
     * 禁用启用 货车模板
     * @param truckTaskId
     * @param status
     * @return
     */
    Integer editTruckTaskStatus(int truckTaskId, int status);

    /**
     * 查询编辑内容
     * @param truckTaskId
     * @return
     */
    EditTruckTask selectEditTruckTask(int truckTaskId);

    /**
     * 修改货车模板信息
     * @param editTruckTaskDto
     * @return
     */
    Integer editTruckTask(EditTruckTaskDto editTruckTaskDto);

    /**
     * 根据货车模板id查询所属区域id
     * @param truckTaskId
     * @return
     */
    TruckTaskDto selectSingaporeAreaById(int truckTaskId);
}
