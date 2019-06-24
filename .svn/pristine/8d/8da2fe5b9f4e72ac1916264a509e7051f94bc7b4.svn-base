package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.GenerateTruckOrder;
import com.siruiman.crosslogistics.model.dto.AddTruckOrderDto;
import com.siruiman.crosslogistics.model.dto.AddTruckOrderRouteDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface GenerateTruckOrdersMapper {
    /**
     * 根据时间和任务模板id查询订单
     * @param fetureDate
     * @return
     */
    Integer countTimeTruckOrder(@Param("fetureDate") String fetureDate, @Param("truckTaskId") int truckTaskId);

    /**
     * 查询货车模板
     * @return
     */
    List<GenerateTruckOrder> selectGenerateTruckOrderAll();

    /**
     * 添加绑定货车司机订单
     * @param addTruckOrderDto
     * @return
     */
    Integer addTruckOrderByDriver(@Param("addTruckOrderDto") AddTruckOrderDto addTruckOrderDto);

    /**
     * 添加"未"绑定货车司机订单
     * @param addTruckOrderDto
     * @return
     */
    Integer addTruckOrder(@Param("addTruckOrderDto") AddTruckOrderDto addTruckOrderDto);

    /**
     * 添加货车订单路线
     * @param addTruckOrderRouteDto
     * @return
     */
    Integer addTruckOrderRoute(@Param("addTruckOrderRouteDto") AddTruckOrderRouteDto addTruckOrderRouteDto);

    /**
     * 查询当前区域过去时间的订单金额平均值
     * @param singaporeAreaId
     * @param outTime
     * @return
     */
    Double selectAverageMoney(@Param("singaporeAreaId") int singaporeAreaId, @Param("outTime") String outTime);
}
