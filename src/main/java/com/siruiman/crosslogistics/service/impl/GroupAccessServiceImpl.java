package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.GroupAccessMapper;
import com.siruiman.crosslogistics.model.Access;
import com.siruiman.crosslogistics.model.Group;
import com.siruiman.crosslogistics.model.GroupAccess;
import com.siruiman.crosslogistics.model.dto.GroupAccessDto;
import com.siruiman.crosslogistics.service.GroupAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupAccessServiceImpl implements GroupAccessService {
    @Autowired
    private GroupAccessMapper groupAccessMapper;

    @Override
    public List<Group> groupAll(int groupId) {
        List<Group> groupAll = groupAccessMapper.groupAll(groupId);
        for(Group group : groupAll){
            group.setAddTime(group.getAddTime().substring(0,19));
        }
        return groupAll;
    }

    @Override
    public Integer count(int groupId) {
        return groupAccessMapper.count(groupId);
    }

    @Override
    public GroupAccess accessAll(int groupId) {
        /*查询当前权限组的权限树*/
        List<Access> accessAll = groupAccessMapper.accessAllByGroup(groupId);
        /*处理*/
        List<Access> handleAccessTree = handleAccessTree(accessAll);
        GroupAccess groupAccess = new GroupAccess();
        GroupAccess selectGroup = groupAccessMapper.selectGroup(groupId);
        groupAccess.setAccessTree(handleAccessTree);
        groupAccess.setGroupName(selectGroup.getGroupName());
        groupAccess.setGroupText(selectGroup.getGroupText());
        return groupAccess;
    }

    @Override
    public List<Access> accessAll() {
        /*查询*/
        List<Access> accessAll = groupAccessMapper.accessAll();
        /*处理*/
        List<Access> handleAccessTree = handleAccessTree(accessAll);
        return handleAccessTree;
    }

    @Transactional
    @Override
    public Integer addGroupAccess(GroupAccessDto groupAccessDto) {
        //添加权限组
        Integer addGroup = groupAccessMapper.addGroup(groupAccessDto);
        if(addGroup < 1){
            return 0;
        }
        //添加权限组和权限关系
        Integer addGroupAccess = groupAccessMapper.addGroupAccess(groupAccessDto);
        if(addGroupAccess < 1){
            return 0;
        }
        return addGroupAccess;
    }

    @Override
    public Integer deleteGroupAndAccess(int groupId) {
        /*查当前删除权限组是否有人使用*/
        Integer selectAccessGroupByUser = groupAccessMapper.selectAccessGroupByUser(groupId);
        if(selectAccessGroupByUser > 0){
            return 2;
        }
        /*删除权限组*/
        Integer deleteGroup = groupAccessMapper.deleteGroup(groupId);
        if(deleteGroup < 1){
            return 0;
        }
        /*删除权限组和权限关系*/
        Integer deleteGroupAccess = groupAccessMapper.deleteGroupAccess(groupId);
        return deleteGroupAccess;
    }

    /**
     * 处理权限树
     * @param accessAll
     * @return
     */
    @Override
    public List<Access> handleAccessTree(List<Access> accessAll) {
        List<Access> returnAccessAll = new ArrayList<>();
        /*处理权限树*/
        for(Access access : accessAll){
            int accessModuleId = access.getAccessModuleId();
            if(access.getAccessModuleId() == 0){/*如果是一级侧边栏*/
                returnAccessAll.add(access);
            }else{
                for(Access access1 : returnAccessAll){
                    if(accessModuleId == access1.getAccessId()){/*如果权限父级id等于权限id---二级权限*/
                        if(access1.getAccesses() == null){
                            List<Access> accesses = new ArrayList<>();
                            accesses.add(access);
                            access1.setAccesses(accesses);
                        }else{
                            access1.getAccesses().add(access);
                        }
                    }else{
                        if(access1.getAccesses().size() != 0){//防止没有三级权限
                            for(Access access2 : access1.getAccesses()){
                                if(accessModuleId == access2.getAccessId()){/*如果权限父级id等于权限id---三级按钮权限*/
                                    if(access2.getAccesses() == null){
                                        List<Access> accessThree = new ArrayList<>();
                                        accessThree.add(access);
                                        access2.setAccesses(accessThree);
                                    }else {
                                        access2.getAccesses().add(access);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return returnAccessAll;
    }

    @Override
    public GroupAccess accessAllByEdit(int groupId) {
        /*查询所有权限*/
        List<Access> accessAll = groupAccessMapper.accessAll();
        /*查询当前组的权限*/
        List<Access> accessAllByGroup = groupAccessMapper.accessAllByGroup(groupId);
        for(Access access : accessAll){
            for(Access access1 : accessAllByGroup){
                if(access.getAccessId().equals(access1.getAccessId())){
                    access.setMark(1);
                }
            }
        }
        /*处理权限数据*/
        List<Access> handleAccessTree = handleAccessTree(accessAll);
        GroupAccess groupAccess = new GroupAccess();
        GroupAccess selectGroup = groupAccessMapper.selectGroup(groupId);
        groupAccess.setAccessTree(handleAccessTree);
        groupAccess.setGroupName(selectGroup.getGroupName());
        groupAccess.setGroupText(selectGroup.getGroupText());
        return groupAccess;
    }

    @Transactional
    @Override
    public Integer editGroupAccess(GroupAccessDto groupAccessDto) {
        try {
            /*删除权限组关联*/
            Integer deleteGroupAccess = groupAccessMapper.deleteGroupAccess(groupAccessDto.getGroupId());
            if(deleteGroupAccess < 1){
                throw new RuntimeException("删除权限组管理失败");
            }
            /*编辑权限组信息*/
            Integer editGroup = groupAccessMapper.editGroup(groupAccessDto);
            if(editGroup < 1){
                throw new RuntimeException("编辑权限组信息失败");
            }
            /*添加权限组关系*/
            Integer addGroupAccess = groupAccessMapper.addGroupAccess(groupAccessDto);
            if(addGroupAccess < 1){
                throw new RuntimeException("添加权限组关系失败");
            }
            return addGroupAccess;
        } catch (Exception e) {
            throw new RuntimeException("编辑权限组失败");
        }
    }
}
