package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.Bag;
import com.siruiman.crosslogistics.model.TaskBagOrder;
import com.siruiman.crosslogistics.model.TaskOrderBag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 张占伟
 * @date 2019/1/13 20:40
 */
@Mapper
@Repository
public interface TaskOrderBagMapper {

    /**
     * 绑定货袋与与订单号
     * @param bagId
     * @param taskOrderId
     */
    void insertTruckBag(@Param("bagId") int bagId,@Param("taskOrderId") int taskOrderId);

    /**
     * 根据货物id查询所有绑定的货袋id
     * @param taskOrderId
     * @return
     */
    List<TaskOrderBag> selectBagsBytruckOrderId(@Param("taskOrderId") int taskOrderId);

    /**
     * 根据货物id查询订单id
     * @param goodsId
     * @return
     */
    TaskBagOrder selectOrderIdByGoodsId(@Param("goodsId") int goodsId);

    /**
     * 根据订单id获取货袋id
     * @param taskOrderId
     * @return
     */
    List<TaskBagOrder> selectBagsByOrderId(@Param("taskOrderId") int taskOrderId);
}
