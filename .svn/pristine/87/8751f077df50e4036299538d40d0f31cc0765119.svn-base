package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(description = "郭阳")
public class TruckTaskDetails {
    @ApiModelProperty(value="货车任务编号", example = "truckTaskNumber")
    private String truckTaskNumber;
    @ApiModelProperty(value="货车任务名称", example = "truckTaskName")
    private String truckTaskName;
    @ApiModelProperty(value="货车司机id", example = "truckDriverId")
    private String truckDriverId;
    @ApiModelProperty(value="货车司机名称", example = "truckDriverName")
    private String truckDriverName;
    @ApiModelProperty(value="状态", example = "0")
    private Integer status;
    @ApiModelProperty(value="添加时间", example = "addTime")
    private String addTime;
    @ApiModelProperty(value="操作人姓名", example = "adminName")
    private String adminName;
    @ApiModelProperty(value = "路线")
    private List<TruckTaskRoute> truckTaskRoutes;

    public String getTruckTaskNumber() {
        return truckTaskNumber;
    }

    public void setTruckTaskNumber(String truckTaskNumber) {
        this.truckTaskNumber = truckTaskNumber;
    }

    public String getTruckTaskName() {
        return truckTaskName;
    }

    public void setTruckTaskName(String truckTaskName) {
        this.truckTaskName = truckTaskName;
    }

    public String getTruckDriverId() {
        return truckDriverId;
    }

    public void setTruckDriverId(String truckDriverId) {
        this.truckDriverId = truckDriverId;
    }

    public String getTruckDriverName() {
        return truckDriverName;
    }

    public void setTruckDriverName(String truckDriverName) {
        this.truckDriverName = truckDriverName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public List<TruckTaskRoute> getTruckTaskRoutes() {
        return truckTaskRoutes;
    }

    public void setTruckTaskRoutes(List<TruckTaskRoute> truckTaskRoutes) {
        this.truckTaskRoutes = truckTaskRoutes;
    }

    @Override
    public String toString() {
        return "TruckTaskDetails{" +
                "truckTaskNumber='" + truckTaskNumber + '\'' +
                ", truckTaskName='" + truckTaskName + '\'' +
                ", truckDriverId='" + truckDriverId + '\'' +
                ", truckDriverName='" + truckDriverName + '\'' +
                ", status=" + status +
                ", addTime='" + addTime + '\'' +
                ", adminName='" + adminName + '\'' +
                ", truckTaskRoutes=" + truckTaskRoutes +
                '}';
    }
}
