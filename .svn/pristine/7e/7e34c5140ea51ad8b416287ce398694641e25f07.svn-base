package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.Objects;

/**
 * @author 张占伟
 * @date 2019/1/2 18:47
 */
@ApiModel(description = "张占伟")
public class SingaporePoint {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingaporePoint that = (SingaporePoint) o;
        return
                Objects.equals(lat, that.lat) &&
                Objects.equals(lng, that.lng);
    }

    @Override
    public int hashCode() {

        return Objects.hash(singaporeAreaId, singaporePointId, lat, lng, delState, createTime);
    }

    @ApiModelProperty(value="新加坡地区id", example = "1")
    private Integer singaporeAreaId;

    @ApiModelProperty(value="顶点id", example = "1")
    private Integer singaporePointId;

    @ApiModelProperty(value="定点纬度", example = "lat")
    private String lat;

    @ApiModelProperty(value="定点经度", example = "lng")
    private String lng;

    @ApiModelProperty(value="删除状态", example = "1")
    private Integer delState;

    @ApiModelProperty(value="创建时间", example = "创建时间")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getSingaporeAreaId() {
        return singaporeAreaId;
    }

    public void setSingaporeAreaId(Integer singaporeAreaId) {
        this.singaporeAreaId = singaporeAreaId;
    }

    public Integer getSingaporePointId() {
        return singaporePointId;
    }

    public void setSingaporePointId(Integer singaporePointId) {
        this.singaporePointId = singaporePointId;
    }

    public String getLat() {
        return lat;
    }


    public void setLat(String lat) {
        this.lat = lat == null ? null : lat.trim();
    }


    public String getLng() {
        return lng;
    }


    public void setLng(String lng) {
        this.lng = lng == null ? null : lng.trim();
    }


    public Integer getDelState() {
        return delState;
    }


    public void setDelState(Integer delState) {
        this.delState = delState;
    }


    public Date getCreateTime() {
        return createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", singaporeAreaId=").append(singaporeAreaId);
        sb.append(", singaporePointId=").append(singaporePointId);
        sb.append(", lat=").append(lat);
        sb.append(", lng=").append(lng);
        sb.append(", delState=").append(delState);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
