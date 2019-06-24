package com.siruiman.crosslogistics.service;


import com.siruiman.crosslogistics.model.dto.AdminUserLoginDto;
import org.springframework.stereotype.Service;

@Service
public interface AdminUserLoginService {

    /**
     * 根据账号密码查询获取用户登录信息
     * @param adminUserLoginDto
     * @return
     */
    AdminUserLoginDto select(AdminUserLoginDto adminUserLoginDto);

    /**
     * 更新登录时间和ip
     * @param userLogin
     */
    void updateLogin(AdminUserLoginDto userLogin);

    /**
     * 查询用户根据用户名
     * @param username
     * @return
     */
    AdminUserLoginDto selectByName(String username);
}
