package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.AppPersonalCenterMapper;
import com.siruiman.crosslogistics.model.AppUserInfo;
import com.siruiman.crosslogistics.service.AppPersonalCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppPersonalCenterServiceImpl implements AppPersonalCenterService {
    @Autowired
    private AppPersonalCenterMapper appPersonalCenterMapper;

    @Override
    public AppUserInfo selectAppUserInfo(int appUserId, String userType) {
        return appPersonalCenterMapper.selectAppUserInfo(appUserId, userType);
    }

    @Override
    public AppUserInfo selectAppUserInfoByPt(int appUserId) {
        return appPersonalCenterMapper.selectAppUserInfoByPt(appUserId);
    }

    @Override
    public Integer editAppUserInfo(AppUserInfo appUserInfo) {
        return appPersonalCenterMapper.editAppUserInfo(appUserInfo);
    }
}
