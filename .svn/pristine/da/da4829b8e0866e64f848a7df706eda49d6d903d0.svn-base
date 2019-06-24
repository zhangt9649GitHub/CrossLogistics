package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.Staff;
import com.siruiman.crosslogistics.model.StaffAccess;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PDALoginMapper {
    /**
     * 查询员工信息
     * @param userName
     * @return
     */
    Staff selectStaff(@Param("userName") String userName);

    /**
     * 查询当前登录员工的权限列表
     * @param staffId
     * @return
     */
    List<StaffAccess> selectStaffAccess(@Param("staffId") int staffId);
}
