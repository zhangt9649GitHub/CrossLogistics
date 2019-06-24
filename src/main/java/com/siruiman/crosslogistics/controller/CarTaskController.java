package com.siruiman.crosslogistics.controller;

import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.enums.GyCode;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.model.dto.AddCarTaskDto;
import com.siruiman.crosslogistics.model.dto.AdminUserLoginDto;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.CarTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Api(value="CarTask",description = "小车任务",tags={"任务管理-小车任务"})
@RestController
@RequestMapping("/carTask")
public class CarTaskController {
    @Autowired
    private CarTaskService carTaskService;

    @ApiOperation(value = "小车任务模板列表",notes = "carTaskAll",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="page", value="第几页",paramType="query",dataType="int"),
            @ApiImplicitParam(name="limit", value="每页容量",paramType="query",dataType="int"),
            @ApiImplicitParam(name="search", value="搜索",paramType="query",dataType="String")
    })
    @RequestMapping(value = "/carTaskAll",method = RequestMethod.GET)
    public LayuiCommonResponse selectCarTaskAll(@Validated @RequestParam(defaultValue = "1") int page, @Validated @RequestParam(defaultValue = "10") int limit,
                                                String search, int rallyPointId) {
        LayuiCommonResponse layuiCommonResponse = new LayuiCommonResponse();
        try{
            PageHelper.startPage(page,limit);
            List<CarTask> selectCarTaskAll = carTaskService.selectCarTaskAll(search, rallyPointId);
            Integer count = carTaskService.count(search);

            layuiCommonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            layuiCommonResponse.setData(selectCarTaskAll);
            layuiCommonResponse.setCount(count);
            layuiCommonResponse.setMsg(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            layuiCommonResponse.setCode(GyCode.FAIL_GET.getCode());
            layuiCommonResponse.setMsg(GyCode.FAIL_GET.getInfo());
        }

        return layuiCommonResponse;
    }

    @ApiOperation(value = "新增小车任务模板",notes = "addTruckTask",tags={"@郭阳"})
    @RequestMapping(value = "/addTruckTask",method = RequestMethod.POST)
    public CommonResponse addTruckTask(@RequestBody AddCarTaskDto addCarTaskDto) {
        CommonResponse commonResponse = new CommonResponse();

        try{
            AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
            addCarTaskDto.setAdminUid(Integer.parseInt(user.getAdminUId()));
            Integer addCarTask = carTaskService.addCarTask(addCarTaskDto);
            if(addCarTask == 2){
                commonResponse.setCode(GyCode.FAIL_TASK_NAME.getCode());
                commonResponse.setMessage(GyCode.FAIL_TASK_NAME.getInfo());
                return commonResponse;
            }
            if(addCarTask < 1){
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

    @ApiOperation(value = "查询所有小车司机",notes = "appUserByCar",tags={"@郭阳"})
    @RequestMapping(value = "/appUserByCar",method = RequestMethod.GET)
    public CommonResponse selectAppUserByCar() {
        CommonResponse commonResponse = new CommonResponse();
        try{
            List<AppUser> selectAppUserByCar = carTaskService.selectAppUserByCar();

            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectAppUserByCar);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "查询小车模板编辑内容",notes = "selectEditCarTask",tags={"@郭阳"})
    @ApiImplicitParam(name="carTaskId", value="小车模板id",paramType="query",dataType="int")
    @RequestMapping(value = "/selectEditCarTask",method = RequestMethod.GET)
    public CommonResponse selectEditCarTask(int carTaskId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            CarTask selectEditCarTask = carTaskService.selectEditCarTask(carTaskId);

            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectEditCarTask);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "编辑小车模板",notes = "editCarTask",tags={"@郭阳"})
    @RequestMapping(value = "/editCarTask",method = RequestMethod.POST)
    public CommonResponse editCarTask(@RequestBody CarTask carTask) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
            carTask.setAdminUid(Integer.parseInt(user.getAdminUId()));
            Integer editCarTask = carTaskService.editCarTask(carTask);
            if(editCarTask < 1){
                commonResponse.setCode(GyCode.FAIL_EDIT.getCode());
                commonResponse.setData(editCarTask);
                commonResponse.setMessage(GyCode.FAIL_EDIT.getInfo());
                return commonResponse;
            }
            commonResponse.setCode(GyCode.SUCCESS_EDIT.getCode());
            commonResponse.setData(editCarTask);
            commonResponse.setMessage(GyCode.SUCCESS_EDIT.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_EDIT.getCode());
            commonResponse.setMessage(GyCode.FAIL_EDIT.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "删除小车模板",notes = "delCarTask",tags={"@郭阳"})
    @ApiImplicitParam(name="carTaskId", value="小车模板id",paramType="query",dataType="int")
    @RequestMapping(value = "/delCarTask",method = RequestMethod.POST)
    public CommonResponse delCarTask(int carTaskId) {
        CommonResponse commonResponse = new CommonResponse();

        try{
            AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
            Integer delCarTask = carTaskService.delCarTask(carTaskId, Integer.parseInt(user.getAdminUId()));
            if(delCarTask < 1){
                commonResponse.setCode(GyCode.FAIL_DELETE.getCode());
                commonResponse.setData(delCarTask);
                commonResponse.setMessage(GyCode.FAIL_DELETE.getInfo());
                return commonResponse;
            }
            commonResponse.setCode(GyCode.SUCCESS_DELETE.getCode());
            commonResponse.setData(delCarTask);
            commonResponse.setMessage(GyCode.SUCCESS_DELETE.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_DELETE.getCode());
            commonResponse.setMessage(GyCode.FAIL_DELETE.getInfo());
        }


        return commonResponse;
    }


    @ApiOperation(value = "修改小车模板状态",notes = "editStatus",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="carTaskId", value="小车模板id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="status", value="状态 1启用 2禁用",paramType="query",dataType="int"),
    })
    @RequestMapping(value = "/editStatus",method = RequestMethod.POST)
    public CommonResponse editStatus(int carTaskId, int status) {
        CommonResponse commonResponse = new CommonResponse();

        try{
            AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
            Integer editStatus = carTaskService.editStatus(carTaskId, status, Integer.parseInt(user.getAdminUId()));
            if(editStatus < 1){
                commonResponse.setCode(GyCode.FAIL_OPERATE.getCode());
                commonResponse.setData(editStatus);
                commonResponse.setMessage(GyCode.FAIL_OPERATE.getInfo());
                return commonResponse;
            }
            commonResponse.setCode(GyCode.SUCCESS_OPERATE.getCode());
            commonResponse.setData(editStatus);
            commonResponse.setMessage(GyCode.SUCCESS_OPERATE.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_OPERATE.getCode());
            commonResponse.setMessage(GyCode.FAIL_OPERATE.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "获取所有小车集结点",notes = "selectRallyPointAll",tags={"@郭阳"})
    @RequestMapping(value = "/selectRallyPointAll",method = RequestMethod.GET)
    public CommonResponse selectRallyPointAll() {
        CommonResponse commonResponse = new CommonResponse();
        try{
            List<RallyPoint> rallyPointAll = carTaskService.selectRallyPointAll();
            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(rallyPointAll);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "验证当前集结点模板数量",notes = "checkRallyPointCarTask",tags={"@郭阳"})
    @ApiImplicitParam(name="rallyPointId", value="小车集结点id",paramType="query",dataType="int")
    @RequestMapping(value = "/checkRallyPointCarTask",method = RequestMethod.POST)
    public CommonResponse checkRallyPointCarTask(int rallyPointId) {
        CommonResponse commonResponse = new CommonResponse();

        try{
            Integer checkRallyPointCarTask = carTaskService.checkRallyPointCarTask(rallyPointId);
            if(checkRallyPointCarTask == 1){
                commonResponse.setCode(GyCode.FAIL_CAR_TASK_NUMBER.getCode());
                commonResponse.setMessage(GyCode.FAIL_CAR_TASK_NUMBER.getInfo());
                return commonResponse;
            }
            commonResponse.setCode(GyCode.SUCCESS_CAR_TASK.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_CAR_TASK.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_DELETE.getCode());
            commonResponse.setMessage(GyCode.FAIL_DELETE.getInfo());
        }
        return commonResponse;
    }
}
