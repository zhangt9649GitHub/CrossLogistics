package com.siruiman.crosslogistics.controller;

import com.siruiman.crosslogistics.enums.ZwCode;
import com.siruiman.crosslogistics.model.SysConfig;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.ConfigService;
import com.siruiman.crosslogistics.util.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 张占伟
 * @date 2019/1/15 14:28
 */
@RestController
@RequestMapping("/adminconfig")
@Api(value = "adminconfig-API",description = "系统参数-api",tags = {"系统参数配置"})
public class AdminConfigController {

    Logger logger = LoggerFactory
            .getLogger(AdminConfigController.class);

    @Autowired
    private ConfigService configService;

    @ApiOperation(value = "系统参数", notes = "获取系统参数列表", nickname = "getAdminConfig", tags = {"@占伟"})
    @RequestMapping(method = RequestMethod.GET, value = "getAdminConfiglist")
    public LayuiCommonResponse getAdminConfig() {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            List<SysConfig> sysConfigs = configService.selectAll();
            response.setData(sysConfigs);
            response.setCode(ZwCode.SUCCESS_GET.getCode());
            response.setMsg(ZwCode.SUCCESS_GET.getInfo());
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(ZwCode.FAIL_GET.getCode());
            response.setMsg(ZwCode.FAIL_GET.getInfo());
        }
        return response;
    }


    @ApiOperation(value = "系统参数修改", notes = "系统参数修改", nickname = "getAdminConfig", tags = {"@占伟"})
    @RequestMapping(method = RequestMethod.POST, value = "updateAdminConfig")
    public LayuiCommonResponse updateAdminConfig(@RequestBody HashMap<String, String> map) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        if (map.size() > 0) {
            logger.info("正在修改系统参数" + DateUtil.getDateTime());
            try {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                   logger.info(entry.getKey()+" : "+entry.getValue());
                   if(Double.parseDouble(entry.getValue())<=0){
                       logger.error("value必须大于0");
                       throw new Exception();
                   }
                    configService.findAndUpdateByKey(entry.getKey(), entry.getValue());
                }
                configService.loadConfig();
                response.setMsg(ZwCode.SUCCESS_EDIT.getInfo());
                response.setCode(ZwCode.SUCCESS_EDIT.getCode());
                logger.info("修改系统参数成功" + DateUtil.getDateTime());
            } catch (Exception e) {
                response.setMsg(ZwCode.FAIL_EDIT.getInfo());
                response.setCode(ZwCode.FAIL_EDIT.getCode());
                e.printStackTrace();
            }
        }
        else {
            response.setMsg(ZwCode.SUCCESS_EDIT.getInfo());
            response.setCode(ZwCode.SUCCESS_EDIT.getCode());
        }
        return response;
    }
}