package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.AppUser;
import com.siruiman.crosslogistics.model.Car;
import com.siruiman.crosslogistics.model.CarTask;
import com.siruiman.crosslogistics.model.RallyPoint;
import com.siruiman.crosslogistics.model.dto.AddCarTaskDto;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CarTaskMapper {
    /**
     * 查询小车任务模板列表
     * @param search
     * @return
     */
    List<CarTask> selectCarTaskAll(@Param("search") String search, @Param("rallyPointId") int rallyPointId);

    /**
     * 查询列表条数
     * @param search
     * @return
     */
    Integer count(@Param("search") String search);

    /**
     * 查询当前小车是否被添加模板
     * @param carId
     * @return
     */
    /*Integer selectCarTaskByCarId(@Param("carId") int carId);*/

    /**
     * 添加小车任务模板
     * @param addCarTaskDto
     * @return
     */
    Integer addCarTask(@Param("addCarTaskDto") AddCarTaskDto addCarTaskDto);

    /**
     * 查询所有小车司机
     * @return
     */
    List<AppUser> selectAppUserByCar();

    /**
     * 查询已经有模板的小车用户
     * @return
     */
    List<Integer> selectAlreadyAppUser();

    /**
     * 查询编辑内容
     * @param carTaskId
     * @return
     */
    CarTask selectEditCarTask(@Param("carTaskId") int carTaskId);

    /**
     * 编辑小车模板
     * @param carTask
     * @return
     */
    Integer editCarTask(@Param("carTask") CarTask carTask);

    /**
     * 删除小车模板
     * @param carTaskId
     * @return
     */
    Integer delCarTask(@Param("carTaskId") int carTaskId, @Param("adminUid") int adminUid);

    /**
     * 修改状态
     * @param carTaskId
     * @param status
     * @return
     */
    Integer editStatus(@Param("carTaskId") int carTaskId, @Param("status") int status, @Param("adminUid") int adminUid);

    /**
     * 查询所有小车
     * @return
     */
    List<Car> selectCarAll();

    /**
     * 查询已有任务模板的小车id
     * @return
     */
    List<Integer> selectAlreadyCarId();

    /**
     * 根据小车模板名车查询小车模板
     * @param carTaskName
     * @return
     */
    Integer selectCarTaskByCarTaskName(@Param("carTaskName") String carTaskName);

    /**
     * 查询所有小车集结点
     * @return
     */
    List<RallyPoint> selectRallyPointAll();

    /**
     * 查询当前集结点小车数量
     * @param rallyPointId
     * @return
     */
    Integer countRallyPointCarNum(@Param("rallyPointId") int rallyPointId);

    /**
     * 查询当前集结点已有模板数量
     * @param rallyPointId
     * @return
     */
    Integer countRallyPointCarTaskNum(@Param("rallyPointId") int rallyPointId);
}
