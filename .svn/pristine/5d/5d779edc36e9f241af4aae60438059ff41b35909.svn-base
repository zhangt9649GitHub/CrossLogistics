package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel(description = "郝腾")
public class CarLock implements Serializable {
    @ApiModelProperty(value = "锁id", example = "1")
    private Integer lockId;

    @ApiModelProperty(value = "小车id", example = "1")
    private Integer carId;

    @ApiModelProperty(value = "锁状态（1.开启 2.锁定）", example = "1")
    private Byte state;

    @ApiModelProperty(value = "锁编号", example = "lockNumber")
    private String lockNumber;

    @ApiModelProperty(value = "移动信号基站编号", example = "baseStationNumber")
    private String baseStationNumber;

    @ApiModelProperty(value = "车锁电量信息", example = "56")
    private String electricity;

    @ApiModelProperty(value = "车锁wgs84纬度信息", example = "56")
    private String lat;

    @ApiModelProperty(value = "车锁wgs84经度信息", example = "56")
    private String lng;

    @ApiModelProperty(value = "车锁信号强度", example = "56")
    private String sign;


    @ApiModelProperty(value = "位置区域码", example = "56")
    private String lac;

    @ApiModelProperty(value = "锁状态 1.正常 2损坏 3.控制", example = "1")
    private Byte status;

    @ApiModelProperty(value = "修改时间", example = "1")
    private Date updateTime;

    @ApiModelProperty(value = "锁的位置 1.小车车厢锁  2.小车锁", example = "1")
    private Integer lockPosition;

    @ApiModelProperty(value = "小车编号", example = "1")
    private String carNumber;

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    private static final long serialVersionUID = 1L;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getLac() {
        return lac;
    }

    public void setLac(String lac) {
        this.lac = lac;
    }

    public CarLock() {
        super();
    }

    public Integer getLockId() {
        return lockId;
    }

    public void setLockId(Integer lockId) {
        this.lockId = lockId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getLockNumber() {
        return lockNumber;
    }

    public void setLockNumber(String lockNumber) {
        this.lockNumber = lockNumber;
    }

    public String getBaseStationNumber() {
        return baseStationNumber;
    }

    public void setBaseStationNumber(String baseStationNumber) {
        this.baseStationNumber = baseStationNumber;
    }

    public String getElectricity() {
        return electricity;
    }

    public void setElectricity(String electricity) {
        this.electricity = electricity;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getLockPosition() {
        return lockPosition;
    }

    public void setLockPosition(Integer lockPosition) {
        this.lockPosition = lockPosition;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "CarLock{" +
                "lockId=" + lockId +
                ", carId=" + carId +
                ", state=" + state +
                ", lockNumber='" + lockNumber + '\'' +
                ", baseStationNumber='" + baseStationNumber + '\'' +
                ", electricity='" + electricity + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", sign='" + sign + '\'' +
                ", lac='" + lac + '\'' +
                ", status=" + status +
                ", updateTime=" + updateTime +
                ", lockPosition=" + lockPosition +
                '}';
    }

    public CarLock(Integer lockId, Integer carId, Byte state, String lockNumber, String baseStationNumber, String electricity, String lat, String lng, String sign, String lac, Byte status, Date updateTime, Integer lockPosition) {
        this.lockId = lockId;
        this.carId = carId;
        this.state = state;
        this.lockNumber = lockNumber;
        this.baseStationNumber = baseStationNumber;
        this.electricity = electricity;
        this.lat = lat;
        this.lng = lng;
        this.sign = sign;
        this.lac = lac;
        this.status = status;
        this.updateTime = updateTime;
        this.lockPosition = lockPosition;
    }
}