package com.siruiman.crosslogistics.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 官网
 */



@Controller

public class UIKJWLController {

    /**
     * 官网首页
     * @param model
     * @return
     */
    @RequestMapping("kjwl")
    public String officialIndex(Model model){
        return "kjwl/index";
    }


    /**
     * 官网物流
     * @param model
     * @return
     */
    @RequestMapping("aboutus")
    public String aboutusAboutus(Model model){
        return "kjwl/aboutus";
    }


    /**
     * 官网关于我们
     * @param model
     * @return
     */
    @RequestMapping("logistics")
    public String officialLogistics(Model model){
        return "kjwl/logistics";
    }

}
