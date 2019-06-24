package com.siruiman.crosslogistics.controller;

import com.siruiman.crosslogistics.enums.HtCode;
import com.siruiman.crosslogistics.model.AppUserCertification;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.service.AppUserCertificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Api(value = "APPRealNameAuthentication", description = "APP实名认证API", tags = {"APP实名认证"})
@RestController
@RequestMapping("/appAuthentication")
public class APPRealNameAuthenticationController {
    @Autowired
    private AppUserCertificationService appUserCertificationService;

    @ApiOperation(value = "获取小车或货车实名认证信息", notes = "获取小车或货车实名认证信息", tags = {"@郝腾"})
    @ApiImplicitParam(name = "appUserCertification", value = "实时定位实体类", dataType = "AppUserCertification")
    @RequestMapping(value = "/getUserCertification", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse getUserCertification(@Validated AppUserCertification appUserCertification) {
        CommonResponse response = new CommonResponse();
        try {
            AppUserCertification appUserCertification1 = appUserCertificationService.selectUserCertificationByUserId(appUserCertification.getAppUserId(), appUserCertification.getUserType());
            if (appUserCertification1 != null && appUserCertification1.getUserCertificationStatus().equals("待审核")) {
                response.setCode(HtCode.FAIL_REPEAT.getCode());
                response.setMessage(HtCode.FAIL_REPEAT.getInfo());
                return response;
            }
            if (appUserCertification1 != null && appUserCertification1.getUserCertificationStatus() != null
                    && !(appUserCertification1.getUserCertificationStatus().equals(""))
                    && appUserCertification1.getUserCertificationStatus().equals("已驳回")) {
                appUserCertification.setUserCertificationStatus("已驳回");
                appUserCertification.setCertificationId(appUserCertification1.getCertificationId());
            }

            if (appUserCertification.getUserType().equals("小车")) {
                if ((appUserCertification.getFrontIdCard() != null && !(appUserCertification.getFrontIdCard().equals(""))
                        && appUserCertification.getReverseIdCard() != null && !(appUserCertification.getReverseIdCard().equals(""))) ||
                        (appUserCertification.getFrontPassport() != null && !(appUserCertification.getFrontPassport().equals(""))) ||
                        (appUserCertification.getFrontWorkCard() != null && !(appUserCertification.getFrontWorkCard().equals("")) &&
                                appUserCertification.getReverseWorkCard() != null && !(appUserCertification.getReverseWorkCard().equals(""))) ||
                        (appUserCertification.getFrontDrivingLicence() != null && !(appUserCertification.getFrontDrivingLicence().equals("")))&&
                                (appUserCertification.getReverseDrivingLicence() != null && !(appUserCertification.getReverseDrivingLicence().equals("")))) {
                    appUserCertificationService.insertAppUserCertification(appUserCertification);
                } else {
                    response.setCode(HtCode.FAIL_CERTIFICATION.getCode());
                    response.setMessage(HtCode.FAIL_CERTIFICATION.getInfo());
                    return response;
                }
            } else if (appUserCertification.getUserType().equals("货车")) {
                if (appUserCertification.getFrontDrivingLicence() != null && !(appUserCertification.getFrontDrivingLicence().equals("")) &&
                        (appUserCertification.getReverseDrivingLicence() != null && !(appUserCertification.getReverseDrivingLicence().equals("")))&&
                        appUserCertification.getTruckPhoto() != null && !(appUserCertification.getTruckPhoto().equals(""))) {
                    appUserCertificationService.insertAppUserCertification(appUserCertification);
                } else {
                    response.setCode(HtCode.FAIL_VAN.getCode());
                    response.setMessage(HtCode.FAIL_VAN.getInfo());
                    return response;
                }
            }
            response.setCode(HtCode.SUCCESS_UPLOAD.getCode());
            response.setMessage(HtCode.SUCCESS_UPLOAD.getInfo());
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_UPLOAD.getCode());
            response.setMessage(HtCode.FAIL_UPLOAD.getInfo());
        }
        return response;
    }
}
