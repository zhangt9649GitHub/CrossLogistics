package com.siruiman.crosslogistics.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(description = "郭阳")
public class AddTruckOrderDto {
    @ApiModelProperty(value="货车任务订单id", example = "1")
    private Integer taskOrderId;
    @ApiModelProperty(value="货车任务id", example = "1")
    private Integer truckTaskId;
    @ApiModelProperty(value="用户id", example = "1")
    private Integer appUserId;
    @ApiModelProperty(value="货车订单编号", example = "orderNumber")
    private String orderNumber;
    @ApiModelProperty(value="货车订单名称", example = "name")
    private String name;
    @ApiModelProperty(value="订单钱数", example = "money")
    private Double money;
    @ApiModelProperty(value="区域id", example = "1")
    private Integer singaporeAreaId;
    @ApiModelProperty(value="订单状态  1.已创建 2.已预约 3.进行中 4.已确认 5.已完成 6.已取消", example = "1")
    private Integer state;
    @ApiModelProperty(value="订单时间", example = "createTime")
    private String createTime;

    public Integer getTaskOrderId() {
        return taskOrderId;
    }

    public void setTaskOrderId(Integer taskOrderId) {
        this.taskOrderId = taskOrderId;
    }

    public Integer getTruckTaskId() {
        return truckTaskId;
    }

    public void setTruckTaskId(Integer truckTaskId) {
        this.truckTaskId = truckTaskId;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "AddTruckOrderDto{" +
                "taskOrderId=" + taskOrderId +
                ", truckTaskId=" + truckTaskId +
                ", appUserId=" + appUserId +
                ", orderNumber='" + orderNumber + '\'' +
                ", name='" + name + '\'' +
                ", money=" + money +
                ", singaporeAreaId=" + singaporeAreaId +
                ", state=" + state +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
