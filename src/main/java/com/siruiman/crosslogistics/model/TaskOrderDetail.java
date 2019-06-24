package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

@ApiModel(description="郭阳")
public class TaskOrderDetail {
    @ApiModelProperty(value = "任务订单id", example = "1")
    private Integer taskOrderId;
    @ApiModelProperty(value = "任务订单编号", example = "orderNumber")
    private String orderNumber;
    @ApiModelProperty(value = "订单名称", example = "name")
    private String name;
    @ApiModelProperty(value = "订单总金额", example = "0.00")
    private BigDecimal totalMoney;
    @ApiModelProperty(value = "订单总积分", example = "0")
    private Integer totalIntegral;
    @ApiModelProperty(value = "订单加金额", example = "0.00")
    private BigDecimal addMoney;
    @ApiModelProperty(value = "订单加积分", example = "0")
    private Integer addIntegral;
    @ApiModelProperty(value = "订单状态", example = "0")
    private Integer state;
    @ApiModelProperty(value = "订单类型", example = "createTime")
    private String type;
    @ApiModelProperty(value = "订单区域名称", example = "singaporeAreaName")
    private String singaporeAreaName;
    @ApiModelProperty(value = "订单区域经纬度", example = "singaporeAreaAtitudeLongitude")
    private String singaporeAreaAtitudeLongitude;
    @ApiModelProperty(value = "送货路线")
    private List<TaskOrderDeliveryRoute> taskOrderDeliveryRoutes;
    @ApiModelProperty(value = "货袋信息")
    private List<TaskOrderBag> taskOrderBags;

    public Integer getTaskOrderId() {
        return taskOrderId;
    }

    public void setTaskOrderId(Integer taskOrderId) {
        this.taskOrderId = taskOrderId;
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

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getTotalIntegral() {
        return totalIntegral;
    }

    public void setTotalIntegral(Integer totalIntegral) {
        this.totalIntegral = totalIntegral;
    }

    public BigDecimal getAddMoney() {
        return addMoney;
    }

    public void setAddMoney(BigDecimal addMoney) {
        this.addMoney = addMoney;
    }

    public Integer getAddIntegral() {
        return addIntegral;
    }

    public void setAddIntegral(Integer addIntegral) {
        this.addIntegral = addIntegral;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSingaporeAreaName() {
        return singaporeAreaName;
    }

    public void setSingaporeAreaName(String singaporeAreaName) {
        this.singaporeAreaName = singaporeAreaName;
    }

    public String getSingaporeAreaAtitudeLongitude() {
        return singaporeAreaAtitudeLongitude;
    }

    public void setSingaporeAreaAtitudeLongitude(String singaporeAreaAtitudeLongitude) {
        this.singaporeAreaAtitudeLongitude = singaporeAreaAtitudeLongitude;
    }

    public List<TaskOrderDeliveryRoute> getTaskOrderDeliveryRoutes() {
        return taskOrderDeliveryRoutes;
    }

    public void setTaskOrderDeliveryRoutes(List<TaskOrderDeliveryRoute> taskOrderDeliveryRoutes) {
        this.taskOrderDeliveryRoutes = taskOrderDeliveryRoutes;
    }

    public List<TaskOrderBag> getTaskOrderBags() {
        return taskOrderBags;
    }

    public void setTaskOrderBags(List<TaskOrderBag> taskOrderBags) {
        this.taskOrderBags = taskOrderBags;
    }

    @Override
    public String toString() {
        return "TaskOrderDetail{" +
                "taskOrderId=" + taskOrderId +
                ", orderNumber='" + orderNumber + '\'' +
                ", name='" + name + '\'' +
                ", totalMoney=" + totalMoney +
                ", totalIntegral=" + totalIntegral +
                ", addMoney=" + addMoney +
                ", addIntegral=" + addIntegral +
                ", state=" + state +
                ", type='" + type + '\'' +
                ", singaporeAreaName='" + singaporeAreaName + '\'' +
                ", singaporeAreaAtitudeLongitude='" + singaporeAreaAtitudeLongitude + '\'' +
                ", taskOrderDeliveryRoutes=" + taskOrderDeliveryRoutes +
                ", taskOrderBags=" + taskOrderBags +
                '}';
    }
}
