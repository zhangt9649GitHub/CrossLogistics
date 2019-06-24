package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.PayGive;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PayGiveService {
    /**
     * 获取工资发放列表
     *
     * @param addTime
     * @param endTime
     * @param staffName
     * @return
     */
    List<PayGive> selectPayGiveList(String addTime, String endTime, String staffName);

    /**
     * 获取工资发放列表总行数
     *
     * @param addTime
     * @param endTime
     * @param staffName
     * @return
     */
    int selectCountPayGiveList(String addTime, String endTime, String staffName);

    /**
     * 添加一条工资发放记录
     *
     * @param payGive
     */
    void insertPayGive(PayGive payGive);

    /**
     * 查询更新前工资信息
     *
     * @param payGiveId
     * @return
     */
    PayGive selectPayGiveById(int payGiveId);

    /**
     * 修改工资信息
     *
     * @param payGive
     */
    void updatePayGive(PayGive payGive);

    /**
     * 删除工资信息
     *
     * @param payGiveId
     */
    void deletePayGiveById(Integer payGiveId);
}
