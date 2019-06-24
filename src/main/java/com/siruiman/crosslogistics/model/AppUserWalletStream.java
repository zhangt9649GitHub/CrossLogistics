package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel(description = "郝腾")
public class AppUserWalletStream implements Serializable {

    @ApiModelProperty(value = "流水记录id", example = "1")
    private Integer streamId;

    @ApiModelProperty(value = "app用户id", example = "1")
    private Integer appUserId;

    @ApiModelProperty(value = "提现申请id", example = "1")
    private Integer withdrawId;

    @ApiModelProperty(value = "小车订单id", example = "1")
    private Integer carOrderId;

    @ApiModelProperty(value = "小车订单id", example = "1")
    private Integer truckOrderId;

    @ApiModelProperty(value = "货物id", example = "1")
    private Integer goodsId;

    @ApiModelProperty(value = "流水类型 1.余额提现 2.抢单佣金 3.抢单积分 4.问题件扣除(佣金) 5.问题件扣除(积分) 6.转运订单", example = "1")
    private Byte streamType;

    @ApiModelProperty(value = "流水记录内容", example = "streamText")
    private String streamText;

    @ApiModelProperty(value = "金额", example = "1")
    private BigDecimal amount;

    @ApiModelProperty(value = "积分", example = "1")
    private Integer integral;

    @ApiModelProperty(value = "创建时间", example = "addTime")
    private Date addTime;

    @ApiModelProperty(value = "余额提现到达时间", example = "arriveTime")
    private Date arriveTime;

    @ApiModelProperty(value = "用户类型 （普通用户 小车 货车）", example = "userType")
    private String userType;

    @ApiModelProperty(value = "小车订单编号", example = "carOrderNumber")
    private String carOrderNumber;

    @ApiModelProperty(value = "货车订单编号", example = "truckOrderNumber")
    private String truckOrderNumber;

    @ApiModelProperty(value = "提现订单号", example = "withdrawOrderNumber")
    private String withdrawOrderNumber;

    @ApiModelProperty(value = "转运货物单号", example = "deliveryNumber")
    private String deliveryNumber;

    @ApiModelProperty(value = "状态  1.待处理（余额结算专用） 2.冻结中（订单专用） 3.已结算(订单专用) 4.已完成（余额提现专用） 5.已扣除(佣金) 6.已扣除（积分）7. 支付成功(转运订单专用)", example = "1")
    private Byte state;

    @ApiModelProperty(value = "单位 1.人民币 2.美元 3.人民币", example = "1")
    private Integer unit ;

    @ApiModelProperty(value = "单位符号", example = "symbol")
    private String symbol;

    private static final long serialVersionUID = 1L;


    public Integer getStreamId() {
        return streamId;
    }


    public void setStreamId(Integer streamId) {
        this.streamId = streamId;
    }


    public Integer getCarOrderId() {
        return carOrderId;
    }

    public void setCarOrderId(Integer carOrderId) {
        this.carOrderId = carOrderId;
    }

    public Integer getTruckOrderId() {
        return truckOrderId;
    }

    public void setTruckOrderId(Integer truckOrderId) {
        this.truckOrderId = truckOrderId;
    }

    public Byte getStreamType() {
        return streamType;
    }


    public void setStreamType(Byte streamType) {
        this.streamType = streamType;
    }


    public String getStreamText() {
        return streamText;
    }


    public void setStreamText(String streamText) {
        this.streamText = streamText == null ? null : streamText.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getCarOrderNumber() {
        return carOrderNumber;
    }

    public void setCarOrderNumber(String carOrderNumber) {
        this.carOrderNumber = carOrderNumber;
    }

    public String getTruckOrderNumber() {
        return truckOrderNumber;
    }

    public void setTruckOrderNumber(String truckOrderNumber) {
        this.truckOrderNumber = truckOrderNumber;
    }

    public Integer getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Integer appUserId) {
        this.appUserId = appUserId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getWithdrawOrderNumber() {
        return withdrawOrderNumber;
    }

    public void setWithdrawOrderNumber(String withdrawOrderNumber) {
        this.withdrawOrderNumber = withdrawOrderNumber;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getWithdrawId() {
        return withdrawId;
    }

    public void setWithdrawId(Integer withdrawId) {
        this.withdrawId = withdrawId;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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
        sb.append(", streamId=").append(streamId);
        sb.append(", streamType=").append(streamType);
        sb.append(", streamText=").append(streamText);
        sb.append(", addTime=").append(addTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}