package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.AppUserAddress;
import com.siruiman.crosslogistics.model.AppUserAdressArea;
import com.siruiman.crosslogistics.model.dto.AddAppUserAdressDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AppCarUserAddressMapper {
    /**
     * 根据用户查询所有收货地址
     * @param appUserId
     * @return
     */
    List<AppUserAddress> selectAppUserAddress(@Param("appUserId") int appUserId);

    /**
     * 添加app用户地址
     * @param addAppUserAdressDto
     * @return
     */
    Integer addAppUserAddress(@Param("addAppUserAdressDto") AddAppUserAdressDto addAppUserAdressDto);

    /**
     * 查询当前用户是否有默认地址
     * @param appUserId
     * @return
     */
    Integer selectDefaultAddressByAppUser(@Param("appUserId") int appUserId);

    /**
     * 根据邮编查询区域id
     * @param zipCode
     * @return
     */
    AppUserAdressArea selectSingaporeAreaId(@Param("zipCode") String zipCode);

    /**
     * 编辑app用户地址
     * @param addAppUserAdressDto
     * @return
     */
    Integer editAppUserAddress(@Param("addAppUserAdressDto") AddAppUserAdressDto addAppUserAdressDto);

    /**
     * 删除app用户地址
     * @param userAddressId
     * @return
     */
    Integer deleteAppUserAddress(@Param("userAddressId") int userAddressId);

    /**
     * 查询用户默认地址的id
     * @param appUserId
     * @return
     */
    Integer selectUserAddressDefault(@Param("appUserId") int appUserId);

    /**
     * 修改APP用户地址状态
     * @param userAddressId
     * @return
     */
    Integer editUserAddressDefaultByOne(@Param("userAddressId") int userAddressId);

    /**
     * 修改APP用户默认地址
     * @param userAddressId
     * @return
     */
    Integer editUserAddressDefault(@Param("userAddressId") int userAddressId);

    /**
     * 根据用户地址id查询地址信息
     * @param userAddressId
     * @return
     */
    AddAppUserAdressDto selectAppUserAddressById(@Param("userAddressId") int userAddressId);

    /**
     * 根据用户id查询此用户最早添加的一条地址
     * @param appUserId
     * @return
     */
    AddAppUserAdressDto selectAppUserAddressOne(@Param("appUserId") int appUserId);
}
