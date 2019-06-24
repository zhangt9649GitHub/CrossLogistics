package com.siruiman.crosslogistics.util;

import java.util.Objects;

/**
 * @author 张占伟
 * @date 2019/3/5 14:09
 * 距离距离工具类
 */
public class Distance {

//   集结点id
    private Integer rallyPointId;
//    距离
    private double distance;
//    大楼id
    private Integer saBuildingId;

    public Distance(Integer rallyPointId, double distance, Integer saBuildingId) {
        this.rallyPointId = rallyPointId;
        this.distance = distance;
        this.saBuildingId = saBuildingId;
    }

    public Distance() {
        super();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distance distance1 = (Distance) o;
        return Double.compare(distance1.distance, distance) == 0 &&
                Objects.equals(rallyPointId, distance1.rallyPointId) &&
                Objects.equals(saBuildingId, distance1.saBuildingId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(rallyPointId, distance, saBuildingId);
    }

    @Override
    public String toString() {
        return "Distance{" +
                "rallyPointId=" + rallyPointId +
                ", distance=" + distance +
                ", saBuildingId=" + saBuildingId +
                '}';
    }

    public Integer getRallyPointId() {
        return rallyPointId;
    }

    public void setRallyPointId(Integer rallyPointId) {
        this.rallyPointId = rallyPointId;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Integer getSaBuildingId() {
        return saBuildingId;
    }

    public void setSaBuildingId(Integer saBuildingId) {
        this.saBuildingId = saBuildingId;
    }
}
