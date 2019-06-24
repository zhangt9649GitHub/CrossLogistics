package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel(description = "郝腾")
public class AppUserWallet implements Serializable {
    @ApiModelProperty(value = "钱包id", example = "1")
    private Integer walletId;

    @ApiModelProperty(value = "app用户id", example = "1")
    private Integer appUserId;

    @ApiModelProperty(value = "余额", example = "1")
    private BigDecimal commissionAmount;

    @ApiModelProperty(value = "积分", example = "1")
    private Integer integral;

    @ApiModelProperty(value = "用户类型", example = "userType")
    private String userType;

    @ApiModelProperty(value = "银行卡号", example = "cardNumber")
    private String cardNumber;

    @ApiModelProperty(value = "发卡银行", example = "bankName")
    private String bankName;

    @ApiModelProperty(value = "可提现金额", example = "1")
    private BigDecimal cashWithdrawal;

    @ApiModelProperty(value = "最低提现金额", example = "1")
    private BigDecimal minimumMoney;

    @ApiModelProperty(value = "提现方式 1,银行卡 2,微信支付宝", example = "1")
    private Integer withdrawalWay;

    @ApiModelProperty(value = "单位符号", example = "symbol")
    private String symbol;


    private static final long serialVersionUID = 1L;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getWalletId() {
        return walletId;
    }


    public void setWalletId(Integer walletId) {
        this.walletId = walletId;
    }


    public Integer getAppUserId() {
        return appUserId;
    }


    public void setAppUserId(Integer appUserId) {
        this.appUserId = appUserId;
    }


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

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public BigDecimal getCashWithdrawal() {
        return cashWithdrawal;
    }

    public void setCashWithdrawal(BigDecimal cashWithdrawal) {
        this.cashWithdrawal = cashWithdrawal;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public BigDecimal getMinimumMoney() {
        return minimumMoney;
    }

    public void setMinimumMoney(BigDecimal minimumMoney) {
        this.minimumMoney = minimumMoney;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getWithdrawalWay() {
        return withdrawalWay;
    }

    public void setWithdrawalWay(Integer withdrawalWay) {
        this.withdrawalWay = withdrawalWay;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", walletId=").append(walletId);
        sb.append(", appUserId=").append(appUserId);
        sb.append(", commissionAmount=").append(commissionAmount);
        sb.append(", integral=").append(integral);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}