package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.AppCarUserAddressMapper;
import com.siruiman.crosslogistics.model.AppUserAddress;
import com.siruiman.crosslogistics.model.AppUserAdressArea;
import com.siruiman.crosslogistics.model.dto.AddAppUserAdressDto;
import com.siruiman.crosslogistics.service.AppCarUserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppCarUserAddressServiceImpl implements AppCarUserAddressService {
    @Autowired
    private AppCarUserAddressMapper appCarUserAddressMapper;

    @Override
    public List<AppUserAddress> selectAppUserAddress(int appUserId) {
        try{
            return appCarUserAddressMapper.selectAppUserAddress(appUserId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer addAppUserAddress(AddAppUserAdressDto addAppUserAdressDto) {
        try{
            /*AppUserAdressArea selectSingaporeAreaId = appCarUserAddressMapper.selectSingaporeAreaId(addAppUserAdressDto.getZipCod());
            addAppUserAdressDto.setSingaporeAreaId(selectSingaporeAreaId.getSingaporeAreaId());*/
            /*查询当前用户是否有默认地址*/
            Integer defaultAddressByAppUser = appCarUserAddressMapper.selectDefaultAddressByAppUser(addAppUserAdressDto.getAppUserId());
            if(defaultAddressByAppUser == 0){
                addAppUserAdressDto.setAddressDefault(2);
            }else{
                addAppUserAdressDto.setAddressDefault(1);
            }

            return appCarUserAddressMapper.addAppUserAddress(addAppUserAdressDto);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer editAppUserAddress(AddAppUserAdressDto addAppUserAdressDto) {
        try{
            /*AppUserAdressArea selectSingaporeAreaId = appCarUserAddressMapper.selectSingaporeAreaId(addAppUserAdressDto.getZipCod());
            addAppUserAdressDto.setSingaporeAreaId(selectSingaporeAreaId.getSingaporeAreaId());*/
            if(addAppUserAdressDto.getAddressDefault() == 2){
                /*查询默认地址id*/
                Integer userAddressDefault = appCarUserAddressMapper.selectUserAddressDefault(addAppUserAdressDto.getAppUserId());
                /*把当前默认地址修改为正常*/
                Integer editUserAddressDefaultByOne = appCarUserAddressMapper.editUserAddressDefaultByOne(userAddressDefault);
                if(editUserAddressDefaultByOne < 1){
                    return 2;
                }
                Integer editAppUserAddress = appCarUserAddressMapper.editAppUserAddress(addAppUserAdressDto);
                return editAppUserAddress;
            }

            Integer editAppUserAddress = appCarUserAddressMapper.editAppUserAddress(addAppUserAdressDto);
            return editAppUserAddress;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer deleteAppUserAddress(int userAddressId) {
        try{
            //根据用户地址id查询该条地址信息
            AddAppUserAdressDto addAppUserAdressDto =appCarUserAddressMapper.selectAppUserAddressById(userAddressId);
            //删除掉此地址信息
            Integer COUNT = appCarUserAddressMapper.deleteAppUserAddress(userAddressId);
            //如果地址为默认地址
            if(addAppUserAdressDto.getAddressDefault()== 2){
                //根据用户id查询最早添加的一条地址
                AddAppUserAdressDto addAppUserAdressDto1 = appCarUserAddressMapper.selectAppUserAddressOne(addAppUserAdressDto.getAppUserId());
                //如果不为空
                if(addAppUserAdressDto1 != null){
                    //设置为默认地址
                    addAppUserAdressDto1.setAddressDefault(2);
                    Integer editAppUserAddress = appCarUserAddressMapper.editAppUserAddress(addAppUserAdressDto1);
                }

            }
            return COUNT;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public AppUserAdressArea selectSingaporeAreaId(String zipCode) {
        try{
            return appCarUserAddressMapper.selectSingaporeAreaId(zipCode);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer editUserAddressDefault(int appUserId, int userAddressId) {
        try{
            /*查询默认地址id*/
            Integer userAddressDefault = appCarUserAddressMapper.selectUserAddressDefault(appUserId);
            /*把当前默认地址修改为正常*/
            Integer editUserAddressDefaultByOne = appCarUserAddressMapper.editUserAddressDefaultByOne(userAddressDefault);
            if(editUserAddressDefaultByOne < 1){
                return 2;
            }
            Integer editUserAddressDefault = appCarUserAddressMapper.editUserAddressDefault(userAddressId);
            return editUserAddressDefault;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
