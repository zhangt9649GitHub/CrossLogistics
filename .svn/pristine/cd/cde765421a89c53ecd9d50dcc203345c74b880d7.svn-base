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
public class SingaporeAreaBuilding implements Serializable {

    @ApiModelProperty(value="大楼id", example = "1")
    private Integer saBuildingId;
    @ApiModelProperty(value="区域id", example = "1")
    private Integer saId;
    @ApiModelProperty(value="大楼名字", example = "央视大裤衩")
    private String saBuildingName;
    @ApiModelProperty(value="大楼纬度", example = "1.37768826264632")
    private String saBuildingLat;
    @ApiModelProperty(value="大楼经度", example = "1.37768826264632")
    private String saBuildingLng;
    @ApiModelProperty(value="大楼邮编", example = "182736")
    private String saZipCode;
    @ApiModelProperty(value="大楼地址", example = "朝阳路朝阳街32号879897")
    private String saBuildingAddress;
    @ApiModelProperty(value="集结点主键id", example = "1")
    private Integer rallyPointId;
    @ApiModelProperty(value="新加坡地区名字", example = "dsafa")
    private String singaporeAreaName;

    @ApiModelProperty(value="是否启用", example = "1")
    private Integer delState;

    public Integer getDelState() {
        return delState;
    }

    public void setDelState(Integer delState) {
        this.delState = delState;
    }

    public String getSingaporeAreaName() {
        return singaporeAreaName;
    }

    public void setSingaporeAreaName(String singaporeAreaName) {
        this.singaporeAreaName = singaporeAreaName;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingaporeAreaBuilding that = (SingaporeAreaBuilding) o;
        return Objects.equals(saBuildingId, that.saBuildingId) &&
                Objects.equals(saId, that.saId) &&
                Objects.equals(saBuildingName, that.saBuildingName) &&
                Objects.equals(saBuildingLat, that.saBuildingLat) &&
                Objects.equals(saBuildingLng, that.saBuildingLng) &&
                Objects.equals(saZipCode, that.saZipCode) &&
                Objects.equals(saBuildingAddress, that.saBuildingAddress) &&
                Objects.equals(rallyPointId, that.rallyPointId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(saBuildingId, saId, saBuildingName, saBuildingLat, saBuildingLng, saZipCode, saBuildingAddress, rallyPointId);
    }

    @Override
    public String toString() {
        return "SingaporeAreaBuilding{" +
                "saBuildingId=" + saBuildingId +
                ", saId=" + saId +
                ", saBuildingName='" + saBuildingName + '\'' +
                ", saBuildingLat='" + saBuildingLat + '\'' +
                ", saBuildingLng='" + saBuildingLng + '\'' +
                ", saZipCode='" + saZipCode + '\'' +
                ", saBuildingAddress='" + saBuildingAddress + '\'' +
                ", rallyPointId=" + rallyPointId +
                ", singaporeAreaName='" + singaporeAreaName + '\'' +
                '}';
    }

    public SingaporeAreaBuilding(Integer saBuildingId, Integer saId, String saBuildingName, String saBuildingLat, String saBuildingLng, String saZipCode, String saBuildingAddress, Integer rallyPointId) {
        this.saBuildingId = saBuildingId;
        this.saId = saId;
        this.saBuildingName = saBuildingName;
        this.saBuildingLat = saBuildingLat;
        this.saBuildingLng = saBuildingLng;
        this.saZipCode = saZipCode;
        this.saBuildingAddress = saBuildingAddress;
        this.rallyPointId = rallyPointId;
    }

    public String getSaBuildingAddress() {
        return saBuildingAddress;
    }

    public void setSaBuildingAddress(String saBuildingAddress) {
        this.saBuildingAddress = saBuildingAddress;
    }

    public SingaporeAreaBuilding() {
        super();
    }


    public String getSaBuildingLat() {
        return saBuildingLat;
    }

    public void setSaBuildingLat(String saBuildingLat) {
        this.saBuildingLat = saBuildingLat;
    }

    public String getSaBuildingLng() {
        return saBuildingLng;
    }

    public void setSaBuildingLng(String saBuildingLng) {
        this.saBuildingLng = saBuildingLng;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private static final long serialVersionUID = 1L;

    public Integer getSaBuildingId() {
        return saBuildingId;
    }

    public void setSaBuildingId(Integer saBuildingId) {
        this.saBuildingId = saBuildingId;
    }

    public Integer getSaId() {
        return saId;
    }

    public void setSaId(Integer saId) {
        this.saId = saId;
    }

    public String getSaBuildingName() {
        return saBuildingName;
    }

    public void setSaBuildingName(String saBuildingName) {
        this.saBuildingName = saBuildingName == null ? null : saBuildingName.trim();
    }

    public String getSaZipCode() {
        return saZipCode;
    }

    public void setSaZipCode(String saZipCode) {
        this.saZipCode = saZipCode == null ? null : saZipCode.trim();
    }

    public Integer getRallyPointId() {
        return rallyPointId;
    }

    public void setRallyPointId(Integer rallyPointId) {
        this.rallyPointId = rallyPointId;
    }
}