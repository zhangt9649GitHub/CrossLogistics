package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.AdminUserLoginMapper;
import com.siruiman.crosslogistics.model.dto.AdminUserLoginDto;
import com.siruiman.crosslogistics.service.AdminUserLoginService;
import com.siruiman.crosslogistics.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 张占伟
 * @date 2019/1/5 17:52
 */
@Service
public class AdminUserLoginServiceImpl implements AdminUserLoginService {

    @Autowired
    private AdminUserLoginMapper adminUserLoginMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public AdminUserLoginDto select(AdminUserLoginDto adminUserLoginDto) {
        String password = adminUserLoginDto.getPassword();
        String userName = adminUserLoginDto.getUserName();
//        如果用户名密码不为空就查询数据库
        if(password!=null&&password!=""&&userName!=null&&userName!=""){
//            对用户密码加密
            adminUserLoginDto.setPassword(MD5Util.encrypt(adminUserLoginDto.getPassword()));
          return adminUserLoginMapper.select(adminUserLoginDto);
        }else{
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateLogin(AdminUserLoginDto userLogin) {
        adminUserLoginMapper.updateUserLogin(userLogin);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public AdminUserLoginDto selectByName(String username) {
        return adminUserLoginMapper.selectByName(username);
    }
}
