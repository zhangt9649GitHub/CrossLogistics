package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.AppLoginMapper;
import com.siruiman.crosslogistics.model.AppUser;
import com.siruiman.crosslogistics.model.dto.AppUserLoginDto;
import com.siruiman.crosslogistics.service.AppLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppLoginServiceImpl implements AppLoginService {
    @Autowired
    private AppLoginMapper appLoginMapper;

    @Override
    public AppUser selectAppUser(AppUserLoginDto appUserLoginDto) {
        AppUser selectAppUser = appLoginMapper.selectAppUser(appUserLoginDto);
        if(selectAppUser != null){
            Integer selectAddress = appLoginMapper.selectAddress(selectAppUser.getAppUserId());
            selectAppUser.setIsAddress(selectAddress);
        }

        return selectAppUser;
    }

    @Override
    public Integer insertCode(String phone, String code) {
        return appLoginMapper.insertCode(phone, code);
    }

    @Override
    public String selectCode(String phone) {
        return appLoginMapper.selectCode(phone);
    }

    @Override
    public Integer editCode(String phone, String code) {
        return appLoginMapper.editCode(phone, code);
    }

    @Override
    public Integer editPsw(AppUserLoginDto appUserLoginDto) {
        return appLoginMapper.editPsw(appUserLoginDto);
    }

    @Override
    public Integer addAppUser(AppUserLoginDto appUserLoginDto) {
        return appLoginMapper.addAppUser(appUserLoginDto);
    }
}
