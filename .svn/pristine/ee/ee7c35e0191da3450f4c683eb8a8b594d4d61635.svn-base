package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.Car;
import com.siruiman.crosslogistics.model.CarOrderUserCar;
import com.siruiman.crosslogistics.model.CarTask;
import com.siruiman.crosslogistics.model.RallyPointAndSingaporeArea;
import com.siruiman.crosslogistics.model.dto.AddCarOrderDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GenerateCarordersMapper {
    /**
     * 查询所有小车
     * @return
     */
    List<Car> selectCarAll();

    /**
     * 根据小车id查询小车模板
     * @param carId
     * @return
     */
    CarTask selectCarTask(@Param("carId") int carId);

    /**
     * 根据小车查询区域和集结点信息
     * @param carId
     * @return
     */
    RallyPointAndSingaporeArea selectRallyPointAndSingaporeArea(@Param("carId") int carId);

    /**
     * 根据模板生成小车订单
     * @param addCarOrderDto
     * @return
     */
    Integer addCarOrderByTask(@Param("addCarOrderDto") AddCarOrderDto addCarOrderDto);

    /**
     * 根据模板生成小车订单
     * @param addCarOrderDto
     * @return
     */
    Integer addCarOrder(@Param("addCarOrderDto") AddCarOrderDto addCarOrderDto);

    /**
     * 查询一下几天后的订单是否存在
     * @param fetureDate
     * @return
     */
    Integer countTimeOrder(@Param("fetureDate") String fetureDate, @Param("carTaskId") int carTaskId);

    /**
     * 根据小车集结点id和时间查询订单金额平均
     * @param rallyPointId
     * @param outTime
     * @return
     */
    Double selectAverageMoney(@Param("rallyPointId") int rallyPointId, @Param("outTime") String outTime);

    /**
     * 根据时间和小车id查询订单
     * @param fetureDate
     * @param carId
     * @return
     */
    CarOrderUserCar selectCarOrderUserCar(@Param("fetureDate") String fetureDate, @Param("carId") int carId);

    /**
     * 删除小车订单
     * @param taskOrderId
     * @return
     */
    Integer delCarOrder(@Param("taskOrderId") int taskOrderId);

    /**
     * 查询小车模板
     * @return
     */
    List<CarTask> selectCarTaskAll(@Param("rallyPointId") int rallyPointId);

    /**
     * 查询当前时间 当前小车集结点的 订单数
     * @param fetureDate
     * @param rallyPointId
     * @return
     */
    Integer countNowCarOrderNum(@Param("fetureDate") String fetureDate, @Param("rallyPointId") int rallyPointId);

    /**
     * 查询当前集结点小车数量
     * @param rallyPointId
     * @return
     */
    Integer countRallyPointCarNum(@Param("rallyPointId") int rallyPointId);

    /**
     * 查询当前区域的区域名称
     * @param singaporeAreaId
     * @return
     */
    String singaporeAreaName(@Param("singaporeAreaId") int singaporeAreaId);

    /**
     * 查询今天集结点是否有订单（订单数量）
     * @param rallyPointId
     * @return
     */
    Integer selectRallyPointIsCarOrder(@Param("rallyPointId") int rallyPointId, @Param("fetureDate") String fetureDate);

    /**
     * 查询当前时间当前集结点已有的未用模板生成的订单数量
     * @param rallyPointId
     * @return
     */
    Integer selectRallyPointCarOrder(@Param("rallyPointId") int rallyPointId);

    /**
     * 查询刚刚添加的一条新小车订单id
     * @return
     */
    Integer selectLastOrderId();
}
