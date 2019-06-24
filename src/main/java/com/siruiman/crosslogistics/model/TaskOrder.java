package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(description="郭阳")
public class TaskOrder {
    @ApiModelProperty(value = "任务订单id", example = "0")
    private Integer taskOrderId;
    @ApiModelProperty(value = "用户id", example = "0")
    private Integer appUserId;
    @ApiModelProperty(value = "用户名称", example = "userName")
    private String userName;
    @ApiModelProperty(value = "任务订单编号", example = "orderNumber")
    private String orderNumber;
    @ApiModelProperty(value = "订单名称", example = "name")
    private String name;
    @ApiModelProperty(value = "订单总金额", example = "0.00")
    private BigDecimal totalMoney;
    @ApiModelProperty(value = "订单总积分", example = "0")
    private Integer totalIntegral;
    @ApiModelProperty(value = "订单加钱", example = "0.00")
    private BigDecimal addMoney;
    @ApiModelProperty(value = "订单加积分", example = "0")
    private Integer addIntegral;
    @ApiModelProperty(value = "订单状态", example = "0")
    private Integer state;
    @ApiModelProperty(value = "订单区域id", example = "0")
    private Integer singaporeAreaId;
    @ApiModelProperty(value = "订单区域名称", example = "singaporeAreaName")
    private String singaporeAreaName;
    @ApiModelProperty(value = "新加坡区域经纬度", example = "singaporeAreaAtitudeLongitude")
    private String singaporeAreaAtitudeLongitude;
    @ApiModelProperty(value = "订单时间", example = "createTime")
    private String createTime;
    @ApiModelProperty(value = "抢单状态", example = "0")
    private Integer grabOrderStatus;
    @ApiModelProperty(value = "抢单时间", example = "grabOrderTime")
    private String grabOrderTime;
    @ApiModelProperty(value = "小车编号", example = "carNumber")
    private String carNumber;

    public Integer getTaskOrderId() {
        return taskOrderId;
    }

    public void setTaskOrderId(Integer taskOrderId) {
        this.taskOrderId = taskOrderId;
    }

    public Integer getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Integer appUserId) {
        this.appUserId = appUserId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
        this.singaporeAreaName = singaporeAreaName;
    }

    public String getSingaporeAreaAtitudeLongitude() {
        return singaporeAreaAtitudeLongitude;
    }

    public void setSingaporeAreaAtitudeLongitude(String singaporeAreaAtitudeLongitude) {
        this.singaporeAreaAtitudeLongitude = singaporeAreaAtitudeLongitude;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getGrabOrderStatus() {
        return grabOrderStatus;
    }

    public void setGrabOrderStatus(Integer grabOrderStatus) {
        this.grabOrderStatus = grabOrderStatus;
    }

    public String getGrabOrderTime() {
        return grabOrderTime;
    }

    public void setGrabOrderTime(String grabOrderTime) {
        this.grabOrderTime = grabOrderTime;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    @Override
    public String toString() {
        return "TaskOrder{" +
                "taskOrderId=" + taskOrderId +
                ", appUserId=" + appUserId +
                ", userName='" + userName + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", name='" + name + '\'' +
                ", totalMoney=" + totalMoney +
                ", totalIntegral=" + totalIntegral +
                ", addMoney=" + addMoney +
                ", addIntegral=" + addIntegral +
                ", state=" + state +
                ", singaporeAreaId=" + singaporeAreaId +
                ", singaporeAreaName='" + singaporeAreaName + '\'' +
                ", singaporeAreaAtitudeLongitude='" + singaporeAreaAtitudeLongitude + '\'' +
                ", createTime='" + createTime + '\'' +
                ", grabOrderStatus=" + grabOrderStatus +
                ", grabOrderTime='" + grabOrderTime + '\'' +
                ", carNumber='" + carNumber + '\'' +
                '}';
    }
}
