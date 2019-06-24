package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.AdminUser;
import com.siruiman.crosslogistics.model.Group;
import com.siruiman.crosslogistics.model.dto.AdminUserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface AdminUserMapper {
    /**
     * 管理员列表
     * @param groupId
     * @param search
     * @return
     */
    List<AdminUser> selectAdminUserAll(@Param("groupId") int groupId, @Param("search") String search);

    /**
     * 管理员列表
     * @param groupId
     * @param search
     * @return
     */
    Integer count(@Param("groupId") int groupId, @Param("search") String search);

    /**
     * 添加管理员
     * @param adminUserDto
     * @return
     */
    Integer addAdminUser(@Param("adminUserDto") AdminUserDto adminUserDto);

    /**
     * 查询当前用户名是否有管理员使用
     * @param adminName
     * @return
     */
    Integer selectAdminUserBuName(@Param("adminName") String adminName);

    /**
     * 查询所有权限组
     * @return
     */
    List<Group> selectGroupAll();

    /**
     * 查询编辑信息
     * @param adminUid
     * @return
     */
    AdminUserDto selectEditAdminUser(@Param("adminUid") int adminUid);

    /**
     * 编辑管理员
     * @param adminUserDto
     * @return
     */
    Integer editAdminUser(@Param("adminUserDto") AdminUserDto adminUserDto);

    /**
     * 删除管理员
     * @param adminUid
     * @return
     */
    Integer deleteAdminUser(@Param("adminUid") int adminUid);

    /**
     * 启用禁用管理员状态
     * @param adminUid
     * @param status
     * @return
     */
    Integer editAdminUserStatus(@Param("adminUid") int adminUid, @Param("status") int status);

    /**
     * 查询密码
     * @param adminUid
     * @return
     */
    String selectPassword(@Param("adminUid") int adminUid);

    List<AdminUser> getAdminList();

}