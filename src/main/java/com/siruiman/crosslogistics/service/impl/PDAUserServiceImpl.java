package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.PDAUserMapper;
import com.siruiman.crosslogistics.model.Staff;
import com.siruiman.crosslogistics.model.dto.EditPDAUserPswDto;
import com.siruiman.crosslogistics.service.PDAUserService;
import com.siruiman.crosslogistics.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PDAUserServiceImpl implements PDAUserService {
    @Autowired
    private PDAUserMapper pdaUserMapper;

    @Override
    public Staff selectStaff(int staffId) {
        return pdaUserMapper.selectStaff(staffId);
    }

    @Override
    public Integer editStaffPsw(EditPDAUserPswDto editPDAUserPswDto) {
        String selPsw = pdaUserMapper.selPsw(editPDAUserPswDto.getStaffId());
        String psw = MD5Util.encrypt(editPDAUserPswDto.getPassword());
        if(selPsw.equals(psw)){
            editPDAUserPswDto.setNewPassword(MD5Util.encrypt(editPDAUserPswDto.getNewPassword()));
            Integer editStaffPsw = pdaUserMapper.editStaffPsw(editPDAUserPswDto);
            return editStaffPsw;
        }
        return null;
    }
}
