package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.AppUserAddress;
import com.siruiman.crosslogistics.model.AppUserAdressArea;
import com.siruiman.crosslogistics.model.SingaporeArea;
import com.siruiman.crosslogistics.model.dto.AppUserAddressDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AppUserAddressMapper {
    /**
     * 查询全部地址列表
     * @return
     */
    List<AppUserAddress> selectAppUserAddressAll(@Param("appUserAddressDto") AppUserAddressDto appUserAddressDto);

    /**
     * 查询地址列表条数
     * @return
     */
    Integer count(@Param("appUserAddressDto") AppUserAddressDto appUserAddressDto);

    /**
     * 查询区域下拉框数据
     * @return
     */
    List<SingaporeArea> selectAppUserAdressAreaAll();

    /**
     * 根据app用户id查询用户的收件地址列表
     * @param appUserId
     * @return
     */
    List<AppUserAddress> selectAppUserAddressByAppUserId(@Param("appUserId") Integer appUserId);

    /**
     * 查出用户名根据用户id  张占伟
     * @param appUserId
     * @return
     */
    String selectAppUserNameByUId(Integer appUserId);
}
