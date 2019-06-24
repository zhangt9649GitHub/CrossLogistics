package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.TaskCarOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TaskCarOrderMapper {

    int deleteByPrimaryKey(Integer taskOrderId);


    int insert(TaskCarOrder record);


    TaskCarOrder selectByPrimaryKey(Integer taskOrderId);


    List<TaskCarOrder> selectAll();


    int updateByPrimaryKey(TaskCarOrder record);

    /**
     * 根据小车id获取订单号
     *
     * @param carId
     * @param state
     * @return
     */
    TaskCarOrder selectAppTaskCarOrderByCarId(@Param("carId") int carId, @Param("state") int state);

    /**
     * 根据小车id和时间确认小车是否还存有订单
     *
     * @param carId
     * @param date
     * @return
     */
    List<TaskCarOrder> selectTaskCarOrderByCarId(@Param("carId") Integer carId, @Param("date") String date);

    /**
     * 根据小车id判断今天未完成的小车订单
     *
     * @param carId
     * @param createTime
     * @return
     */
    TaskCarOrder selectTaskCarOrderByCarIdAndTime(@Param("carId") Integer carId, @Param("createTime") String createTime);

    /**
     * 根据时间查询所有订单
     *
     * @param time
     * @return
     */
    List<TaskCarOrder> selectTaskCarOrderByTime(@Param("time") String time);

    /**
     * 变更订单的状态为已结算
     */
    void updateState(@Param("taskOrderId") int taskOrderId);
}