package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(description = "郭阳")
public class GenerateTruckOrder {
    @ApiModelProperty(value="货车任务id", example = "1")
    private Integer truckTaskId;
    @ApiModelProperty(value="货车任务名称", example = "truckTaskName")
    private String truckTaskName;
    @ApiModelProperty(value="货车司机id", example = "truckDriverId")
    private String truckDriverId;
    @ApiModelProperty(value="区域id", example = "1")
    private Integer singaporeAreaId;
    @ApiModelProperty(value="区域名称", example = "singaporeAreaName")
    private String singaporeAreaName;
    private List<GenerateTruckOrderRoute> generateTruckOrderRoutes;

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

    public String getSingaporeAreaName() {
        return singaporeAreaName;
    }

    public void setSingaporeAreaName(String singaporeAreaName) {
        this.singaporeAreaName = singaporeAreaName;
    }

    public List<GenerateTruckOrderRoute> getGenerateTruckOrderRoutes() {
        return generateTruckOrderRoutes;
    }

    public void setGenerateTruckOrderRoutes(List<GenerateTruckOrderRoute> generateTruckOrderRoutes) {
        this.generateTruckOrderRoutes = generateTruckOrderRoutes;
    }

    @Override
    public String toString() {
        return "GenerateTruckOrder{" +
                "truckTaskId=" + truckTaskId +
                ", truckTaskName='" + truckTaskName + '\'' +
                ", truckDriverId='" + truckDriverId + '\'' +
                ", singaporeAreaId=" + singaporeAreaId +
                ", singaporeAreaName='" + singaporeAreaName + '\'' +
                ", generateTruckOrderRoutes=" + generateTruckOrderRoutes +
                '}';
    }
}
