package com.siruiman.crosslogistics.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="郭阳")
public class AddAppUserDto {
    @ApiModelProperty(value = "用户id", example = "1")
    private Integer appUserId;
    @ApiModelProperty(value = "用户编号", example = "20181221123456")
    private String number;
    @ApiModelProperty(value = "app用户名称", example = "userName")
    private String userName;
    @ApiModelProperty(value = "用户头像", example = "headPic")
    private String headPic;
    @ApiModelProperty(value = "年龄", example = "1")
    private Integer age;
    @ApiModelProperty(value = "性别", example = "sex")
    private String sex;
    @ApiModelProperty(value = "手机号", example = "mobile")
    private String mobile;
    @ApiModelProperty(value = "密码", example = "password")
    private String password;
    @ApiModelProperty(value = "新密码", example = "newPassword")
    private String newPassword;
    @ApiModelProperty(value = "邮箱地址", example = "email")
    private String email;
    @ApiModelProperty(value = "真实姓名", example = "actualName")
    private String actualName;
    @ApiModelProperty(value = "居住地址", example = "address")
    private String address;
    @ApiModelProperty(value = "邮编", example = "zipCode")
    private String zipCode;
    @ApiModelProperty(value = "门牌号", example = "houseNumber")
    private String houseNumber;

    public Integer getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Integer appUserId) {
        this.appUserId = appUserId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActualName() {
        return actualName;
    }

    public void setActualName(String actualName) {
        this.actualName = actualName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return "AddAppUserDto{" +
                "appUserId=" + appUserId +
                ", number='" + number + '\'' +
                ", userName='" + userName + '\'' +
                ", headPic='" + headPic + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", email='" + email + '\'' +
                ", actualName='" + actualName + '\'' +
                ", address='" + address + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                '}';
    }
}
