package com.siruiman.crosslogistics.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="郭阳")
public class SigningDto {
    @ApiModelProperty(value = "用户id", example = "1")
    private Integer appUserId;
    @ApiModelProperty(value = "用户真实姓名", example = "actualName")
    private String actualName;
    @ApiModelProperty(value = "货物id", example = "1")
    private Integer goodsId;
    @ApiModelProperty(value = "货物id(多个货物签收)", example = "1")
    private String goodsIds;
    @ApiModelProperty(value = "小车订单id/货车订单id", example = "1")
    private Integer taskOrderId;
    @ApiModelProperty(value = "是否有人接收 1.有人 2.无人", example = "1")
    private Integer isPeopleReceive;
    @ApiModelProperty(value = "图片id", example = "1")
    private Integer ufId;
    @ApiModelProperty(value = "图片路径", example = "图片路径")
    private String ufSavePath;
    @ApiModelProperty(value = "小车1/货车2", example = "1")
    private Integer carOrTruck = 1;

    public Integer getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Integer appUserId) {
        this.appUserId = appUserId;
    }

    public String getActualName() {
        return actualName;
    }

    public void setActualName(String actualName) {
        this.actualName = actualName;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsIds() {
        return goodsIds;
    }

    public void setGoodsIds(String goodsIds) {
        this.goodsIds = goodsIds;
    }

    public Integer getTaskOrderId() {
        return taskOrderId;
    }

    public void setTaskOrderId(Integer taskOrderId) {
        this.taskOrderId = taskOrderId;
    }

    public Integer getIsPeopleReceive() {
        return isPeopleReceive;
    }

    public void setIsPeopleReceive(Integer isPeopleReceive) {
        this.isPeopleReceive = isPeopleReceive;
    }

    public Integer getUfId() {
        return ufId;
    }

    public void setUfId(Integer ufId) {
        this.ufId = ufId;
    }

    public String getUfSavePath() {
        return ufSavePath;
    }

    public void setUfSavePath(String ufSavePath) {
        this.ufSavePath = ufSavePath;
    }

    public Integer getCarOrTruck() {
        return carOrTruck;
    }

    public void setCarOrTruck(Integer carOrTruck) {
        this.carOrTruck = carOrTruck;
    }

    @Override
    public String toString() {
        return "SigningDto{" +
                "appUserId=" + appUserId +
                ", actualName='" + actualName + '\'' +
                ", goodsId=" + goodsId +
                ", goodsIds='" + goodsIds + '\'' +
                ", taskOrderId=" + taskOrderId +
                ", isPeopleReceive=" + isPeopleReceive +
                ", ufId=" + ufId +
                ", ufSavePath='" + ufSavePath + '\'' +
                ", carOrTruck=" + carOrTruck +
                '}';
    }
}
