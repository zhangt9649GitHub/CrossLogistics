package com.siruiman.crosslogistics.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GenerateOrder {
    public static Integer timeLimitCar;
    public static Integer timeLimitTruck;

    public Integer getTimeLimitCar() {
        return timeLimitCar;
    }

    @Value("${generateOrder.timeLimitCar}")
    public void setTimeLimitCar(Integer timeLimitCar) {
        GenerateOrder.timeLimitCar = timeLimitCar;
    }

    public Integer getTimeLimitTruck() {
        return timeLimitTruck;
    }

    @Value("${generateOrder.timeLimitCar}")
    public void setTimeLimitTruck(Integer timeLimitTruck) {
        GenerateOrder.timeLimitTruck = timeLimitTruck;
    }
}
