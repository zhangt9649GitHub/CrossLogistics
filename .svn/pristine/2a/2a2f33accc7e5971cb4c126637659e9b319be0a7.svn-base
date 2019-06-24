package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.AppTaskCarOrder;
import com.siruiman.crosslogistics.model.AppTaskCarOrderBag;
import com.siruiman.crosslogistics.model.AppTaskCarOrderDetails;
import com.siruiman.crosslogistics.model.AppTaskCarOrderRoute;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AppTaskCarOrderMapper {
    /**
     * 根据状态查询小车订单列表
     * @param type
     * @return
     */
    List<AppTaskCarOrder> selectAppTaskCarOrderByStatus(@Param("type") int type, @Param("appUserId") int appUserId);

    /**
     * 根据状态查询小车订单列表条数
     * @param type
     * @return
     */
    Integer count(@Param("type") int type, @Param("appUserId") int appUserId);

    /**
     * 小车任务订单id
     * @param taskOrderId
     * @return
     */
    AppTaskCarOrderDetails selectAppTaskCarOrderDetails(@Param("taskOrderId") int taskOrderId);

    /**
     * 小车订单送货路线
     * @param taskOrderId
     * @return
     */
    List<AppTaskCarOrderRoute> selectAppTaskCarOrderRoute(@Param("taskOrderId") int taskOrderId);

    /**
     * 查询货物信息
     * @param taskOrderId
     * @return
     */
    List<AppTaskCarOrderBag> selectAppTaskCarOrderBag(@Param("taskOrderId") int taskOrderId);
}
