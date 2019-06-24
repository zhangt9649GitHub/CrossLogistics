package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.FinanceMoneyFlow;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FinanceMoneyFlowService {
    /**
     * 获取财务流水记录列表
     *
     * @param createTime
     * @param endTime
     * @param financeMoneyType
     * @return
     */
    List<FinanceMoneyFlow> selectFinanceFlowList(String createTime, String endTime, String financeMoneyType, Integer unit);

    /**
     * 获取财务流水记录列表总行数
     *
     * @param createTime
     * @param endTime
     * @param financeMoneyType
     * @return
     */
    int selectCountFinanceFlowList(String createTime, String endTime, String financeMoneyType, Integer unit);

    /**
     * 添加财务流水记录
     *
     * @param financeMoneyFlow
     */
    void insert(FinanceMoneyFlow financeMoneyFlow);
}
