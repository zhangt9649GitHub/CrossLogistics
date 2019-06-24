package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.Staff;
import com.siruiman.crosslogistics.model.dto.EditPDAUserPswDto;

public interface PDAUserService {
    /**
     * PDA个人信息
     * @param staffId
     * @return
     */
    Staff selectStaff(int staffId);

    /**
     * 修改PDA员工密码
     * @param editPDAUserPswDto
     * @return
     */
    Integer editStaffPsw(EditPDAUserPswDto editPDAUserPswDto);
}
