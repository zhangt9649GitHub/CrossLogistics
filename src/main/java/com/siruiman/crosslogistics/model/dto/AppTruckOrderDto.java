package com.siruiman.crosslogistics.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="郭阳")
public class AppTruckOrderDto {
    @ApiModelProperty(value = "货车订单状态1全部订单 2可抢订单 3已被抢订单", example = "1")
    private Integer orderStatus;
    @ApiModelProperty(value = "排序（时间排序 orderTime, 距离 distance）", example = "排序（时间排序 orderTime, 距离 distance）")
    private String sortName;
    @ApiModelProperty(value = "1正序 2倒序", example = "1")
    private Integer sort;
    @ApiModelProperty(value = "订单时间", example = "订单时间")
    private String createTime;
    @ApiModelProperty(value = "区域id", example = "1")
    private Integer singaporeAreaId;

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getSingaporeAreaId() {
        return singaporeAreaId;
    }

    public void setSingaporeAreaId(Integer singaporeAreaId) {
        this.singaporeAreaId = singaporeAreaId;
    }

    @Override
    public String toString() {
        return "AppTruckOrderDto{" +
                "orderStatus=" + orderStatus +
                ", sortName='" + sortName + '\'' +
                ", sort=" + sort +
                ", createTime='" + createTime + '\'' +
                ", singaporeAreaId=" + singaporeAreaId +
                '}';
    }
}
