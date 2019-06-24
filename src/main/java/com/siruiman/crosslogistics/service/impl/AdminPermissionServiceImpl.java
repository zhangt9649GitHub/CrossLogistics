package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.AdminPermissionMapper;
import com.siruiman.crosslogistics.model.AdminPermission;
import com.siruiman.crosslogistics.service.AdminPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 张占伟
 * @date 2019/1/21 10:18
 */
@Service
public class AdminPermissionServiceImpl implements AdminPermissionService {


    @Autowired
    private AdminPermissionMapper adminPermissionMapper;

    @Override
    public List<AdminPermission> selectByUId(int uid) {
        return  adminPermissionMapper.selectByUId(uid);
    }
}
