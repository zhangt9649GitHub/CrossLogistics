package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.AdminUserMapper;
import com.siruiman.crosslogistics.model.AdminUser;
import com.siruiman.crosslogistics.model.Group;
import com.siruiman.crosslogistics.model.dto.AdminUserDto;
import com.siruiman.crosslogistics.service.AdminUserService;
import com.siruiman.crosslogistics.service.AdminUserTypeService;
import com.siruiman.crosslogistics.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Autowired
    private AdminUserMapper adminUserMapper;

    @Autowired
    private AdminUserTypeService adminUserTypeService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<AdminUser> selectAdminUserAll(int groupId, String search) {
        List<AdminUser> selectAdminUserAll = adminUserMapper.selectAdminUserAll(groupId, search);
        for(AdminUser adminUser : selectAdminUserAll){
            adminUser.setAddTime(adminUser.getAddTime().substring(0,19));
            if(adminUser.getStatus() == 1){
                adminUser.setStatusName("启用");
            }
            if(adminUser.getStatus() == 0){
                adminUser.setStatusName("禁用");
            }
        }
        return selectAdminUserAll;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer count(int groupId, String search) {
        return adminUserMapper.count(groupId, search);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer addAdminUser(AdminUserDto adminUserDto) {
        /*验证用户名重复*/
        Integer selectAdminUserBuName = adminUserMapper.selectAdminUserBuName(adminUserDto.getAdminName());
        if(selectAdminUserBuName > 0){
            return 2;
        }
        //验证用户类型是否为第三方 第三方
        if (adminUserDto.getAdminUserTypeId()!=1){
//            查出来的为第三方是否有账户 2019 -03- 21 用户类型 三方公司只能添加一个账号
            boolean b = adminUserTypeService.checkUseState(adminUserDto.getAdminUserTypeId());
            if (!b){
                return 3;
            }
        }
        String password = MD5Util.encrypt(adminUserDto.getPassword());
        adminUserDto.setPassword(password);
        System.out.println(password);
        Integer addAdminUser = adminUserMapper.addAdminUser(adminUserDto);
        return addAdminUser;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<Group> selectGroupAll() {
        return adminUserMapper.selectGroupAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public AdminUserDto selectEditAdminUser(int adminUid) {
        return adminUserMapper.selectEditAdminUser(adminUid);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer editAdminUser(AdminUserDto adminUserDto) {
        String password = MD5Util.encrypt(adminUserDto.getPassword());
        String selectPassword = adminUserMapper.selectPassword(adminUserDto.getAdminUid());
        if(password.equals(selectPassword)){
            String newPassword = MD5Util.encrypt(adminUserDto.getNewPassword());
            adminUserDto.setNewPassword(newPassword);
            Integer editAdminUser = adminUserMapper.editAdminUser(adminUserDto);
            return editAdminUser;
        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer deleteAdminUser(int adminUid) {
        return adminUserMapper.deleteAdminUser(adminUid);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer editAdminUserStatus(int adminUid, int status) {
        return adminUserMapper.editAdminUserStatus(adminUid, status);
    }

    @Override
    public List<AdminUser> getAdminList() {
        try{
            return adminUserMapper.getAdminList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
