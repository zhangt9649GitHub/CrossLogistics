package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * @author 张占伟
 * @date 2018/12/20
 */
@ApiModel(description = "占伟")

public class Bag implements Serializable {
    @ApiModelProperty(value = "货袋主键id", example = "1")
    private Integer bagId;

    @ApiModelProperty(value = "货袋长度", example = "5.54")
    private Double length;


    @ApiModelProperty(value = "货袋更新时间", example = "2019-12-12")
    private String updateTime;

    @ApiModelProperty(value = "货袋宽度", example = "5.54")
    private Double width;

    @ApiModelProperty(value = "货袋高度度", example = "5.54")
    private Double high;

    @ApiModelProperty(value = "货袋承重量", example = "5.54")
    private Double load;

    @ApiModelProperty(value = "货袋编号", example = "ADGFASD32465234")
    private String bagNumber;

    @ApiModelProperty(value = "货袋状态", example = "1")
    private Short state;

    @ApiModelProperty(value = "创建人名字", example = "1")
    private String staffName;

    @ApiModelProperty(value = "货袋所在仓库id", example = "5")
    private Integer warehouseId;

    @ApiModelProperty(value = "货袋关联集结点id", example = "5")
    private Integer rallyPointId;

    @ApiModelProperty(value = "货袋所属仓库仓位id", example = "4")
    private Integer warehousePositionsId;

    @ApiModelProperty(value = "货袋操作员主键id", example = "1")
    private Integer adminUid;

    @ApiModelProperty(value = "货袋所属小车id", example = "1")
    private Integer carId;

    @ApiModelProperty(value = "货袋所属大货车id", example = "1")
    private Integer truckId;

    @ApiModelProperty(value = "货袋所属小车编号", example = "sg0000011")
    private String carNumber;

    @ApiModelProperty(value = "货袋所属大货车牌照", example = "1")
    private String licensePlate;

    @ApiModelProperty(value = "货袋所属任务订单编号", example = "1")
    private String orderNumber;

    @ApiModelProperty(value = "货袋创建时间", example = "1")
    private String createTime;


    @ApiModelProperty(value = "货袋创建时间", example = "1")
    private int staffId;

    @ApiModelProperty(value = "货袋物流进程", example = "1")
    private LogisticInfo info;

    @ApiModelProperty(value = "新加坡地区名字", example = "singaporeAreaName")
    private String singaporeAreaName;

    @ApiModelProperty(value = "集结点名字", example = "rallyPointName")
    private String rallyPointName;

    @ApiModelProperty(value = "所在中国仓库id", example = "1")
    private int initWarehouseId;

    @ApiModelProperty(value = "所在新加坡仓库id", example = "1")
    private int lastWarehouseId;

    @ApiModelProperty(value = "所在中国仓位id", example = "1")
    private int initWpId;

    @ApiModelProperty(value = "所在新加坡仓位id", example = "1")
    private int lastWpId;

    @ApiModelProperty(value = "入库仓库名称", example = "cnWarehouseName")
    private String initWarehouseName;

    @ApiModelProperty(value = "出库仓库名称", example = "sgpWarehouseName")
    private String lastWarehouseName;

    @ApiModelProperty(value = "入库仓位编号", example = "cnWpNumber")
    private String initWpNumber;

    @ApiModelProperty(value = "出库仓位编号", example = "sgpWpNumber")
    private String lastWpNumber;

    @ApiModelProperty(value = "所属新加坡区域id", example = "1")
    private Integer singaporeAreaId;

    @ApiModelProperty(value = "删除状态为2,正常为1", example = "1")
    private Short delState;

    @ApiModelProperty(value = "货袋司机直接配送货车司机id", example = "1")
    private int truckDriverId;


    public int getTruckDriverId() {
        return truckDriverId;
    }

    public void setTruckDriverId(int truckDriverId) {
        this.truckDriverId = truckDriverId;
    }


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public LogisticInfo getInfo() {
        return info;
    }

    public void setInfo(LogisticInfo info) {
        this.info = info;
    }

