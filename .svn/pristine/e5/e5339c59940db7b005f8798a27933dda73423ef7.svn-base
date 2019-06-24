package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.AppUser;
import com.siruiman.crosslogistics.model.dto.AppUserLoginDto;
import org.apache.ibatis.annotations.Param;

public interface AppLoginService {
    /**
     * 根据手机号密码查询用户信息
     * @param appUserLoginDto
     * @return
     */
    AppUser selectAppUser(AppUserLoginDto appUserLoginDto);

    /**
     * 插入验证码
     * @param phone
     * @param code
     * @return
     */
    Integer insertCode(String phone, String code);

    /**
     * 查询验证码
     * @param phone
     * @return
     */
    String selectCode(String phone);

    /**
     * 修改验证码
     * @param phone
     * @param code
     * @return
     */
    Integer editCode(String phone, String code);

    /**
     * 修改密码
     * @param appUserLoginDto
     * @return
     */
    Integer editPsw(AppUserLoginDto appUserLoginDto);

    /**
     * 新建账号
     * @param appUserLoginDto
     * @return
     */
    Integer addAppUser(AppUserLoginDto appUserLoginDto);
}
