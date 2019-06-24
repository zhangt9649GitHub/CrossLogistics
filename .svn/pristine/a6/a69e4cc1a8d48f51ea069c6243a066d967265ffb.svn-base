                package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ApiModel(description="郭阳")
public class TaskOrderDetails {
    @ApiModelProperty(value = "任务订单id", example = "1")
    private Integer taskOrderId;
    @ApiModelProperty(value = "任务订单编号", example = "任务订单编号")
    private String orderNumber;
    @ApiModelProperty(value = "订单名称", example = "订单名称")
    private String name;
    @ApiModelProperty(value = "订单总金额", example = "订单总金额")
    private BigDecimal totalMoney;
    @ApiModelProperty(value = "订单总积分", example = "1")
    private Integer totalIntegral;
    @ApiModelProperty(value = "订单加钱", example = "订单加钱")
    private BigDecimal addMoney;
    @ApiModelProperty(value = "订单加积分", example = "1")
    private Integer addIntegral;
    @ApiModelProperty(value = "订单状态", example = "1")
    private Integer state;
    @ApiModelProperty(value = "订单区域id", example = "1")
    private Integer singaporeAreaId;
    @ApiModelProperty(value = "订单区域名称", example = "订单区域名称")
    private String singaporeAreaName;
    @ApiModelProperty(value = "新加坡区域经纬度", example = "新加坡区域经纬度")
    private List<SingaporePoint> singaporePoints;
    @ApiModelProperty(value = "订单时间", example = "订单时间")
    private String createTime;
    @ApiModelProperty(value = "集结地地点", example = "集结地地点")
    private String rallyPointAddress;
    @ApiModelProperty(value = "集结点纬度", example = "集结点纬度")
    private Double rallyPointLat;
    @ApiModelProperty(value = "集结点经度", example = "集结点经度")
    private Double rallyPointLng;
    @ApiModelProperty(value = "送货路线")
    private List<AppTaskOrderGoods> appTaskOrderGoods = new ArrayList<>();

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getRallyPointAddress() {
        return rallyPointAddress;
    }

    public void setRallyPointAddress(String rallyPointAddress) {
        this.rallyPointAddress = rallyPointAddress;
    }

    public Double getRallyPointLat() {
        return rallyPointLat;
    }

    public void setRallyPointLat(Double rallyPointLat) {
        this.rallyPointLat = rallyPointLat;
    }

    public Double getRallyPointLng() {
        return rallyPointLng;
    }

    public void setRallyPointLng(Double rallyPointLng) {
        this.rallyPointLng = rallyPointLng;
    }

    public List<AppTaskOrderGoods> getAppTaskOrderGoods() {
        return appTaskOrderGoods;
    }

    public void setAppTaskOrderGoods(List<AppTaskOrderGoods> appTaskOrderGoods) {
        this.appTaskOrderGoods = appTaskOrderGoods;
    }

    @Override
    public String toString() {
        return "TaskOrderDetails{" +
                "taskOrderId=" + taskOrderId +
                ", orderNumber='" + orderNumber + '\'' +
                ", name='" + name + '\'' +
                ", totalMoney=" + totalMoney +
                ", totalIntegral=" + totalIntegral +
                ", addMoney=" + addMoney +
                ", addIntegral=" + addIntegral +
                ", state=" + state +
                ", singaporeAreaId=" + singaporeAreaId +
                ", singaporeAreaName='" + singaporeAreaName + '\'' +
                ", singaporePoints=" + singaporePoints +
                ", createTime='" + createTime + '\'' +
                ", rallyPointAddress='" + rallyPointAddress + '\'' +
                ", rallyPointLat=" + rallyPointLat +
                ", rallyPointLng=" + rallyPointLng +
                ", appTaskOrderGoods=" + appTaskOrderGoods +
                '}';
    }
}
