package com.siruiman.crosslogistics.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "郝腾")
public class PDAGoodsDetailsDto {

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

    /*@ApiModelProperty(value="异常类型", example = "warningType")
    private String warningType;

    @ApiModelProperty(value="异常描述", example = "warningComment")
    private String warningComment;

    @ApiModelProperty(value="异常状态", example = "无异常，处理中，异常")
    private String warningState;

    @ApiModelProperty(value="异常状态编号 1.无异常 2.异常 3.正在处理中", example = "1")
    private Integer warningStateNumber;
*/
   /* @ApiModelProperty(value="处理描述", example = "dealComment")
    private String dealComment;*/

    @ApiModelProperty(value = "货品分类 普通 敏感", example = "category")
    private String category;

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

   /* public String getWarningType() {
        return warningType;
    }

    public void setWarningType(String warningType) {
        this.warningType = warningType;
    }

    public String getWarningComment() {
        return warningComment;
    }

    public void setWarningComment(String warningComment) {
        this.warningComment = warningComment;
    }

    public Integer getWarningStateNumber() {
        return warningStateNumber;
    }

    public void setWarningStateNumber(Integer warningStateNumber) {
        this.warningStateNumber = warningStateNumber;
    }*/

   /* public String getDealComment() {
        return dealComment;
    }

    public void setDealComment(String dealComment) {
        this.dealComment = dealComment;
    }*/

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

  /* public String getWarningState() {
        return warningState;
    }

    public void setWarningState(String warningState) {
        this.warningState = warningState;
    }*/
}
