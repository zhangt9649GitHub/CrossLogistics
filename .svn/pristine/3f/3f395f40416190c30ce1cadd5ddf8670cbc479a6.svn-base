package com.siruiman.crosslogistics.controller;

import com.siruiman.crosslogistics.enums.HtCode;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api(value = "APPPositionInfo", description = "APP小车货车实时定位API", tags = {"APP小车货车实时定位"})
@RestController
@RequestMapping("/appPositionInfo")
public class APPPositionInfoController {

    @Autowired
    private PositionInfoService positionInfoService;
    @Autowired
    private TaskCarOrderService taskCarOrderService;
    @Autowired
    private AppUserCertificationService appUserCertificationService;
    @Autowired
    private TruckService truckService;

    @ApiOperation(value = "获取小车或货车实时定位", notes = "获取小车或货车实时定位", tags = {"@郝腾"})
    @ApiImplicitParam(name = "positionInfo", value = "实时定位实体类", required = true, dataType = "PositionInfo")
    @RequestMapping(value = "/insertPositionInfo", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse insertPositionInfo(@Validated PositionInfo positionInfo) {
        CommonResponse response = new CommonResponse();
        try {
            if (positionInfo.getUserType().equals("小车")) {
                TaskCarOrder taskCarOrder = taskCarOrderService.selectCarIdById(positionInfo.getTaskOrderId());
                if (taskCarOrder.getCarId() != null) {
                    positionInfo.setCarId(taskCarOrder.getCarId());
                }
                positionInfo.setAddtime(new Date());
                positionInfoService.insertPositionInfo(positionInfo);
            } else if (positionInfo.getUserType().equals("货车")) {
                AppUserCertification appUserCertification = appUserCertificationService.selectUserCertificationByUserId(positionInfo.getAppUserId(), positionInfo.getUserType());
                if (appUserCertification.getLicensePlate() != null) {
                    Truck truck = truckService.selectTruckDetailsByBag(appUserCertification.getLicensePlate());
                    if (truck != null) {
                        positionInfo.setTruckId(truck.getTruckId());
                    }
                }
                positionInfo.setAddtime(new Date());
                positionInfoService.insertPositionInfo(positionInfo);
            }

            response.setCode(HtCode.SUCCESS_GET.getCode());
            response.setMessage(HtCode.SUCCESS_GET.getInfo());
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_GET.getCode());
            response.setMessage(HtCode.FAIL_GET.getInfo());
        }
        return response;
    }
}
