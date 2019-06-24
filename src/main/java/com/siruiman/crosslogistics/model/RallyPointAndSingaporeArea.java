package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "郭阳")
public class RallyPointAndSingaporeArea {
    @ApiModelProperty(value="小车集结点id", example = "0")
    private Integer rallyPointId;
    @ApiModelProperty(value="小车集结点名称", example = "rallyPointName")
    private String rallyPointName;
    @ApiModelProperty(value="区域id", example = "0")
    private Integer singaporeAreaId;
    @ApiModelProperty(value="区域名称", example = "singaporeAreaName")
    private String singaporeAreaName;

    public Integer getRallyPointId() {
        return rallyPointId;
    }

    public void setRallyPointId(Integer rallyPointId) {
        this.rallyPointId = rallyPointId;
    }

    public String getRallyPointName() {
        return rallyPointName;
    }

    public void setRallyPointName(String rallyPointName) {
        this.rallyPointName = rallyPointName;
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

    @Override
    public String toString() {
        return "RallyPointAndSingaporeArea{" +
                "rallyPointId=" + rallyPointId +
                ", rallyPointName='" + rallyPointName + '\'' +
                ", singaporeAreaId=" + singaporeAreaId +
                ", singaporeAreaName='" + singaporeAreaName + '\'' +
                '}';
    }
}
