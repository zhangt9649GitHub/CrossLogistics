package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="郭阳")
public class CarOrderUserCar {
    @ApiModelProperty(value="小车任务订单id", example = "1")
    private Integer taskOrderId;
    @ApiModelProperty(value="用户id", example = "1")
    private Integer appUserId;

    public Integer getTaskOrderId() {
        return taskOrderId;
    }

    public void setTaskOrderId(Integer taskOrderId) {
        this.taskOrderId = taskOrderId;
    }

    public Integer getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Integer appUserId) {
        this.appUserId = appUserId;
    }

    @Override
    public String toString() {
        return "CarOrderUserCar{" +
                "taskOrderId=" + taskOrderId +
                ", appUserId=" + appUserId +
                '}';
    }
}
