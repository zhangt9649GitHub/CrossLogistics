package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.RallyPointMapper;
import com.siruiman.crosslogistics.model.RallyPoint;
import com.siruiman.crosslogistics.service.RallyPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 张占伟
 * @date 2018/12/20
 */


@Service
public class RallyPointServiceImpl implements RallyPointService {
    @Autowired
    private RallyPointMapper rallyPointMapper;

    @Override
    public void insertRallyPoint(RallyPoint rallyPoint) {
        rallyPointMapper.insert(rallyPoint);
    }

    @Override
    public int selectCountRallyPoint(RallyPoint rallyPoint) {
        return rallyPointMapper.selectCountRallyPoint(rallyPoint);
    }

    @Override
    public List<RallyPoint> selectRallyPointList(RallyPoint rallyPoint) {
        return rallyPointMapper.selectAll(rallyPoint);
    }

    @Override
    public void updateRallyPoint(RallyPoint rallyPoint) {
        rallyPointMapper.updateByPrimaryKey(rallyPoint);
    }

    @Override
    public void delRallyPoint(int rallyPointId) {
        rallyPointMapper.delRallyPointById(rallyPointId);
    }

    @Override
    public List<RallyPoint> selectRallyPointListOfIdAndAdress() {
        return rallyPointMapper.selectRallyPointListOfIdAndAdress();
    }

    @Override
    public RallyPoint selectById(int rallyPointId) {
        return rallyPointMapper.selectByPrimaryKey(rallyPointId);
    }

    @Override
    public List<RallyPoint> selectRallyPointListById(int sgId) {
        try{
            return rallyPointMapper.selectRallyPointListById(sgId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<RallyPoint> selectBySGId(int singaporeAreaId) {
        return rallyPointMapper.selectBySGId(singaporeAreaId);
    }


    @Override
    public boolean checkName(RallyPoint rallyPoint) {
        if(rallyPoint.getRallyPointId()==0){
            try {
                int rallyPointId  = rallyPointMapper.selectByName(rallyPoint.getRallyPointName());
                if (rallyPointId>0){
                    return false;
                }
            }catch (Exception e){
                return false;
            }
        }else {

            try {
                int rallyPointId  = rallyPointMapper.selectByName(rallyPoint.getRallyPointName());
                if (rallyPointId!=rallyPoint.getRallyPointId()){
                    return false;
                }
            }catch (Exception e){
                return false;
            }
        }
        return true;
    }

    @Override
    public int selectIdByRallyPointName(String rallyPointName) {
       try{
           return rallyPointMapper.selectIdByRallyPointName(rallyPointName);
       }catch(Exception e){
           e.printStackTrace();
       }
        return 0;
    }
}
