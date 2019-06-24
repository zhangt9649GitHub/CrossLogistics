package com.siruiman.crosslogistics.controller;

import com.siruiman.crosslogistics.enums.HtCode;
import com.siruiman.crosslogistics.model.CopyWriter;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.service.CopyWriterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Api(value = "APPCopyWriting", description = "APP设置API", tags = {"APP设置"})
@RestController
@RequestMapping("/appCopyWriting")
public class APPCopyWriterController {

    @Autowired
    private CopyWriterService copyWriterService;

    @ApiOperation(value = "获取设置列表", notes = "获取设置列表信息", tags = {"@郝腾"})
    @ApiImplicitParam(name = "language", value = "语言", paramType = "query", required = true, dataType = "String")
    @RequestMapping(value = "/getCopyWritingList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse getCopyWritingList(@Validated @RequestParam String language) {
        CommonResponse response = new CommonResponse();
        try {
            List<CopyWriter> copyWriterList = new ArrayList<>();
            copyWriterList = copyWriterService.selectCopyWritingList(language);
            for (CopyWriter copyWriter : copyWriterList
            ) {
                copyWriter.setPath("/admin/copybookSettings/open?copyWriterId=" + copyWriter.getCwId());
            }

            response.setCode(HtCode.SUCCESS_GET.getCode());
            response.setMessage(HtCode.SUCCESS_GET.getInfo());
            response.setData(copyWriterList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_GET.getCode());
            response.setMessage(HtCode.FAIL_GET.getInfo());
        }
        return response;
    }

}
