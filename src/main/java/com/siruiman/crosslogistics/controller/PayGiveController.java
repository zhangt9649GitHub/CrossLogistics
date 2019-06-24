package com.siruiman.crosslogistics.controller;

import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.enums.HtCode;
import com.siruiman.crosslogistics.model.FinanceMoneyFlow;
import com.siruiman.crosslogistics.model.LogisticInfo;
import com.siruiman.crosslogistics.model.PayGive;
import com.siruiman.crosslogistics.model.Staff;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.LogisticInfoService;
import com.siruiman.crosslogistics.service.PayGiveService;
import com.siruiman.crosslogistics.service.StaffService;
import com.siruiman.crosslogistics.util.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Api(value = "PayGive", description = "工资发放API", tags = {"工资发放"})
@RestController
@RequestMapping("/payGive")
public class PayGiveController {

    @Autowired
    private PayGiveService payGiveService;
    @Autowired
    private StaffService staffService;

    @ApiOperation(value = "获取工资发放列表", notes = "获取工资发放列表", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页容量", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "addTime", value = "开始时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "staffName", value = "员工姓名", paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/getPayGiveList", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getPayGiveList"})
    public LayuiCommonResponse getPayGiveList(@Validated @RequestParam(defaultValue = "1") int page,
                                              @Validated @RequestParam(defaultValue = "10") int limit,
                                              @Validated String addTime,
                                              @Validated String endTime,
                                              @Validated String staffName) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            PageHelper.startPage(page, limit);
            List<PayGive> payGiveList = payGiveService.selectPayGiveList(addTime, endTime, staffName);
            for (PayGive payGive : payGiveList
            ) {
                if (!(payGive.getAddTime() == null) && !(payGive.getAddTime().equals(""))) {
                    payGive.setAddTime(payGive.getAddTime().substring(0, payGive.getAddTime().indexOf(".")));
                }
            }
            int count = payGiveService.selectCountPayGiveList(addTime, endTime, staffName);
            response.setMsg("success");
            response.setCode(0);
            response.setCount(count);
            response.setData(payGiveList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
        }
        return response;
    }

    @ApiOperation(value = "添加工资发放", notes = "添加工资发放", tags = {"@郝腾"})
    @ApiImplicitParam(name = "payGive", value = "工资发放实体", required = true, dataType = "PayGive")
    @RequestMapping(value = "/insertPayGive", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = {"insertPayGive"})
    public LayuiCommonResponse insertPayGive(@Validated @RequestBody PayGive payGive) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            Staff staff = staffService.selectStaffbyNumber(payGive.getNumber(), payGive.getStaffName());
            payGive.setStaffId(staff.getStaffId());
            String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            payGive.setAddTime(dateStr);
            payGiveService.insertPayGive(payGive);
            response.setCode(HtCode.SUCCESS_ADD.getCode());
            response.setMsg(HtCode.SUCCESS_ADD.getInfo());
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_ADD.getCode());
            response.setMsg(HtCode.FAIL_ADD.getInfo());
            return response;
        }
        return response;
    }

    @ApiOperation(value = "查询更新前工资信息", notes = "查询更新前工资信息", tags = {"@郝腾"})
    @ApiImplicitParam(name = "payGiveId", value = "工资发放id", paramType = "query", required = true, dataType = "int")
    @RequestMapping(value = "/getPayGiveById", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"updatePayGive"})
    public LayuiCommonResponse getPayGiveById(@Validated int payGiveId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            PayGive payGive = payGiveService.selectPayGiveById(payGiveId);
            Staff staff = staffService.selectStaffDetail(payGive.getStaffId());
            payGive.setStaffName(staff.getStaffName());
            payGive.setNumber(staff.getNumber());
            response.setCode(HtCode.SUCCESS_GET.getCode());
            response.setMsg(HtCode.SUCCESS_GET.getInfo());
            response.setData(payGive);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_GET.getCode());
            response.setMsg(HtCode.FAIL_GET.getInfo());
            return response;
        }
        return response;
    }

    @ApiOperation(value = "更新工资发放信息", notes = "更新工资发放信息", tags = {"@郝腾"})
    @ApiImplicitParam(name = "payGive", value = "工资发放实体类", dataType = "PayGive")
    @RequestMapping(value = "/updatePayGive", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = {"updatePayGive"})
    public LayuiCommonResponse updatePayGive(@Validated @RequestBody PayGive payGive) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            PayGive payGive1 = payGiveService.selectPayGiveById(payGive.getPayGiveId());
            Staff staff = staffService.selectStaffbyNumber(payGive.getNumber(), payGive.getStaffName());
            if (staff == null) {
                response.setCode(HtCode.FAIL_ZERO.getCode());
                response.setMsg(HtCode.FAIL_ZERO.getInfo());
                return response;
            }
            payGive.setStaffId(staff.getStaffId());
            payGive.setAddTime(payGive1.getAddTime());
            payGiveService.updatePayGive(payGive);
            response.setCode(HtCode.SUCCESS_UPDATE.getCode());
            response.setMsg(HtCode.SUCCESS_UPDATE.getInfo());
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_UPDATE.getCode());
            response.setMsg(HtCode.FAIL_UPDATE.getInfo());
            return response;
        }
        return response;
    }

    @ApiOperation(value = "删除工资发放信息", notes = "删除工资发放信息", tags = {"@郝腾"})
    @ApiImplicitParam(name = "payGiveId", value = "工资发放id", required = true, dataType = "Integer", paramType = "query")
    @RequestMapping(value = "/deletePayGiveById", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"deletePayGiveById"})
    public LayuiCommonResponse deletePayGiveById(@Validated Integer payGiveId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            payGiveService.deletePayGiveById(payGiveId);
            response.setCode(HtCode.SUCCESS_DELETE.getCode());
            response.setMsg(HtCode.SUCCESS_DELETE.getInfo());
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_DELETE.getCode());
            response.setMsg(HtCode.FAIL_DELETE.getInfo());
            return response;
        }
        return response;
    }
}
