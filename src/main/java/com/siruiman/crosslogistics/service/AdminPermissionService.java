package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.AdminPermission;

import java.util.List;

/**
 * @author 张占伟
 * @date 2019/1/21 10:08
 */
public interface AdminPermissionService {

    /**
     * 查询用户权限根据用户id
      * @return
     */
    List<AdminPermission> selectByUId (int uid);
}
