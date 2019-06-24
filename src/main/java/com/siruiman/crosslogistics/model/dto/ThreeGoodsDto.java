package com.siruiman.crosslogistics.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "郝腾")
public class ThreeGoodsDto {

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

 /*   @ApiModelProperty(value="货物类型", example = "goodType")
    private String goodType;*/

    @ApiModelProperty(value = "货物id", example = "1")
    private Integer goodsId;

    @ApiModelProperty(value = "图片存储路径", example = "ufSavePath")
    private String ufSavePath;

    @ApiModelProperty(value = "备注", example = "remark")
    private String remark;

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

 /*   public String getGoodType() {
        return goodType;
    }

    public void setGoodType(String goodType) {
        this.goodType = goodType;
    }*/

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getUfSavePath() {
        return ufSavePath;
    }

    public void setUfSavePath(String ufSavePath) {
        this.ufSavePath = ufSavePath;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
