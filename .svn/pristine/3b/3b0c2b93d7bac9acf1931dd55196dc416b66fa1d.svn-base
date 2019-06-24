package com.siruiman.crosslogistics.controller;

import com.siruiman.crosslogistics.enums.GyCode;
import com.siruiman.crosslogistics.model.AppUserAddress;
import com.siruiman.crosslogistics.model.AppUserAdressArea;
import com.siruiman.crosslogistics.model.dto.AddAppUserAdressDto;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.service.AppCarUserAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value="AppCarUserAddress",description = "收货地址",tags={"APP用户端-收货地址"})
@RestController
@RequestMapping("/appCarUserAddress")
public class AppCarUserAddressController {
    @Autowired
    private AppCarUserAddressService appCarUserAddressService;

    @ApiOperation(value = "收货地址列表",notes = "carAppUserAddress",tags={"@郭阳"})
    @ApiImplicitParam(name="appUserId", value="app用户id",paramType="query",dataType="int")
    @RequestMapping(value = "/carAppUserAddress",method = RequestMethod.GET)
    public CommonResponse carAppUserAddress(int appUserId) {

        CommonResponse commonResponse = new CommonResponse();

        try{
            List<AppUserAddress> selectAppUserAddress = appCarUserAddressService.selectAppUserAddress(appUserId);

            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectAppUserAddress);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "添加收货地址",notes = "addAppUserAdress",tags={"@郭阳"})
    @RequestMapping(value = "/addAppUserAdress",method = RequestMethod.POST)
    public CommonResponse addAppUserAdress(AddAppUserAdressDto addAppUserAdressDto) {

        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer addAppUserAddress = appCarUserAddressService.addAppUserAddress(addAppUserAdressDto);
            if(addAppUserAddress < 1){
                commonResponse.setCode(GyCode.FAIL_ADD.getCode());
                commonResponse.setMessage(GyCode.FAIL_ADD.getInfo());
                return commonResponse;
            }
            commonResponse.setCode(GyCode.SUCCESS_ADD.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_ADD.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_ADD.getCode());
            commonResponse.setMessage(GyCode.FAIL_ADD.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "编辑收货地址",notes = "editAppUserAdress",tags={"@郭阳"})
    @RequestMapping(value = "/editAppUserAdress",method = RequestMethod.POST)
    public CommonResponse editAppUserAdress(AddAppUserAdressDto addAppUserAdressDto) {

        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer editAppUserAddress = appCarUserAddressService.editAppUserAddress(addAppUserAdressDto);
            if(editAppUserAddress < 1){
                commonResponse.setCode(GyCode.FAIL_EDIT.getCode());
                commonResponse.setMessage(GyCode.FAIL_EDIT.getInfo());
                return commonResponse;
            }
            commonResponse.setCode(GyCode.SUCCESS_EDIT.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_EDIT.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_EDIT.getCode());
            commonResponse.setMessage(GyCode.FAIL_EDIT.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "删除收货地址",notes = "deleteAppUserAddress",tags={"@郭阳"})
    @ApiImplicitParam(name="userAddressId", value="app用户地址id",paramType="query",dataType="int")
    @RequestMapping(value = "/deleteAppUserAddress",method = RequestMethod.POST)
    public CommonResponse deleteAppUserAddress(int userAddressId) {

        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer deleteAppUserAddress = appCarUserAddressService.deleteAppUserAddress(userAddressId);
            if(deleteAppUserAddress < 1){
                commonResponse.setCode(GyCode.FAIL_DELETE.getCode());
                commonResponse.setMessage(GyCode.FAIL_DELETE.getInfo());
                return commonResponse;
            }
            commonResponse.setCode(GyCode.SUCCESS_DELETE.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_DELETE.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_DELETE.getCode());
            commonResponse.setMessage(GyCode.FAIL_DELETE.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "根据邮编查询地址",notes = "selectSingaporeArea",tags={"@郭阳"})
    @ApiImplicitParam(name="zipCode", value="邮编",paramType="query",dataType="String")
    @RequestMapping(value = "/selectSingaporeArea",method = RequestMethod.GET)
    public CommonResponse selectSingaporeArea(String zipCode) {

        CommonResponse commonResponse = new CommonResponse();
        try{
            AppUserAdressArea selectSingaporeAreaId = appCarUserAddressService.selectSingaporeAreaId(zipCode);
            if(selectSingaporeAreaId == null){
                commonResponse.setCode(GyCode.FAIL_ZIP_CODE.getCode());
                commonResponse.setMessage(GyCode.FAIL_ZIP_CODE.getInfo());
            }else {
                commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
                commonResponse.setData(selectSingaporeAreaId);
                commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
            }
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "修改默认地址",notes = "editUserAddressDefault",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="appUserId", value="用户id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="userAddressId", value="用户地址id",paramType="query",dataType="int"),
    })
    @RequestMapping(value = "/editUserAddressDefault",method = RequestMethod.GET)
    public CommonResponse editUserAddressDefault(int appUserId, int userAddressId) {

        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer editUserAddressDefault = appCarUserAddressService.editUserAddressDefault(appUserId, userAddressId);
            if(editUserAddressDefault == 2 || editUserAddressDefault < 1){
                commonResponse.setCode(GyCode.FAIL_EDIT.getCode());
                commonResponse.setMessage(GyCode.FAIL_EDIT.getInfo());
                return commonResponse;
            }
            commonResponse.setCode(GyCode.SUCCESS_EDIT.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_EDIT.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_EDIT.getCode());
            commonResponse.setMessage(GyCode.FAIL_EDIT.getInfo());
        }

        return commonResponse;
    }
}
