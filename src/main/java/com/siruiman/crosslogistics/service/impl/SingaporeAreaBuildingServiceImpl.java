package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.SingaporeAreaBuildingMapper;
import com.siruiman.crosslogistics.mapper.SingaporeAreaMapper;
import com.siruiman.crosslogistics.model.SingaporeAreaBuilding;
import com.siruiman.crosslogistics.service.SingaporeAreaBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 张占伟
 * @date 2018/12/24 19:53
 */
@Service
public class SingaporeAreaBuildingServiceImpl implements SingaporeAreaBuildingService {
    @Autowired
    private SingaporeAreaBuildingMapper singaporeAreaBuildingMapper;
    @Override
    public void insert(SingaporeAreaBuilding s) {
        singaporeAreaBuildingMapper.insert(s);
    }

    @Override
    public List<SingaporeAreaBuilding> selectSGBuildingList(String saZipCode) {
        return singaporeAreaBuildingMapper.selectAll(saZipCode);
    }

    @Override
    public SingaporeAreaBuilding selectSGBuildingById(int saBuildingId) {
        return singaporeAreaBuildingMapper.selectByPrimaryKey(saBuildingId);
    }


    @Override
    public int getCountSGBuilding(String saZipCode) {
        return singaporeAreaBuildingMapper.getCountSGBuilding(saZipCode);
    }

    @Override
    public void deleteById(int saBuildingId) {
        singaporeAreaBuildingMapper.deleteById(saBuildingId);

    }

    @Override
    public void updateSGBuilding(SingaporeAreaBuilding singaporeAreaBuilding) {
        singaporeAreaBuildingMapper.updateByPrimaryKey(singaporeAreaBuilding);
    }

    @Override
    public SingaporeAreaBuilding selectSGBuildingByZipCode(String saZipCode) {
        try{
            return singaporeAreaBuildingMapper.selectSGBuildingByZipCode(saZipCode);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateBuidingArea(SingaporeAreaBuilding a) {
        singaporeAreaBuildingMapper.updateBuidingArea(a);

    }

    @Override
    public List<SingaporeAreaBuilding> selectNoAreaBuilding(String saZipCode){
        return singaporeAreaBuildingMapper.selectNoAreaBuilding(saZipCode);
    }

    @Override
    public int getCountNoAreaBuilding(String saZipCode) {
        return singaporeAreaBuildingMapper.selectCountNoAreaBuilding(saZipCode);
    }


    @Override
    public List<SingaporeAreaBuilding> selectBySaId(Integer singaporeAreaId) {
        return singaporeAreaBuildingMapper.selectBySaId(singaporeAreaId);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateBuidingRallyPoint(SingaporeAreaBuilding a) {
        singaporeAreaBuildingMapper.updateBuidingRallyPoint(a);
    }


    @Override
    public List<SingaporeAreaBuilding> selectInAreaBuilding(String saZipCode) {
        return singaporeAreaBuildingMapper.selectInAreaBuilding(saZipCode);
    }

    @Override
    public int getCountInAreaBuilding(String saZipCode) {
        return singaporeAreaBuildingMapper.getCountInAreaBuilding(saZipCode);
    }


    @Override
    public boolean checkZipCode(String saZipCode, int singaporeAreaId) {
        try {
            SingaporeAreaBuilding building = singaporeAreaBuildingMapper.selectSGBuildingByZipCode(saZipCode);
            if(building!=null){
                if(singaporeAreaId>0&&singaporeAreaId==building.getSaBuildingId()){
                    System.out.println(true);
                    return true;
                }
                if (singaporeAreaId>0&&singaporeAreaId!=building.getSaBuildingId()){
                    System.out.println("id!=id");
                    return false;
                }
                if(singaporeAreaId==0){
                    System.out.println(0);
                    return false;
                }
            }
            if(building==null){
                return true;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void recoveryBuilding(int saBuildingId) {
        singaporeAreaBuildingMapper.recoveryBuilding(saBuildingId);
    }
}
