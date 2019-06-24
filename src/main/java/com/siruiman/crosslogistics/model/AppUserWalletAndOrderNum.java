package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(description = "郭阳")
public class AppUserWalletAndOrderNum {
    @ApiModelProperty(value="余额", example = "1")
    private BigDecimal commissionAmount;
    @ApiModelProperty(value="积分", example = "1")
    private Integer integral;
    @ApiModelProperty(value="完成订单数", example = "1")
    private Integer completeOrderNum;

    public BigDecimal getCommissionAmount() {
        return commissionAmount;
    }

    public void setCommissionAmount(BigDecimal commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getCompleteOrderNum() {
        return completeOrderNum;
    }

    public void setCompleteOrderNum(Integer completeOrderNum) {
        this.completeOrderNum = completeOrderNum;
    }

    @Override
    public String toString() {
        return "AppUserWalletAndOrderNum{" +
                "commissionAmount=" + commissionAmount +
                ", integral=" + integral +
                ", completeOrderNum=" + completeOrderNum +
                '}';
    }
}
