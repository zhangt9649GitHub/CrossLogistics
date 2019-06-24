package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ApiModel(description="郭阳")
public class AppTaskCarOrderDetails {
    @ApiModelProperty(value = "小车任务订单id", example = "1")
    private Integer taskOrderId;
    @ApiModelProperty(value = "任务订单编号", example = "任务订单编号")
    private String orderNumber;
    @ApiModelProperty(value = "订单名称", example = "订单名称")
    private String name;
    @ApiModelProperty(value = "订单总金额", example = "订单总金额")
    private BigDecimal totalMoney;
    @ApiModelProperty(value = "订单总积分", example = "1")
    private Integer totalIntegral;
    @ApiModelProperty(value = "订单加金额", example = "订单加金额")
    private BigDecimal addMoney;
    @ApiModelProperty(value = "订单加积分", example = "1")
    private Integer addIntegral;
    @ApiModelProperty(value = "订单状态  1.已创建 2.已预约 3.进行中 4.已确认 5.已完成 6.已取消", example = "1")
    private Integer state;
    @ApiModelProperty(value = "订单区域id", example = "1")
    private Integer singaporeAreaId;
    @ApiModelProperty(value = "订单区域名称", example = "订单区域名称")
    private String singaporeAreaName;
    @ApiModelProperty(value = "订单区域经纬度", example = "订单区域经纬度")
    private List<SingaporePoint> singaporePoints = new ArrayList<>();
    @ApiModelProperty(value = "小车集结点id", example = "1")
    private Integer rallyPointId;
    @ApiModelProperty(value = "小车集结点名称", example = "小车集结点名称")
    private String rallyPointName;
    @ApiModelProperty(value = "小车集结点纬度", example = "小车集结点纬度")
    private String rallyPointLat;
    @ApiModelProperty(value = "小车集结点经度", example = "小车集结点经度")
    private String rallyPointLng;
    @ApiModelProperty(value = "送货路线", example = "送货路线")
    private List<AppTaskCarOrderRoute> appTaskCarOrderRoutes = new ArrayList<>();
    @ApiModelProperty(value = "小车id", example = "1")
    private Integer carId;
    @ApiModelProperty(value = "小车编号", example = "小车编号")
    private String carNumber;
    @ApiModelProperty(value = "订单时间", example = "订单时间")
    private String createTime;
    @ApiModelProperty(value = "货物信息", example = "货物信息")
    private List<AppTaskCarOrderBag> appTaskCarOrderBags = new ArrayList<>();

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

    public Integer getRallyPointId() {
        return rallyPointId;
    }

    public void setRallyPointId(Integer rallyPointId) {
        this.rallyPointId = rallyPointId;
    }

    public String getRallyPointName() {
        return rallyPointName;
    }

    public void setRallyPointName(String rallyPointName) {
        this.rallyPointName = rallyPointName;
    }

    public String getRallyPointLat() {
        return rallyPointLat;
    }

    public void setRallyPointLat(String rallyPointLat) {
        this.rallyPointLat = rallyPointLat;
    }

    public String getRallyPointLng() {
        return rallyPointLng;
    }

    public void setRallyPointLng(String rallyPointLng) {
        this.rallyPointLng = rallyPointLng;
    }

    public List<AppTaskCarOrderRoute> getAppTaskCarOrderRoutes() {
        return appTaskCarOrderRoutes;
    }

    public void setAppTaskCarOrderRoutes(List<AppTaskCarOrderRoute> appTaskCarOrderRoutes) {
        this.appTaskCarOrderRoutes = appTaskCarOrderRoutes;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<AppTaskCarOrderBag> getAppTaskCarOrderBags() {
        return appTaskCarOrderBags;
    }

    public void setAppTaskCarOrderBags(List<AppTaskCarOrderBag> appTaskCarOrderBags) {
        this.appTaskCarOrderBags = appTaskCarOrderBags;
    }

    @Override
    public String toString() {
        return "AppTaskCarOrderDetails{" +
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
                ", rallyPointId=" + rallyPointId +
                ", rallyPointName='" + rallyPointName + '\'' +
                ", rallyPointLat='" + rallyPointLat + '\'' +
                ", rallyPointLng='" + rallyPointLng + '\'' +
                ", appTaskCarOrderRoutes=" + appTaskCarOrderRoutes +
                ", carId=" + carId +
                ", carNumber='" + carNumber + '\'' +
                ", createTime='" + createTime + '\'' +
                ", appTaskCarOrderBags=" + appTaskCarOrderBags +
                '}';
    }
}
