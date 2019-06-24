package com.siruiman.crosslogistics;


import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.MultipartConfigElement;
import java.io.File;


/**
 * @program: swagger2-API
 * @description: swagger2配置类
 * @author: Bacy
 * @create: 2018年12月17日
 **/
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())//调用下面apiInfo()方法
                .select()
                //Controller所在路径
                .apis(RequestHandlerSelectors.basePackage("com.siruiman.crosslogistics"))
                .paths(PathSelectors.any())
                .build();

    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("跨境物流众包项目 API")
                .description("API接口文档")
                .termsOfServiceUrl("http://47.75.251.179/swagger-ui.html#/")
                .contact("ht")
                .version("0.0.1")
                .build();

    }

    //启动类中加入配置临时文件目录 临时文件地址无效
    // 解决 The temporary upload location [/tmp/tomcat.2737591724424319502.8062/work/Tomcat/localhost/ROOT] is not valid
    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        String location = System.getProperty("user.dir") + "/data/tmp";
        File tmpFile = new File(location);
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }
        factory.setLocation(location);
        return factory.createMultipartConfig();
    }

}
