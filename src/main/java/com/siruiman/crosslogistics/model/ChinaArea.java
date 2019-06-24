package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author 张占伟
 * @date 2018/12/19
 */
@ApiModel(description = "张占伟")

public class ChinaArea implements Serializable {
    @ApiModelProperty(value="中国地区id", example = "1")
    private Integer chinaAreaId;

    @ApiModelProperty(value="中国地区名字", example = "北京")
    private String chinaAreaName;

    @ApiModelProperty(value="中国地区上级地区id", example = "1")
    private Integer chinaAreaParentId;
    public ChinaArea() {
        super();
    }

    public ChinaArea(Integer chinaAreaId, String chinaAreaName, Integer chinaAreaParentId, Integer chinaAreaType) {
        this.chinaAreaId = chinaAreaId;
        this.chinaAreaName = chinaAreaName;
        this.chinaAreaParentId = chinaAreaParentId;
        this.chinaAreaType = chinaAreaType;
    }

    @ApiModelProperty(value="中国地区类型", example = "1")
    private Integer chinaAreaType;

    private static final long serialVersionUID = 1L;

    public Integer getChinaAreaId() {
        return chinaAreaId;
    }

    public void setChinaAreaId(Integer chinaAreaId) {
        this.chinaAreaId = chinaAreaId;
    }

    public String getChinaAreaName() {
        return chinaAreaName;
    }

    public void setChinaAreaName(String chinaAreaName) {
        this.chinaAreaName = chinaAreaName == null ? null : chinaAreaName.trim();
    }

    public Integer getChinaAreaParentId() {
        return chinaAreaParentId;
    }

    public void setChinaAreaParentId(Integer chinaAreaParentId) {
        this.chinaAreaParentId = chinaAreaParentId;
    }

    public Integer getChinaAreaType() {
        return chinaAreaType;
    }

    public void setChinaAreaType(Integer chinaAreaType) {
        this.chinaAreaType = chinaAreaType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", chinaAreaId=").append(chinaAreaId);
        sb.append(", chinaAreaName=").append(chinaAreaName);
        sb.append(", chinaAreaParentId=").append(chinaAreaParentId);
        sb.append(", chinaAreaType=").append(chinaAreaType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}