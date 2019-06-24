package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel(description = "郝腾")
public class AppUserPreferencesArea implements Serializable {
    @ApiModelProperty(value = "偏好地区id", example = "1")
    private Integer preferencesId;

    @ApiModelProperty(value = "app用户id", example = "1")
    private Integer appUserId;

    @ApiModelProperty(value = "用户类型（小车 货车）", example = "userType")
    private String userType;

    @ApiModelProperty(value = "选择的偏好区域id", example = "1")
    private Integer singaporeAreaId;

    @ApiModelProperty(value = "创建时间", example = "addTime")
    private Date addTime;

    @ApiModelProperty(value = "修改时间", example = "updateTime")
    private Date updateTime;


    private static final long serialVersionUID = 1L;


    public Integer getPreferencesId() {
        return preferencesId;
    }


    public void setPreferencesId(Integer preferencesId) {
        this.preferencesId = preferencesId;
    }


    public Integer getAppUserId() {
        return appUserId;
    }


    public void setAppUserId(Integer appUserId) {
        this.appUserId = appUserId;
    }


    public String getUserType() {
        return userType;
    }


    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }


    public Integer getSingaporeAreaId() {
        return singaporeAreaId;
    }


    public void setSingaporeAreaId(Integer singaporeAreaId) {
        this.singaporeAreaId = singaporeAreaId;
    }


    public Date getAddTime() {
        return addTime;
    }


    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }


    public Date getUpdateTime() {
        return updateTime;
    }


    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", preferencesId=").append(preferencesId);
        sb.append(", appUserId=").append(appUserId);
        sb.append(", userType=").append(userType);
        sb.append(", singaporeAreaId=").append(singaporeAreaId);
        sb.append(", addTime=").append(addTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}