package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel(description = "郝腾")
public class GoodsWarning implements Serializable {
    @ApiModelProperty(value = "异常货物id", example = "0")
    private Integer goodsWarningId;

    @ApiModelProperty(value = "货物id", example = "0")
    private Integer goodsId;

    @ApiModelProperty(value = "异常描述", example = "warningComment")
    private String warningComment;

    @ApiModelProperty(value = "处理描述", example = "dealComment")
    private String dealComment;

    @ApiModelProperty(value = "异常状态", example = "warningState")
    private String warningState;

    @ApiModelProperty(value = "异常状态编号 1.无异常 2.异常 3.正在处理中", example = "1")
    private Integer warningStateNumber;

    @ApiModelProperty(value = "异常类型", example = "warningType")
    private String warningType;

    @ApiModelProperty(value = "添加时间", example = "yyyy-MM-dd-HH-mm-ss")
    private Date createTime;

    @ApiModelProperty(value = "处理时间", example = "yyyy-MM-dd-HH-mm-ss")
    private Date dealTime;

    @ApiModelProperty(value = "后台用户主键id", example = "1")
    private Integer adminUid;

    @ApiModelProperty(value = "实际货物长度", example = "1.1")
    private Float actualLong;

    @ApiModelProperty(value = "实际货物宽度", example = "1.1")
    private Float actualWidth;

    @ApiModelProperty(value = "实际货物高度", example = "1.1")
    private Float actualHeight;

    @ApiModelProperty(value = "实际货物重量", example = "1.1")
    private Float actualWeight;

    @ApiModelProperty(value = "货物数据", example = "goodData")
    private String goodData;

    @ApiModelProperty(value = "三方物流单号", example = "tripartiteNumber")
    private String tripartiteNumber;

    @ApiModelProperty(value = "货物类型", example = "goodType")
    private String goodType;

    @ApiModelProperty(value = "来源", example = "from")
    private String from;

    @ApiModelProperty(value = "快递单号", example = "deliveryNumber")
    private String deliveryNumber;

    @ApiModelProperty(value = "状态 1 保留 2 删除", example = "1")
    private Integer delStatus;


    private static final long serialVersionUID = 1L;

    public String getGoodType() {
        return goodType;
    }

    public void setGoodType(String goodType) {
        this.goodType = goodType;
    }

    public String getTripartiteNumber() {
        return tripartiteNumber;
    }

    public void setTripartiteNumber(String tripartiteNumber) {
        this.tripartiteNumber = tripartiteNumber;
    }

    public String getGoodData() {
        return goodData = "" + this.actualLong + "*" + "" + this.actualWidth + "*" + "" + this.actualHeight + "," + "" + this.actualWeight;
    }

    public void setGoodData(String goodData) {
        this.goodData = this.goodData;
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

    public Integer getGoodsWarningId() {
        return goodsWarningId;
    }


    public void setGoodsWarningId(Integer goodsWarningId) {
        this.goodsWarningId = goodsWarningId;
    }


    public Integer getGoodsId() {
        return goodsId;
    }


    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }


    public String getWarningComment() {
        return warningComment;
    }


    public void setWarningComment(String warningComment) {
        this.warningComment = warningComment == null ? null : warningComment.trim();
    }


    public String getDealComment() {
        return dealComment;
    }


    public void setDealComment(String dealComment) {
        this.dealComment = dealComment == null ? null : dealComment.trim();
    }


    public String getWarningState() {
        return warningState;
    }


    public void setWarningState(String warningState) {
        this.warningState = warningState == null ? null : warningState.trim();
    }


    public String getWarningType() {
        return warningType;
    }


    public void setWarningType(String warningType) {
        this.warningType = warningType == null ? null : warningType.trim();
    }


    public Date getCreateTime() {
        return createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Date getDealTime() {
        return dealTime;
    }


    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }


    public Integer getAdminUid() {
        return adminUid;
    }


    public void setAdminUid(Integer adminUid) {
        this.adminUid = adminUid;
    }


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Integer getWarningStateNumber() {
        return warningStateNumber;
    }

    public void setWarningStateNumber(Integer warningStateNumber) {
        this.warningStateNumber = warningStateNumber;
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public Integer getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", goodsWarningId=").append(goodsWarningId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", warningComment=").append(warningComment);
        sb.append(", dealComment=").append(dealComment);
        sb.append(", warningState=").append(warningState);
        sb.append(", warningType=").append(warningType);
        sb.append(", createTime=").append(createTime);
        sb.append(", dealTime=").append(dealTime);
        sb.append(", adminUid=").append(adminUid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}