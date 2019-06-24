package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.SingaporeAreaMapper;
import com.siruiman.crosslogistics.model.SingaporeArea;
import com.siruiman.crosslogistics.service.SingaporeAreaService;
import org.apache.ibatis.binding.BindingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SingaporeAreaServiceImpl implements SingaporeAreaService {



    @Autowired
    private SingaporeAreaMapper singaporeAreaMapper;
    @Override
    public List<SingaporeArea> selectAll(String singaporeAreaName) {
        return singaporeAreaMapper.selectAll(singaporeAreaName);
    }

    @Override
    public int getCount(String singaporeAreaName) {
        return singaporeAreaMapper.selectCount(singaporeAreaName);
    }

    @Override
    public void editSingaporeArea(SingaporeArea singaporeArea) {
        singaporeAreaMapper.updateByPrimaryKey(singaporeArea);
    }

    @Override
    public void insertSingaporeArea(SingaporeArea singaporeArea) {
        singaporeAreaMapper.insert(singaporeArea);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSingaporeArea(int singaporeAreaId) {
        singaporeAreaMapper.delById(singaporeAreaId);
    }

    @Override
    public List<SingaporeArea> getSingaporeAreaIdAndName() {
        return singaporeAreaMapper.getSingaporeAreaIdAndName();
    }


    @Override
    public int selectSGIdBySingaporeName(String singaporeAreaName) {
        return singaporeAreaMapper.selectSGIdBySingaporeName(singaporeAreaName);
    }

    @Override
    public boolean checkSingaporeAreaName(int singaporeAreaId, String singaporeAreaName) {
//        如果id存在的情况下
        if (singaporeAreaId > 0) {

            try {
                int id = singaporeAreaMapper.selectSGIdBySingaporeName(singaporeAreaName);
//                没有修改名字就可以使用
                if (id == singaporeAreaId) {
                    return true;
                }
                if(id != singaporeAreaId){
                    return false;
                }
                //查出来为空就可以使用
            } catch (BindingException e) {
                return true;
            }
        } else {
//            id不存在查询
            try {
                int id = singaporeAreaMapper.selectSGIdBySingaporeName(singaporeAreaName);

//            查不出来就可用
            } catch (BindingException e) {
                return true;
            }
        }
        return false;
    }


    @Override
    public SingaporeArea selectById(int singaporeAreaId) {
        return singaporeAreaMapper.selectByPrimaryKey(singaporeAreaId);
    }

    @Override
    public List<SingaporeArea> selectSingaporeAreaNameList() {
        try{
            return singaporeAreaMapper.selectSingaporeAreaNameList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<SingaporeArea> selectSGAreaList() {
        return singaporeAreaMapper.selectSGAreaList();
    }
}
