package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.PayGiveMapper;
import com.siruiman.crosslogistics.model.PayGive;
import com.siruiman.crosslogistics.service.PayGiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
public class PayGiveServiceImpl implements PayGiveService {
    @Autowired
    private PayGiveMapper payGiveMapper;

    @Override
    public List<PayGive> selectPayGiveList(String addTime, String endTime, String staffName) {
        try {
            HashMap<Object, String> map = new HashMap<>();
            map.put("addTime", addTime);
            map.put("endTime", endTime);
            map.put("staffName", staffName);
            return payGiveMapper.selectPayGiveList(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int selectCountPayGiveList(String addTime, String endTime, String staffName) {
        try {
            HashMap<Object, String> map = new HashMap<>();
            map.put("addTime", addTime);
            map.put("endTime", endTime);
            map.put("staffName", staffName);
            return payGiveMapper.selectCountPayGiveList(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertPayGive(PayGive payGive) {
        payGiveMapper.insert(payGive);

    }

    @Override
    public PayGive selectPayGiveById(int payGiveId) {
        try {
            return payGiveMapper.selectByPrimaryKey(payGiveId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updatePayGive(PayGive payGive) {
        payGiveMapper.updateByPrimaryKey(payGive);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deletePayGiveById(Integer payGiveId) {
        payGiveMapper.deleteByPrimaryKey(payGiveId);
    }
}
