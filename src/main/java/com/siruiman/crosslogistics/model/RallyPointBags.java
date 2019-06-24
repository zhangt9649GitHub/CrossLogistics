package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="郭阳")
public class RallyPointBags {
    @ApiModelProperty(value="货袋主键id", example = "1")
    private Integer bagId;
    @ApiModelProperty(value="货袋编号", example = "货袋编号")
    private String bagNumber;
    @ApiModelProperty(value="该货袋是否送出 1为送出", example = "1")
    private Integer state;

    public Integer getBagId() {
        return bagId;
    }

    public void setBagId(Integer bagId) {
        this.bagId = bagId;
    }

    public String getBagNumber() {
        return bagNumber;
    }

    public void setBagNumber(String bagNumber) {
        this.bagNumber = bagNumber;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "RallyPointBags{" +
                "bagId=" + bagId +
                ", bagNumber='" + bagNumber + '\'' +
                ", state=" + state +
                '}';
    }
}
