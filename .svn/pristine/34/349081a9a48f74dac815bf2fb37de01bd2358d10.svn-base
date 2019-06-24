package com.siruiman.crosslogistics.controller;

import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.enums.HtCode;
import com.siruiman.crosslogistics.model.Bizdictionary;
import com.siruiman.crosslogistics.model.CopyWriter;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.CopyWriterService;
import com.siruiman.crosslogistics.util.Base64;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Api(value = "CopyWriter", description = "文案设置API", tags = {"文案设置"})
@RestController
@RequestMapping("/copyWriter")
public class CopyWriterController {

    @Autowired
    private CopyWriterService copyWriterService;

    @ApiOperation(value = "获取文案列表", notes = "获取文案列表", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页容量", paramType = "query", dataType = "int"),
    })
    @RequestMapping(value = "/getCopywritingList", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getCopywritingList"})
    public LayuiCommonResponse getCopywritingList(@Validated @RequestParam(defaultValue = "1") int page,
                                                  @Validated @RequestParam(defaultValue = "10") int limit) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            PageHelper.startPage(page, limit);
            List<CopyWriter> copyWriterList = copyWriterService.selectCopyWriterList();
            Integer count = copyWriterService.selectCopyWriterListCount();
            response.setMsg("success");
            response.setCode(0);
            response.setCount(count);
            response.setData(copyWriterList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
            return response;
        }
        return response;
    }

    @ApiOperation(value = "根据id获取文案内容", notes = "根据id获取文案内容", tags = {"@郝腾"})
    @ApiImplicitParam(name = "cwId", value = "文案id", paramType = "query", dataType = "int")
    @RequestMapping(value = "/getCopyWriterById", method = RequestMethod.GET)
    @ResponseBody
    public LayuiCommonResponse getcopyWriterById(@Validated @RequestParam int cwId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            CopyWriter copyWriter = copyWriterService.selectcopyWriterById(cwId);
            response.setMsg("success");
            response.setCode(0);
            response.setData(copyWriter);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
            return response;
        }
        return response;
    }

    @ApiOperation(value = "更新文案内容", notes = "更新文案内容", tags = {"@郝腾"})
    @ApiImplicitParam(name = "copyWriter", value = "文案实体类", dataType = "CopyWriter")
    @RequestMapping(value = "/updateCopyWriter", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = {"updateCopyWriter"})
    public LayuiCommonResponse updateCopyWriter(@RequestBody CopyWriter copyWriter) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            copyWriterService.updateCopyWriter(copyWriter);
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

    @ApiOperation(value = "添加文案", notes = "添加文案", tags = {"@郝腾"})
    @ApiImplicitParam(name = "copyWriter", value = "文案实体类", dataType = "CopyWriter")
    @RequestMapping(value = "/insertCopyWriter", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = {"insertCopyWriter"})
    public LayuiCommonResponse insertCopyWriter(@RequestBody CopyWriter copyWriter) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            List<CopyWriter> copyWriterList = copyWriterService.selectCopyWriterList();
            for (CopyWriter copyWriter1 : copyWriterList
            ) {
                if(!copyWriter1.getType().equals("隐藏")){
                    if (copyWriter1.getType().equals(copyWriter.getType()) && copyWriter1.getLanguage().equals(copyWriter.getLanguage())) {
                        response.setCode(HtCode.FAIL_ADD_REPEAT.getCode());
                        response.setMsg(HtCode.FAIL_ADD_REPEAT.getInfo());
                        return response;
                    }
                }

            }
            copyWriterService.insertCopyWriter(copyWriter);
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

    @ApiOperation(value = "删除文案", notes = "删除文案", tags = {"@郝腾"})
    @ApiImplicitParam(name = "cwId", value = "文案id", required = true, dataType = "Integer", paramType = "query")
    @RequestMapping(value = "/deleteCopyWriterById", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"deleteCopyWriterById"})
    public LayuiCommonResponse deleteCopyWriterById(Integer cwId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            copyWriterService.deleteCopyWriterById(cwId);
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

    @ApiOperation(value = "获取文案类型列表", notes = "获取文案类型列表", tags = {"@郝腾"})
    @RequestMapping(value = "/getCopywritingTypeList", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"updateCopyWriter"})
    public LayuiCommonResponse getCopywritingTypeList() {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            List<CopyWriter> copyWriterList = copyWriterService.selectCopyWriterTypeList();
            response.setMsg("success");
            response.setCode(0);
            response.setData(copyWriterList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
            return response;
        }
        return response;
    }
}


