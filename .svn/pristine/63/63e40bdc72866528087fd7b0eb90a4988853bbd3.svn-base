package com.siruiman.crosslogistics.mapper;


import com.siruiman.crosslogistics.model.dto.AdminUserLoginDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdminUserLoginMapper {

    /**
     * 根据账号密码查出用户
     * @param adminUserLoginDto
     * @return
     */
    AdminUserLoginDto select(@Param("adminUserLoginDto") AdminUserLoginDto adminUserLoginDto);

    /**
     * 修改用户登录ip 时间
     * @param userLogin
     */
    void updateUserLogin(@Param("userLogin")AdminUserLoginDto userLogin);

    /**
     * 根据账号查出用户
     * @param username
     * @return
     */
    AdminUserLoginDto selectByName(String username);
}
