package com.siruiman.crosslogistics.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 在spring boot中加入相应配置的扫描
 * @author :gy
 * @date :2019/1/17 20:30
 */
@Configuration
public class MultipleFileUploadConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        /*
        * 说明：增加虚拟路径(经过本人测试：在此处配置的虚拟路径，用springboot内置的tomcat时有效，
        * 用外部的tomcat也有效;所以用到外部的tomcat时不需在tomcat/config下的相应文件配置虚拟路径了,阿里云linux也没问题)
        */
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {  //如果是Windows系统
            registry.addResourceHandler("/importFile/**")
                    // /apple/**表示在磁盘apple目录下的所有资源会被解析为以下的路径
                    .addResourceLocations("file:D:/importFile/"); //媒体资源
                    /*.addResourceLocations("classpath:/META-INF/resources/");  //swagger2页面*/
        } else {  //linux 和mac
            registry.addResourceHandler("/images/**")
                    .addResourceLocations("file:/usr/local/images/");   //媒体资源
                    /*.addResourceLocations("classpath:/META-INF/resources/");  //swagger2页面;*/
        }

        /*registry.addResourceHandler("/upload/video/**").addResourceLocations("file:G:/upload/video/");*/

        //阿里云(映射路径去除盘符)
        //registry.addResourceHandler("/ueditor/image/**").addResourceLocations("/upload/image/");
        //registry.addResourceHandler("/ueditor/video/**").addResourceLocations("/upload/video/");
        super.addResourceHandlers(registry);
    }
}
