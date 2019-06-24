package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(description = "郝腾")
public class ThreeGoodsOrderStatistical {

    //订单已派送数
    @ApiModelProperty(value = "订单已完成派送总数量", example = "quantityDispatched")
    private int quantityDispatched;
    //订单派送完成数
    @ApiModelProperty(value = "订单派送中完成数量", example = "orderFulfillment")
    private int orderFulfillment;
    //订单派送异常件
    @ApiModelProperty(value = "订单派送中异常件数量", example = "orderFulfillment")
    private int orderException;
    //订单未派送数
    @ApiModelProperty(value = "尚未派送的订单数量", example = "undeliveredQuantity")
    private int undeliveredQuantity;
    //订单已派送完成件中应收货到付款金额
    @ApiModelProperty(value = "已完成中派送件中应收划款总金额", example = "accountsReceivable")
    private BigDecimal accountsReceivable;
    //订单已派送完成件中实收货到付款金额
    @ApiModelProperty(value = "已完成中派送件中实收划款总金额", example = "realCollection")
    private BigDecimal realCollection;

    public int getQuantityDispatched() {
        return quantityDispatched;
    }

    public void setQuantityDispatched(int quantityDispatched) {
        this.quantityDispatched = quantityDispatched;
    }

    public int getOrderFulfillment() {
        return orderFulfillment;
    }

    public void setOrderFulfillment(int orderFulfillment) {
        this.orderFulfillment = orderFulfillment;
    }

    public int getOrderException() {
        return orderException;
    }

    public void setOrderException(int orderException) {
        this.orderException = orderException;
    }

    public int getUndeliveredQuantity() {
        return undeliveredQuantity;
    }

    public void setUndeliveredQuantity(int undeliveredQuantity) {
        this.undeliveredQuantity = undeliveredQuantity;
    }

    public BigDecimal getAccountsReceivable() {
        return accountsReceivable;
    }

    public void setAccountsReceivable(BigDecimal accountsReceivable) {
        this.accountsReceivable = accountsReceivable;
    }

    public BigDecimal getRealCollection() {
        return realCollection;
    }

    public void setRealCollection(BigDecimal realCollection) {
        this.realCollection = realCollection;
    }
}
