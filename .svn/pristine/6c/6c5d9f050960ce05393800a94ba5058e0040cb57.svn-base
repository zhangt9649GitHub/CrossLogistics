package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "郭阳")
public class TruckDeliveryAssStep {
    @ApiModelProperty(value="车锁编号", example = "lockNumber")
    private String lockNumber;
    @ApiModelProperty(value="货袋编号", example = "bagNumber")
    private String bagNumber;
    @ApiModelProperty(value="路线主键id", example = "0")
    private Integer todrId = 0;
    @ApiModelProperty(value="小车集结点id", example = "0")
    private Integer rallyPointId = 0;

    public String getLockNumber() {
        return lockNumber;
    }

    public void setLockNumber(String lockNumber) {
        this.lockNumber = lockNumber;
    }

    public String getBagNumber() {
        return bagNumber;
    }

    public void setBagNumber(String bagNumber) {
        this.bagNumber = bagNumber;
    }

    public Integer getTodrId() {
        return todrId;
    }

    public void setTodrId(Integer todrId) {
        this.todrId = todrId;
    }

    public Integer getRallyPointId() {
        return rallyPointId;
    }

    public void setRallyPointId(Integer rallyPointId) {
        this.rallyPointId = rallyPointId;
    }

    @Override
    public String toString() {
        return "TruckDeliveryAssStep{" +
                "lockNumber='" + lockNumber + '\'' +
                ", bagNumber='" + bagNumber + '\'' +
                ", todrId=" + todrId +
                ", rallyPointId=" + rallyPointId +
                '}';
    }
}
