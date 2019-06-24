package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.PDALoginMapper;
import com.siruiman.crosslogistics.model.Staff;
import com.siruiman.crosslogistics.model.StaffAccess;
import com.siruiman.crosslogistics.service.PDALoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PDALoginServiceImpl implements PDALoginService {
    @Autowired
    private PDALoginMapper pdaLoginMapper;

    @Override
    public Staff selectStaff(String userName) {
        try{
            Staff selectStaff = pdaLoginMapper.selectStaff(userName);
            List<StaffAccess> selectStaffAccess = pdaLoginMapper.selectStaffAccess(selectStaff.getStaffId());
            selectStaff.setStaffAccesses(selectStaffAccess);
            return selectStaff;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
