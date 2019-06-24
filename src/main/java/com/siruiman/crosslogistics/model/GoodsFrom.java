package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class GoodsFrom implements Serializable {

    @ApiModelProperty(value = "送货表单id", example = "1")
    private Integer formId;

    @ApiModelProperty(value = "表单号 （司机userid+时间戳）", example = "fromNumber")
    private String fromNumber;

    @ApiModelProperty(value = "司机姓名", example = "driverName")
    private String driverName;

    @ApiModelProperty(value = "添加时间", example = "addTime")
    private Date addTime;

    @ApiModelProperty(value = "是否打印 0 不打印 1 打印", example = "0")
    private Integer isPrint;

    @ApiModelProperty(value = "货物清单", example = "goodsFromInfoList")
    private List<GoodsFromInfo> goodsFromInfoList = new ArrayList<>();


    @ApiModelProperty(value = "货物打印货单的类型（1.是简易app获取的货物表单，2.是根据货车订单直接配送的货物表单）", example = "1")
    private Integer type;


    private static final long serialVersionUID = 1L;


    public Integer getFormId() {
        return formId;
    }


    public void setFormId(Integer formId) {
        this.formId = formId;
    }


    public String getFromNumber() {
        return fromNumber;
    }


    public void setFromNumber(String fromNumber) {
        this.fromNumber = fromNumber == null ? null : fromNumber.trim();
    }


    public String getDriverName() {
        return driverName;
    }


    public void setDriverName(String driverName) {
        this.driverName = driverName == null ? null : driverName.trim();
    }


    public Date getAddTime() {
        return addTime;
    }


    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }


    public Integer getIsPrint() {
        return isPrint;
    }


    public void setIsPrint(Integer isPrint) {
        this.isPrint = isPrint;
    }

    public List<GoodsFromInfo> getGoodsFromInfoList() {
        return goodsFromInfoList;
    }

    public void setGoodsFromInfoList(List<GoodsFromInfo> goodsFromInfoList) {
        this.goodsFromInfoList = goodsFromInfoList;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", formId=").append(formId);
        sb.append(", fromNumber=").append(fromNumber);
        sb.append(", driverName=").append(driverName);
        sb.append(", addTime=").append(addTime);
        sb.append(", isPrint=").append(isPrint);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}