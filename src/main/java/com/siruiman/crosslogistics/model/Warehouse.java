package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author 张占伟
 * @date 2018/12/19
 */
@ApiModel(description = "张占伟")
public class Warehouse implements Serializable {
    @ApiModelProperty(value="仓库id", example = "0")
    private Integer warehouseId;

    @ApiModelProperty(value="仓库编号", example = "香港012")
    private String warehouseNumber;

    @ApiModelProperty(value="仓库纬度", example = "123.1321.121.123")

    private String warehouseLat;


    @ApiModelProperty(value="仓库纬度", example = "123.1321.121.123")

    private String warehouseLng;

    @ApiModelProperty(value="仓库名字", example = "酒水仓库")

    private String warehouseName;

    @ApiModelProperty(value="仓库容量", example = "51234231.123")

    private Double warehouseCapacity;

    @ApiModelProperty(value="仓库地址", example = "朝阳区朝阳街道XX号")

    private String warehouseAddress;

    @ApiModelProperty(value="仓库当前容量", example = "43212324.123")

    private Double warehouseCurrentCapacity;

    @ApiModelProperty(value="仓库状态", example = "2")
    private Integer warehouseState;

    @ApiModelProperty(value="仓库类型", example = "3")

    private Integer warehouseType;

    @ApiModelProperty(value="仓库联系电话", example = "13254123423")
    private String warehousePhone;


    @ApiModelProperty(value="仓库负责人id", example = "123413412")
    private Integer warehouseHead;

    public Warehouse() {
        super();
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "warehouseId=" + warehouseId +
                ", warehouseNumber='" + warehouseNumber + '\'' +
                ", warehouseLat='" + warehouseLat + '\'' +
                ", warehouseLng='" + warehouseLng + '\'' +
                ", warehouseName='" + warehouseName + '\'' +
                ", warehouseCapacity=" + warehouseCapacity +
                ", warehouseAddress='" + warehouseAddress + '\'' +
                ", warehouseCurrentCapacity=" + warehouseCurrentCapacity +
                ", warehouseState=" + warehouseState +
                ", warehouseType=" + warehouseType +
                ", warehousePhone='" + warehousePhone + '\'' +
                ", warehouseHead=" + warehouseHead +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Warehouse warehouse = (Warehouse) o;
        return Objects.equals(warehouseId, warehouse.warehouseId) &&
                Objects.equals(warehouseNumber, warehouse.warehouseNumber) &&
                Objects.equals(warehouseLat, warehouse.warehouseLat) &&
                Objects.equals(warehouseLng, warehouse.warehouseLng) &&
                Objects.equals(warehouseName, warehouse.warehouseName) &&
                Objects.equals(warehouseCapacity, warehouse.warehouseCapacity) &&
                Objects.equals(warehouseAddress, warehouse.warehouseAddress) &&
                Objects.equals(warehouseCurrentCapacity, warehouse.warehouseCurrentCapacity) &&
                Objects.equals(warehouseState, warehouse.warehouseState) &&
                Objects.equals(warehouseType, warehouse.warehouseType) &&
                Objects.equals(warehousePhone, warehouse.warehousePhone) &&
                Objects.equals(warehouseHead, warehouse.warehouseHead);
    }

    @Override
    public int hashCode() {

        return Objects.hash(warehouseId, warehouseNumber, warehouseLat, warehouseLng, warehouseName, warehouseCapacity, warehouseAddress, warehouseCurrentCapacity, warehouseState, warehouseType, warehousePhone, warehouseHead);
    }


    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseNumber() {
        return warehouseNumber;
    }

    public void setWarehouseNumber(String warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }

    public String getWarehouseLat() {
        return warehouseLat;
    }

    public void setWarehouseLat(String warehouseLat) {
        this.warehouseLat = warehouseLat;
    }

    public String getWarehouseLng() {
        return warehouseLng;
    }

    public void setWarehouseLng(String warehouseLng) {
        this.warehouseLng = warehouseLng;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public Double getWarehouseCapacity() {
        return warehouseCapacity;
    }

    public void setWarehouseCapacity(Double warehouseCapacity) {
        this.warehouseCapacity = warehouseCapacity;
    }

    public String getWarehouseAddress() {
        return warehouseAddress;
    }

    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }

    public Double getWarehouseCurrentCapacity() {
        return warehouseCurrentCapacity;
    }

    public void setWarehouseCurrentCapacity(Double warehouseCurrentCapacity) {
        this.warehouseCurrentCapacity = warehouseCurrentCapacity;
    }

    public Integer getWarehouseState() {
        return warehouseState;
    }

    public void setWarehouseState(Integer warehouseState) {
        this.warehouseState = warehouseState;
    }

    public Integer getWarehouseType() {
        return warehouseType;
    }

    public void setWarehouseType(Integer warehouseType) {
        this.warehouseType = warehouseType;
    }

    public String getWarehousePhone() {
        return warehousePhone;
    }

    public void setWarehousePhone(String warehousePhone) {
        this.warehousePhone = warehousePhone;
    }

    public Integer getWarehouseHead() {
        return warehouseHead;
    }

    public void setWarehouseHead(Integer warehouseHead) {
        this.warehouseHead = warehouseHead;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private static final long serialVersionUID = 1L;


    public Warehouse(Integer warehouseId, String warehouseNumber, String warehouseLat, String warehouseLng, String warehouseName, Double warehouseCapacity, String warehouseAddress, Double warehouseCurrentCapacity, Integer warehouseState, Integer warehouseType, String warehousePhone, Integer warehouseHead) {
        this.warehouseId = warehouseId;
        this.warehouseNumber = warehouseNumber;
        this.warehouseLat = warehouseLat;
        this.warehouseLng = warehouseLng;
        this.warehouseName = warehouseName;
        this.warehouseCapacity = warehouseCapacity;
        this.warehouseAddress = warehouseAddress;
        this.warehouseCurrentCapacity = warehouseCurrentCapacity;
        this.warehouseState = warehouseState;
        this.warehouseType = warehouseType;
        this.warehousePhone = warehousePhone;
        this.warehouseHead = warehouseHead;
    }
}