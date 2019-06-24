package com.siruiman.crosslogistics.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="郭阳")
public class StaffDto {
    @ApiModelProperty(value = "编号，姓名，联系方式", example = "mobile")
    private String search;
    @ApiModelProperty(value = "员工权限组id", example = "0")
    private Integer staffGroupId;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Integer getStaffGroupId() {
        return staffGroupId;
    }

    public void setStaffGroupId(Integer staffGroupId) {
        this.staffGroupId = staffGroupId;
    }

    @Override
    public String toString() {
        return "StaffDto{" +
                "search='" + search + '\'' +
                ", staffGroupId=" + staffGroupId +
                '}';
    }
}
