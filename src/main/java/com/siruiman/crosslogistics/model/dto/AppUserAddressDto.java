package com.siruiman.crosslogistics.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="郭阳")
public class AppUserAddressDto {
    @ApiModelProperty(value = "搜索（用户名、手机号、邮编）", example = "0")
    private String search;
    @ApiModelProperty(value = "区域id数组", example = "singaporeAreaIds")
    private String singaporeAreaIds;
    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getSingaporeAreaIds() {
        return singaporeAreaIds;
    }

    public void setSingaporeAreaIds(String singaporeAreaIds) {
        this.singaporeAreaIds = singaporeAreaIds;
    }

    @Override
    public String toString() {
        return "AppUserAddressDto{" +
                "search='" + search + '\'' +
                ", singaporeAreaIds='" + singaporeAreaIds + '\'' +
                '}';
    }
}
