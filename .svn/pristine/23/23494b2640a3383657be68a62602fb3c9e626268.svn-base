package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.AppUser;
import com.siruiman.crosslogistics.model.dto.AppUserLoginDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AppLoginMapper {
    /**
     * 根据手机号密码查询用户信息
     * @param appUserLoginDto
     * @return
     */
    AppUser selectAppUser(@Param("appUserLoginDto") AppUserLoginDto appUserLoginDto);

    /**
     * 查询当前登录账号是否有收货地址
     * @param appUserId
     * @return
     */
    Integer selectAddress(@Param("appUserId") int appUserId);

    /**
     * 插入验证码
     * @param phone
     * @param code
     * @return
     */
    Integer insertCode(@Param("phone") String phone, @Param("code") String code);

    /**
     * 查询验证码
     * @param phone
     * @return
     */
    String selectCode(@Param("phone") String phone);

    /**
     * 修改验证码
     * @param phone
     * @param code
     * @return
     */
    Integer editCode(@Param("phone") String phone, @Param("code") String code);

    /**
     * 修改密码
     * @param appUserLoginDto
     * @return
     */
    Integer editPsw(@Param("appUserLoginDto") AppUserLoginDto appUserLoginDto);

    /**
     * 新建账号
     * @param appUserLoginDto
     * @return
     */
    Integer addAppUser(@Param("appUserLoginDto") AppUserLoginDto appUserLoginDto);


}
