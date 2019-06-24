package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;


@ApiModel(description = "郭阳")
public class OrderRecord {
    @ApiModelProperty(value="订单编号", example = "订单编号")
    private String orderNumber;
    @ApiModelProperty(value="订单金额", example = "订单金额")
    private BigDecimal totalMoney;
    @ApiModelProperty(value="订单积分", example = "1")
    private Integer totalIntegral;
    @ApiModelProperty(value="订单时间", example = "订单时间")
    private String createTime;
    @ApiModelProperty(value="订单状态  1.已创建 2.已预约 3.进行中 4.已确认 5.已完成 6.已取消", example = "1")
    private Integer state;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "OrderRecord{" +
                "orderNumber='" + orderNumber + '\'' +
                ", totalMoney=" + totalMoney +
                ", totalIntegral=" + totalIntegral +
                ", createTime='" + createTime + '\'' +
                ", state=" + state +
                '}';
    }
}
