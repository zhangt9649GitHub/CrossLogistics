package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.TaskCarOrder;
import com.siruiman.crosslogistics.model.TaskTruckOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TaskTruckOrderMapper {

    int deleteByPrimaryKey(Integer taskOrderId);


    int insert(TaskTruckOrder record);


    TaskTruckOrder selectByPrimaryKey(Integer taskOrderId);


    List<TaskTruckOrder> selectAll();


    int updateByPrimaryKey(TaskTruckOrder record);

    /**
     * 根据用户id获取货车订单编号
     *
     * @param appUserId
     * @return
     */
    TaskTruckOrder selectTaskTruckOrderByUserId(@Param("appUserId") int appUserId, @Param("createTime") String createTime);

    /**
     * 根据时间查询今日订单
     *
     * @param createTime
     * @return
     */
    List<TaskTruckOrder> selectTaskTruckOrderByTime(@Param("createTime") String createTime);

    /**
     * 根据订单编号查询货车订单
     *
     * @param orderNumber
     * @return
     */
    TaskTruckOrder selectTruckOrderByOrderNumber(@Param("orderNumber") String orderNumber);

    /**
     * 根据用户id查询货车模式正在进行的订单id
     * @return
     */
    TaskTruckOrder selectTruckOrderByAppUserId(@Param("appUserId") int appUserId);
}