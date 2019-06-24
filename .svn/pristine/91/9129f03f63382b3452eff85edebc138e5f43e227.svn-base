package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.AppUserPreferencesAreaMapper;
import com.siruiman.crosslogistics.model.AppUserPreferencesArea;
import com.siruiman.crosslogistics.service.AppUserPreferencesAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppUserPreferencesAreaServiceImpl implements AppUserPreferencesAreaService {
    @Autowired
    private AppUserPreferencesAreaMapper appUserPreferencesAreaMapper;

    @Override
    public List<AppUserPreferencesArea> selectDefaultAreaByUserId(int appUserId, String userType) {
        try {
            return appUserPreferencesAreaMapper.selectDefaultAreaByUserId(appUserId, userType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateAppUserPreferencesArea(AppUserPreferencesArea appUserPreferencesArea) {
        appUserPreferencesAreaMapper.updateByPrimaryKey(appUserPreferencesArea);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertAppUserPreferencesArea(AppUserPreferencesArea appUserPreferencesArea) {
        appUserPreferencesAreaMapper.insert(appUserPreferencesArea);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteDefaultAreaByUserId(int appUserId, String userType) {
        List<AppUserPreferencesArea> appUserPreferencesAreaList = appUserPreferencesAreaMapper.selectDefaultAreaByUserId(appUserId, userType);
        for (AppUserPreferencesArea appUserPreferencesArea : appUserPreferencesAreaList
        ) {
            appUserPreferencesAreaMapper.deleteByPrimaryKey(appUserPreferencesArea.getPreferencesId());
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteByAreaId(int areaId) {
        appUserPreferencesAreaMapper.deleteByAreaId(areaId);
    }
}
