package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.AppUserAddressMapper;
import com.siruiman.crosslogistics.model.AppUserAddress;
import com.siruiman.crosslogistics.model.AppUserAdressArea;
import com.siruiman.crosslogistics.model.SingaporeArea;
import com.siruiman.crosslogistics.model.dto.AppUserAddressDto;
import com.siruiman.crosslogistics.service.AppUserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppUserAddressServiceImpl implements AppUserAddressService {
    @Autowired
    private AppUserAddressMapper appUserAddressMapper;

    @Override
    public List<AppUserAddress> selectAppUserAddressAll(AppUserAddressDto appUserAddressDto) {
        try{
            List<AppUserAddress> selectAppUserAddressAll = appUserAddressMapper.selectAppUserAddressAll(appUserAddressDto);
            if(appUserAddressDto.getSingaporeAreaIds() != null && appUserAddressDto.getSingaporeAreaIds() != ""){
                List<AppUserAddress> newAppUserAddressAll = new ArrayList<>();
                String[] singaporeAreaIds = appUserAddressDto.getSingaporeAreaIds().split(",");
                for(AppUserAddress appUserAddress : selectAppUserAddressAll){
                    int singaporeAreaId = appUserAddress.getSingaporeAreaId();
                    for(int i = 0; i < singaporeAreaIds.length; i++){
                        int sid = Integer.parseInt(singaporeAreaIds[i]);
                        if(singaporeAreaId == sid){
                            newAppUserAddressAll.add(appUserAddress);
                        }
                    }
                }
                return newAppUserAddressAll;
            }
            return selectAppUserAddressAll;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer count(AppUserAddressDto appUserAddressDto) {
        try{
            return appUserAddressMapper.count(appUserAddressDto);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<SingaporeArea> selectAppUserAdressAreaAll() {
        try{
            return appUserAddressMapper.selectAppUserAdressAreaAll();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<AppUserAddress> selectAppUserAddressByAppUserId(Integer appUserId) {
        try{
            return appUserAddressMapper.selectAppUserAddressByAppUserId(appUserId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public String selectAppUserNameByUId(Integer appUserId) {
        try{
            return appUserAddressMapper.selectAppUserNameByUId(appUserId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
