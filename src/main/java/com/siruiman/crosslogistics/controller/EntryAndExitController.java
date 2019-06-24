package com.siruiman.crosslogistics.controller;

import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.enums.HtCode;
import com.siruiman.crosslogistics.model.Bizdictionary;
import com.siruiman.crosslogistics.model.EntryAndExit;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.EntryAndExitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 出入境物流途径
 */
@Api(value = "EntryAndExit", description = "出入境物流途径参数配置API", tags = {"出入境物流途径参数配置"})
@RestController
@RequestMapping("/entryAndExit")
public class EntryAndExitController {
    @Autowired
    private EntryAndExitService entryAndExitService;


    @ApiOperation(value = "获取出入境物流途径参数配置列表", notes = "获取entryAndExit对象列表信息", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页容量", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "exitWay", value = "出境方式", paramType = "query", dataType = "int"),
    })
    @RequestMapping(value = "/getEntryAndExitList", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getEntryAndExitList"})
    public LayuiCommonResponse getEntryAndExitList(@Validated @RequestParam(defaultValue = "1") int page,
                                                   @Validated @RequestParam(defaultValue = "10") int limit,
                                                   @Validated Integer exitWay) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            PageHelper.startPage(page, limit);
            List<EntryAndExit> list = entryAndExitService.getEntryAndExitList(exitWay);
            int count = entryAndExitService.selectEntryAndExitCount(exitWay);
            response.setMsg("success");
            response.setCode(0);
            response.setCount(count);
            response.setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
        }
        return response;
    }

    @ApiOperation(value = "查询更新前出入境物流途径参数配置信息", notes = "获取entryAndExit对象信息", tags = {"@郝腾"})
    @ApiImplicitParam(name = "eaeId", value = "出入境物流途径id", required = true, dataType = "Integer", paramType = "query")
    @RequestMapping(value = "/getEntryAndExitById", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"updateEntryAndExit"})
    public LayuiCommonResponse getEntryAndExitById(Integer eaeId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            EntryAndExit entryAndExit = entryAndExitService.selectEntryAndExitById(eaeId);
            response.setMsg("success");
            response.setCode(0);
            response.setData(entryAndExit);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
        }
        return response;
    }

    @ApiOperation(value = "更新出入境物流途径参数", notes = "更新entryAndExit对象信息", tags = {"@郝腾"})
    @ApiImplicitParam(name = "entryAndExit", value = "出入境物流途径参数实体类", dataType = "EntryAndExit")
    @RequestMapping(value = "/updateEntryAndExit", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = {"updateEntryAndExit"})
    public LayuiCommonResponse updateEntryAndExit(@Validated @RequestBody EntryAndExit entryAndExit) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            //EntryAndExit entryAndExit1 = entryAndExitService.selectEntryAndExitById(entryAndExit.getEaeId());

            entryAndExitService.updateEntryAndExit(entryAndExit);
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

    @ApiOperation(value = "添加出入境物流途径参数", notes = "添加entryAndExit对象出入境物流途径参数信息", tags = {"@郝腾"})
    @ApiImplicitParam(name = "entryAndExit", value = "数据字典实体类", required = true, dataType = "EntryAndExit")
    @RequestMapping(value = "/insertEntryAndExit", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = {"insertEntryAndExit"})
    public LayuiCommonResponse insertEntryAndExit(@RequestBody EntryAndExit entryAndExit) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            entryAndExitService.insertBizdictionary(entryAndExit);
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

    @ApiOperation(value = "删除出入境物流途径参数", notes = "删除entryAndExit对象出入境物流途径参数信息", tags = {"@郝腾"})
    @ApiImplicitParam(name = "eaeId", value = "出入境物流途径id", required = true, dataType = "Integer", paramType = "query")
    @RequestMapping(value = "/deleteEntryAndExitById", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"deleteEntryAndExitById"})
    public LayuiCommonResponse deleteEntryAndExitById(Integer eaeId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            entryAndExitService.deleteEntryAndExitById(eaeId);
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
