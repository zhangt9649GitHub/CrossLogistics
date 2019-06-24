package com.siruiman.crosslogistics.controller;

import com.siruiman.crosslogistics.enums.GyCode;
import com.siruiman.crosslogistics.model.TruckContactAddress;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.service.TruckContactAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Api(value="TruckContactAddress",description = "货车联系地址",tags={"APP货车端-货车联系地址"})
@RestController
@RequestMapping("/truckContactAddress")
public class TruckContactAddressController {
    @Autowired
    private TruckContactAddressService truckContactAddressService;

    @ApiOperation(value = "货车联系地址",notes = "truckContactAddress",tags={"@郭阳"})
    @ApiImplicitParam(name="appUserId", value="app用户id",paramType="query",dataType="int")
    @RequestMapping(value = "/truckContactAddress",method = RequestMethod.GET)
    public CommonResponse appUserInfo(int appUserId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            TruckContactAddress selectTruckContactAddress = truckContactAddressService.selectTruckContactAddress(appUserId);

            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectTruckContactAddress);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "根据邮编查询地址",notes = "selectAddress",tags={"@郭阳"})
    @ApiImplicitParam(name="zipCode", value="邮编",paramType="query",dataType="String")
    @RequestMapping(value = "/selectAddress",method = RequestMethod.GET)
    public CommonResponse selectAddress(String zipCode) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            String selectAddress = truckContactAddressService.selectAddress(zipCode);

            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectAddress);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "编辑联系地址",notes = "editTruckAddress",tags={"@郭阳"})
    @RequestMapping(value = "/editTruckAddress",method = RequestMethod.POST)
    public CommonResponse editTruckAddress(TruckContactAddress truckContactAddress) {
        CommonResponse commonResponse = new CommonResponse();

        try{
            Integer editTruckAddress = truckContactAddressService.editTruckAddress(truckContactAddress);
            commonResponse.setCode(GyCode.SUCCESS_EDIT.getCode());
            commonResponse.setData(editTruckAddress);
            commonResponse.setMessage(GyCode.SUCCESS_EDIT.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_EDIT.getCode());
            commonResponse.setMessage(GyCode.FAIL_EDIT.getInfo());
        }

        return commonResponse;
    }
}
