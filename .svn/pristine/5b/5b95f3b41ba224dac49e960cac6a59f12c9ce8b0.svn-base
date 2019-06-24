package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.TaskOrder;
import com.siruiman.crosslogistics.model.TaskOrderDetail;
import com.siruiman.crosslogistics.model.dto.TaskOrderDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface TaskOrderMapper {
    /**
     * 任务订单列表
     * @return
     */
    List<TaskOrder> selectTaskOrderAll(@Param("taskOrderDto") TaskOrderDto taskOrderDto);

    /**
     * 任务订单条数
     * @return
     */
    Integer count(@Param("taskOrderDto") TaskOrderDto taskOrderDto);

    /**
     * 查询任务订单详情
     * @param taskOrderId
     * @return
     */
    TaskOrderDetail selectTaskOrderDetail(@Param("taskOrderId") int taskOrderId);
}
