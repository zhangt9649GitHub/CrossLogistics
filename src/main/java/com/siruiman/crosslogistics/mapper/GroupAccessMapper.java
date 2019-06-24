package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.Access;
import com.siruiman.crosslogistics.model.Group;
import com.siruiman.crosslogistics.model.GroupAccess;
import com.siruiman.crosslogistics.model.dto.GroupAccessDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GroupAccessMapper {
    /**
     * 权限组列表
     * @return
     */
    List<Group> groupAll(@Param("groupId") int groupId);

    /**
     * 权限组条数
     * @return
     */
    Integer count(@Param("groupId") int groupId);

    /**
     * 根据组id查询权限树
     * @return
     */
    List<Access> accessAllByGroup(@Param("groupId") int groupId);

    /**
     * 查询权限组信息
     * @param groupId
     * @return
     */
    GroupAccess selectGroup(@Param("groupId") int groupId);

    /**
     * 查询所有权限树
     * @return
     */
    List<Access> accessAll();

    /**
     * 添加权限组
     * @param groupAccessDto
     * @return
     */
    Integer addGroup(@Param("groupAccessDto") GroupAccessDto groupAccessDto);

    /**
     * 添加权限组权限关系
     * @param groupAccessDto
     * @return
     */
    Integer addGroupAccess(@Param("groupAccessDto") GroupAccessDto groupAccessDto);

    /**
     * 查询当前权限组是否有管理员使用
     * @param groupId
     * @return
     */
    Integer selectAccessGroupByUser(@Param("groupId") int groupId);

    /**
     * 删除权限组
     * @param groupId
     * @return
     */
    Integer deleteGroup(@Param("groupId") int groupId);

    /**
     * 删除权限组关联
     * @param groupId
     * @return
     */
    Integer deleteGroupAccess(@Param("groupId") int groupId);

    /**
     * 修改权限组信息
     * @param groupAccessDto
     * @return
     */
    Integer editGroup(@Param("groupAccessDto") GroupAccessDto groupAccessDto);
}
