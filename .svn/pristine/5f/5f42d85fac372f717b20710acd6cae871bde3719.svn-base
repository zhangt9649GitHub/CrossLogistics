package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.StaffAccess;
import com.siruiman.crosslogistics.model.StaffGroupAccess;
import com.siruiman.crosslogistics.model.dto.StaffGroupDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StaffGroupAccessMapper {
    /**
     * 员工权限组列表
     * @param staffGroupId
     * @return
     */
    List<StaffGroupAccess> selectStaffGroupAll(@Param("staffGroupId") int staffGroupId);

    /**
     * 根据权限组id查询权限
     * @param staffGroupId
     * @return
     */
    List<StaffAccess> selectStaffAccess(@Param("staffGroupId") int staffGroupId);

    /**
     * 员工权限组列表条数
     * @param staffGroupId
     * @return
     */
    Integer count(@Param("staffGroupId") int staffGroupId);

    /**
     * 添加员工权限组
     * @param staffGroupDto
     * @return
     */
    Integer addStaffGroup(@Param("staffGroupDto") StaffGroupDto staffGroupDto);

    /**
     * 添加员工权限组和权限关联
     * @param staffGroupDto
     * @return
     */
    Integer addStaffGroupAccess(@Param("staffGroupDto") StaffGroupDto staffGroupDto);

    /**
     * 查询编辑权限组信息
     * @param staffGroupId
     * @return
     */
    StaffGroupDto selectEditStaffGroup(@Param("staffGroupId") int staffGroupId);

    /**
     * 编辑权限组信息
     * @param staffGroupDto
     * @return
     */
    Integer editStaffGroup(@Param("staffGroupDto") StaffGroupDto staffGroupDto);

    /**
     * 编辑权限组关联
     * @param staffGroupDto
     * @return
     */
    Integer deleteStaffGroupAccess(@Param("staffGroupDto") StaffGroupDto staffGroupDto);

    /**
     * 删除员工权限组
     * @param staffGroupId
     * @return
     */
    Integer deleteStaffGroup(@Param("staffGroupId") int staffGroupId);

    /**
     * 查询当前被删员工权限组是否有员工再用
     * @param staffGroupId
     * @return
     */
    Integer selectStaffGroupByStaff(@Param("staffGroupId") int staffGroupId);
}
