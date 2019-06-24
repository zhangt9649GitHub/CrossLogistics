package com.siruiman.crosslogistics.mapper;


import com.siruiman.crosslogistics.model.dto.TruckDriverOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TruckDriverOrderMapper {

    /**
     * 查出当天大货车司机的任务及大货车信息
     * @param appUserId
     * @param date
     * @return
     */
    TruckDriverOrder selectByUserId(@Param("appUserId") int appUserId,@Param("date")  String date);


    /**
     * 查出大货车司机所抢的订单id
     * @param appUserId
     * @param date
     * @return
     */
    int selectTruckOrderIdByUId(@Param("appUserId") int appUserId,@Param("date")  String date);


    /**
     * 修改订单状态
     * @param order
     */
    void updateTruckOrderState(TruckDriverOrder order);


    /**
     * 查出司机所抢订单对应的区域集结点id
     * @param taskOrderId
     * @return
     */
    List<Integer> selectRallyPointIds(int taskOrderId);

    /**
     * 根据时间查询所有货车订单的个数
     *     2019-03-21 新增统计订单功能
     * @param date
     * @return
     */
    int selectCountOrderByTime(@Param("date") String date);

    /**
     * 根据时间查询当天完成货车订单个数
     *     2019-03-21 新增统计订单功能
     * @param date
     * @return
     */
    int selectCountFinishOrderByTime(@Param("date")String date);

    /**
     * 查出货车司机的认证名字
     * @param appUserId
     * @return
     */
    TruckDriverOrder getUserByUID(@Param("appUserId")int appUserId);
}
