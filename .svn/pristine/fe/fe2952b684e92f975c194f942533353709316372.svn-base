package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.LogInfo;
import com.siruiman.crosslogistics.model.Staff;
import com.siruiman.crosslogistics.model.StaffAccess;
import com.siruiman.crosslogistics.model.StaffGroupAccess;
import com.siruiman.crosslogistics.model.dto.StaffDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StaffMapper {
    /**
     * 员工列表
     * @param staffDto
     * @return
     */
    List<Staff> selectStaffAll(@Param("staffDto") StaffDto staffDto);

    /**
     * 员工列表条数
     * @param staffDto
     * @return
     */
    Integer count(@Param("staffDto") StaffDto staffDto);

    /**
     * 新增员工
     * @param staff
     * @return
     */
    Integer addStaff(@Param("staff") Staff staff);

    /**
     * 验证用户名 是否存在
     * @param userName
     * @return
     */
    Integer selectStaffUserName(@Param("userName") String userName);

    /**
     * 查询员工详情
     * @param staffId
     * @return
     */
    Staff selectStaffDetail(@Param("staffId") int staffId);

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
    Staff selectEeditStaff(@Param("staffId") int staffId);

    /**
     * 编辑员工信息
     * @param staff
     * @return
     */
    Integer editStaff(@Param("staff") Staff staff);

    /**
     * 查询员工密码
     * @param staffId
     * @return
     */
    String selectStaffPsw(@Param("staffId") int staffId);

    /**
     * 删除员工
     * @param staffId
     * @return
     */
    Integer deleteStaff(@Param("staffId") int staffId);

    /**
     * 编辑员工状态
     * @param staffId
     * @param status
     * @return
     */
    Integer editStaffStatus(@Param("staffId") int staffId, @Param("status") int status);

    /**
     * 验证新增员工用户名重复
     * @param userName
     * @return
     */
    Integer checkUserName(@Param("userName") String userName);

    /**
     * 根据员工编号和员工姓名查询员工信息
     * @param number
     * @param staffName
     * @return
     */
    Staff selectStaffbyNumber(@Param("number") String number,@Param("staffName") String staffName);

    /**
     * 根据员工查询员工操作记录
     * @param staffId
     * @return
     */
    List<LogInfo> selectLogisticInfoByStaff(@Param("staffId") int staffId);

    /**
     * 根据员工查询员工操作记录条数
     * @param staffId
     * @return
     */
    Integer countLogisticInfoByStaff(@Param("staffId") int staffId);

    /**
     * 查询月份
     * @return
     */
    List<String> selectMonthAll();

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
