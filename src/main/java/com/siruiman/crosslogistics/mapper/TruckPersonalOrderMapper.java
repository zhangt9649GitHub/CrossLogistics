package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.AppTruckOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TruckPersonalOrderMapper {
    /**
     * 货车司机个人订单列表
     * @param appUserId
     * @return
     */
    List<AppTruckOrder> selectAppTruckOrderAll(@Param("appUserId") int appUserId, @Param("orderStatus") int orderStatus);

    /**
     * 货车司机个人订单列表条数
     * @param appUserId
     * @return
     */
    Integer count(@Param("appUserId") int appUserId, @Param("orderStatus") int orderStatus);
}
