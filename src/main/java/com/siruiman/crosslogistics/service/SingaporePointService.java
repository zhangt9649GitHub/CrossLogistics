package com.siruiman.crosslogistics.service;


import com.siruiman.crosslogistics.model.SingaporePoint;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SingaporePointService {

    /**
     * 查出一个区域所有的顶点的经纬度
     * @param singaporeAreaId
     * @return
     */
    List<SingaporePoint> selectBySGAreaId(Integer singaporeAreaId);

    /**
     * 新增区域的定点
     * @param singaporePoints
     */
    void insertSGPoints(List<SingaporePoint> singaporePoints);

    /**
     * 删除区域也删除定点
     * @param singaporeAreaId
     */
    void deleteBySGAreaId(int singaporeAreaId);
}
