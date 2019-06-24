package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel(description = "郝腾")
public class FinanceMoneyFlow implements Serializable {
    @ApiModelProperty(value = "财务流水id", example = "1")
    private Integer financeMoneyId;

    @ApiModelProperty(value = "金额", example = "98.65")
    private BigDecimal amount;

    @ApiModelProperty(value = "单位(1.人民币 2.美分 3.新币)", example = "1")
    private Integer unit;

    @ApiModelProperty(value = "类型", example = "financeMoneyType")
    private String financeMoneyType;

    @ApiModelProperty(value = "创建时间", example = "createTime")
    private String createTime;

    @ApiModelProperty(value = "创建时间", example = "endTime")
    private String endTime;

    @ApiModelProperty(value = "备注", example = "comment")
    private String comment;

    @ApiModelProperty(value = "单号", example = "serialNumber")
    private String serialNumber;

    @ApiModelProperty(value = "对象", example = "financeObject")
    private String financeObject;


    private static final long serialVersionUID = 1L;


    public Integer getFinanceMoneyId() {
        return financeMoneyId;
    }


    public void setFinanceMoneyId(Integer financeMoneyId) {
        this.financeMoneyId = financeMoneyId;
    }


    public BigDecimal getAmount() {
        return amount;
    }


    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


    public String getFinanceMoneyType() {
        return financeMoneyType;
    }


    public void setFinanceMoneyType(String financeMoneyType) {
        this.financeMoneyType = financeMoneyType == null ? null : financeMoneyType.trim();
    }


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getComment() {
        return comment;
    }


    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }


    public String getSerialNumber() {
        return serialNumber;
    }


    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber == null ? null : serialNumber.trim();
    }


    public String getFinanceObject() {
        return financeObject;
    }


    public void setFinanceObject(String financeObject) {
        this.financeObject = financeObject == null ? null : financeObject.trim();
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", financeMoneyId=").append(financeMoneyId);
        sb.append(", amount=").append(amount);
        sb.append(", financeMoneyType=").append(financeMoneyType);
        sb.append(", createTime=").append(createTime);
        sb.append(", comment=").append(comment);
        sb.append(", serialNumber=").append(serialNumber);
        sb.append(", financeObject=").append(financeObject);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}