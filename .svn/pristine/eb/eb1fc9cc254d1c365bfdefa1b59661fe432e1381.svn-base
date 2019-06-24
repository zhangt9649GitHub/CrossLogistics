package com.siruiman.crosslogistics.controller;

import com.siruiman.crosslogistics.enums.GyCode;
import com.siruiman.crosslogistics.model.Staff;
import com.siruiman.crosslogistics.model.dto.EditPDAUserPswDto;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.service.PDAUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value="PDAUser",description = "个人中心",tags={"PDA用户端-个人中心"})
@RestController
@RequestMapping("/pDAUser")
public class PDAUserController {
    @Autowired
    private PDAUserService pdaUserService;

    @ApiOperation(value = "个人信息",notes = "selectStaff",tags={"@郭阳"})
    @ApiImplicitParam(name="staffId", value="pda用户id",paramType="query",dataType="int")
    @RequestMapping(value = "/selectStaff",method = RequestMethod.GET)
    public CommonResponse selectStaff(int staffId) {

        CommonResponse commonResponse = new CommonResponse();
        try{
            Staff selectStaff = pdaUserService.selectStaff(staffId);

            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectStaff);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "修改密码",notes = "editStaffPsw",tags={"@郭阳"})
    @RequestMapping(value = "/editStaffPsw",method = RequestMethod.POST)
    public CommonResponse editStaffPsw(EditPDAUserPswDto editPDAUserPswDto) {

        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer editStaffPsw = pdaUserService.editStaffPsw(editPDAUserPswDto);
            if(editStaffPsw == null){
                commonResponse.setCode(GyCode.ERROR_PASSWORD.getCode());
                commonResponse.setMessage(GyCode.ERROR_PASSWORD.getInfo());
                return commonResponse;
            }
            if(editStaffPsw < 1){
                commonResponse.setCode(GyCode.FAIL_EDIT.getCode());
                commonResponse.setMessage(GyCode.FAIL_EDIT.getInfo());
                return commonResponse;
            }
            commonResponse.setCode(GyCode.SUCCESS_EDIT.getCode());
            commonResponse.setData(editStaffPsw);
            commonResponse.setMessage(GyCode.SUCCESS_EDIT.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_EDIT.getCode());
            commonResponse.setMessage(GyCode.FAIL_EDIT.getInfo());
        }

        return commonResponse;
    }
}
