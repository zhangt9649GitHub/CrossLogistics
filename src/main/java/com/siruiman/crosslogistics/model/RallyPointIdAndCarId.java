package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "郭阳")
public class RallyPointIdAndCarId {
    @ApiModelProperty(value="小车集结点id", example = "1")
    private Integer rallyPointId;
    @ApiModelProperty(value="小车id", example = "1")
    private Integer carId;

    public Integer getRallyPointId() {
        return rallyPointId;
    }

    public void setRallyPointId(Integer rallyPointId) {
        this.rallyPointId = rallyPointId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    @Override
    public String toString() {
        return "RallyPointIdAndCarId{" +
                "rallyPointId=" + rallyPointId +
                ", carId=" + carId +
                '}';
    }
}
