package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(description = "郝腾")
public class GoodsAndGoodsDetails {

    @ApiModelProperty(value = "货物id", example = "0")
    private Integer goodsId;

    @ApiModelProperty(value = "三方物流单号", example = "tripartiteNumber")
    private String tripartiteNumber;

    @ApiModelProperty(value = "货物类型", example = "goodType")
    private String goodType;

    @ApiModelProperty(value = "邮编", example = "zipCode")
    private String zipCode;

    @ApiModelProperty(value = "是否为特殊货物 1是 0不是", example = "1")
    private Integer isSpecialGoods;

    @ApiModelProperty(value = "出境物流方式", example = "exitWay")
    private String exitWay;

    @ApiModelProperty(value = "发货地址", example = "shipAddress")
    private String shipAddress;

    @ApiModelProperty(value = "发货联系人", example = "shipContact")
    private String shipContact;

    @ApiModelProperty(value = "发货人手机号", example = "shipContactMobile")
    private String shipContactMobile;

    @ApiModelProperty(value = "收货地址", example = "receiptAddress")
    private String receiptAddress;

    @ApiModelProperty(value = "收货联系人", example = "receiptContact")
    private String receiptContact;

    @ApiModelProperty(value = "收货联系人手机号", example = "receiptContactMobile")
    private String receiptContactMobile;

    @ApiModelProperty(value = "货品分类", example = "category")
    private String category;

    @ApiModelProperty(value = "入库仓位编号", example = "cnWpNumber")
    private String intoWpNumber;

    @ApiModelProperty(value = "出库仓位编号", example = "sgpWpNumber")
    private String outWpNumber;

    @ApiModelProperty(value = "货值", example = "1")
    private BigDecimal itemValue;

    @ApiModelProperty(value = "是否加急", example = "isUrgent")
    private String isUrgent;

    @ApiModelProperty(value = "用户编号", example = "number")
    private String number;

    @ApiModelProperty(value = "备注", example = "remark")
    private String remark;

    @ApiModelProperty(value = "实际货物长度", example = "1")
    private Float actualLong;

    @ApiModelProperty(value = "实际货物宽度", example = "1")
    private Float actualWidth;

    @ApiModelProperty(value = "实际货物高度", example = "1")
    private Float actualHeight;

    @ApiModelProperty(value = "实际货物重量", example = "1")
    private Float actualWeight;

    @ApiModelProperty(value = "货袋编号", example = "bagNumber")
    private String bagNumber;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getTripartiteNumber() {
        return tripartiteNumber;
    }

    public void setTripartiteNumber(String tripartiteNumber) {
        this.tripartiteNumber = tripartiteNumber;
    }

    public String getGoodType() {
        return goodType;
    }

    public void setGoodType(String goodType) {
        this.goodType = goodType;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Integer getIsSpecialGoods() {
        return isSpecialGoods;
    }

    public void setIsSpecialGoods(Integer isSpecialGoods) {
        this.isSpecialGoods = isSpecialGoods;
    }

    public String getExitWay() {
        return exitWay;
    }

    public void setExitWay(String exitWay) {
        this.exitWay = exitWay;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getShipContact() {
        return shipContact;
    }

    public void setShipContact(String shipContact) {
        this.shipContact = shipContact;
    }

    public String getShipContactMobile() {
        return shipContactMobile;
    }

    public void setShipContactMobile(String shipContactMobile) {
        this.shipContactMobile = shipContactMobile;
    }

    public String getReceiptAddress() {
        return receiptAddress;
    }

    public void setReceiptAddress(String receiptAddress) {
        this.receiptAddress = receiptAddress;
    }

    public String getReceiptContact() {
        return receiptContact;
    }

    public void setReceiptContact(String receiptContact) {
        this.receiptContact = receiptContact;
    }

    public String getReceiptContactMobile() {
        return receiptContactMobile;
    }

    public void setReceiptContactMobile(String receiptContactMobile) {
        this.receiptContactMobile = receiptContactMobile;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIntoWpNumber() {
        return intoWpNumber;
    }

    public void setIntoWpNumber(String intoWpNumber) {
        this.intoWpNumber = intoWpNumber;
    }

    public String getOutWpNumber() {
        return outWpNumber;
    }

    public void setOutWpNumber(String outWpNumber) {
        this.outWpNumber = outWpNumber;
    }

    public BigDecimal getItemValue() {
        return itemValue;
    }

    public void setItemValue(BigDecimal itemValue) {
        this.itemValue = itemValue;
    }

    public String getIsUrgent() {
        return isUrgent;
    }

    public void setIsUrgent(String isUrgent) {
        this.isUrgent = isUrgent;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Float getActualLong() {
        return actualLong;
    }

    public void setActualLong(Float actualLong) {
        this.actualLong = actualLong;
    }

    public Float getActualWidth() {
        return actualWidth;
    }

    public void setActualWidth(Float actualWidth) {
        this.actualWidth = actualWidth;
    }

    public Float getActualHeight() {
        return actualHeight;
    }

    public void setActualHeight(Float actualHeight) {
        this.actualHeight = actualHeight;
    }

    public Float getActualWeight() {
        return actualWeight;
    }

    public void setActualWeight(Float actualWeight) {
        this.actualWeight = actualWeight;
    }

    public String getBagNumber() {
        return bagNumber;
    }

    public void setBagNumber(String bagNumber) {
        this.bagNumber = bagNumber;
    }
}
