package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author 张占伟
 * @date 2018/12/19
 */
@ApiModel(description = "张占伟")
public class WarehousePositions implements Serializable {
    @ApiModelProperty(value="仓位id", example = "13254123423")

    private Integer wpId;
    @ApiModelProperty(value="仓位所属仓库名字", example = "香港仓库")
    private String warehouseName;

    public String getWarehouseName() {
        return warehouseName;
    }

    public WarehousePositions(Integer wpId, String warehouseName, String wpNumber, Integer warehouseId, Integer wpState, Integer wpHead, Double wpCapacity, Double wpCurrentCapacity, String wpUse) {
        this.wpId = wpId;
        this.warehouseName = warehouseName;
        this.wpNumber = wpNumber;
        this.warehouseId = warehouseId;
        this.wpState = wpState;
        this.wpHead = wpHead;
        this.wpCapacity = wpCapacity;
        this.wpCurrentCapacity = wpCurrentCapacity;
        this.wpUse = wpUse;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public WarehousePositions() {
        super();
    }

    public WarehousePositions(Integer wpId, String wpNumber, Integer warehouseId, Integer wpState, Integer wpHead, Double wpCapacity, Double wpCurrentCapacity, String wpUse) {
        this.wpId = wpId;
        this.wpNumber = wpNumber;
        this.warehouseId = warehouseId;
        this.wpState = wpState;
        this.wpHead = wpHead;
        this.wpCapacity = wpCapacity;
        this.wpCurrentCapacity = wpCurrentCapacity;
        this.wpUse = wpUse;
    }

    @ApiModelProperty(value="仓位编号", example = "ABfd13254123423")

    private String wpNumber;

    @ApiModelProperty(value="所属仓库id", example = "13254123423")

    private Integer warehouseId;

    @ApiModelProperty(value="仓库状态", example = "3")

    private Integer wpState;
    @ApiModelProperty(value="仓位负责人id", example = "1242135")

    private Integer wpHead;
    @ApiModelProperty(value="仓位容量", example = "1325412.3423")

    private Double wpCapacity;

    @ApiModelProperty(value="当前仓位容量", example = "1325412.3423")

    private Double wpCurrentCapacity;

    @ApiModelProperty(value="仓位用途", example = "储藏烟酒")

    private String wpUse;

    @ApiModelProperty(value="区域id", example = "1")
    private Integer saId;

    @ApiModelProperty(value="集结点主键id", example = "1")
    private Integer rallyPointId;

    public Integer getSaId() {
        return saId;
    }

    public void setSaId(Integer saId) {
        this.saId = saId;
    }

    public Integer getRallyPointId() {
        return rallyPointId;
    }

    public void setRallyPointId(Integer rallyPointId) {
        this.rallyPointId = rallyPointId;
    }

    private static final long serialVersionUID = 1L;

    public Integer getWpId() {
        return wpId;
    }

    public void setWpId(Integer wpId) {
        this.wpId = wpId;
    }

    public String getWpNumber() {
        return wpNumber;
    }

    public void setWpNumber(String wpNumber) {
        this.wpNumber = wpNumber == null ? null : wpNumber.trim();
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getWpState() {
        return wpState;
    }

    public void setWpState(Integer wpState) {
        this.wpState = wpState;
    }

    public Integer getWpHead() {
        return wpHead;
    }

    public void setWpHead(Integer wpHead) {
        this.wpHead = wpHead;
    }

    public Double getWpCapacity() {
        return wpCapacity;
    }

    public void setWpCapacity(Double wpCapacity) {
        this.wpCapacity = wpCapacity;
    }

    public Double getWpCurrentCapacity() {
        return wpCurrentCapacity;
    }

    public void setWpCurrentCapacity(Double wpCurrentCapacity) {
        this.wpCurrentCapacity = wpCurrentCapacity;
    }

    public String getWpUse() {
        return wpUse;
    }

    public void setWpUse(String wpUse) {
        this.wpUse = wpUse == null ? null : wpUse.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", wpId=").append(wpId);
        sb.append(", wpNumber=").append(wpNumber);
        sb.append(", warehouseId=").append(warehouseId);
        sb.append(", wpState=").append(wpState);
        sb.append(", wpHead=").append(wpHead);
        sb.append(", wpCapacity=").append(wpCapacity);
        sb.append(", wpCurrentCapacity=").append(wpCurrentCapacity);
        sb.append(", wpUse=").append(wpUse);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();

    }
}