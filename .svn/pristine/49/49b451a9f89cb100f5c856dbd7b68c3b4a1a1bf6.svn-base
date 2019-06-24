package com.siruiman.crosslogistics.model.dto;

import com.siruiman.crosslogistics.model.AdminPermission;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author 张占伟
 * @date 2019/1/5 17:32
 */
@ApiModel(description = "张占伟")
public class AdminUserLoginDto implements Serializable {


    @ApiModelProperty(value = "用户名", example = "username")
    private String userName;


    @ApiModelProperty(value = "用户id", example = "用户id")
    private String adminUId;

    @ApiModelProperty(value = "用户密码", example = "password")
    private String password;

    @ApiModelProperty(value = "用户登录时间", example = "后台登录成功生成")
    private String loginTime;

    @ApiModelProperty(value = "用户ip地址", example = "后台获取")
    private String ip;

    @ApiModelProperty(value = "用户启用状态", example = "1")
    private int status;

    @ApiModelProperty(value = "用户类型", example = "1")
    private String userType;

    @ApiModelProperty(value = "用户类型id", example = "1")
    private int adminUserTypeId;

    @ApiModelProperty(value = "验证码", example = "123456")
    private String code;

    @ApiModelProperty(value = "权限组id", example = "123456")
    private int groupId;

    @ApiModelProperty(value = "操作人所属id", example = "1")
    private Integer WarehouseId;

    @ApiModelProperty(value = " 权限类型 1.超级权限 2.仓库权限 3.合作三方权限", example = "1")
    private Integer type;

    @ApiModelProperty(value = "权限组", example = "")
    private List<AdminPermission> permissions;

    public void setAdminUserTypeId(int adminUserTypeId) {
        this.adminUserTypeId = adminUserTypeId;
    }

    public AdminUserLoginDto() {
        super();
    }

    public AdminUserLoginDto(String userName, String adminUId, String password, String loginTime, String ip, int status, String userType, int adminUserTypeId, String code, int groupId, List<AdminPermission> permissions) {
        this.userName = userName;
        this.adminUId = adminUId;
        this.password = password;
        this.loginTime = loginTime;
        this.ip = ip;
        this.status = status;
        this.userType = userType;
        this.adminUserTypeId = adminUserTypeId;
        this.code = code;
        this.groupId = groupId;
        this.permissions = permissions;
    }

    public List<AdminPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<AdminPermission> permissions) {
        this.permissions = permissions;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminUserLoginDto that = (AdminUserLoginDto) o;
        return status == that.status &&
                groupId == that.groupId &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(adminUId, that.adminUId) &&
                Objects.equals(password, that.password) &&
                Objects.equals(loginTime, that.loginTime) &&
                Objects.equals(ip, that.ip) &&
                Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userName, adminUId, password, loginTime, ip, status, code, groupId);
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }



    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getAdminUserTypeId() {
        return adminUserTypeId;
    }


    public String getAdminUId() {

        return adminUId;
    }


    public void setAdminUId(String adminUId) {
        this.adminUId = adminUId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getWarehouseId() {
        return WarehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        WarehouseId = warehouseId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
