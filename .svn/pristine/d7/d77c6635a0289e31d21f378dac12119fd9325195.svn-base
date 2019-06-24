package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel(description = "郝腾")
public class BankCard implements Serializable {
    @ApiModelProperty(value = "银行卡id", example = "1")
    private Integer bankCardId;

    @ApiModelProperty(value = "发卡银行", example = "bankName")
    private String bankName;

    @ApiModelProperty(value = "卡号", example = "cardNumber")
    private String cardNumber;

    @ApiModelProperty(value = "账户名称", example = "cardName")
    private String cardName;

    @ApiModelProperty(value = "开户行", example = "openingBank")
    private String openingBank;

    @ApiModelProperty(value = "是否是默认 1.是 2.不是", example = "1")
    private Integer isDefault;

    @ApiModelProperty(value = "app用户id", example = "1")
    private Integer appUserId;

    @ApiModelProperty(value = "员工id", example = "1")
    private Integer staffId;

    @ApiModelProperty(value = "添加时间", example = "addTime")
    private Date addTime;

    @ApiModelProperty(value = "修改时间", example = "updatetime")
    private Date updateTime;

    @ApiModelProperty(value = "提现金额", example = "1")
    private BigDecimal withdrawMoney;

    @ApiModelProperty(value = "删除状态", example = "1")
    private Integer status;

    private static final long serialVersionUID = 1L;

    public BigDecimal getWithdrawMoney() {
        return withdrawMoney;
    }

    public void setWithdrawMoney(BigDecimal withdrawMoney) {
        this.withdrawMoney = withdrawMoney;
    }

    public Integer getBankCardId() {
        return bankCardId;
    }


    public void setBankCardId(Integer bankCardId) {
        this.bankCardId = bankCardId;
    }


    public String getBankName() {
        return bankName;
    }


    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }


    public String getCardNumber() {
        return cardNumber;
    }


    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber == null ? null : cardNumber.trim();
    }


    public String getCardName() {
        return cardName;
    }


    public void setCardName(String cardName) {
        this.cardName = cardName == null ? null : cardName.trim();
    }


    public String getOpeningBank() {
        return openingBank;
    }


    public void setOpeningBank(String openingBank) {
        this.openingBank = openingBank == null ? null : openingBank.trim();
    }


    public Integer getIsDefault() {
        return isDefault;
    }


    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }


    public Integer getAppUserId() {
        return appUserId;
    }


    public void setAppUserId(Integer appUserId) {
        this.appUserId = appUserId;
    }


    public Integer getStaffId() {
        return staffId;
    }


    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }


    public Date getAddTime() {
        return addTime;
    }


    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", bankCardId=").append(bankCardId);
        sb.append(", bankName=").append(bankName);
        sb.append(", cardNumber=").append(cardNumber);
        sb.append(", cardName=").append(cardName);
        sb.append(", openingBank=").append(openingBank);
        sb.append(", isDefault=").append(isDefault);
        sb.append(", appUserId=").append(appUserId);
        sb.append(", staffId=").append(staffId);
        sb.append(", addTime=").append(addTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}