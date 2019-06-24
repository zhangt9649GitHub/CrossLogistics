package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "郭阳")
public class CarOrderTime {
    @ApiModelProperty(value="小车订单id(如果没有抢单的人，那这个字段就是模板id)", example = "1")
    private Integer taskOrderId;
    @ApiModelProperty(value="抢单时间(对应上面是新增模板时间)", example = "grabOrderTime")
    private String grabOrderTime;

    public Integer getTaskOrderId() {
        return taskOrderId;
    }

    public void setTaskOrderId(Integer taskOrderId) {
        this.taskOrderId = taskOrderId;
    }

    public String getGrabOrderTime() {
        return grabOrderTime;
    }

    public void setGrabOrderTime(String grabOrderTime) {
        this.grabOrderTime = grabOrderTime;
    }

    @Override
    public String toString() {
        return "CarOrderTime{" +
                "taskOrderId=" + taskOrderId +
                ", grabOrderTime='" + grabOrderTime + '\'' +
                '}';
    }
}
