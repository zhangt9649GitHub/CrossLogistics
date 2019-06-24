package com.siruiman.crosslogistics.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "郝腾")
public class PDAGoodsDto {
    @ApiModelProperty(value = "来源", example = "from")
    private String from;

    @ApiModelProperty(value = "三方物流单号", example = "tripartiteNumber")
    private String tripartiteNumber;

    @ApiModelProperty(value = "快递单号", example = "deliveryNumber")
    private String deliveryNumber;

    @ApiModelProperty(value = "实际货物长度", example = "1")
    private Float actualLong;

    @ApiModelProperty(value = "实际货物宽度", example = "1")
    private Float actualWidth;

    @ApiModelProperty(value = "实际货物高度", example = "1")
    private Float actualHeight;

    @ApiModelProperty(value = "实际货物重量", example = "1")
    private Float actualWeight;

    @ApiModelProperty(value = "是否为特殊货物 1是 2不是", example = "1")
    private Integer isSpecialGoods;

    @ApiModelProperty(value = "货物类型", example = "goodType")
    private String goodType;

    @ApiModelProperty(value = "用户编号", example = "number")
    private String number;

    @ApiModelProperty(value = "入库仓位编号", example = "cnWpNumber")
    private String intoWpNumber;

    @ApiModelProperty(value = "员工id", example = "1")
    private int staffId;

    @ApiModelProperty(value = "货物id", example = "1")
    private Integer goodsId;

    @ApiModelProperty(value = "货品分类 普通 敏感", example = "category")
    private String category;

    @ApiModelProperty(value = "图片存储路径", example = "ufSavePath")
    private String ufSavePath;

    @ApiModelProperty(value = "共计几箱", example = "1")
    private Integer totalGoods;

    @ApiModelProperty(value = "第几箱", example = "1")
    private Integer order;

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

    public Integer getIsSpecialGoods() {
        return isSpecialGoods;
    }

    public void setIsSpecialGoods(Integer isSpecialGoods) {
        this.isSpecialGoods = isSpecialGoods;
    }

    public String getGoodType() {
        return goodType;
    }

    public void setGoodType(String goodType) {
        this.goodType = goodType;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIntoWpNumber() {
        return intoWpNumber;
    }

    public void setIntoWpNumber(String intoWpNumber) {
        this.intoWpNumber = intoWpNumber;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUfSavePath() {
        return ufSavePath;
    }

    public void setUfSavePath(String ufSavePath) {
        this.ufSavePath = ufSavePath;
    }

    public Integer getTotalGoods() {
        return totalGoods;
    }

    public void setTotalGoods(Integer totalGoods) {
        this.totalGoods = totalGoods;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTripartiteNumber() {
        return tripartiteNumber;
    }

    public void setTripartiteNumber(String tripartiteNumber) {
        this.tripartiteNumber = tripartiteNumber;
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }
}
