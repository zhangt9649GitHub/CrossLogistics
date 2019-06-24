package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.model.dto.EditTruckTaskDto;
import com.siruiman.crosslogistics.model.dto.TruckTaskDto;
import com.siruiman.crosslogistics.model.dto.TruckTaskRouteDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TruckTaskMapper {
    /**
     * 货车任务模板列表
     * @param search
     * @return
     */
    List<TruckTask> selectTruckTaskAll(@Param("search") String search);

    /**
     * 查询货车司机姓名
     * @param truckDriverId
     * @return
     */
    String driverName(@Param("truckDriverId") int truckDriverId);

    /**
     * 列表条数
     * @param search
     * @return
     */
    Integer count(@Param("search") String search);

    /**
     * 添加货车任务
     * @param truckTaskDto
     * @return
     */
    Integer addTruckTask(@Param("truckTaskDto") TruckTaskDto truckTaskDto);

    /**
     * 验证是否重名
     * @param truckTaskName
     * @return
     */
    Integer selectTruckTaskByTruckTaskName(@Param("truckTaskName") String truckTaskName);

    /**
     * 添加货车任务路线
     * @param truckTaskId
     * @param truckTaskRouteDto
     * @return
     */
    Integer addTruckTaskRoute(@Param("truckTaskId") int truckTaskId, @Param("truckTaskRouteDto") TruckTaskRouteDto truckTaskRouteDto);

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
    List<RallyPoint> selectRallyPointBySA(@Param("singaporeAreaId") int singaporeAreaId);

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
    Integer deleteTruckTask(@Param("truckTaskId") int truckTaskId);

    /**
     * 查询模板详情
     * @param truckTaskId
     * @return
     */
    TruckTaskDetails selectTruckTaskDetails(@Param("truckTaskId") int truckTaskId);

    /**
     * 查询司机姓名
     * @param driverId
     * @return
     */
    String selectUserName(@Param("driverId") int driverId);

    /**
     * 查询货车模板路线
     * @param driverId
     * @return
     */
    List<TruckTaskRouteDto> selectTruckTaskRoute(@Param("truckTaskId") int truckTaskId);

    /**
     * 根据小车集结点id查询 集结点名称和经纬度
     * @param routeId
     * @return
     */
    TruckTaskRoute selectTruckTaskRouteByCar(@Param("routeId") int routeId);

    /**
     * 根据仓库id查询 仓库名称和经纬度
     * @param routeId
     * @return
     */
    TruckTaskRoute selectTruckTaskRouteByWarehouse(@Param("routeId") int routeId);

    /**
     * 禁用启用 货车模板
     * @param truckTaskId
     * @param status
     * @return
     */
    Integer editTruckTaskStatus(@Param("truckTaskId") int truckTaskId, @Param("status") int status);

    /**
     * 查询编辑内容
     * @param truckTaskId
     * @return
     */
    EditTruckTask selectEditTruckTask(@Param("truckTaskId") int truckTaskId);

    /**
     * 修改货车模板信息
     * @param editTruckTaskDto
     * @return
     */
    Integer editTruckTask(@Param("editTruckTaskDto") EditTruckTaskDto editTruckTaskDto);

    /**
     * 删除路线
     * @param truckTaskId
     * @return
     */
    Integer delTruckTaskRoute(@Param("truckTaskId") int truckTaskId);

    /**
     * 根据货车模板id查询所属区域id
     * @param truckTaskId
     * @return
     */
    TruckTaskDto selectSingaporeAreaById(@Param("truckTaskId") int truckTaskId);
}
