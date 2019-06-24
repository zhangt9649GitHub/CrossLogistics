package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description="郭阳")
public class ReceivingPoint  implements Serializable, Comparable<ReceivingPoint> {
    @ApiModelProperty(value = "收货点邮编", example = "收货点邮编")
    private String zipCode;
    @ApiModelProperty(value = "大楼名称", example = "大楼名称")
    private String saBuildingName;
    @ApiModelProperty(value = "收货点纬度", example = "收货点纬度")
    private String lat;
    @ApiModelProperty(value = "收货点经度", example = "收货点经度")
    private String lng;
    @ApiModelProperty(value = "是否完成(1完成)", example = "1")
    private Integer isComplete = 0;

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getSaBuildingName() {
        return saBuildingName;
    }

    public void setSaBuildingName(String saBuildingName) {
        this.saBuildingName = saBuildingName;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public Integer getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(Integer isComplete) {
        this.isComplete = isComplete;
    }

    @Override
    public String toString() {
        return "ReceivingPoint{" +
                "zipCode='" + zipCode + '\'' +
                ", saBuildingName='" + saBuildingName + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", isComplete=" + isComplete +
                '}';
    }

    @Override
    public int compareTo(ReceivingPoint o) {
        int i = this.getIsComplete() - o.getIsComplete();
        return i;
    }
}
