package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.FinanceMoneyFlowMapper;
import com.siruiman.crosslogistics.model.FinanceMoneyFlow;
import com.siruiman.crosslogistics.service.FinanceMoneyFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
public class FinanceMoneyFlowServiceImpl implements FinanceMoneyFlowService {

    @Autowired
    private FinanceMoneyFlowMapper financeMoneyFlowMapper;

    @Override
    public List<FinanceMoneyFlow> selectFinanceFlowList(String createTime, String endTime, String financeMoneyType, Integer unit) {
        try {
            HashMap<String, Object> map = new HashMap<>();
            map.put("createTime", createTime);
            map.put("endTime", endTime);
            map.put("financeMoneyType", financeMoneyType);
            map.put("unit", unit);
            return financeMoneyFlowMapper.selectFinanceFlowList(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int selectCountFinanceFlowList(String createTime, String endTime, String financeMoneyType, Integer unit) {
        try {
            HashMap<String, Object> map = new HashMap<>();
            map.put("createTime", createTime);
            map.put("endTime", endTime);
            map.put("financeMoneyType", financeMoneyType);
            map.put("unit", unit);
            return financeMoneyFlowMapper.selectCountFinanceFlowList(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insert(FinanceMoneyFlow financeMoneyFlow) {
        financeMoneyFlowMapper.insert(financeMoneyFlow);
    }
}
