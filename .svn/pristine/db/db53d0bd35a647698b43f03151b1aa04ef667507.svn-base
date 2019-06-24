package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.AppUserAddress;
import com.siruiman.crosslogistics.model.AppUserAdressArea;
import com.siruiman.crosslogistics.model.dto.AddAppUserAdressDto;

import java.util.List;

public interface AppCarUserAddressService {
    /**
     * 根据用户查询所有收货地址
     * @param appUserId
     * @return
     */
    List<AppUserAddress> selectAppUserAddress(int appUserId);

    /**
     * 添加app用户地址
     * @param addAppUserAdressDto
     * @return
     */
    Integer addAppUserAddress(AddAppUserAdressDto addAppUserAdressDto);

    /**
     * 编辑app用户地址
     * @param addAppUserAdressDto
     * @return
     */
    Integer editAppUserAddress(AddAppUserAdressDto addAppUserAdressDto);

    /**
     * 删除app用户地址
     * @param userAddressId
     * @return
     */
    Integer deleteAppUserAddress(int userAddressId);

    /**
     * 根据邮编查询区域id
     * @param zipCode
     * @return
     */
    AppUserAdressArea selectSingaporeAreaId(String zipCode);

    /**
     * 修改APP用户默认地址
     * @param appUserId
     * @param userAddressId
     * @return
     */
    Integer editUserAddressDefault(int appUserId, int userAddressId);
}
