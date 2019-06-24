package com.siruiman.crosslogistics.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class CarMaintenance implements Serializable {

    private Integer carMaintenanceId;


    private Integer carId;


    private String address;


    private String content;


    private Integer state;


    private Date cbmAddtime;


    private Date cbmServicetime;


    private Date cbmFinishtime;

    private String carNumber;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "CarMaintenance{" +
                "carMaintenanceId=" + carMaintenanceId +
                ", carId=" + carId +
                ", address='" + address + '\'' +
                ", content='" + content + '\'' +
                ", state=" + state +
                ", cbmAddtime=" + cbmAddtime +
                ", cbmServicetime=" + cbmServicetime +
                ", cbmFinishtime=" + cbmFinishtime +
                ", carNumber='" + carNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarMaintenance that = (CarMaintenance) o;
        return Objects.equals(carMaintenanceId, that.carMaintenanceId) &&
                Objects.equals(carId, that.carId) &&
                Objects.equals(address, that.address) &&
                Objects.equals(content, that.content) &&
                Objects.equals(state, that.state) &&
                Objects.equals(cbmAddtime, that.cbmAddtime) &&
                Objects.equals(cbmServicetime, that.cbmServicetime) &&
                Objects.equals(cbmFinishtime, that.cbmFinishtime) &&
                Objects.equals(carNumber, that.carNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(carMaintenanceId, carId, address, content, state, cbmAddtime, cbmServicetime, cbmFinishtime, carNumber);
    }

    public Integer getCarMaintenanceId() {

        return carMaintenanceId;
    }

    public void setCarMaintenanceId(Integer carMaintenanceId) {
        this.carMaintenanceId = carMaintenanceId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCbmAddtime() {
        return cbmAddtime;
    }

    public void setCbmAddtime(Date cbmAddtime) {
        this.cbmAddtime = cbmAddtime;
    }

    public Date getCbmServicetime() {
        return cbmServicetime;
    }

    public void setCbmServicetime(Date cbmServicetime) {
        this.cbmServicetime = cbmServicetime;
    }

    public Date getCbmFinishtime() {
        return cbmFinishtime;
    }

    public void setCbmFinishtime(Date cbmFinishtime) {
        this.cbmFinishtime = cbmFinishtime;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public CarMaintenance() {
        super();
    }

    public CarMaintenance(Integer carMaintenanceId, Integer carId, String address, String content, Integer state, Date cbmAddtime, Date cbmServicetime, Date cbmFinishtime, String carNumber) {
        this.carMaintenanceId = carMaintenanceId;
        this.carId = carId;
        this.address = address;
        this.content = content;
        this.state = state;
        this.cbmAddtime = cbmAddtime;
        this.cbmServicetime = cbmServicetime;
        this.cbmFinishtime = cbmFinishtime;
        this.carNumber = carNumber;
    }
}