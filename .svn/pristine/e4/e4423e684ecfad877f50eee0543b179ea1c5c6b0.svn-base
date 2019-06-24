package com.siruiman.crosslogistics.controller;

import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.enums.ZwCode;
import com.siruiman.crosslogistics.model.CarLock;
import com.siruiman.crosslogistics.model.dto.CarLockDto;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.CarLockService;
import com.siruiman.crosslogistics.service.CarService;
import com.siruiman.crosslogistics.util.LockUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api(value="CarLock",description = "小车锁管理-API",tags={"小车锁管理"})
@RestController
@RequestMapping("/carLock")
public class CarLockController {

    @Autowired
    private CarLockService carLockService;

    @Autowired
    private CarService carService;


    @RequestMapping(value = "unlock",method = RequestMethod.POST)
    @ApiOperation(value = "维护人员使用",notes = "开锁",tags = {"@占伟"})
    public CommonResponse unlock(String lockNumber){

        CommonResponse response = new CommonResponse();
        try {
            carLockService.unLock(lockNumber);
            response.setCode(0);
            response.setMessage("开锁成功");
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMessage("开锁失败");
        }

        return response;
    }

    @RequiresPermissions(value = {"getList"})
    @ApiOperation(value = "获取车锁管理列表",notes = "获取车锁管理列表",tags = {"@占伟"})
    @RequestMapping(value = "getList",method = RequestMethod.POST)
    public LayuiCommonResponse getList(@Validated @RequestParam(defaultValue = "1") int page,
                                  @Validated @RequestParam(defaultValue = "10") int limit,
                                       String lockNumber,@Validated@RequestParam(defaultValue = "0") Byte status){

        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            PageHelper.startPage(page,limit);
            List<CarLock> locks =   carLockService.getList(lockNumber,status);
            int count = carLockService.getCount(lockNumber,status);
            response.setCode(0);
            response.setMsg(ZwCode.SUCCESS_GET.getInfo());
            response.setData(locks);
            response.setCount(count);

        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(ZwCode.FAIL_GET.getCode());
            response.setMsg(ZwCode.FAIL_GET.getInfo());
        }

