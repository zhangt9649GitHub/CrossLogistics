package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.ChinaAreaMapper;
import com.siruiman.crosslogistics.model.ChinaArea;
import com.siruiman.crosslogistics.service.ChinaAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ChinaAreaServiceImpl implements ChinaAreaService {
    @Autowired
    private ChinaAreaMapper chinaAreaMapper;

    @Override
    public void update(ChinaArea chinaArea) {
        chinaAreaMapper.updateByPrimaryKey(chinaArea);
    }

    @Override
    public void delById(ChinaArea chinaArea) {
        chinaAreaMapper.delById(chinaArea);
    }

    @Override
    public int selectCountProvince() {
        return chinaAreaMapper.selectCountProvince();
    }

    @Override
    public void insert(ChinaArea chinaArea) {
        ChinaArea chinaArea1 = chinaAreaMapper.selectByPrimaryKey(chinaArea.getChinaAreaParentId());
        Integer parentId = chinaArea.getChinaAreaParentId();
        Integer type = chinaArea1.getChinaAreaType()+1;
//        如果与上级是直辖市或特别行政区就把类型再加1成为区级
        if(parentId==2||parentId==3||parentId==10||parentId==23||parentId==33||parentId==34||parentId==35||parentId==36){
            type++;
        }
        chinaArea.setChinaAreaType(type);
        chinaAreaMapper.insert(chinaArea);
    }

    @Override
    public List<ChinaArea> selectChildById(int chinaAreaId) {
        return chinaAreaMapper.selectChildById(chinaAreaId);
    }

    @Override
    public List<ChinaArea> selectProvince() {
        return chinaAreaMapper.selectAllProvince();
    }

    @Override
    public List<ChinaArea> selectAll() {
        return chinaAreaMapper.selectAll();
    }

    @Override
    public ChinaArea selectChinaAreaById(int chinaAreaId) {
        return chinaAreaMapper.selectByPrimaryKey(chinaAreaId);
    }
}
