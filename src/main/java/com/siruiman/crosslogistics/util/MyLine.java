package com.siruiman.crosslogistics.util;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author 张占伟
 * @date 2018/12/27 16:57
 */
public class MyLine {

    private static final int DEF_DIV_SCALE = 14;

    private MyPoint pointA;

    private MyPoint pointB;

    public MyPoint getPointA() {
        return pointA;
    }

    public void setPointA(MyPoint pointA) {
        this.pointA = pointA;
    }

    public MyPoint getPointB() {
        return pointB;
    }

    public void setPointB(MyPoint pointB) {
        this.pointB = pointB;
    }



    @Override
    public int hashCode() {

        return Objects.hash(pointA, pointB);
    }

    public MyLine(MyPoint pointA, MyPoint pointB) {

        this.pointA = pointA;
        this.pointB = pointB;
    }


    public boolean isContainsPoint(MyPoint point) {
        boolean result = false;
        //判断给定点point与端点1构成线段的斜率是否和当前线段的斜率相同
        //给定点point与端点1构成线段的斜率k1
        Double k1 = null;
        if (point.X.equals(this.pointA.X)) {
            k1 = Double.NEGATIVE_INFINITY;
        } else {
            k1 = div(sub(point.Y, this.pointA.Y), sub(point.X, this.pointA.X));
        }
        //当前线段的斜率k2
        Double k2 = null;
        if (this.pointB.X.equals(this.pointA.X)) {
            k2 = Double.NEGATIVE_INFINITY;
        } else {
            k2 = div(sub(this.pointB.Y, this.pointA.Y), sub(this.pointB.X, this.pointA.X));
        }
        if (k1 != null && k2 != null) {
            if (k1.equals(k2)) {
                //若斜率相同，继续判断给定点point的x是否在pointA.x和pointB.x之间,若在 则说明该点在当前边上
                if (sub(point.X, this.pointA.X) * sub(point.X, this.pointB.X) < 0) {
                    result = true;
                }
            }
        }
        return result;
    }


    /**
     * 叉积
     * @param a
     * @param b
     * @param c
     * @return
     */
   double mult(MyPoint a, MyPoint b, MyPoint c) {
       return (a.X-c.X)*(b.Y-c.Y)-(b.X-c.X)*(a.Y-c.Y);
   }

    /**
     * 提供精确的加法计算
     * @param v1 加数
     * @param v2 加数
     * @return 和 double类型
     */
    public static double sum(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确的减法运算。
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double sub(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * 小数点以后14位，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static double div(double v1,double v2){
        return div(v1,v2,DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1,double v2,int scale){
        if(scale<0){
            throw new IllegalArgumentException(
//                    小数点保留位数必须是正整数或者0
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 给定线段是否与当前线段相交</br>
     * 相交返回true, 不相交返回false
     * @param line
     * @return
     */
    public boolean isIntersect(MyLine line) {
        MyPoint aa = this.pointA;
        MyPoint bb = this.pointB;
        MyPoint cc = line.pointA;
        MyPoint dd = line.pointB;
        if (Math.max(aa.X, bb.X) < Math.min(cc.X, dd.X)) {
            return false;
        }
        if (Math.max(aa.Y, bb.Y) < Math.min(cc.Y, dd.Y)) {
            return false;
        }
        if (Math.max(cc.X, dd.X) < Math.min(aa.X, bb.X)) {
            return false;
        }
        if (Math.max(cc.Y, dd.Y) < Math.min(aa.Y, bb.Y)) {
            return false;
        }
        if (mult(cc, bb, aa) * mult(bb, dd, aa) < 0 ) {
            return false;
        }
        if ( mult(aa, dd, cc) * mult(dd, bb, cc) < 0 ) {
            return false;
        }
        return true;
    }

}


