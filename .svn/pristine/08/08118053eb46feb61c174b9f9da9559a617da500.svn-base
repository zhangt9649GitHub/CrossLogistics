package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.AdminUserTypeMapper;
import com.siruiman.crosslogistics.model.AdminUserType;
import com.siruiman.crosslogistics.service.AdminUserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 张占伟
 * @date 2019/3/21 14:40
 *  后台添加三方用户 类型加以区别权限
 */
@Service
public class AdminUserTypeServiceImpl implements AdminUserTypeService {

    @Autowired
    private AdminUserTypeMapper adminUserTypeMapper;
    @Override
    public List<AdminUserType> getAll() {
        return adminUserTypeMapper.selectAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateUserType(AdminUserType adminUserType) {
         adminUserTypeMapper.updateByPrimaryKey(adminUserType);
    }

    @Override
    public boolean checkUseState(Integer adminUserTypeId) {
        try {
//            查出使用该用户类型的用户个数
            int count = adminUserTypeMapper.getCountUserByTypeId(adminUserTypeId);
            if (count>=1){
                return false;
            }else {
                return true;
            }
        }catch (Exception e){
            return false;
        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteUserType(int adminUserTypeId) {
        adminUserTypeMapper.deleteByPrimaryKey(adminUserTypeId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addUserType(AdminUserType adminUserType) {
        adminUserTypeMapper.insert(adminUserType);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int getCount() {
        return adminUserTypeMapper.getCount();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public AdminUserType getById(int adminUserTypeId) {
        return adminUserTypeMapper.selectByPrimaryKey(adminUserTypeId);
    }

    @Override
    public boolean checkUserTypeName(int adminUserTypeId, String name) {
        try {
            AdminUserType userType = adminUserTypeMapper.selectByName(name);
            if (userType==null){
                return true;
            }
            if (userType!=null){
//                传入的id不为空 和查出来的相等 用户类型名没有修改
                if(adminUserTypeId!=0&&adminUserTypeId==userType.getAdminUserTypeId()){
                    return true;
                }
                else{
                  return   false;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
}
