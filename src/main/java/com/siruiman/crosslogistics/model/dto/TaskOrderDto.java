package com.siruiman.crosslogistics.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="郭阳")
public class TaskOrderDto {
    @ApiModelProperty(value = "搜索(订单编号、订单名称)", example = "search")
    private String search;
    @ApiModelProperty(value = "开始时间", example = "startTime")
    private String startTime;
    @ApiModelProperty(value = "结束时间", example = "endTime")
    private String endTime;
    @ApiModelProperty(value = "订单状态 1.已创建 2.已预约 3.进行中 4.已确认 5.已完成 6.已取消", example = "0")
    private Integer state;
    @ApiModelProperty(value = "订单区域id", example = "0")
    private Integer singaporeAreaId;
    @ApiModelProperty(value = "订单类型（小车，货车）", example = "0")
    private String type;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getSingaporeAreaId() {
        return singaporeAreaId;
    }

    public void setSingaporeAreaId(Integer singaporeAreaId) {
        this.singaporeAreaId = singaporeAreaId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TaskOrderDto{" +
                "search='" + search + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", state=" + state +
                ", singaporeAreaId=" + singaporeAreaId +
                ", type='" + type + '\'' +
                '}';
    }
}
