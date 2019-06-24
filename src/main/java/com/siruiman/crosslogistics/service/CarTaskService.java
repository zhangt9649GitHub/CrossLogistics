package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.AppUser;
import com.siruiman.crosslogistics.model.Car;
import com.siruiman.crosslogistics.model.CarTask;
import com.siruiman.crosslogistics.model.RallyPoint;
import com.siruiman.crosslogistics.model.dto.AddCarTaskDto;

import java.util.List;

public interface CarTaskService {
    /**
     * 查询小车任务模板列表
     * @param search
     * @return
     */
    List<CarTask> selectCarTaskAll(String search, int rallyPointId);

    /**
     * 查询列表条数
     * @param search
     * @return
     */
    Integer count(String search);

    /**
     * 添加下车任务模板
     * @param addCarTaskDto
     * @return
     */
    Integer addCarTask(AddCarTaskDto addCarTaskDto);

    /**
     * 查询所有小车司机
     * @return
     */
    List<AppUser> selectAppUserByCar();

    /**
     * 查询编辑内容
     * @param carTaskId
     * @return
     */
    CarTask selectEditCarTask(int carTaskId);

    /**
     * 编辑小车模板
     * @param carTask
     * @return
     */
    Integer editCarTask(CarTask carTask);

    /**
     * 删除小车模板
     * @param carTaskId
     * @return
     */
    Integer delCarTask(int carTaskId, int adminUid);

    /**
     * 修改状态
     * @param carTaskId
     * @param status
     * @return
     */
    Integer editStatus(int carTaskId, int status, int adminUid);

    /**
     * 查询所有小车集结点
     * @return
     */
    List<RallyPoint> selectRallyPointAll();

    /**
     * 验证当前集结点模板数量
     * @param rallyPointId
     * @return
     */
    Integer checkRallyPointCarTask(int rallyPointId);
}
