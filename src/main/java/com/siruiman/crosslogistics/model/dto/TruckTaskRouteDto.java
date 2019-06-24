package com.siruiman.crosslogistics.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "郭阳")
public class TruckTaskRouteDto {
    @ApiModelProperty(value="对应状态显示对应的id", example = "1")
    private Integer routeId;
    @ApiModelProperty(value="1小车集结点 2仓库", example = "0")
    private Integer status;
    @ApiModelProperty(value="排序", example = "1")
    private Integer sort;

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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "TruckTaskRouteDto{" +
                "routeId=" + routeId +
                ", status=" + status +
                ", sort=" + sort +
                '}';
    }
}
