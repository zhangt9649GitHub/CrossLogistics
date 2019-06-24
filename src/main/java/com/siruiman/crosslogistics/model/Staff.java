package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@ApiModel(description="郭阳")
public class Staff {
    @ApiModelProperty(value = "员工id", example = "1")
    private Integer staffId;
    @ApiModelProperty(value = "员工权限组id", example = "1")
    private Integer staffGroupId;
    @ApiModelProperty(value = "员工权限组名称", example = "staffGroupName")
    private String staffGroupName;
    @ApiModelProperty(value = "员工编号", example = "12345678")
    private String number;
    @ApiModelProperty(value = "员工姓名", example = "staffName")
    private String staffName;
    @ApiModelProperty(value = "员工头像url", example = "headPic")
    private String headPic;
    @ApiModelProperty(value = "性别", example = "sex")
    private String sex;
    @ApiModelProperty(value = "出生日期", example = "bornYears")
    private String bornYears;
    @ApiModelProperty(value = "年龄", example = "1")
    private Integer age;
    @ApiModelProperty(value = "手机号", example = "mobile")
    private String mobile;
    @ApiModelProperty(value = "职位", example = "position")
    private String position;
    @ApiModelProperty(value = "用户名", example = "userName")
    private String userName;
    @ApiModelProperty(value = "密码", example = "password")
    private String password;
    @ApiModelProperty(value = "新密码", example = "newPassword")
    private String newPassword;
    @ApiModelProperty(value = "状态（0禁用，1启用，-1删除）", example = "1")
    private Integer status;
    @ApiModelProperty(value = "状态（0禁用，1启用）", example = "statusName")
    private String statusName;
    @ApiModelProperty(value = "添加时间", example = "addTime")
    private String addTime;
    @ApiModelProperty(value = "计件总计", example = "1")
    private Integer piece;
    @ApiModelProperty(value = "员工归属地", example = "attribution")
    private String attribution;
    @ApiModelProperty(value="仓库id", example = "1")
    private Integer warehouseId;
    @ApiModelProperty(value="仓库名称", example = "warehouseName")
    private String warehouseName;

    private List<StaffAccess> staffAccesses = new ArrayList<>();

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Integer getStaffGroupId() {
        return staffGroupId;
    }

    public void setStaffGroupId(Integer staffGroupId) {
        this.staffGroupId = staffGroupId;
    }

    public String getStaffGroupName() {
        return staffGroupName;
    }

    public void setStaffGroupName(String staffGroupName) {
        this.staffGroupName = staffGroupName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBornYears() {
        return bornYears;
    }

    public void setBornYears(String bornYears) {
        this.bornYears = bornYears;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
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

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public Integer getPiece() {
        return piece;
    }

    public void setPiece(Integer piece) {
        this.piece = piece;
    }

    public List<StaffAccess> getStaffAccesses() {
        return staffAccesses;
    }

    public void setStaffAccesses(List<StaffAccess> staffAccesses) {
        this.staffAccesses = staffAccesses;
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

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staffId=" + staffId +
                ", staffGroupId=" + staffGroupId +
                ", staffGroupName='" + staffGroupName + '\'' +
                ", number='" + number + '\'' +
                ", staffName='" + staffName + '\'' +
                ", headPic='" + headPic + '\'' +
                ", sex='" + sex + '\'' +
                ", bornYears='" + bornYears + '\'' +
                ", age=" + age +
                ", mobile='" + mobile + '\'' +
                ", position='" + position + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", status=" + status +
                ", statusName='" + statusName + '\'' +
                ", addTime='" + addTime + '\'' +
                ", piece=" + piece +
                ", staffAccesses=" + staffAccesses +
                '}';
    }
}
