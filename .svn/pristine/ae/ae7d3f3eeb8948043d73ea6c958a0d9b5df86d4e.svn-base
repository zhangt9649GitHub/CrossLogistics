package com.siruiman.crosslogistics.controller;

import com.siruiman.crosslogistics.enums.HtCode;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.service.*;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api(value = "APPPreferencesRegion", description = "APP偏好设置API", tags = {"APP偏好设置"})
@RestController
@RequestMapping("/appPreferencesRegion")
public class APPPreferencesRegionController {
    @Autowired
    private SingaporeAreaService singaporeAreaService;
    @Autowired
    private SingaporePointService singaporePointService;
    @Autowired
    private AppUserPreferencesAreaService appUserPreferencesAreaService;
    @Autowired
    private AppUserCertificationService appUserCertificationService;
    @Autowired
    private TruckService truckService;

    @ApiOperation(value = "获取偏好地区列表", notes = "获取偏好地区列表", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appUserId", value = "用户id", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "userType", value = "用户类型", paramType = "query", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getSingaporeAreaList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse getSingaporeAreaList(@Validated int appUserId, @Validated String userType) {
        CommonResponse response = new CommonResponse();
        try {
            List<AppUserPreferencesArea> appUserPreferencesAreaList = new ArrayList<>();
            if (userType.equals("小车")) {
                appUserPreferencesAreaList = appUserPreferencesAreaService.selectDefaultAreaByUserId(appUserId, userType);

            } else if (userType.equals("货车")) {
                appUserPreferencesAreaList = appUserPreferencesAreaService.selectDefaultAreaByUserId(appUserId, userType);
            }
            List<SingaporeArea> singaporeAreaList = singaporeAreaService.getSingaporeAreaIdAndName();
            List<Integer> ids = new ArrayList<>();
            for (AppUserPreferencesArea appUserPreferencesArea : appUserPreferencesAreaList
            ) {
                if (appUserPreferencesArea != null && appUserPreferencesArea.getSingaporeAreaId() != null && !(appUserPreferencesArea.getSingaporeAreaId().equals(""))) {
                    ids.add(appUserPreferencesArea.getSingaporeAreaId());
                }
            }
            for (SingaporeArea singaporeArea : singaporeAreaList
            ) {
                for (Integer id : ids
                ) {
                    if (singaporeArea.getSingaporeAreaId() == id) {
                        singaporeArea.setIsDefault(1);
                    }
                }
                if (singaporeArea.getIsDefault() == null || singaporeArea.getIsDefault().equals("")) {
                    singaporeArea.setIsDefault(2);
                }
            }
            response.setCode(HtCode.SUCCESS_GET.getCode());
            response.setMessage(HtCode.SUCCESS_GET.getInfo());
            response.setData(singaporeAreaList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_GET.getCode());
            response.setMessage(HtCode.FAIL_GET.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "查看分区地图", notes = "查看分区地图", tags = {"@郝腾"})
    @ApiImplicitParam(name = "singaporeAreaId", value = "区域id", required = true, paramType = "query", dataType = "int")
    @RequestMapping(value = "/getSingaporeAreaMap", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse getSingaporeAreaMap(@Validated int singaporeAreaId) {
        CommonResponse response = new CommonResponse();
        try {
            List<SingaporePoint> singaporePointList = singaporePointService.selectBySGAreaId(singaporeAreaId);
            response.setCode(HtCode.SUCCESS_GET.getCode());
            response.setMessage(HtCode.SUCCESS_GET.getInfo());
            response.setData(singaporePointList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_GET.getCode());
            response.setMessage(HtCode.FAIL_GET.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "保存修改", notes = "保存修改", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appUserId", value = "用户id", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "userType", value = "用户类型", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "singaporeAreaIds", value = "区域id数组", required = true, paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/updateAppUserPreferencesArea", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse updateAppUserPreferencesArea(@Validated int appUserId, @Validated String userType, @Validated String singaporeAreaIds) {
        CommonResponse response = new CommonResponse();
        try {
            List<AppUserPreferencesArea> appUserPreferencesAreaList = appUserPreferencesAreaService.selectDefaultAreaByUserId(appUserId, userType);
            String[] sgIds = singaporeAreaIds.split(",");
            Integer[] xinsgIds = new Integer[sgIds.length];
            for (int i = 0; i < sgIds.length; i++) {
                xinsgIds[i] = Integer.valueOf(sgIds[i]);
            }
            if (appUserPreferencesAreaList != null) {
                appUserPreferencesAreaService.deleteDefaultAreaByUserId(appUserId, userType);
            }
            for (int i = 0; i < xinsgIds.length; i++) {
                AppUserPreferencesArea appUserPreferencesArea1 = new AppUserPreferencesArea();
                appUserPreferencesArea1.setAppUserId(appUserId);
                appUserPreferencesArea1.setUserType(userType);
                appUserPreferencesArea1.setSingaporeAreaId(xinsgIds[i]);
                appUserPreferencesArea1.setAddTime(new Date());
                appUserPreferencesAreaService.insertAppUserPreferencesArea(appUserPreferencesArea1);
            }
            if (userType.equals("货车")) {
                AppUserCertification appUserCertification = appUserCertificationService.selectUserCertificationByUserId(appUserId, userType);
                if (appUserCertification != null && !(appUserCertification.getLicensePlate().equals(""))) {
                    Truck truck = truckService.selectTruckDetailsByBag(appUserCertification.getLicensePlate());
                    String singaporeAreaId = "";
                    for (Integer id : xinsgIds
                    ) {
                        if (id != null) {
                            if (singaporeAreaId.equals("")) {
                                singaporeAreaId = singaporeAreaId + id;
                            } else {
                                singaporeAreaId = singaporeAreaId + "," + id;
                            }
                        }
                    }
                    truck.setSingaporeAreaId(singaporeAreaId);
                    truck.setUpdateTime(new Date());
                    truckService.updateTruck(truck);
                }
            }
            response.setCode(HtCode.SUCCESS_UPDATE.getCode());
            response.setMessage(HtCode.SUCCESS_UPDATE.getInfo());
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_UPDATE.getCode());
            response.setMessage(HtCode.FAIL_UPDATE.getInfo());
        }
        return response;
    }
}
