package com.siruiman.crosslogistics.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="郭阳")
public class TruckOrderDto {
    /*@ApiModelProperty(value = "定位纬度", example = "1")
    private double positionLat;
    @ApiModelProperty(value = "定位经度", example = "1")
    private double positionLng;*/
    @ApiModelProperty(value = "订单状态0全部 1.已创建 2.已预约 3.进行中 5.已完成 7 已结算", example = "0")
    private Integer taskOrderStatus;
    @ApiModelProperty(value = "订单编号", example = "订单编号")
    private String orderNumber;
    @ApiModelProperty(value = "区域id", example = "1")
    private Integer singaporeAreaId;
    @ApiModelProperty(value = "小车集结点id", example = "1")
    private Integer rallyPointId;
    /*@ApiModelProperty(value = "小车集结点id", example = "1")
    private int carAssemblyPoint;*/
    @ApiModelProperty(value = "日期", example = "日期")
    private String orderTime;
    /*@ApiModelProperty(value = "距离（米）", example = "距离（米）")
    private int distance;*/

    public Integer getTaskOrderStatus() {
        return taskOrderStatus;
    }

    public void setTaskOrderStatus(Integer taskOrderStatus) {
        this.taskOrderStatus = taskOrderStatus;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getSingaporeAreaId() {
        return singaporeAreaId;
    }

    public void setSingaporeAreaId(Integer singaporeAreaId) {
        this.singaporeAreaId = singaporeAreaId;
    }

    public Integer getRallyPointId() {
        return rallyPointId;
    }

    public void setRallyPointId(Integer rallyPointId) {
        this.rallyPointId = rallyPointId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public String toString() {
        return "TruckOrderDto{" +
                "taskOrderStatus=" + taskOrderStatus +
                ", orderNumber='" + orderNumber + '\'' +
                ", singaporeAreaId=" + singaporeAreaId +
                ", rallyPointId=" + rallyPointId +
                ", orderTime='" + orderTime + '\'' +
                '}';
    }
}
