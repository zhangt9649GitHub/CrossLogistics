package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="郭阳")
public class Group {
    @ApiModelProperty(value="权限组id", example = "1")
    private Integer groupId;
    @ApiModelProperty(value="权限组名称", example = "groupName")
    private String groupName;
    @ApiModelProperty(value="权限组描述", example = "groupText")
    private String groupText;
    @ApiModelProperty(value="添加时间", example="addTime")
    private String addTime;

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

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", groupText='" + groupText + '\'' +
                ", addTime='" + addTime + '\'' +
                '}';
    }
}
