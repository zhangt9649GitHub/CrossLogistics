package com.siruiman.crosslogistics.controller;

import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.mapper.FinanceMoneyFlowMapper;
import com.siruiman.crosslogistics.model.FinanceMoneyFlow;
import com.siruiman.crosslogistics.model.WithdrawApplication;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.FinanceMoneyFlowService;
import com.siruiman.crosslogistics.util.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "FinanceFlow", description = "财务流水API", tags = {"财务流水"})
@RestController
@RequestMapping("/financeFlow")
public class FinanceFlowController {

    @Autowired
    private FinanceMoneyFlowService financeMoneyFlowService;

    @ApiOperation(value = "获取财务流水列表", notes = "获取财务流水列表", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页容量", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "createTime", value = "开始时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "financeMoneyType", value = "用途", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "unit", value = "单位", paramType = "query", dataType = "Integer"),
    })
    @RequestMapping(value = "/getFinanceFlowList", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getFinanceFlowList"})
    public LayuiCommonResponse getFinanceFlowList(@Validated @RequestParam(defaultValue = "1") int page,
                                                  @Validated @RequestParam(defaultValue = "10") int limit,
                                                  @Validated String createTime,
                                                  @Validated String endTime,
                                                  @Validated String financeMoneyType,
                                                  @Validated Integer unit) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            PageHelper.startPage(page, limit);
            List<FinanceMoneyFlow> financeMoneyFlowList = financeMoneyFlowService.selectFinanceFlowList(createTime, endTime, financeMoneyType, unit);
            for (FinanceMoneyFlow financeMoneyFlow : financeMoneyFlowList
            ) {
                if (!(financeMoneyFlow.getCreateTime() == null) && !(financeMoneyFlow.getCreateTime().equals(""))) {
                    financeMoneyFlow.setCreateTime(financeMoneyFlow.getCreateTime().substring(0, financeMoneyFlow.getCreateTime().indexOf(".")));
                }
            }
            int count = financeMoneyFlowService.selectCountFinanceFlowList(createTime, endTime, financeMoneyType, unit);
            response.setMsg("success");
            response.setCode(0);
            response.setCount(count);
            response.setData(financeMoneyFlowList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
        }
        return response;
    }

}
