package com.siruiman.crosslogistics.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(description = "郭阳")
public class EditTruckTaskDto {
    @ApiModelProperty(value="货车任务id", example = "0")
    private Integer truckTaskId;
    @ApiModelProperty(value="货车任务名称", example = "truckTaskName")
    private String truckTaskName;
    @ApiModelProperty(value = "货车司机id", example = "0")
    private String truckDriverId;
    @ApiModelProperty(value = "区域id", example = "0")
    private Integer singaporeAreaId;
    @ApiModelProperty(value = "经纬度", example = "latLng")
    private String latLng;
    @ApiModelProperty(value="操作人", example = "0")
    private Integer adminId;
    @ApiModelProperty(value = "路线")
    private String truckTaskRoutes;

    public Integer getTruckTaskId() {
        return truckTaskId;
    }

    public void setTruckTaskId(Integer truckTaskId) {
        this.truckTaskId = truckTaskId;
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

    public Integer getSingaporeAreaId() {
        return singaporeAreaId;
    }

    public void setSingaporeAreaId(Integer singaporeAreaId) {
        this.singaporeAreaId = singaporeAreaId;
    }

    public String getLatLng() {
        return latLng;
    }

    public void setLatLng(String latLng) {
        this.latLng = latLng;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getTruckTaskRoutes() {
        return truckTaskRoutes;
    }

    public void setTruckTaskRoutes(String truckTaskRoutes) {
        this.truckTaskRoutes = truckTaskRoutes;
    }

    @Override
    public String toString() {
        return "EditTruckTaskDto{" +
                "truckTaskId=" + truckTaskId +
                ", truckTaskName='" + truckTaskName + '\'' +
                ", truckDriverId='" + truckDriverId + '\'' +
                ", singaporeAreaId=" + singaporeAreaId +
                ", latLng='" + latLng + '\'' +
                ", adminId=" + adminId +
                ", truckTaskRoutes='" + truckTaskRoutes + '\'' +
                '}';
    }
}
