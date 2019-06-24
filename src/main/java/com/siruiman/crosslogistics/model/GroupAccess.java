package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(description="郭阳")
public class GroupAccess {
    @ApiModelProperty(value="权限组id", example = "1")
    private Integer groupId;
    @ApiModelProperty(value="权限组名称", example="groupName")
    private String groupName;
    @ApiModelProperty(value="权限组描述", example="groupText")
    private String groupText;
    private List<Access> accessTree;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupText() {
        return groupText;
    }

    public void setGroupText(String groupText) {
        this.groupText = groupText;
    }

    public List<Access> getAccessTree() {
        return accessTree;
    }

    public void setAccessTree(List<Access> accessTree) {
        this.accessTree = accessTree;
    }

    @Override
    public String toString() {
        return "GroupAccess{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", groupText='" + groupText + '\'' +
                ", accessTree=" + accessTree +
                '}';
    }
}
