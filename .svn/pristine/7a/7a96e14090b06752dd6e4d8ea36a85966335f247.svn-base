package com.siruiman.crosslogistics.model.dto;

import com.siruiman.crosslogistics.model.Bag;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Objects;

/**
 * @author 张占伟
 * @date 2019/1/9 14:16
 */
@ApiModel(description = "张占伟")
public class TruckDriverOrder {

    @ApiModelProperty(value="大货车司机id", example = "1")
    private Integer appUserId;

    @ApiModelProperty(value="大货车司机名字", example = "王师傅")
    private String userTrueName;

    @ApiModelProperty(value="大货车司机所抢订单id", example = "1")
    private int taskOrderId;

    @ApiModelProperty(value="大货车司机所抢订单编号", example = "王师傅")
    private String orderNumber;

    @ApiModelProperty(value="司机联系方式", example = "18787878777")
    private String mobile;

    @ApiModelProperty(value="货车牌照", example = "X-8888")
    private String licensePlate;

    @ApiModelProperty(value="集结点id", example = "1")
    private int rallyPointId;

    @ApiModelProperty(value="所抢区域现有未运输货袋")
    private List<Bag> bags;

    @ApiModelProperty(value="1")
    private int  state;



    @ApiModelProperty(value="（1正常 2货车送货）",example = "1")

    private int  orderType;

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public Integer getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Integer appUserId) {
        this.appUserId = appUserId;
    }

    public String getUserTrueName() {
        return userTrueName;
    }

    public void setUserTrueName(String userTrueName) {
        this.userTrueName = userTrueName;
    }

    public int getTaskOrderId() {
        return taskOrderId;
    }

    public void setTaskOrderId(int taskOrderId) {
        this.taskOrderId = taskOrderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getRallyPointId() {
        return rallyPointId;
    }

    public void setRallyPointId(int rallyPointId) {
        this.rallyPointId = rallyPointId;
    }

    public List<Bag> getBags() {
        return bags;
    }

    public void setBags(List<Bag> bags) {
        this.bags = bags;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TruckDriverOrder that = (TruckDriverOrder) o;
        return taskOrderId == that.taskOrderId &&
                rallyPointId == that.rallyPointId &&
                state == that.state &&
                Objects.equals(appUserId, that.appUserId) &&
                Objects.equals(userTrueName, that.userTrueName) &&
                Objects.equals(orderNumber, that.orderNumber) &&
                Objects.equals(mobile, that.mobile) &&
                Objects.equals(licensePlate, that.licensePlate) &&
                Objects.equals(bags, that.bags);
    }

    @Override
    public int hashCode() {

        return Objects.hash(appUserId, userTrueName, taskOrderId, orderNumber, mobile, licensePlate, rallyPointId, bags, state);
    }

    @Override
    public String toString() {
        return "TruckDriverOrder{" +
                "appUserId=" + appUserId +
                ", userTrueName='" + userTrueName + '\'' +
                ", taskOrderId=" + taskOrderId +
                ", orderNumber='" + orderNumber + '\'' +
                ", mobile='" + mobile + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", rallyPointId=" + rallyPointId +
                ", bags=" + bags +
                ", state=" + state +
                '}';
    }

    public TruckDriverOrder() {
        super();
    }
}
