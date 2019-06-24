package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author 张占伟
 * @date 2019/3/24 11:24
 */

public class AppCarOrder {
    @ApiModelProperty(value = "货车任务订单id", example = "1")
    private Integer taskOrderId;
    @ApiModelProperty(value = "用户id", example = "1")
    private Integer appUserId;
    @ApiModelProperty(value = "用户名称", example = "userName")
    private String userName;
    @ApiModelProperty(value = "小车任务订单编号", example = "货车任务订单编号")
    private String orderNumber;
    @ApiModelProperty(value = "小车任务订单名称", example = "货车任务订单名称")
    private String orderName;
    @ApiModelProperty(value = "订单钱数", example = "订单钱数")
    private BigDecimal totalMoney;
    @ApiModelProperty(value = "订单积分数", example = "1")
    private Integer totalIntegral;
    @ApiModelProperty(value = "订单加钱数", example = "订单钱数")
    private BigDecimal addMoney;
    @ApiModelProperty(value = "订单加积分数", example = "1")
    private Integer addIntegral;
    @ApiModelProperty(value = "订单状态", example = "1")
    private Integer state;
    @ApiModelProperty(value = "订单时间", example = "订单时间")
    private String createTime;

    public AppCarOrder() {
        super();
    }

    @Override
    public String toString() {
        return "AppCarOrder{" +
                "taskOrderId=" + taskOrderId +
                ", appUserId=" + appUserId +
                ", userName='" + userName + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", orderName='" + orderName + '\'' +
                ", totalMoney=" + totalMoney +
                ", totalIntegral=" + totalIntegral +
                ", addMoney=" + addMoney +
                ", addIntegral=" + addIntegral +
                ", state=" + state +
                ", createTime='" + createTime + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppCarOrder that = (AppCarOrder) o;
        return Objects.equals(taskOrderId, that.taskOrderId) &&
                Objects.equals(appUserId, that.appUserId) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(orderNumber, that.orderNumber) &&
                Objects.equals(orderName, that.orderName) &&
                Objects.equals(totalMoney, that.totalMoney) &&
                Objects.equals(totalIntegral, that.totalIntegral) &&
                Objects.equals(addMoney, that.addMoney) &&
                Objects.equals(addIntegral, that.addIntegral) &&
                Objects.equals(state, that.state) &&
                Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(taskOrderId, appUserId, userName, orderNumber, orderName, totalMoney, totalIntegral, addMoney, addIntegral, state, createTime);
    }

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

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public AppCarOrder(Integer taskOrderId, Integer appUserId, String userName, String orderNumber, String orderName, BigDecimal totalMoney, Integer totalIntegral, BigDecimal addMoney, Integer addIntegral, Integer state, String createTime) {
        this.taskOrderId = taskOrderId;
        this.appUserId = appUserId;
        this.userName = userName;
        this.orderNumber = orderNumber;
        this.orderName = orderName;
        this.totalMoney = totalMoney;
        this.totalIntegral = totalIntegral;
        this.addMoney = addMoney;
        this.addIntegral = addIntegral;
        this.state = state;
        this.createTime = createTime;
    }
}
