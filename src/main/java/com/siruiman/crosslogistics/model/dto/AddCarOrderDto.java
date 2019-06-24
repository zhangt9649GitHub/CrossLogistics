package com.siruiman.crosslogistics.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "郭阳")
public class AddCarOrderDto {
    @ApiModelProperty(value="小车任务id", example = "1")
    private Integer carTaskId;
    @ApiModelProperty(value="用户id", example = "0")
    private Integer appUserId;
    @ApiModelProperty(value="订单号", example = "orderNumber")
    private String orderNumber;
    @ApiModelProperty(value="订单名称", example = "name")
    private String name;
    @ApiModelProperty(value="订单金额", example = "1")
    private Double money;
    @ApiModelProperty(value="区域id", example = "0")
    private Integer singaporeAreaId;
    @ApiModelProperty(value="小车集结点id", example = "0")
    private Integer rallyPointId;
    @ApiModelProperty(value="小车id", example = "0")
    private Integer carId;
    @ApiModelProperty(value = "时间", example = "createTime")
    private String createTime;

    public Integer getCarTaskId() {
        return carTaskId;
    }

    public void setCarTaskId(Integer carTaskId) {
        this.carTaskId = carTaskId;
    }

    public Integer getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Integer appUserId) {
        this.appUserId = appUserId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
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

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "AddCarOrderDto{" +
                "carTaskId=" + carTaskId +
                ", appUserId=" + appUserId +
                ", orderNumber='" + orderNumber + '\'' +
                ", name='" + name + '\'' +
                ", money=" + money +
                ", singaporeAreaId=" + singaporeAreaId +
                ", rallyPointId=" + rallyPointId +
                ", carId=" + carId +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
