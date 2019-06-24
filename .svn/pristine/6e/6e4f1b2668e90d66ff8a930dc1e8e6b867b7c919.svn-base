package com.siruiman.crosslogistics.controller;


import com.siruiman.crosslogistics.enums.HtCode;
import com.siruiman.crosslogistics.model.Bizdictionary;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.BizdictionaryService;
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
 * 数据字典管理
 */
@Api(value = "Bizdictionary", description = "数据字典API", tags = {"数据字典"})
@RestController
@RequestMapping("/bizdictionary")
public class BizdictionaryController {

    @Autowired
    private BizdictionaryService bizdictionaryService;

    @ApiOperation(value = "获取数据字典列表", notes = "获取Bizdictionary对象列表信息", tags = {"@郝腾"})
    @RequestMapping(value = "/getBizdictionaryList", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getBizdictionaryList"})
    public LayuiCommonResponse getBizdictionaryList() {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            List<Bizdictionary> bizdictionaryList = bizdictionaryService.getBizdictionaryList();
            response.setMsg("success");
            response.setCode(0);
            response.setData(bizdictionaryList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
        }
        return response;
    }

    @ApiOperation(value = "添加字典数据", notes = "添加Bizdictionary数据字典信息", tags = {"@郝腾"})
    @ApiImplicitParam(name = "bizdictionary", value = "数据字典实体类", required = true, dataType = "Bizdictionary")
    @RequestMapping(value = "/insertBizdictionary", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = "insertBizdictionary")
    public LayuiCommonResponse insertBizdictionary(@RequestBody Bizdictionary bizdictionary) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            bizdictionaryService.insertBizdictionary(bizdictionary);
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

    @ApiOperation(value = "查询更新前数据字典信息", notes = "获取Bizdictionary对象信息", tags = {"@郝腾"})
    @ApiImplicitParam(name = "id", value = "数据字典id", required = true, dataType = "Integer", paramType = "query")
    @RequestMapping(value = "/getBizdictionary", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = "updateBizdictionary")
    public LayuiCommonResponse getBizdictionary(Integer id) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            Bizdictionary bizdictionary = bizdictionaryService.selectBizdictionary(id);
            response.setMsg("success");
            response.setCode(0);
            response.setData(bizdictionary);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
        }
        return response;
    }

    @ApiOperation(value = "更新数据字典", notes = "更新Bizdictionary对象信息", tags = {"@郝腾"})
    @ApiImplicitParam(name = "bizdictionary", value = "数据字典实体类", paramType = "query", dataType = "Bizdictionary")
    @RequestMapping(value = "/updateBizdictionary", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = "updateBizdictionary")
    public LayuiCommonResponse updateBizdictionary(@Validated @RequestBody Bizdictionary bizdictionary) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            Bizdictionary oldBizdictionary = bizdictionaryService.selectBizdictionary(bizdictionary.getId());
            if (oldBizdictionary.getImg() != null && oldBizdictionary.getImg() != "") {
                bizdictionary.setImg(oldBizdictionary.getImg());
            }
            bizdictionaryService.updateBizdictionary(bizdictionary);
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

    @ApiOperation(value = "删除数据字典", notes = "删除Bizdictionary对象信息", tags = {"@郝腾"})
    @ApiImplicitParam(name = "id", value = "数据字典id", required = true, dataType = "Integer", paramType = "query")
    @RequestMapping(value = "/deleteBizdictionaryById", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = "deleteBizdictionaryById")
    public LayuiCommonResponse deleteBizdictionaryById(Integer id) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {

            bizdictionaryService.deleteBizdictionaryById(id);
            List<Bizdictionary> bizdictionaryList = bizdictionaryService.selectBizdictionaryByParentId(id);
            for (int i = 0; i < bizdictionaryList.size(); i++) {
                if (bizdictionaryList.get(i).getId() != null && !(bizdictionaryList.get(i).getId().equals(""))) {
                    deleteBizdictionary(bizdictionaryList.get(i).getId());
                }
            }
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

    public void deleteBizdictionary(Integer id) {
        bizdictionaryService.deleteBizdictionaryById(id);
        List<Bizdictionary> bizdictionaryList = bizdictionaryService.selectBizdictionaryByParentId(id);
        for (int i = 0; i < bizdictionaryList.size(); i++) {
            if (bizdictionaryList.get(i).getId() != null && !(bizdictionaryList.get(i).getId().equals(""))) {
                deleteBizdictionary(bizdictionaryList.get(i).getId());
            }
        }
    }
}
