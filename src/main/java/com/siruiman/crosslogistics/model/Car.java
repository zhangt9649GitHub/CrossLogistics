package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Locale;
import java.util.stream.IntStream;

/**
 * @author 张占伟
 * @date 2018/12/19
 */
@ApiModel(description = "张占伟")

public class Car implements Serializable {

    @ApiModelProperty(value = "新加坡地区名字", example = "singaporeAreaName")
    private String singaporeAreaName;

    @ApiModelProperty(value = "骑手联系电话", example = "15892300670")
    private String mobile;

    @ApiModelProperty(value = "小车关联骑手名字", example = "张三")
    private String userName;

    @ApiModelProperty(value = "小车id", example = "1")
    private Integer carId;

    @ApiModelProperty(value = "集结点Id", example = "1")
    private Integer rallyPointId;

    @ApiModelProperty(value = "小车编号", example = "sg787986")
    private String carNumber;

    @ApiModelProperty(value = "新加坡自定义区域id", example = "0132")
    private Integer singaporeAreaId;

    @ApiModelProperty(value = "小车状态", example = "1")
    private Integer state;

    @ApiModelProperty(value = "小车添加时间", example = "后台处理")
    private String addTime;

    @ApiModelProperty(value = "小车修改时间", example = "后天处理")
    private String updateTime;

    @ApiModelProperty(value = "小车备注", example = "公四是的")
    private String remark;

    @ApiModelProperty(value = "小车关联骑手id", example = "1")
    private int userId;

    @ApiModelProperty(value = "小车关联货袋编号", example = "bagNumber")
    private String bagNumber;

    @ApiModelProperty(value = "小车归还状态", example = "1")
    private int carReturnState;

    @ApiModelProperty(value = "小车订单编号", example = "orderNumber")
    private String orderNumber;

    @ApiModelProperty(value = "集结点编号", example = "rallyPointNumber")
    private String rallyPointNumber;

    @ApiModelProperty(value = "集结点名字", example = "rallyPointName")
    private String rallyPointName;

    @ApiModelProperty(value = "关联骑手的用户编号", example = "number")
    private String number;

    @ApiModelProperty(value = "小车锁编号", example = "lockCarNumber")
    private String carLockNumber;

    @ApiModelProperty(value = "小车后箱锁编号", example = "carLockBoxNumber")
    private String carLockBoxNumber;

    @ApiModelProperty(value = "小车后箱锁id", example = "1")
    private Integer carLockBoxId;

    @ApiModelProperty(value = "小车锁的id", example = "1")
    private Integer carLockId;

    @ApiModelProperty(value = "1为正常,2为删除", example = "1")
    private Integer disableState;


    public Integer getRallyPointId() {
        return rallyPointId;
    }

    public void setRallyPointId(Integer rallyPointId) {
        this.rallyPointId = rallyPointId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }


    public int getCarReturnState() {
        return carReturnState;
    }

    public void setCarReturnState(int carReturnState) {
        this.carReturnState = carReturnState;
    }


    public String getSingaporeAreaName() {

        return singaporeAreaName;
    }

    public void setSingaporeAreaName(String singaporeAreaName) {
        this.singaporeAreaName = singaporeAreaName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Integer getSingaporeAreaId() {
        return singaporeAreaId;
    }

    public void setSingaporeAreaId(Integer singaporeAreaId) {
        this.singaporeAreaId = singaporeAreaId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }


    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public String getBagNumber() {
        return bagNumber;
    }

    public void setBagNumber(String bagNumber) {
        this.bagNumber = bagNumber;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private static final long serialVersionUID = 1L;

    public String getRallyPointNumber() {
        return rallyPointNumber;
    }

    public void setRallyPointNumber(String rallyPointNumber) {
        this.rallyPointNumber = rallyPointNumber;
    }

    public String getRallyPointName() {
        return rallyPointName;
    }

    public void setRallyPointName(String rallyPointName) {
        this.rallyPointName = rallyPointName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCarLockNumber() {
        return carLockNumber;
    }

    public void setCarLockNumber(String carLockNumber) {
        this.carLockNumber = carLockNumber;
    }

    public String getCarLockBoxNumber() {
        return carLockBoxNumber;
    }

    public void setCarLockBoxNumber(String carLockBoxNumber) {
        this.carLockBoxNumber = carLockBoxNumber;
    }

    public Integer getCarLockBoxId() {
        return carLockBoxId;
    }

    public void setCarLockBoxId(Integer carLockBoxId) {
        this.carLockBoxId = carLockBoxId;
    }

    public Integer getCarLockId() {
        return carLockId;
    }

    public void setCarLockId(Integer carLockId) {
        this.carLockId = carLockId;
    }

    public Integer getDisableState() {
        return disableState;
    }

    public void setDisableState(Integer disableState) {
        this.disableState = disableState;
    }
}