package com.siruiman.crosslogistics.util;

/**
 * @author 张占伟
 * 用来做地图定点 类
 * @date 2018/12/27 16:45
 */
public class MyPoint {
    public Double getX() {
        return X;
    }

    public void setX(Double x) {
        X = x;
    }

    public Double getY() {
        return Y;
    }

    public void setY(Double y) {
        Y = y;
    }

    /**
     * 水平方向值/经度
     */
    public Double X;
    /**
     * 垂直方向值/纬度
     */
    public Double Y;

    public MyPoint() {
        super();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        // 如果为同一对象的不同引用,则相同
        if (this == obj) {
            return true;
        }
        // 如果传入的对象为空,则返回false
        if (obj == null) {
            return false;
        }
        if (obj instanceof MyPoint) {
            MyPoint point = (MyPoint) obj;
            if (point.X.equals(this.X) && point.Y.equals(this.Y)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "MyPoint{" +
                "X=" + X +
                ", Y=" + Y +
                '}';
    }

    public MyPoint(Double x, Double y) {
        X = x;
        Y = y;
    }
}
