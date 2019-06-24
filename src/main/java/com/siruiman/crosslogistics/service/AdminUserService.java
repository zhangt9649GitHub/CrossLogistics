package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.AdminUser;
import com.siruiman.crosslogistics.model.Group;
import com.siruiman.crosslogistics.model.dto.AdminUserDto;

import java.util.List;

public interface AdminUserService {
    /**
     * 管理员列表
     * @param groupId
     * @param search
     * @return
     */
    List<AdminUser> selectAdminUserAll(int groupId, String search);

    /**
     * 管理员列表
     * @param groupId
     * @param search
     * @return
     */
    Integer count(int groupId, String search);

    /**
     * 添加管理员
     * @param adminUserDto
     * @return
     */
    Integer addAdminUser(AdminUserDto adminUserDto);

    /**
     * 查询所有权限组
     * @return
     */
    List<Group> selectGroupAll();

    /**
     * 查询编辑信息
     * @param adminUid
     * @return
     */
    AdminUserDto selectEditAdminUser(int adminUid);

    /**
     * 编辑管理员
     * @param adminUserDto
     * @return
     */
    Integer editAdminUser(AdminUserDto adminUserDto);

    /**
     * 删除管理员
     * @param adminUid
     * @return
     */
    Integer deleteAdminUser(int adminUid);

    /**
     * 启用禁用管理员状态
     * @param adminUid
     * @param status
     * @return
     */
    Integer editAdminUserStatus(int adminUid, int status);

    List<AdminUser> getAdminList();

}
