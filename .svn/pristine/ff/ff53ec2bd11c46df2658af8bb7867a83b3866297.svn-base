package com.siruiman.crosslogistics.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(description="郭阳")
public class StaffGroupDto {
    @ApiModelProperty(value = "员工权限组id", example = "0")
    private Integer staffGroupId;
    @ApiModelProperty(value = "员工权限组名称", example = "staffGroupName")
    private String staffGroupName;
    @ApiModelProperty(value = "操作人id", example = "0")
    private Integer adminUid;
    private List<Integer> saIds;

    public Integer getStaffGroupId() {
        return staffGroupId;
    }

    public void setStaffGroupId(Integer staffGroupId) {
        this.staffGroupId = staffGroupId;
    }

    public String getStaffGroupName() {
        return staffGroupName;
    }

    public void setStaffGroupName(String staffGroupName) {
        this.staffGroupName = staffGroupName;
    }

    public Integer getAdminUid() {
        return adminUid;
    }

    public void setAdminUid(Integer adminUid) {
        this.adminUid = adminUid;
    }

    public List<Integer> getSaIds() {
        return saIds;
    }

    public void setSaIds(List<Integer> saIds) {
        this.saIds = saIds;
    }

    @Override
    public String toString() {
        return "StaffGroupDto{" +
                "staffGroupId=" + staffGroupId +
                ", staffGroupName='" + staffGroupName + '\'' +
                ", adminUid=" + adminUid +
                ", saIds=" + saIds +
                '}';
    }
}
