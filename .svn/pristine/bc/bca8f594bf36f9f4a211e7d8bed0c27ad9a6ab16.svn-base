package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.Access;
import com.siruiman.crosslogistics.model.Group;
import com.siruiman.crosslogistics.model.GroupAccess;
import com.siruiman.crosslogistics.model.dto.GroupAccessDto;

import java.util.List;

public interface GroupAccessService {
    /**
     * 权限组列表
     * @return
     */
    List<Group> groupAll(int groupId);

    /**
     * 权限组条数
     * @return
     */
    Integer count(int groupId);

    /**
     * 查询权限树
     * @return
     */
    GroupAccess accessAll(int groupId);

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
    Integer addGroupAccess(GroupAccessDto groupAccessDto);

    /**
     * 删除权限组
     * @param groupId
     * @return
     */
    Integer deleteGroupAndAccess(int groupId);

    /**
     * 处理权限返回权限树
     * @param accesses
     * @return
     */
    List<Access> handleAccessTree(List<Access> accesses);

    /**
     * 查询编辑权限树
     * @return
     */
    GroupAccess accessAllByEdit(int groupId);

    /**
     * 编辑权限组
     * @param groupAccessDto
     * @return
     */
    Integer editGroupAccess(GroupAccessDto groupAccessDto);
}
