package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(description="郭阳")
public class AdminUser {
    @ApiModelProperty(value="后台用户主键id", example = "1")
    private Integer adminUid;
    @ApiModelProperty(value="用户名称", example="adminName")
    private String adminName;
    @ApiModelProperty(value="权限组名称", example="groupName")
    private String groupName;
    @ApiModelProperty(value="添加时间", example="addTime")
    private String addTime;
    @ApiModelProperty(value="状态（0禁用，1启用，-1删除）", example="1")
    private Integer status;
    @ApiModelProperty(value="状态（0禁用，1启用，-1删除）", example="启用")
    private String statusName;

    @ApiModelProperty(value="用户类型", example="admin")
    private String userType;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getAdminUid() {
        return adminUid;
    }

    public void setAdminUid(Integer adminUid) {
        this.adminUid = adminUid;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return "AdminUser{" +
                "adminUid=" + adminUid +
                ", adminName='" + adminName + '\'' +
                ", groupName='" + groupName + '\'' +
                ", addTime='" + addTime + '\'' +
                ", status=" + status +
                ", statusName='" + statusName + '\'' +
                '}';
    }
}
