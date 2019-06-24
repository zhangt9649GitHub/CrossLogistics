package com.siruiman.crosslogistics.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(description="郭阳")
public class AdminUserDto {
    @ApiModelProperty(value="管理员id", example="1")
    private Integer adminUid;
    @ApiModelProperty(value="管理员名称", example="adminName")
    private String adminName;
    @ApiModelProperty(value="权限组id", example="1")
    private Integer groupId;
    @ApiModelProperty(value="密码", example="password")
    private String password;
    @ApiModelProperty(value="新密码", example="password")
    private String newPassword;
//    2019 -03- 21 用户新增时添加用户类型 张占伟
    @ApiModelProperty(value="用户类型id", example="1")
    private int adminUserTypeId;
    //    2019 -03- 21 用户新增时添加用户类型 张占伟
    private String userType;

    @ApiModelProperty(value = "员工归属地", example = "attribution")
    private String attribution;

    @ApiModelProperty(value="仓库id", example = "1")
    private Integer warehouseId;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getAdminUserTypeId() {
        return adminUserTypeId;
    }

    public void setAdminUserTypeId(int adminUserTypeId) {
        this.adminUserTypeId = adminUserTypeId;
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

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getAttribution() {
        return attribution;
    }

    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    @Override
    public String toString() {
        return "AdminUserDto{" +
                "adminUid=" + adminUid +
                ", adminName='" + adminName + '\'' +
                ", groupId=" + groupId +
                ", password='" + password + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
