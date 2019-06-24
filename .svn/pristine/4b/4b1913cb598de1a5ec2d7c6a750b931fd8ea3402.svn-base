package com.siruiman.crosslogistics.util;

import com.siruiman.crosslogistics.model.SingaporePoint;

import java.math.BigDecimal;
import java.util.List;

/**
 * 地图两点之间求距离工具类
 * @author 张占伟
 * @date 2018/12/24
 */
public class MapUtils {
//    地球的半径
    private static double EARTH_RADIUS = 6371.393;
    private static double rad(double d)
    {
        return d * Math.PI / 180.0;
    }

    /**
     * 计算的是直线距离不是路线距离
     * @param lat1 点1纬度
     * @param lng1 点1经度
     * @param lat2 点2纬度
     * @param lng2 点2经度
     * @return 两点之间的距离 单位:米
     */
    public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.abs(Math.pow(Math.sin(a/2),2) +
                Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2))));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 1000);
        return s;
    }




    /**
     * 判断一个点是否在一个平面内
     * @param point
     * @param points
     * @return true 在面内 false 不在面内
     */
    public static boolean isPointInPolygon(MyPoint point, List<MyPoint> points){
       boolean result = false;
       int intersectCount = 0;

        /**
         *  判断依据：求解从该点向右发出的水平线射线与多边形各边的交点，当交点数为奇数，则在内部
         *         不过要注意几种特殊情况：1、点在边或者顶点上;2、点在边的延长线上;3、点出发的水平射线与多边形相交在顶点上
         * 具体步骤如下：
         * 循环遍历各个线段：
         *  1、判断点是否在当前边上(斜率相同,且该点的x值在两个端口的x值之间),若是则返回true
         *  2、否则判断由该点发出的水平射线是否与当前边相交,若不相交则continue
         *  3、若相交,则判断是否相交在顶点上(边的端点是否在给定点的水平右侧).若不在,则认为此次相交为穿越,交点数+1 并continue
         *  4、若交在顶点上,则判断上一条边的另外一个端点与当前边的另外一个端点是否分布在水平射线的两侧.若是则认为此次相交为穿越,交点数+1.
         *
        **/
        for (int i = 0; i <points.size() ; i++) {
            MyPoint pointA = points.get(i);
            MyPoint pointB = null;
            MyPoint pointPre = null;
            //若当前是第一个点,则上一点则是list里面的最后一个点
            if (i == 0) {
                pointPre = points.get(points.size() - 1);
            } else {
                pointPre = points.get(i - 1);
            }
            //若已经循环到最后一个点,则与之连接的是第一个点
            if (i == (points.size() - 1)) {
                pointB = points.get(0);
            } else {
                pointB = points.get(i + 1);
            }
            MyLine line = new MyLine(pointA, pointB);
            //1、判断点是否在当前边上(斜率相同,且该点的x值在两个端口的x值之间),若是则返回true
            boolean isAtLine = line.isContainsPoint(point);
            if (isAtLine) {
                return true;
            } else {
                //2、若不在边上,判断由该点发出的水平射线是否与当前边相交,若不相交则continue
                //设置该射线的另外一个端点的x值=999,保证边的x永远不超过
                MyPoint  radialPoint = new MyPoint(999d, point.Y);
                MyLine radial = new  MyLine(point, radialPoint);
                boolean isIntersect = radial.isIntersect(line);
                if (!isIntersect) {
                    continue;
                } else {
                    //3、若相交,则判断是否相交在顶点上(边的端点是否在给定点的水平右侧).若不在,则认为此次相交为穿越,交点数+1 并continue
                    if (!( (pointA.X > point.X) && (pointA.Y.equals(point.Y))
                            || (pointB.X > point.X) && (pointB.Y.equals(point.Y)) )) {
                        intersectCount++;
                        continue;
                    } else {
                        //4、若交在顶点上,则判断上一条边的另外一个端点与当前边的另外一个端点是否分布在水平射线的两侧.若是则认为此次相交为穿越,交点数+1
                        if (( pointPre.Y - point.Y) * (pointB.Y - point.Y) < 0) {
                            intersectCount++;
                        }
                    }
                }
            }
        }
        result = intersectCount % 2 == 1;
        return result;

    }

    /**
     *
     * 点与(很多点之间之间距离最短的点)
     * @param point
     * @param list
     * @return
     */

    public SingaporePoint getMinDistanceOfPoint(SingaporePoint point, List<SingaporePoint> list) {

//          定义一个结果点
        SingaporePoint results = new SingaporePoint();
//        定义最短长度
        double resultsmin =10000000.0;
        double distance1,distance2;
        SingaporePoint sp1, sp2;
        double lat1 = Double.parseDouble(point.getLat());
        double lng1 = Double.parseDouble(point.getLng());
        for (int i = 0; i < list.size(); i++) {
//            如果循环到最后一个就跳出循环
            if(i==list.size()-1){
                break;
            }
            sp1 = list.get(i);
            sp2 = list.get(i);
//          求出两点之间距离
            distance1 = getDistance(lat1,lng1,Double.parseDouble(sp1.getLat()),Double.parseDouble(sp1.getLng()));

            distance2 = getDistance(lat1,lng1,Double.parseDouble(sp2.getLat()),Double.parseDouble(sp2.getLng()));

            double min = Math.min(distance1, distance2);
            if (min==distance1 && resultsmin>min){
                results=sp1;
            }else if (min==distance2 && resultsmin>min){
                results=sp2;
            }
            if(resultsmin>min){
                resultsmin=min;
            }
        }

        return results;
    }

}
