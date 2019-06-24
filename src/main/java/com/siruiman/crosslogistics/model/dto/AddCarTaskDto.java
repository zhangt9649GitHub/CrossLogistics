package com.siruiman.crosslogistics.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "郭阳")
public class AddCarTaskDto {
    @ApiModelProperty(value="小车任务编号", example = "carTaskNumber")
    private String carTaskNumber;
    @ApiModelProperty(value="小车任务名称", example = "carTaskName")
    private String carTaskName;
    @ApiModelProperty(value="用户id", example = "0")
    private Integer userId;
    @ApiModelProperty(value="小车集结点id", example = "0")
    private Integer rallyPointId;
    @ApiModelProperty(value="操作人", example = "0")
    private Integer adminUid;

    public String getCarTaskNumber() {
        return carTaskNumber;
    }

    public void setCarTaskNumber(String carTaskNumber) {
        this.carTaskNumber = carTaskNumber;
    }

    public String getCarTaskName() {
        return carTaskName;
    }

    public void setCarTaskName(String carTaskName) {
        this.carTaskName = carTaskName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRallyPointId() {
        return rallyPointId;
    }

    public void setRallyPointId(Integer rallyPointId) {
        this.rallyPointId = rallyPointId;
    }

    public Integer getAdminUid() {
        return adminUid;
    }

    public void setAdminUid(Integer adminUid) {
        this.adminUid = adminUid;
    }

    @Override
    public String toString() {
        return "AddCarTaskDto{" +
                "carTaskNumber='" + carTaskNumber + '\'' +
                ", carTaskName='" + carTaskName + '\'' +
                ", userId=" + userId +
                ", rallyPointId=" + rallyPointId +
                ", adminUid=" + adminUid +
                '}';
    }
}
