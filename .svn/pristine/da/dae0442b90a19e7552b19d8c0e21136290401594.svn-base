package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.StaffGroupAccessMapper;
import com.siruiman.crosslogistics.model.StaffAccess;
import com.siruiman.crosslogistics.model.StaffGroupAccess;
import com.siruiman.crosslogistics.model.dto.AdminUserLoginDto;
import com.siruiman.crosslogistics.model.dto.StaffGroupDto;
import com.siruiman.crosslogistics.service.StaffGroupAccessService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StaffGroupAccessServiceImpl implements StaffGroupAccessService {
    @Autowired
    private StaffGroupAccessMapper staffGroupAccessMapper;

    @Override
    public List<StaffGroupAccess> selectStaffGroupAll(int staffGroupId) {
        try{
            List<StaffGroupAccess> selectStaffGroupAll = staffGroupAccessMapper.selectStaffGroupAll(staffGroupId);
            for(StaffGroupAccess staffGroupAccess : selectStaffGroupAll){
                staffGroupAccess.setAddTime(staffGroupAccess.getAddTime().substring(0,19));
                List<StaffAccess> selectStaffAccess = staffGroupAccessMapper.selectStaffAccess(staffGroupAccess.getStaffGroupId());
                String str = "";
                for(StaffAccess staffAccess : selectStaffAccess){
                    if(staffAccess != null){
                        if(str.length() == 0){
                            str = staffAccess.getSaName();
                        }else{
                            str = str + "," + staffAccess.getSaName();
                        }
                    }
                }
                staffGroupAccess.setStaffAccess(str);
            }
            return selectStaffGroupAll;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer count(int staffGroupId) {
        try{
            return staffGroupAccessMapper.count(staffGroupId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    @Override
    public Integer addStaffGroup(StaffGroupDto staffGroupDto) {
        try{
            Integer addStaffGroup = staffGroupAccessMapper.addStaffGroup(staffGroupDto);
            if(addStaffGroup < 1){
                throw new RuntimeException("新增员工权限组失败");
            }
            Integer addStaffGroupAccess = staffGroupAccessMapper.addStaffGroupAccess(staffGroupDto);
            if(addStaffGroupAccess < 1){
                throw new RuntimeException("新增员工权限组关联失败");
            }
            return addStaffGroupAccess;
        }catch (Exception e){
            throw new RuntimeException("新增员工权限组失败");
        }
    }

    @Override
    public StaffGroupDto selectEditStaffGroup(int staffGroupId) {
        try{
            return staffGroupAccessMapper.selectEditStaffGroup(staffGroupId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    @Override
    public Integer editStaffGroup(StaffGroupDto staffGroupDto) {
        try{
            AdminUserLoginDto user = (AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
            staffGroupDto.setAdminUid(Integer.valueOf(user.getAdminUId()));
            Integer editStaffGroup = staffGroupAccessMapper.editStaffGroup(staffGroupDto);
            if(editStaffGroup < 1){
                throw new RuntimeException("编辑员工权限组失败");
            }
            Integer deleteStaffGroup = staffGroupAccessMapper.deleteStaffGroupAccess(staffGroupDto);
            if(deleteStaffGroup < 1){
                throw new RuntimeException("删除员工权限组关联失败");
            }
            Integer addStaffGroupAccess = staffGroupAccessMapper.addStaffGroupAccess(staffGroupDto);
            if(addStaffGroupAccess < 1){
                throw new RuntimeException("新增员工权限组关联失败");
            }
            return addStaffGroupAccess;
        }catch (Exception e){
            throw new RuntimeException("编辑员工权限组失败");
        }
    }

    @Override
    public Integer deleteStaffGroup(int staffGroupId) {
        try{
            /*查询当前删除权限组是否有员工使用*/
            Integer selectStaffGroupByStaff = staffGroupAccessMapper.selectStaffGroupByStaff(staffGroupId);
            if(selectStaffGroupByStaff > 0){
                return 2;
            }
            return staffGroupAccessMapper.deleteStaffGroup(staffGroupId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
