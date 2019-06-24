package com.siruiman.crosslogistics.controller;

import com.siruiman.crosslogistics.enums.GyCode;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.util.Base64;
import com.siruiman.crosslogistics.util.MultipleFileUploadConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


@Api(value="Staff",description = "上传图片",tags={"上传图片"})
@SpringBootApplication
@RestController
@RequestMapping("/multipleFileUpload")
public class MultipleFileUploadController {


    @ApiOperation(value = "保存图片",notes = "selectStaffAll",tags={"@郭阳"})
    @RequestMapping(value = "/importFile",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public CommonResponse importFile(@RequestParam(value = "file", required = false) MultipartFile file){

        CommonResponse response = new CommonResponse();

        try {
            String fileName = file.getOriginalFilename();

            /*ApplicationHome home = new ApplicationHome(getClass());
            File jarFile = home.getSource();
            String filePath = jarFile.getParentFile().toString();*/

            String folderName = Base64.getRandomFileName();

            String os = System.getProperty("os.name");
            if (os.toLowerCase().startsWith("win")) {  //如果是Windows系统
                File dest = new File("D:/importFile/" + folderName + fileName);
                if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
                    dest.getParentFile().mkdir();
                }
                try {
                    file.transferTo(dest); //保存文件

                    response.setData("/importFile/" + folderName + fileName);

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {  //linux 和mac
                File dest = new File("/usr/local/images/" + folderName + fileName);
                if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
                    dest.getParentFile().mkdir();
                }
                try {
                    file.transferTo(dest); //保存文件

                    response.setData("/images/" + folderName + fileName);

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setCode(GyCode.SUCCESS_IMPORT.getCode());
        response.setMessage(GyCode.SUCCESS_IMPORT.getInfo());


        return response;
    }

}
