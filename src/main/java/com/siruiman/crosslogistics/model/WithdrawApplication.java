package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel(description = "郝腾")
public class WithdrawApplication implements Serializable {

    @ApiModelProperty(value = "提现申请id", example = "1")
    private Integer withdrawId;

    @ApiModelProperty(value = "提现金额", example = "1")
    private BigDecimal withdrawMoney;

    @ApiModelProperty(value = "用户id", example = "1")
    private Integer userId;

    @ApiModelProperty(value = "提现角色", example = "withdrawRole")
    private String withdrawRole;

    @ApiModelProperty(value = "提现状态", example = "1")
    private Byte withdrawType;

    @ApiModelProperty(value = "提现订单号", example = "withdrawOrderNumber")
    private String withdrawOrderNumber;

    @ApiModelProperty(value = "提现时间", example = "addTime")
    private Date addTime;

    @ApiModelProperty(value = "到账时间", example = "arriveTime")
    private Date arriveTime;

    @ApiModelProperty(value = "描述", example = "comment")
    private String comment;

    @ApiModelProperty(value = "提现方式", example = "withdrawWay")
    private String withdrawWay;

    @ApiModelProperty(value = "app用户真实姓名", example = "userTrueName")
    private String userTrueName;

    @ApiModelProperty(value = "银行卡id", example = "1")
    private Integer bankCardId;

    @ApiModelProperty(value = "发卡银行", example = "bankName")
    private String bankName;

    @ApiModelProperty(value = "卡号", example = "cardNumber")
    private String cardNumber;

    @ApiModelProperty(value = "账号", example = "account")
    private String account;

    @ApiModelProperty(value = "用户名", example = "userName")
    private String userName;

    @ApiModelProperty(value = "客服账号", example = "serviceAccount")
    private String serviceAccount;

    @ApiModelProperty(value = "用户编号", example = "number")
    private String number;

    @ApiModelProperty(value = "后台用户主键id", example = "1")
    private Integer adminUid;

    @ApiModelProperty(value = "单位 1.人民币 2.美元 3.人民币", example = "1")
    private Integer unit;

    @ApiModelProperty(value = "单位符号", example = "symbol")
    private String symbol;


    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getBankCardId() {
        return bankCardId;
    }

    public void setBankCardId(Integer bankCardId) {
        this.bankCardId = bankCardId;
    }

    public String getUserTrueName() {
        return userTrueName;
    }

    public String getWithdrawWay() {
        return withdrawWay;
    }

    public void setWithdrawWay(String withdrawWay) {
        this.withdrawWay = withdrawWay;
    }

    public void setUserTrueName(String userTrueName) {
        this.userTrueName = userTrueName;
    }

    private static final long serialVersionUID = 1L;


    public Integer getWithdrawId() {
        return withdrawId;
    }


    public void setWithdrawId(Integer withdrawId) {
        this.withdrawId = withdrawId;
    }


    public BigDecimal getWithdrawMoney() {
        return withdrawMoney;
    }


    public void setWithdrawMoney(BigDecimal withdrawMoney) {
        this.withdrawMoney = withdrawMoney;
    }


    public Integer getUserId() {
        return userId;
    }


    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public String getWithdrawRole() {
        return withdrawRole;
    }

    public void setWithdrawRole(String withdrawRole) {
        this.withdrawRole = withdrawRole;
    }

    public Byte getWithdrawType() {
        return withdrawType;
    }


    public void setWithdrawType(Byte withdrawType) {
        this.withdrawType = withdrawType;
    }


    public String getWithdrawOrderNumber() {
        return withdrawOrderNumber;
    }


    public void setWithdrawOrderNumber(String withdrawOrderNumber) {
        this.withdrawOrderNumber = withdrawOrderNumber == null ? null : withdrawOrderNumber.trim();
    }


    public Date getAddTime() {
        return addTime;
    }


    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }


    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getComment() {
        return comment;
    }


    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getServiceAccount() {
        return serviceAccount;
    }

    public void setServiceAccount(String serviceAccount) {
        this.serviceAccount = serviceAccount;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getAdminUid() {
        return adminUid;
    }

    public void setAdminUid(Integer adminUid) {
        this.adminUid = adminUid;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
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
        sb.append(", withdrawId=").append(withdrawId);
        sb.append(", withdrawMoney=").append(withdrawMoney);
        sb.append(", userId=").append(userId);
        sb.append(", withdrawRole=").append(withdrawRole);
        sb.append(", withdrawType=").append(withdrawType);
        sb.append(", withdrawOrderNumber=").append(withdrawOrderNumber);
        sb.append(", addTime=").append(addTime);
        sb.append(", arriveTime=").append(arriveTime);
        sb.append(", comment=").append(comment);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}