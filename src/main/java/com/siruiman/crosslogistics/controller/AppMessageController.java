package com.siruiman.crosslogistics.controller;

import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.enums.HtCode;
import com.siruiman.crosslogistics.model.AppMessage;
import com.siruiman.crosslogistics.model.Message;
import com.siruiman.crosslogistics.pojo.AppCommonResponse;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api(value = "AppMessage", description = "APP消息中心API", tags = {"APP消息中心"})
@RestController
@RequestMapping("/appMessage")
public class AppMessageController {

    @Autowired
    private MessageService messageService;

    @ApiOperation(value = "获取消息中心列表", notes = "获取消息中心列表信息", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页容量", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "category", value = "分类", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "appUserId", value = "app用户id", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "language", value = "语言 （zh 中文 en 英文）", paramType = "query", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/getMessageList", method = RequestMethod.GET)
    @ResponseBody
    public AppCommonResponse getMessageList(@Validated @RequestParam(defaultValue = "1") int page,
                                            @Validated @RequestParam(defaultValue = "15") int limit,
                                            @Validated @RequestParam String category,
                                            @Validated @RequestParam int appUserId,
                                            @Validated @RequestParam String language) {
        AppCommonResponse response = new AppCommonResponse();
        try {
            PageHelper.startPage(page, limit);
            List<AppMessage> appMessagesList = new ArrayList<>();
            if (language.equals("zh")) {
                appMessagesList = messageService.selectMessageList(category, appUserId);
            } else if (language.equals("en")) {
                appMessagesList = messageService.selectEnMessageList(category, appUserId);
            }
            int number = 0;
            if (category.equals("货车")) {
                for (AppMessage appMessage : appMessagesList
                ) {
                    if (appMessage.getState().equals("未读")) {
                        number++;
                    }
                }
            } else {
                List<AppMessage> appMessagesList1 = messageService.selectMessageList("小车", appUserId);
                for (AppMessage appMessage : appMessagesList1
                ) {
                    if (appMessage.getState().equals("未读")) {
                        number++;
                    }
                }
                List<AppMessage> appMessagesList2 = messageService.selectMessageList("普通用户", appUserId);
                for (AppMessage appMessage : appMessagesList2
                ) {
                    if (appMessage.getState().equals("未读")) {
                        number++;
                    }
                }
            }
            response.setCode(HtCode.SUCCESS_GET.getCode());
            response.setMessage(HtCode.SUCCESS_GET.getInfo());
            response.setData(appMessagesList);
            response.setCount(number);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_GET.getCode());
            response.setMessage(HtCode.FAIL_GET.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "获取消息详细信息", notes = "获取消息详细信息", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "messageId", value = "消息id", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "language", value = "语言 （zh 中文 en 英文）", paramType = "query", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse getMessage(@Validated @RequestParam int messageId, @Validated @RequestParam String language) {
        CommonResponse response = new CommonResponse();
        try {
            AppMessage appMessage = new AppMessage();
            if (language.equals("zh")) {
                appMessage = messageService.selectMessageById(messageId);
                if (appMessage.getState().equals("未读")) {
                    appMessage.setState("已读");
                    appMessage.setUpdateTime(new Date());
                    messageService.updateMessageState(appMessage);
                }
            } else if (language.equals("en")) {
                appMessage = messageService.selectEnMessageById(messageId);
                if (appMessage.getState().equals("未读")) {
                    appMessage.setState("已读");
                    appMessage.setUpdateTime(new Date());
                    messageService.updateMessageState(appMessage);
                }
            }

            response.setCode(HtCode.SUCCESS_GET.getCode());
            response.setMessage(HtCode.SUCCESS_GET.getInfo());
            response.setData(appMessage);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_GET.getCode());
            response.setMessage(HtCode.FAIL_GET.getInfo());
        }
        return response;
    }
}
