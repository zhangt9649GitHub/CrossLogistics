package com.siruiman.crosslogistics.service;


import com.siruiman.crosslogistics.model.dto.TruckDriverOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TruckDriverOrderService {

    /**
     * 根据大货车司机id,及当天时间查出货车当天订单路线及大货车信息牌照
     * @param appUserId
     * @return
     */
    TruckDriverOrder selectByUserId(int appUserId,String date);


    /**
     * 查出大货车司机今天所抢的订单id
     * @param appUserId
     * @param date
     * @return
     */
    int selectTruckOrderIdByUId(int appUserId,String date);


    /**
     * 修改司机订单为进行中
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
     * 根据时间查出所有订单
     * @param date
     * @return
     */
    int selectCountOrderByTime(String date);

    /**
     * 根据时间查出完成订单个数
     * @param date
     * @return
     */
    int selectCountFinishOrderByTime(String date);

    /**
     * 查出货车司机的认证名字
     * @param appUserId
     * @return
     */
    TruckDriverOrder getUserByUID(int appUserId);
}
