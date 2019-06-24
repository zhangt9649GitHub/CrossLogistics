package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.AppTaskOrder;
import com.siruiman.crosslogistics.model.AppTaskOrderGoods;
import com.siruiman.crosslogistics.model.TaskOrder;
import com.siruiman.crosslogistics.model.TaskOrderDetails;
import com.siruiman.crosslogistics.model.dto.AppTaskOrderDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AppTaskOrderMapper {
    /**
     * App任务订单列表
     * @param appTaskOrderDto
     * @return
     */
    List<TaskOrder> selectTaskOrderAll(@Param("appTaskOrderDto") AppTaskOrderDto appTaskOrderDto);

    /**
     * App任务订单列表条数
     * @param appTaskOrderDto
     * @return
     */
    AppTaskOrder count(@Param("appTaskOrderDto") AppTaskOrderDto appTaskOrderDto);

    /**
     * 查询订单详情
     * @param taskOrderId
     * @return
     */
    TaskOrderDetails selectTaskOrderDetails(@Param("taskOrderId") int taskOrderId);

    /**
     * 查询送货路线
     * @param taskOrderId
     * @return
     */
    List<AppTaskOrderGoods> selectAppTaskOrderGoods(@Param("taskOrderId") int taskOrderId);
}
