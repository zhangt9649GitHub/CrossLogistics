package com.siruiman.crosslogistics.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "郭阳")
public class AddTruckOrderRouteDto {
    @ApiModelProperty(value="货车任务订单id", example = "1")
    private Integer taskOrderId;
    @ApiModelProperty(value="对应状态id", example = "1")
    private Integer routeId;
    @ApiModelProperty(value="1小车集结点 2仓库", example = "1")
    private Integer status;
    @ApiModelProperty(value="排序", example = "1")
    private Integer sequence;

    public Integer getTaskOrderId() {
        return taskOrderId;
    }

    public void setTaskOrderId(Integer taskOrderId) {
        this.taskOrderId = taskOrderId;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        return "AddTruckOrderRouteDto{" +
                "taskOrderId=" + taskOrderId +
                ", routeId=" + routeId +
                ", status=" + status +
                ", sequence=" + sequence +
                '}';
    }
}
