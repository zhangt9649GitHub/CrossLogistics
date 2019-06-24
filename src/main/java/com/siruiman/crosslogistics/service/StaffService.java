package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.LogInfo;
import com.siruiman.crosslogistics.model.Staff;
import com.siruiman.crosslogistics.model.StaffAccess;
import com.siruiman.crosslogistics.model.StaffGroupAccess;
import com.siruiman.crosslogistics.model.dto.StaffDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StaffService {
    /**
     * 员工列表
     * @param staffDto
     * @return
     */
    List<Staff> selectStaffAll(StaffDto staffDto);

    /**
     * 员工列表条数
     * @param staffDto
     * @return
     */
    Integer count(StaffDto staffDto);

    /**
     * 新增员工
     * @param staff
     * @return
     */
    Integer addStaff(Staff staff);

    /**
     * 查询员工详情
     * @param staffId
     * @return
     */
    Staff selectStaffDetail(int staffId);

    /**
     * 查询所有员工权限
     * @return
     */
    List<StaffAccess> selectStaffAccessAll();

    /**
     * 查询编辑员工信息
     * @param staffId
     * @return
     */
    Staff selectEeditStaff(int staffId);

    /**
     * 编辑员工信息
     * @param staff
     * @return
     */
    Integer editStaff(Staff staff);

    /**
     * 删除员工
     * @param staffId
     * @return
     */
    Integer deleteStaff(int staffId);

    /**
     * 编辑员工状态
     * @param staffId
     * @param status
     * @return
     */
    Integer editStaffStatus(int staffId, int status);

    /**
     * 验证新增员工用户名重复
     * @param userName
     * @return
     */
    Integer checkUserName(String userName);

    /**
     * 根据员工编号和员工姓名查询员工信息
     * @param number
     * @param staffName
     * @return
     */
    Staff selectStaffbyNumber(String number,String staffName);

    /**
     * 根据员工查询员工操作记录
     * @param staffId
     * @return
     */
    List<LogInfo> selectLogisticInfoByStaff(int staffId);

    /**
     * 根据员工查询员工操作记录条数
     * @param staffId
     * @return
     */
    Integer countLogisticInfoByStaff(int staffId);

    /**
     * 员工权限组列表
     * @return
     */
    List<StaffGroupAccess> selectStaffGroupAll();

    /**
     * 获取PDA员工集合
     * @return
     */
     List<Staff>  selectStaffList();
}
