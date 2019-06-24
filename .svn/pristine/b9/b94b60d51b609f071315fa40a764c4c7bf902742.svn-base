package com.siruiman.crosslogistics.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="郭阳")
public class AppTaskOrderDto {
    @ApiModelProperty(value = "app用户id", example = "0")
    private Integer appUserId;
    @ApiModelProperty(value = "定位纬度", example = "1")
    private Double positionLat = 1.288942;
    @ApiModelProperty(value = "定位经度", example = "1")
    private Double positionLng = 103.847982;
    @ApiModelProperty(value = "订单状态0全部 1可抢订单 2已被抢订单", example = "1")
    private Integer taskOrderStatus = 0;
    @ApiModelProperty(value = "排序状态0默认 1时间正序 2时间倒序 3距离正序 4距离倒序", example = "1")
    private Integer sort = 0;
    @ApiModelProperty(value = "区域id", example = "1")
    private Integer singaporeAreaId;
    /*@ApiModelProperty(value = "小车集结点id", example = "小车集结点id")
    private int carAssemblyPoint;*/
    @ApiModelProperty(value = "日期", example = "日期")
    private String orderTime;
    @ApiModelProperty(value = "距离（米）", example = "1")
    private Integer distance = 0;

    @ApiModelProperty(value = "自用时间参数", example = "自用时间参数")
    private String nowTime;

    public Integer getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Integer appUserId) {
        this.appUserId = appUserId;
    }

    public Double getPositionLat() {
        return positionLat;
    }

    public void setPositionLat(Double positionLat) {
        this.positionLat = positionLat;
    }

    public Double getPositionLng() {
        return positionLng;
    }

    public void setPositionLng(Double positionLng) {
        this.positionLng = positionLng;
    }

    public Integer getTaskOrderStatus() {
        return taskOrderStatus;
    }

    public void setTaskOrderStatus(Integer taskOrderStatus) {
        this.taskOrderStatus = taskOrderStatus;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSingaporeAreaId() {
        return singaporeAreaId;
    }

    public void setSingaporeAreaId(Integer singaporeAreaId) {
        this.singaporeAreaId = singaporeAreaId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getNowTime() {
        return nowTime;
    }

    public void setNowTime(String nowTime) {
        this.nowTime = nowTime;
    }

    @Override
    public String toString() {
        return "AppTaskOrderDto{" +
                "appUserId=" + appUserId +
                ", positionLat=" + positionLat +
                ", positionLng=" + positionLng +
                ", taskOrderStatus=" + taskOrderStatus +
                ", sort=" + sort +
                ", singaporeAreaId=" + singaporeAreaId +
                ", orderTime='" + orderTime + '\'' +
                ", distance=" + distance +
                ", nowTime='" + nowTime + '\'' +
                '}';
    }
}
