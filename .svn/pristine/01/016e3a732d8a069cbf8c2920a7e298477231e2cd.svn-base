package com.siruiman.crosslogistics.controller;

import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.enums.GyCode;
import com.siruiman.crosslogistics.model.AppUserAddress;
import com.siruiman.crosslogistics.model.AppUserAdressArea;
import com.siruiman.crosslogistics.model.SingaporeArea;
import com.siruiman.crosslogistics.model.dto.AppUserAddressDto;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.AppUserAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value="AppUserAddress",description = "用户地址管理",tags={"用户管理-用户地址管理"})
@RestController
@RequestMapping("/appUserAddress")
public class AppUserAddressController {
    @Autowired
    private AppUserAddressService appUserAddressService;

    @ApiOperation(value = "获取地址列表",notes = "appUserAddressAll",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="page", value="第几页",paramType="query",dataType="int"),
            @ApiImplicitParam(name="limit", value="每页容量",paramType="query",dataType="int"),
    })
    @RequestMapping(value = "/appUserAddressAll",method = RequestMethod.GET)
    public LayuiCommonResponse appUserAddressAll(@Validated @RequestParam(defaultValue = "1") int page, @Validated @RequestParam(defaultValue = "10") int limit,
                                                 AppUserAddressDto appUserAddressDto) {

        LayuiCommonResponse layuiCommonResponse = new LayuiCommonResponse();
        try{
            PageHelper.startPage(page,limit);
            List<AppUserAddress> selectAppUserAddressAll = appUserAddressService.selectAppUserAddressAll(appUserAddressDto);
            /*Integer count = appUserAddressService.count(appUserAddressDto);*/

            layuiCommonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            layuiCommonResponse.setData(selectAppUserAddressAll);
            layuiCommonResponse.setCount(selectAppUserAddressAll.size());
            layuiCommonResponse.setMsg(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            layuiCommonResponse.setCode(GyCode.FAIL_GET.getCode());
            layuiCommonResponse.setMsg(GyCode.FAIL_GET.getInfo());
        }

        return layuiCommonResponse;
    }

    @ApiOperation(value = "查询区域下拉框数据",notes = "appUserAdressAreaAll",tags={"@郭阳"})
    @RequestMapping(value = "/appUserAdressAreaAll",method = RequestMethod.GET)
    public CommonResponse appUserAdressAreaAll() {

        CommonResponse commonResponse = new CommonResponse();
        try{
            List<SingaporeArea> selectAppUserAdressAreaAll = appUserAddressService.selectAppUserAdressAreaAll();

            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectAppUserAdressAreaAll);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

}