        return response;
    }

    @RequiresPermissions(value = {"updateLock"})
    @ApiOperation(value = "车锁编辑",notes = "车锁编辑",tags = {"@占伟"})
    @RequestMapping(value = "updateLock",method = RequestMethod.POST)
    public LayuiCommonResponse updateLock( @RequestBody CarLockDto dto
            ){

        LayuiCommonResponse response = new LayuiCommonResponse();
        CarLock carLock;
        try {
            carLock = carLockService.selectCarLockById(dto.getLockId());
            if(new Byte("1").equals(carLock.getStatus())){
                response.setCode(ZwCode.WARNING_LOCK.getCode());
                response.setMsg(ZwCode.WARNING_LOCK.getInfo());
                return response;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(ZwCode.WARNIng_LOCK_ID.getCode());
            response.setMsg(ZwCode.WARNIng_LOCK_ID.getInfo());
            return response;
        }
        try {
            carLock.setLockNumber(dto.getLockNumber());
            carLock.setLockPosition(dto.getLockPosition());
            carLock.setUpdateTime(new Date());
            carLockService.updateCarNumberAndPosition(carLock);
            response.setCode(ZwCode.SUCCESS_EDIT.getCode());
            response.setMsg(ZwCode.SUCCESS_EDIT.getInfo());

        }catch (Exception e){
            e.printStackTrace();
            response.setCode(ZwCode.FAIL_EDIT.getCode());
            response.setMsg(ZwCode.FAIL_EDIT.getInfo());
        }
        return response;
    }


    @RequiresPermissions(value = {"scrapLock"})
    @ApiOperation(value = "车锁报废",notes = "车锁报废",tags = {"@占伟"})
    @RequestMapping(value = "scrapLock",method = RequestMethod.POST)
    public LayuiCommonResponse scrapLock(Integer lockId,String carNumber){
        LayuiCommonResponse response = new LayuiCommonResponse();
        if(carNumber==null||carNumber==""){
            try {
                carLockService.scrapLock(lockId);
                response.setCode(ZwCode.SUCCESS_EDIT.getCode());
                response.setMsg(ZwCode.SUCCESS_EDIT.getInfo());
            }catch (Exception e){
                response.setCode(ZwCode.FAIL_EDIT.getCode());
                response.setMsg(ZwCode.FAIL_EDIT.getInfo());
                return response;
            }
        }else {

                try {

                    int state = carService.selectCarStateByNum(carNumber);
//           小车状态为维护中或者报废就可以报废锁
                    if (state==2||state==5){
                   carLockService.scrapLock(lockId);
                   response.setCode(ZwCode.SUCCESS_EDIT.getCode());
                   response.setMsg(ZwCode.SUCCESS_EDIT.getInfo());}
                    else {
                        response.setCode(ZwCode.WARNING_CAR_LOCK.getCode());
                        response.setMsg(ZwCode.WARNING_CAR_LOCK.getInfo());
                    }
                }catch (Exception e){
                   response.setCode(ZwCode.FAIL_EDIT.getCode());
                   response.setMsg(ZwCode.FAIL_EDIT.getInfo());
                }


        }
        return response;
    }

    @RequiresPermissions(value = {"repairLock"})
    @ApiOperation(value = "车锁修复",notes = "车锁修复修改为空闲状态",tags = {"@占伟"})
    @RequestMapping(value = "repairLock",method = RequestMethod.POST)
    public LayuiCommonResponse repairLock(Integer lockId){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            carLockService.repairLock(lockId);
            response.setCode(ZwCode.SUCCESS_EDIT.getCode());
            response.setMsg(ZwCode.SUCCESS_EDIT.getInfo());
        }catch (Exception e){
            response.setCode(ZwCode.FAIL_EDIT.getCode());
            response.setMsg(ZwCode.FAIL_EDIT.getInfo());
        }
        return response;
    }

    @RequiresPermissions(value = {"addLock"})
    @ApiOperation(value = "车锁添加",notes = "添加车锁",tags = {"@占伟"})
    @RequestMapping(value = "addLock",method = RequestMethod.POST)
    public LayuiCommonResponse addLock(@RequestBody CarLockDto dto){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            carLockService.add(dto);
            response.setCode(ZwCode.SUCCESS_INSERT.getCode());
            response.setMsg(ZwCode.SUCCESS_INSERT.getInfo());
        }catch (Exception e){
            response.setCode(ZwCode.FAIL_INSERT.getCode());
            response.setMsg(ZwCode.FAIL_INSERT.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "车锁添加效验编号是否重复",notes = "校验编号",tags = {"@占伟"})
    @RequestMapping(value = "checkNumber",method = RequestMethod.POST)
    public LayuiCommonResponse checkNumber(String lockNumber,@Validated @RequestParam(defaultValue = "0")Integer lockId){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {

            boolean results = carLockService.checkNumber(lockNumber,lockId);
            if (results){
                response.setCode(ZwCode.CAN_USE_NUM.getCode());
                response.setMsg(ZwCode.CAN_USE_NUM.getInfo());
            }else{
                response.setCode(ZwCode.UNABLE_CHECK_NUM.getCode());
                response.setMsg(ZwCode.UNABLE_CHECK_NUM.getInfo());
            }

        }catch (Exception e){
            response.setCode(ZwCode.FAIL_CHECKNAME.getCode());
            response.setMsg(ZwCode.FAIL_CHECKNAME.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "获取小车编辑详情",notes = "获取小车编辑详情",tags = {"@占伟"})
    @RequestMapping(value = "getLockById",method = RequestMethod.POST)
    public LayuiCommonResponse getLockById(Integer lockId){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            CarLock carLock = carLockService.selectCarLockById(lockId);
            response.setData(carLock);
            response.setCode(ZwCode.SUCCESS_GET.getCode());
            response.setMsg(ZwCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            response.setCode(ZwCode.FAIL_GET.getCode());
            response.setMsg(ZwCode.FAIL_GET.getInfo());
        }
        return response;
    }
}