    public Bag(Integer bagId, Double length, Double width, Double high, Double load, String bagNumber, Short state, Integer warehouseId, Integer rallyPointId, Integer warehousePositionsId, Integer adminUid, Integer carId, Integer truckId, String carNumber, String sgName, String licensePlate, String orderNumber, String createTime, String wpNumber, String warehouseName, String singaporeAreaName, String rallyPointName, int initWarehouseId, int lastWarehouseId, int initWpId, int lastWpId, String initWarehouseName, String lastWarehouseName, String initWpNumber, String lastWpNumber, String updateTime, int singaporeAreaId) {
        this.bagId = bagId;
        this.length = length;
        this.width = width;
        this.high = high;
        this.load = load;
        this.bagNumber = bagNumber;
        this.state = state;
        this.warehouseId = warehouseId;
        this.rallyPointId = rallyPointId;
        this.warehousePositionsId = warehousePositionsId;
        this.adminUid = adminUid;
        this.carId = carId;
        this.truckId = truckId;
        this.carNumber = carNumber;
        this.licensePlate = licensePlate;
        this.orderNumber = orderNumber;
        this.createTime = createTime;
        this.singaporeAreaName = singaporeAreaName;
        this.rallyPointName = rallyPointName;
        this.initWarehouseId = initWarehouseId;
        this.lastWarehouseId = lastWarehouseId;
        this.initWpId = initWpId;
        this.lastWpId = lastWpId;
        this.initWarehouseName = initWarehouseName;
        this.lastWarehouseName = lastWarehouseName;
        this.initWpNumber = initWpNumber;
        this.lastWpNumber = lastWpNumber;
        this.updateTime = updateTime;
        this.singaporeAreaId = singaporeAreaId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getInitWarehouseId() {
        return initWarehouseId;
    }

    public void setInitWarehouseId(int initWarehouseId) {
        this.initWarehouseId = initWarehouseId;
    }

    public int getLastWarehouseId() {
        return lastWarehouseId;
    }

    public void setLastWarehouseId(int lastWarehouseId) {
        this.lastWarehouseId = lastWarehouseId;
    }

    public Integer getSingaporeAreaId() {
        return singaporeAreaId;
    }

    public void setSingaporeAreaId(Integer singaporeAreaId) {
        this.singaporeAreaId = singaporeAreaId;
    }

    public String getInitWarehouseName() {
        return initWarehouseName;
    }

    public void setInitWarehouseName(String initWarehouseName) {
        this.initWarehouseName = initWarehouseName;
    }

    public String getLastWarehouseName() {
        return lastWarehouseName;
    }

    public void setLastWarehouseName(String lastWarehouseName) {
        this.lastWarehouseName = lastWarehouseName;
    }

    public String getInitWpNumber() {
        return initWpNumber;
    }

    public void setInitWpNumber(String initWpNumber) {
        this.initWpNumber = initWpNumber;
    }

    public String getLastWpNumber() {
        return lastWpNumber;
    }

    public void setLastWpNumber(String lastWpNumber) {
        this.lastWpNumber = lastWpNumber;
    }


    public int getInitWarehoseId() {
        return initWarehouseId;
    }

    public void setInitWarehoseId(int initWarehoseId) {
        this.initWarehouseId = initWarehoseId;
    }

    public int getLastWarehoseId() {
        return lastWarehouseId;
    }

    public void setLastWarehoseId(int lastWarehoseId) {
        this.lastWarehouseId = lastWarehoseId;
    }

    public int getInitWpId() {
        return initWpId;
    }

    public void setInitWpId(int initWpId) {
        this.initWpId = initWpId;
    }

    public int getLastWpId() {
        return lastWpId;
    }

    public Short getDelState() {
        return delState;
    }

    public void setDelState(Short delState) {
        this.delState = delState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bag bag = (Bag) o;
        return initWarehouseId == bag.initWarehouseId &&
                lastWarehouseId == bag.lastWarehouseId &&
                initWpId == bag.initWpId &&
                lastWpId == bag.lastWpId &&
                singaporeAreaId == bag.singaporeAreaId &&
                Objects.equals(bagId, bag.bagId) &&
                Objects.equals(length, bag.length) &&
                Objects.equals(width, bag.width) &&
                Objects.equals(high, bag.high) &&
                Objects.equals(load, bag.load) &&
                Objects.equals(bagNumber, bag.bagNumber) &&
                Objects.equals(state, bag.state) &&
                Objects.equals(warehouseId, bag.warehouseId) &&
                Objects.equals(rallyPointId, bag.rallyPointId) &&
                Objects.equals(warehousePositionsId, bag.warehousePositionsId) &&
                Objects.equals(adminUid, bag.adminUid) &&
                Objects.equals(carId, bag.carId) &&
                Objects.equals(truckId, bag.truckId) &&
                Objects.equals(carNumber, bag.carNumber) &&
                Objects.equals(licensePlate, bag.licensePlate) &&
                Objects.equals(orderNumber, bag.orderNumber) &&
                Objects.equals(singaporeAreaName, bag.singaporeAreaName) &&
                Objects.equals(rallyPointName, bag.rallyPointName) &&
                Objects.equals(initWarehouseName, bag.initWarehouseName) &&
                Objects.equals(lastWarehouseName, bag.lastWarehouseName) &&
                Objects.equals(initWpNumber, bag.initWpNumber) &&
                Objects.equals(lastWpNumber, bag.lastWpNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(bagId, length, width, high, load, bagNumber, state, warehouseId, rallyPointId, warehousePositionsId, adminUid, carId, truckId, carNumber, licensePlate, orderNumber, singaporeAreaName, rallyPointName, initWarehouseId, lastWarehouseId, initWpId, lastWpId, initWarehouseName, lastWarehouseName, initWpNumber, lastWpNumber, singaporeAreaId);
    }

    public String getRallyPointName() {
        return rallyPointName;
    }

    public void setRallyPointName(String rallyPointName) {
        this.rallyPointName = rallyPointName;
    }

    public void setLastWpId(int lastWpId) {
        this.lastWpId = lastWpId;
    }

    public Bag() {
        super();
    }

    public Integer getBagId() {
        return bagId;
    }

    public void setBagId(Integer bagId) {
        this.bagId = bagId;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLoad() {
        return load;
    }

    public void setLoad(Double load) {
        this.load = load;
    }

    public String getBagNumber() {
        return bagNumber;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public void setBagNumber(String bagNumber) {
        this.bagNumber = bagNumber;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getRallyPointId() {
        return rallyPointId;
    }

    public void setRallyPointId(Integer rallyPointId) {
        this.rallyPointId = rallyPointId;
    }

    public Integer getWarehousePositionsId() {
        return warehousePositionsId;
    }

    public void setWarehousePositionsId(Integer warehousePositionsId) {
        this.warehousePositionsId = warehousePositionsId;
    }

    public Integer getAdminUid() {
        return adminUid;
    }

    public void setAdminUid(Integer adminUid) {
        this.adminUid = adminUid;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }


    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }


    public String getSingaporeAreaName() {
        return singaporeAreaName;
    }

    public void setSingaporeAreaName(String singaporeAreaName) {
        this.singaporeAreaName = singaporeAreaName;
    }

}
