package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@ApiModel(description = "郝腾")
public class PositionInfo implements Serializable {
    @ApiModelProperty(value="实时定位id", example = "0")
    private Integer piId;

    @ApiModelProperty(value="小车id", example = "0")
    private Integer carId;

    @ApiModelProperty(value="货车id", example = "0")
    private Integer truckId;

    @ApiModelProperty(value="纬度", example = "1")
    private BigDecimal latitude;

    @ApiModelProperty(value="经度", example = "1")
    private BigDecimal longitude;

    @ApiModelProperty(value="添加时间", example = "yyyy-MM-dd-HH-mm-ss")
    private Date addtime;

    @ApiModelProperty(value="用户id", example = "0")
    private Integer appUserId;

    @ApiModelProperty(value="用户类型（小车 货车）", example = "0")
    private String userType;

    @ApiModelProperty(value="小车任务订单id", example = "1")
    private Integer taskOrderId;


    private static final long serialVersionUID = 1L;


    public Integer getPiId() {
        return piId;
    }


    public void setPiId(Integer piId) {
        this.piId = piId;
    }


    public Integer getCarId() {
        return carId;
    }


    public void setCarId(Integer carId) {
        this.carId = carId;
    }


    public Integer getTruckId() {
        return truckId;
    }


    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Date getAddtime() {
        return addtime;
    }


    public void setAddtime(Date addtime) {
        this.addtime = addtime;
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
        this.userType = userType;
    }

    public Integer getTaskOrderId() {
        return taskOrderId;
    }

    public void setTaskOrderId(Integer taskOrderId) {
        this.taskOrderId = taskOrderId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", piId=").append(piId);
        sb.append(", carId=").append(carId);
        sb.append(", truckId=").append(truckId);
        sb.append(", addtime=").append(addtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}