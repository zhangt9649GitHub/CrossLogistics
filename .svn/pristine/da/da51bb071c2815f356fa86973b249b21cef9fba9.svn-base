package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 张占伟
 * @date 2018/12/19
 */
@ApiModel(description = "张占伟")
public class SingaporeArea implements Serializable {
    @ApiModelProperty(value="新加坡地区id", example = "0")
    private Integer singaporeAreaId;

    public SingaporeArea() {
        super();
    }
    @ApiModelProperty(value="新加坡地区名字", example = "dsafa")
    private String singaporeAreaName;

    @ApiModelProperty(value="新加坡区域经纬度列表", example = "1234.132.1213.13")
    private List<SingaporePoint> singaporePoints = new ArrayList<>();

    @ApiModelProperty(value="是否设置 是为1 否为2 ", example = "1")
    private Integer isDefault;

    public List<SingaporePoint> getSingaporePoints() {
        return singaporePoints;
    }

    public void setSingaporePoints(List<SingaporePoint> singaporePoints) {
        this.singaporePoints = singaporePoints;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public SingaporeArea(Integer singaporeAreaId, String singaporeAreaName,  List<SingaporePoint> singaporePoints) {
        this.singaporeAreaId = singaporeAreaId;
        this.singaporeAreaName = singaporeAreaName;
        this.singaporePoints = singaporePoints;
    }

    private static final long serialVersionUID = 1L;


    public Integer getSingaporeAreaId() {
        return singaporeAreaId;
    }

    public void setSingaporeAreaId(Integer singaporeAreaId) {
        this.singaporeAreaId = singaporeAreaId;
    }

    public String getSingaporeAreaName() {
        return singaporeAreaName;
    }

    public void setSingaporeAreaName(String singaporeAreaName) {
        this.singaporeAreaName = singaporeAreaName == null ? null : singaporeAreaName.trim();
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", singaporeAreaId=").append(singaporeAreaId);
        sb.append(", singaporeAreaName=").append(singaporeAreaName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}