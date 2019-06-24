package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.AdminUserType;

import java.util.List;

/**
 * @author 张占伟
 * @date 2019/3/21 14:09
 *  后台添加三方用户 类型加以区别权限
 */
public interface AdminUserTypeService {

    /**
     * 获取所有的用户类型
     * @return
     */
    List<AdminUserType> getAll();

    /**
     * 用户类型修改更新
     * @param adminUserType
     */
    void updateUserType(AdminUserType adminUserType);

    /**
     * 检查当前用户角色是否有人使用
     * @param adminUserTypeId
     * @return true 有人使用false 无人使用
     */
    boolean checkUseState(Integer adminUserTypeId);

    /**
     * 删除用户类型
     * @param adminUserTypeId
     */
    void deleteUserType(int adminUserTypeId);

    /**
     * 添加用户类型
     * @param adminUserType
     */
    void addUserType(AdminUserType adminUserType);

    /**
     * 获取管理员用户类型的个数
     * @return
     */
    int getCount();

    /**
     * 获取用户类型根据 id
     * @param adminUserTypeId
     * @return
     */
    AdminUserType getById(int adminUserTypeId);

    /**
     * 效验用户类型名字是否重复
     * @param adminUserTypeId
     * @param name
     * @return
     */
    boolean checkUserTypeName(int adminUserTypeId, String name);
}
