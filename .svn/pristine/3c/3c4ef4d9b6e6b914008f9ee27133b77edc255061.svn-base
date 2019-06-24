package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.Staff;
import com.siruiman.crosslogistics.model.dto.EditPDAUserPswDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PDAUserMapper {
    /**
     * PDA个人信息
     * @param staffId
     * @return
     */
    Staff selectStaff(@Param("staffId") int staffId);

    /**
     * 修改PDA员工密码
     * @param editPDAUserPswDto
     * @return
     */
    Integer editStaffPsw(@Param("editPDAUserPswDto") EditPDAUserPswDto editPDAUserPswDto);

    /**
     * 查询密码
     * @param staffId
     * @return
     */
    String selPsw(@Param("staffId") int staffId);
}
