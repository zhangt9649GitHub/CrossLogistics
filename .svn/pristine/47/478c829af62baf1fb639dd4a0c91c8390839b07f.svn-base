package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="郭阳")
public class CargoInfo {
    @ApiModelProperty(value = "异常状态  无异常 异常 正在处理中", example = "异常状态  无异常 异常 正在处理中")
    private String warningState;
    @ApiModelProperty(value = "是否确认收货 1.是 2 否", example = "1")
    private Integer isReceiveGoods;

    public String getWarningState() {
        return warningState;
    }

    public void setWarningState(String warningState) {
        this.warningState = warningState;
    }

    public Integer getIsReceiveGoods() {
        return isReceiveGoods;
    }

    public void setIsReceiveGoods(Integer isReceiveGoods) {
        this.isReceiveGoods = isReceiveGoods;
    }

    @Override
    public String toString() {
        return "CargoInfo{" +
                "warningState='" + warningState + '\'' +
                ", isReceiveGoods=" + isReceiveGoods +
                '}';
    }
}
