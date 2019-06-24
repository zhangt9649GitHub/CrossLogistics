package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ApiModel(description="郭阳")
public class AppTruckOrderDetails {
    @ApiModelProperty(value = "货车任务订单id", example = "1")
    private Integer taskOrderId;
    @ApiModelProperty(value = "用户id", example = "1")
    private Integer appUserId;
    @ApiModelProperty(value = "货车任务订单编号", example = "货车任务订单编号")
    private String orderNumber;
    @ApiModelProperty(value = "货车任务订单名称", example = "货车任务订单名称")
    private String name;
    @ApiModelProperty(value = "订单钱数", example = "1")
    private BigDecimal totalMoney;
    @ApiModelProperty(value = "订单积分数", example = "1")
    private Integer totalIntegral;
    @ApiModelProperty(value = "订单加钱数", example = "1")
    private BigDecimal addMoney;
    @ApiModelProperty(value = "订单加积分数", example = "1")
    private Integer addIntegral;
    @ApiModelProperty(value = "订单状态", example = "1")
    private Integer state;
    @ApiModelProperty(value = "订单时间", example = "订单时间")
    private String createTime;
    @ApiModelProperty(value = "区域id", example = "1")
    private Integer singaporeAreaId;
    @ApiModelProperty(value = "区域名称", example = "区域名称")
    private String singaporeAreaName;
    @ApiModelProperty(value = "区域经纬度", example = "区域经纬度")
    private List<SingaporePoint> singaporePoints = new ArrayList<>();
    @ApiModelProperty(value = "路线", example = "路线")
    private List<TruckTaskRoute> truckTaskRoutes = new ArrayList<>();
    @ApiModelProperty(value = "货袋", example = "货袋")
    private List<AppTruckOrderBag> appTruckOrderBags = new ArrayList<>();

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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

    public List<SingaporePoint> getSingaporePoints() {
        return singaporePoints;
    }

    public void setSingaporePoints(List<SingaporePoint> singaporePoints) {
        this.singaporePoints = singaporePoints;
    }

    public List<TruckTaskRoute> getTruckTaskRoutes() {
        return truckTaskRoutes;
    }

    public void setTruckTaskRoutes(List<TruckTaskRoute> truckTaskRoutes) {
        this.truckTaskRoutes = truckTaskRoutes;
    }

    public List<AppTruckOrderBag> getAppTruckOrderBags() {
        return appTruckOrderBags;
    }

    public void setAppTruckOrderBags(List<AppTruckOrderBag> appTruckOrderBags) {
        this.appTruckOrderBags = appTruckOrderBags;
    }

    @Override
    public String toString() {
        return "AppTruckOrderDetails{" +
                "taskOrderId=" + taskOrderId +
                ", appUserId=" + appUserId +
                ", orderNumber='" + orderNumber + '\'' +
                ", name='" + name + '\'' +
                ", totalMoney=" + totalMoney +
                ", totalIntegral=" + totalIntegral +
                ", addMoney=" + addMoney +
                ", addIntegral=" + addIntegral +
                ", state=" + state +
                ", createTime='" + createTime + '\'' +
                ", singaporeAreaId=" + singaporeAreaId +
                ", singaporeAreaName='" + singaporeAreaName + '\'' +
                ", singaporePoints=" + singaporePoints +
                ", truckTaskRoutes=" + truckTaskRoutes +
                ", appTruckOrderBags=" + appTruckOrderBags +
                '}';
    }
}
