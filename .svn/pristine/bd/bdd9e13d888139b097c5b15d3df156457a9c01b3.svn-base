package com.siruiman.crosslogistics.controller;

import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.enums.GyCode;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.model.dto.AdminUserLoginDto;
import com.siruiman.crosslogistics.model.dto.EditTruckTaskDto;
import com.siruiman.crosslogistics.model.dto.TruckTaskDto;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.TruckTaskService;
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


@Api(value="TruckTask",description = "货车任务",tags={"任务管理-货车任务"})
@RestController
@RequestMapping("/truckTask")
public class TruckTaskController {
    @Autowired
    private TruckTaskService truckTaskService;

    @ApiOperation(value = "货车任务模板列表",notes = "truckTaskAll",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="page", value="第几页",paramType="query",dataType="int"),
            @ApiImplicitParam(name="limit", value="每页容量",paramType="query",dataType="int"),
            @ApiImplicitParam(name="search", value="搜索",paramType="query",dataType="String")
    })
    @RequestMapping(value = "/truckTaskAll",method = RequestMethod.GET)
    public LayuiCommonResponse selectTruckTaskAll(@Validated @RequestParam(defaultValue = "1") int page, @Validated @RequestParam(defaultValue = "10") int limit, String search) {
        LayuiCommonResponse layuiCommonResponse = new LayuiCommonResponse();

        try{
            PageHelper.startPage(page,limit);
            List<TruckTask> selectTruckTaskAll = truckTaskService.selectTruckTaskAll(search);
            Integer count = truckTaskService.count(search);


            layuiCommonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            layuiCommonResponse.setData(selectTruckTaskAll);
            layuiCommonResponse.setCount(count);
            layuiCommonResponse.setMsg(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            layuiCommonResponse.setCode(GyCode.FAIL_GET.getCode());
            layuiCommonResponse.setMsg(GyCode.FAIL_GET.getInfo());
        }

        return layuiCommonResponse;
    }

    @ApiOperation(value = "新增货车任务模板",notes = "addTruckTask",tags={"@郭阳"})
    @RequestMapping(value = "/addTruckTask",method = RequestMethod.POST)
    public CommonResponse addTruckTask(TruckTaskDto truckTaskDto) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
            truckTaskDto.setAdminId(Integer.parseInt(user.getAdminUId()));

            Integer addTruckTask = truckTaskService.addTruckTask(truckTaskDto);
            if(addTruckTask == 2){
                commonResponse.setCode(GyCode.FAIL_TASK_NAME.getCode());
                commonResponse.setMessage(GyCode.FAIL_TASK_NAME.getInfo());
                return commonResponse;
            }
            if(addTruckTask < 1){
                commonResponse.setCode(GyCode.FAIL_ADD.getCode());
                commonResponse.setMessage(GyCode.FAIL_ADD.getInfo());
                return commonResponse;
            }
            commonResponse.setCode(GyCode.SUCCESS_ADD.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_ADD.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "查询区域列表",notes = "singaporeAreaAll",tags={"@郭阳"})
    @RequestMapping(value = "/singaporeAreaAll",method = RequestMethod.GET)
    public CommonResponse selectSingaporeAreaAll() {
        CommonResponse commonResponse = new CommonResponse();

        try{
            List<SingaporeArea> selectSingaporeAreaAll = truckTaskService.selectSingaporeAreaAll();

            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectSingaporeAreaAll);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "小车集结点列表",notes = "selectRallyPointBySA",tags={"@郭阳"})
    @ApiImplicitParam(name="singaporeAreaId", value="区域id",paramType="query",dataType="int")
    @RequestMapping(value = "/selectRallyPointBySA",method = RequestMethod.GET)
    public CommonResponse selectRallyPointBySA(int singaporeAreaId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            List<RallyPoint> selectRallyPointBySA = truckTaskService.selectRallyPointBySA(singaporeAreaId);

            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectRallyPointBySA);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "查询货车司机列表",notes = "selectAppUserByTruck",tags={"@郭阳"})
    @RequestMapping(value = "/selectAppUserByTruck",method = RequestMethod.GET)
    public CommonResponse selectAppUserByTruck() {
        CommonResponse commonResponse = new CommonResponse();
        try{
            List<AppUser> selectAppUserByTruck = truckTaskService.selectAppUserByTruck();

            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectAppUserByTruck);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "查询仓库列表",notes = "selectWarehouseAll",tags={"@郭阳"})
    @RequestMapping(value = "/selectWarehouseAll",method = RequestMethod.GET)
    public CommonResponse selectWarehouseAll() {
        CommonResponse commonResponse = new CommonResponse();
        try{
            List<Warehouse> selectWarehouseAll = truckTaskService.selectWarehouseAll();

            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectWarehouseAll);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "删除货车模板",notes = "deleteTruckTask",tags={"@郭阳"})
    @ApiImplicitParam(name="truckTaskId", value="货车模板id",paramType="query",dataType="int")
    @RequestMapping(value = "/deleteTruckTask",method = RequestMethod.POST)
    public CommonResponse deleteTruckTask(int truckTaskId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer deleteTruckTask = truckTaskService.deleteTruckTask(truckTaskId);
            if(deleteTruckTask < 1){
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

    @ApiOperation(value = "查询货车模板详情",notes = "truckTaskDetails",tags={"@郭阳"})
    @ApiImplicitParam(name="truckTaskId", value="货车模板id",paramType="query",dataType="int")
    @RequestMapping(value = "/truckTaskDetails",method = RequestMethod.GET)
    public CommonResponse truckTaskDetails(int truckTaskId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            TruckTaskDetails truckTaskDetails = truckTaskService.truckTaskDetails(truckTaskId);

            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(truckTaskDetails);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "启用禁用货车模板",notes = "editTruckTaskStatus",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="truckTaskId", value="货车模板id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="status", value="状态1启用2禁用",paramType="query",dataType="int"),
    })
    @RequestMapping(value = "/editTruckTaskStatus",method = RequestMethod.GET)
    public CommonResponse editTruckTaskStatus(int truckTaskId, int status) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer editTruckTaskStatus = truckTaskService.editTruckTaskStatus(truckTaskId, status);
            if(editTruckTaskStatus < 1){
                commonResponse.setCode(GyCode.FAIL_OPERATE.getCode());
                commonResponse.setMessage(GyCode.FAIL_OPERATE.getInfo());
                return commonResponse;
            }
            commonResponse.setCode(GyCode.SUCCESS_OPERATE.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_OPERATE.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_OPERATE.getCode());
            commonResponse.setMessage(GyCode.FAIL_OPERATE.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "查询编辑货车模板信息",notes = "selectEditTruckTask",tags={"@郭阳"})
    @ApiImplicitParam(name="truckTaskId", value="货车模板id",paramType="query",dataType="int")
    @RequestMapping(value = "/selectEditTruckTask",method = RequestMethod.GET)
    public CommonResponse selectEditTruckTask(int truckTaskId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            EditTruckTask selectEditTruckTask = truckTaskService.selectEditTruckTask(truckTaskId);

            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectEditTruckTask);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "编辑货车模板信息",notes = "editTruckTask",tags={"@郭阳"})
    @RequestMapping(value = "/editTruckTask",method = RequestMethod.POST)
    public CommonResponse editTruckTask(EditTruckTaskDto editTruckTaskDto) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
            editTruckTaskDto.setAdminId(Integer.parseInt(user.getAdminUId()));
            Integer editTruckTask = truckTaskService.editTruckTask(editTruckTaskDto);
            if(editTruckTask < 1){
                commonResponse.setCode(GyCode.FAIL_EDIT.getCode());
                commonResponse.setMessage(GyCode.FAIL_EDIT.getInfo());
                return commonResponse;
            }
            commonResponse.setCode(GyCode.SUCCESS_EDIT.getCode());
            commonResponse.setData(editTruckTask);
            commonResponse.setMessage(GyCode.SUCCESS_EDIT.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_EDIT.getCode());
            commonResponse.setMessage(GyCode.FAIL_EDIT.getInfo());
        }

        return commonResponse;
    }
}
