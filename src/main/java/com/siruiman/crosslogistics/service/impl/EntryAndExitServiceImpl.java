package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.EntryAndExitMapper;
import com.siruiman.crosslogistics.model.EntryAndExit;
import com.siruiman.crosslogistics.service.EntryAndExitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EntryAndExitServiceImpl implements EntryAndExitService {

    @Autowired
    private EntryAndExitMapper entryAndExitMapper;

    @Override
    public List<EntryAndExit> getEntryAndExitList(Integer exitWay) {
        try {
            return entryAndExitMapper.selectEntryAndExitList(exitWay);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public EntryAndExit selectEntryAndExitById(Integer eaeId) {
        try {
            return entryAndExitMapper.selectByPrimaryKey(eaeId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateEntryAndExit(EntryAndExit entryAndExit) {
        entryAndExitMapper.updateByPrimaryKey(entryAndExit);
    }

    @Override
    public int selectEntryAndExitCount(Integer exitWay) {
        try {
            return entryAndExitMapper.selectEntryAndExitCount(exitWay);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertBizdictionary(EntryAndExit entryAndExit) {
        entryAndExitMapper.insert(entryAndExit);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteEntryAndExitById(Integer eaeId) {
        entryAndExitMapper.deleteByPrimaryKey(eaeId);
    }
}
