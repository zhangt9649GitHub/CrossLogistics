package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.StaffGroupAccess;
import com.siruiman.crosslogistics.model.dto.StaffGroupDto;

import java.util.List;

public interface StaffGroupAccessService {
    /**
     * 员工权限组列表
     * @param staffGroupId
     * @return
     */
    List<StaffGroupAccess> selectStaffGroupAll(int staffGroupId);

    /**
     * 员工权限组列表条数
     * @param staffGroupId
     * @return
     */
    Integer count(int staffGroupId);

    /**
     * 添加员工权限组
     * @param staffGroupDto
     * @return
     */
    Integer addStaffGroup(StaffGroupDto staffGroupDto);

    /**
     * 查询编辑权限组信息
     * @param staffGroupId
     * @return
     */
    StaffGroupDto selectEditStaffGroup(int staffGroupId);

    /**
     * 编辑权限组信息
     * @param staffGroupDto
     * @return
     */
    Integer editStaffGroup(StaffGroupDto staffGroupDto);

    /**
     * 删除员工权限组
     * @param staffGroupId
     * @return
     */
    Integer deleteStaffGroup(int staffGroupId);
}
