package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.BizdictionaryMapper;

import com.siruiman.crosslogistics.model.Bizdictionary;
import com.siruiman.crosslogistics.service.BizdictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

/**
 * 字典数据业务层实现类
 */
@Service
public class BizdictionaryServiceImpl implements BizdictionaryService {

    @Autowired
    private BizdictionaryMapper bizdictionaryMapper;

    @Override
    public List<Bizdictionary> getBizdictionaryList() {
        try {
            return bizdictionaryMapper.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Bizdictionary> selectBizdictionaryByParentId(Integer parentId) {
        try {
            return bizdictionaryMapper.selectBizdictionaryByParentId(parentId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertBizdictionary(Bizdictionary bizdictionary) {
        bizdictionaryMapper.insert(bizdictionary);
    }

    @Override
    public Bizdictionary selectBizdictionary(Integer id) {
        try {
            return bizdictionaryMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateBizdictionary(Bizdictionary bizdictionary) {
        bizdictionaryMapper.updateByPrimaryKey(bizdictionary);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteBizdictionaryById(Integer id) {
        bizdictionaryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Bizdictionary getBizdictionaryById(Integer id) {
        try {
            return bizdictionaryMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
