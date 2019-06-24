package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.SingaporePointMapper;
import com.siruiman.crosslogistics.model.SingaporePoint;
import com.siruiman.crosslogistics.service.SingaporePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 张占伟
 * @date 2019/1/2 18:56
 */
@Service
public class SingaporePointServiceImpl implements SingaporePointService {

    @Autowired
    private SingaporePointMapper singaporePointMapper;

    @Override
    public List<SingaporePoint> selectBySGAreaId(Integer singaporeAreaId) {
        return singaporePointMapper.selectBySGAreaId(singaporeAreaId);
    }


    @Override
    public void insertSGPoints(List<SingaporePoint> singaporePoints) {
        singaporePointMapper.insertSGPoints(singaporePoints);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteBySGAreaId(int singaporeAreaId) {
        singaporePointMapper.deleteBySGAreaId(singaporeAreaId);
    }
}
