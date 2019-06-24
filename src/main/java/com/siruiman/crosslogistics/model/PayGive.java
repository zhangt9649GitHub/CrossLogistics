package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel(description = "郝腾")
public class PayGive implements Serializable {
    @ApiModelProperty(value = "工资发放id", example = "1")
    private Integer payGiveId;

    @ApiModelProperty(value = "金额", example = "1")
    private BigDecimal giveMoney;

    @ApiModelProperty(value = "员工id", example = "1")
    private Integer staffId;

    @ApiModelProperty(value = "工资发放月份", example = "moneyGiveMonth")
    private String moneyGiveMonth;

    @ApiModelProperty(value = "工资发放时间", example = "addTime")
    private String addTime;

    @ApiModelProperty(value = "搜索结束时间", example = "endTime")
    private String endTime;

    @ApiModelProperty(value = "员工姓名", example = "staffName")
    private String staffName;

    @ApiModelProperty(value = "员工编号", example = "12345678")
    private String number;


    private static final long serialVersionUID = 1L;


    public Integer getPayGiveId() {
        return payGiveId;
    }


    public void setPayGiveId(Integer payGiveId) {
        this.payGiveId = payGiveId;
    }


    public BigDecimal getGiveMoney() {
        return giveMoney;
    }


    public void setGiveMoney(BigDecimal giveMoney) {
        this.giveMoney = giveMoney;
    }


    public Integer getStaffId() {
        return staffId;
    }


    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }


    public String getMoneyGiveMonth() {
        return moneyGiveMonth;
    }

    public void setMoneyGiveMonth(String moneyGiveMonth) {
        this.moneyGiveMonth = moneyGiveMonth;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", payGiveId=").append(payGiveId);
        sb.append(", giveMoney=").append(giveMoney);
        sb.append(", staffId=").append(staffId);
        sb.append(", moneyGiveMonth=").append(moneyGiveMonth);
        sb.append(", addTime=").append(addTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}