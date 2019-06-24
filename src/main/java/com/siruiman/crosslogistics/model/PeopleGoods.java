package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(description="郭阳")
public class PeopleGoods {
    @ApiModelProperty(value = "货物id", example = "1")
    private Integer goodsId;
    @ApiModelProperty(value = "快递单号", example = "快递单号")
    private String deliveryNumber;
    @ApiModelProperty(value = "货袋编号", example = "bagNumber")
    private String bagNumber;

    /*@ApiModelProperty(value = "货物状态 1.正常 2.异常", example = "1")
    private Integer status;
    @ApiModelProperty(value = "是否收货 1.是 2 否", example = "1")
    private Integer isReceiveGoods;*/

    @ApiModelProperty(value = "是否货到付款 1.否 2.是", example = "1")
    private Integer isArrivalPay;
    @ApiModelProperty(value = "货值", example = "0.00")
    private BigDecimal COD = new BigDecimal(0.00);
    @ApiModelProperty(value = "需要支付GST的价格", example = "0.00")
    private BigDecimal GST = new BigDecimal(0.00);
    @ApiModelProperty(value = "合计需要收的钱", example = "0.00")
    private BigDecimal totalCodGst = new BigDecimal(0.00);

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

    public String getBagNumber() {
        return bagNumber;
    }

    public void setBagNumber(String bagNumber) {
        this.bagNumber = bagNumber;
    }

    public Integer getIsArrivalPay() {
        return isArrivalPay;
    }

    public void setIsArrivalPay(Integer isArrivalPay) {
        this.isArrivalPay = isArrivalPay;
    }

    public BigDecimal getCOD() {
        return COD;
    }

    public void setCOD(BigDecimal COD) {
        this.COD = COD;
    }

    public BigDecimal getGST() {
        return GST;
    }

    public void setGST(BigDecimal GST) {
        this.GST = GST;
    }

    public BigDecimal getTotalCodGst() {
        return totalCodGst;
    }

    public void setTotalCodGst(BigDecimal totalCodGst) {
        this.totalCodGst = totalCodGst;
    }

    @Override
    public String toString() {
        return "PeopleGoods{" +
                "goodsId=" + goodsId +
                ", deliveryNumber='" + deliveryNumber + '\'' +
                ", bagNumber='" + bagNumber + '\'' +
                ", isArrivalPay=" + isArrivalPay +
                ", COD=" + COD +
                ", GST=" + GST +
                ", totalCodGst=" + totalCodGst +
                '}';
    }
}
