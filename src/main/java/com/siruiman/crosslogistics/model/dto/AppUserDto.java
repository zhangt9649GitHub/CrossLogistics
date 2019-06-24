package com.siruiman.crosslogistics.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="郭阳")
public class AppUserDto {
    @ApiModelProperty(value = "搜索", example = "search")
    private String search;
    @ApiModelProperty(value = "性别", example = "sex")
    private String sex;
    @ApiModelProperty(value = "用户状态普通用户 小车用户 货车用户", example = "userStatus")
    private String userStatus;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return "AppUserDto{" +
                "search='" + search + '\'' +
                ", sex='" + sex + '\'' +
                ", userStatus='" + userStatus + '\'' +
                '}';
    }
}
