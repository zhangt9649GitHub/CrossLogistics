package com.siruiman.crosslogistics.controller;

import com.siruiman.crosslogistics.model.AdminPermission;
import com.siruiman.crosslogistics.model.dto.AdminUserLoginDto;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class UIController {

    /**
     * 登录页面
     * @param model
     * @return
     */
    @RequestMapping("/index/login")
    public String login(Model model){
        return "index/login";
    }

    /**
     * 主体页面
     * @param model
     * @return
     */
    @RequestMapping("/index/index")
    public String indexIndex(Model model ){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }


        return "index/index";
    }

    @RequestMapping("/index/homepage")
    public String homepage(Model model){
        return "index/homepage";
    }


    /**
     * 修改密码页面
     * @param model
     * @return
     */
    @RequestMapping("/index/changePassword")
    public String indexChangePassword(Model model){

        return "/index/changePassword";
    }


    /**
     * 消息提醒
     * @param model
     * @return
     */
    @RequestMapping("/index/messageReminder")
    public String indexMessageReminder(Model model){

        return "/index/messageReminder";
    }


    /**
     * 权限管理列表页面
     * @param model
     * @return
     */
    @RequestMapping("/jurisdiction/index")
    public String jurisdictionIndex(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }
        return "/jurisdiction/index";
    }

    /**
     * 权限管理添加页面
     * @param model
     * @return
     */
    @RequestMapping("/jurisdiction/add")
    public String jurisdictionAdd(Model model){

        return "/jurisdiction/add";
    }

    /**
     * 权限管理编辑页面
     * @param model
     * @return
     */
    @RequestMapping("/jurisdiction/edit")
    public String jurisdictionEdit(Model model,Integer groupId){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }

        String groupId1 = Integer.toString(groupId);
        model.addAttribute("groupId",groupId1);
        return "/jurisdiction/edit";
    }



    /**
     * 管理员类型列表页面
     * @param model
     * @return
     */
    @RequestMapping("/administratorType/index")
    public String administratorTypeIndex(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }
        return "/administratorType/index";
    }

    /**
     * 管理员类型添加页面
     * @param model
     * @return
     */
    @RequestMapping("/administratorType/add")
    public String administratorTypeAdd(Model model){

        return "/administratorType/add";
    }

    /**
     * 管理员类型编辑页面
     * @param model
     * @return
     */
    @RequestMapping("/administratorType/edit")
    public String administratorTypeEdit(Model model,Integer adminUserTypeId){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }

        String adminUserTypeId1 = Integer.toString(adminUserTypeId);
        model.addAttribute("adminUserTypeId",adminUserTypeId1);
        return "/administratorType/edit";
    }



    /**
     * 管理员管理列表页面
     * @param model
     * @return
     */
    @RequestMapping("/adminList/index")
    public String adminListIndex(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }
        return "/adminList/index";
    }

    /**
     * 管理员管理添加页面
     * @param model
     * @return
     */
    @RequestMapping("/adminList/add")
    public String adminListAdd(Model model){

        return "/adminList/add";
    }

    /**
     * 管理员管理编辑页面
     * @param model
     * @return
     */
    @RequestMapping("/adminList/edit")
    public String adminListEdit(Model model,Integer adminUid){

        String adminUid1 = Integer.toString(adminUid);
       model.addAttribute("adminUid",adminUid1);
        return "/adminList/edit";
    }



    /**
     * 员工列表页面
     * @param model
     * @return
     */
    @RequestMapping("/staffList/index")
    public String staffListIndex(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }
        return "/staffList/index";
    }

    /**
     * 员工添加页面
     * @param model
     * @return
     */
    @RequestMapping("/staffList/add")
    public String staffListAdd(Model model){

        return "/staffList/add";
    }

    /**
     * 员工编辑页面
     * @param model
     * @return
     */
    @RequestMapping("/staffList/edit")
    public String staffListEdit(Model model,Integer staffId){

        String staffId1 = Integer.toString(staffId);
        model.addAttribute("staffId",staffId1);
        return "/staffList/edit";
    }

    /**
     * 员工查看页面
     * @param model
     * @return
     */
    @RequestMapping("/staffList/open")
    public String staffListOpen(Model model,Integer staffId){

        String staffId1 = Integer.toString(staffId);
       model.addAttribute("staffId",staffId1);
        return "/staffList/open";
    }



    /**
     * 员工职位权限列表页面
     * @param model
     * @return
     */
    @RequestMapping("/jurisdiction_1/index")
    public String jurisdiction_1Index(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }
        return "/jurisdiction_1/index";
    }

    /**
     * 员工职位权限添加页面
     * @param model
     * @return
     */
    @RequestMapping("/jurisdiction_1/add")
    public String jurisdiction_1Add(Model model){

        return "/jurisdiction_1/add";
    }

    /**
     * 员工职位权限编辑页面
     * @param model
     * @return
     */
    @RequestMapping("/jurisdiction_1/edit")
    public String jurisdiction_1Edit(Model model,Integer staffGroupId){

        String staffGroupId1 = Integer.toString(staffGroupId);
        model.addAttribute("staffGroupId",staffGroupId1);
        return "/jurisdiction_1/edit";
    }




    /**
     * 用户管理列表页面
     * @param model
     * @return
     */
    @RequestMapping("/userList/index")
    public String userListIndex(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }
        return "/userList/index";
    }

    /**
     * 用户管理添加页面
     * @param model
     * @return
     */
    @RequestMapping("/userList/add")
    public String userListAdd(Model model){

        return "/userList/add";
    }

    /**
     * 用户管理编辑页面
     * @param model
     * @return
     */
    @RequestMapping("/userList/edit")
    public String userListEdit(Model model,Integer appUserId){

        String appUserId1 = Integer.toString(appUserId);
        model.addAttribute("appUserId",appUserId1);
        return "/userList/edit";
    }

    /**
     * 用户管理查看页面
     * @param model
     * @return
     */
    @RequestMapping("/userList/open")
    public String userListOpen(Model model ,Integer appUserId){

        String appUserId1 = Integer.toString(appUserId);
        model.addAttribute("appUserId",appUserId1);
        return "/userList/open";
    }





    /**
     * 用户审核列表页面
     * @param model
     * @return
     */
    @RequestMapping("/userAuditList/index")
    public String userAuditListIndex(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }
        return "/userAuditList/index";
    }

    /**
     * 用户审核查看页面
     * @param model
     * @return
     */
    @RequestMapping("/userAuditList/open")
    public String userAuditListOpen(Model model ,Integer certificationId){

        String certificationId1 = Integer.toString(certificationId);
        model.addAttribute("certificationId",certificationId);
        return "/userAuditList/open";
    }

    /**
     * 用户地址列表页面
     * @param model
     * @return
     */
    @RequestMapping("/userAddressList/index")
    public String userAddressListIndex(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }
        return "/userAddressList/index";
    }



    /**
     * 正常货物管理列表页面
     * @param model
     * @return
     */
    @RequestMapping("/normalGoods/index")
    public String normalGoodsIndex(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }

        model.addAttribute("getwarehouselist","fdsgsd");
        return "/normalGoods/index";
    }

    /**
     * 正常货物管理查看页面
     * @param model
     * @return
     */
    @RequestMapping("/normalGoods/open")
    public String normalGoodsOpen(Model model,Integer goodsId){

        String goodsId1 = Integer.toString(goodsId);
        model.addAttribute("goodsId",goodsId1);
        return "/normalGoods/open";
    }

    /**
     * 正常货物管理编辑页面
     * @param model
     * @return
     */
    @RequestMapping("/normalGoods/edit")
    public String normalGoodsEdit(Model model,Integer goodsId,String from){

        String goodsId1 = Integer.toString(goodsId);
        model.addAttribute("goodsId",goodsId1);
        model.addAttribute("from",from);
        return "/normalGoods/edit";
    }

    /**
     * 正常货物管理转运货物入库页面
     * @param model
     * @return
     */
    @RequestMapping("/normalGoods/zhuanyunruku")
    public String normalGoodsZhuanyunruku(Model model){


        return "/normalGoods/zhuanyunruku";
    }

    /**
     * 正常货物管理三方货物入库页面
     * @param model
     * @return
     */
    @RequestMapping("/normalGoods/sanfangruku")
    public String normalGoodsSanfangruku(Model model,Integer goodsId){

        String goodsId1 = Integer.toString(goodsId);
        model.addAttribute("goodsId",goodsId1);
        return "/normalGoods/sanfangruku";
    }


    /**
     * 异常货物管理编辑页面
     * @param model
     * @return
     */
    @RequestMapping("/normalGoods/abnormaledit")
    public String normalGoodsAbnormaleditEdit(Model model,Integer goodsId,String from){
        String goodsId1 = Integer.toString(goodsId);
        model.addAttribute("goodsId",goodsId1);

        model.addAttribute("from",from);
        return "/normalGoods/abnormaledit";
    }


    /**
     * 异常货物管理查看页面
     * @param model
     * @return
     */
    @RequestMapping("/normalGoods/abnormalOpen")
    public String normalGoodsAbnormaleditOpen(Model model,Integer goodsId){

        String goodsId1 = Integer.toString(goodsId);
        model.addAttribute("goodsId",goodsId1);


        return "/normalGoods/abnormalOpen";
    }




    /**
     * 异常货物管理列表页面
     * @param model
     * @return
     */
    @RequestMapping("/abnormalGoods/index")
    public String abnormalGoodsIndex(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }

        return "/abnormalGoods/index";
    }

    /**
     * 异常货物管理编辑页面
     * @param model
     * @return
     */
    @RequestMapping("/abnormalGoods/edit")
    public String abnormalGoodsEdit(Model model,Integer goodsId,String from){
        String goodsId1 = Integer.toString(goodsId);
        model.addAttribute("goodsId",goodsId1);

        model.addAttribute("from",from);
        return "/abnormalGoods/edit";
    }


    /**
     * 异常货物管理查看页面
     * @param model
     * @return
     */
    @RequestMapping("/abnormalGoods/open")
    public String abnormalGoodsOpen(Model model,Integer goodsId){

        String goodsId1 = Integer.toString(goodsId);
        model.addAttribute("goodsId",goodsId1);


        return "/abnormalGoods/open";
    }



    /**
     * 三方普通货物列表页面
     * @param model
     * @return
     */
    @RequestMapping("/generalcargo/index")
    public String generalcargoIndex(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }


        return "/generalcargo/index";
    }


    /**
     * 三方普通货物添加页面
     * @param model
     * @return
     */
    @RequestMapping("/generalcargo/add")
    public String generalcargoAdd(Model model){


        return "/generalcargo/add";
    }

    /**
     * 三方Cod货物查看页面
     * @param model
     * @return
     */
    @RequestMapping("/generalcargo/open")
    public String generalcargoOpen(Model model,Integer goodsId){

        String goodsId1 = Integer.toString(goodsId);
        model.addAttribute("goodsId",goodsId1);
        return "/generalcargo/open";
    }




    /**
     * 三方Cod货物列表页面
     * @param model
     * @return
     */
    @RequestMapping("/codcargo/index")
    public String codcargoIndex(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }


        return "/codcargo/index";
    }


    /**
     * 三方Cod货物添加页面
     * @param model
     * @return
     */
    @RequestMapping("/codcargo/add")
    public String codcargoAdd(Model model){


        return "/codcargo/add";
    }



    /**
     * 三方Cod货物查看页面
     * @param model
     * @return
     */
    @RequestMapping("/codcargo/open")
    public String codcargoOpen(Model model,Integer goodsId){

        String goodsId1 = Integer.toString(goodsId);
        model.addAttribute("goodsId",goodsId1);
        return "/codcargo/open";
    }





    /**
     * 货袋管理列表页面
     * @param model
     * @return
     */
    @RequestMapping("/cargoBagList/index")
    public String cargoBagListIndex(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }
        return "/cargoBagList/index";
    }

    /**
     * 货袋管理查看页面
     * @param model
     * @return
     */
    @RequestMapping("/cargoBagList/open")
    public String cargoBagListOpen(Model model,Integer bagId){

        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }

        String bagId1 = Integer.toString(bagId);
        model.addAttribute("bagId",bagId1);
        return "/cargoBagList/open";
    }






    /**
     * 仓库列表页面
     * @param model
     * @return
     */
    @RequestMapping("/warehouseManagementList/index")
    public String warehouseManagementListIndex(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }
        return "warehouseManagementList/index";
    }

    /**
     * 仓库添加页面
     * @param model
     * @return
     */
    @RequestMapping("/warehouseManagementList/add")
    public String warehouseManagementListAdd(Model model){

        return "warehouseManagementList/add";
    }


    /**
     * 仓库编辑页面
     * @param model
     * @return
     */
    @RequestMapping("/warehouseManagementList/edit")
    public String warehouseManagementListEdit(Model model,Integer warehouseId){

        String warehouseId1 = Integer.toString(warehouseId);
        model.addAttribute("warehouseId",warehouseId1);

        return "warehouseManagementList/edit";
    }




    /**
     * 仓位列表页面
     * @param model
     * @return
     */
    @RequestMapping("/warehouseManagement/index")
    public String warehouseManagementIndex(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }

        return "warehouseManagement/index";
    }

    /**
     * 仓位添加页面
     * @param model
     * @return
     */
    @RequestMapping("/warehouseManagement/add")
    public String warehouseManagementAdd(Model model){

        return "warehouseManagement/add";
    }


    /**
     * 仓位编辑页面
     * @param model
     * @return
     */
    @RequestMapping("/warehouseManagement/edit")
    public String warehouseManagementEdit(Model model,Integer wpId){
        String wpId1 = Integer.toString(wpId);
        model.addAttribute("wpId",wpId1);

        return "warehouseManagement/edit";
    }





    /**
     * 小车任务模板列表页面
     * @param model
     * @return
     */
    @RequestMapping("/carMission/index")
    public String carMissionIndex(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }
        return "carMission/index";
    }

    /**
     * 小车任务模板添加页面
     * @param model
     * @return
     */
    @RequestMapping("/carMission/add")
    public String carMissionAdd(Model model){

        return "carMission/add";
    }

    /**
     * 小车任务模板编辑页面
     * @param model
     * @return
     */
    @RequestMapping("/carMission/edit")
    public String carMissionEdit(Model model,Integer carTaskId){

        String carTaskId1 = Integer.toString(carTaskId);
        model.addAttribute("carTaskId",carTaskId1);
        return "carMission/edit";
    }






    /**
     * 货车任务模板列表页面
     * @param model
     * @return
     */
    @RequestMapping("/truckMission/index")
    public String truckMissionIndex(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }
        return "truckMission/index";
    }

    /**
     * 货车任务模板编辑页面
     * @param model
     * @return
     */
    @RequestMapping("/truckMission/edit")
    public String truckMissionEdit(Model model,Integer truckTaskId){

        String truckTaskId1 = Integer.toString(truckTaskId);
        model.addAttribute("truckTaskId",truckTaskId1);
        return "truckMission/edit";
    }

    /**
     * 货车任务模板添加页面
     * @param model
     * @return
     */
    @RequestMapping("/truckMission/add")
    public String truckMissionAdd(Model model){

        return "truckMission/add";
    }

    /**
     * 货车任务模板查看页面
     * @param model
     * @return
     */
    @RequestMapping("/truckMission/open")
    public String truckMissionOpen(Model model,Integer truckTaskId){

        String truckTaskId1 = Integer.toString(truckTaskId);
        model.addAttribute("truckTaskId",truckTaskId1);

        return "/truckMission/open";
    }







    /**
     * 小车管理列表页面
     * @param model
     * @return
     */
    @RequestMapping("/car/index")
    public String carIndex(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }
        return "car/index";
    }

    /**
     * 小车管理添加页面
     * @param model
     * @return
     */
    @RequestMapping("/car/add")
    public String carAdd(Model model){

        return "car/add";
    }

    /**
     * 小车管理编辑页面
     * @param model
     * @return
     */
    @RequestMapping("/car/edit")
    public String carEdit(Model model,Integer carId){

        String carId1 = Integer.toString(carId);
        model.addAttribute("carId",carId1);
        return "car/edit";
    }

    /**
     * 小车管理查看页面
     * @param model
     * @return
     */
    @RequestMapping("/car/open")
    public String carOpen(Model model,Integer carId){

        String carId1 = Integer.toString(carId);
        model.addAttribute("carId",carId1);
        return "car/open";
    }







    /**
     * 小车维护列表页面
     * @param model
     * @return
     */
    @RequestMapping("/carMaintenance/index")
    public String carMaintenanceIndex(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }
        return "carMaintenance/index";
    }

    /**
     * 小车维护新增页面
     * @param model
     * @return
     */
    @RequestMapping("/carMaintenance/add")
    public String carMaintenanceAdd(Model model){

        return "carMaintenance/add";
    }

    /**
     * 小车维护编辑页面
     * @param model
     * @return
     */
    @RequestMapping("/carMaintenance/edit")
    public String carMaintenanceEdit(Model model,Integer carMaintenanceId){

        String carMaintenanceId1 = Integer.toString(carMaintenanceId);
        model.addAttribute("carMaintenanceId",carMaintenanceId1);

        return "carMaintenance/edit";
    }



    /**
     * 小车维护查看页面
     * @param model
     * @return
     */
    @RequestMapping("/carMaintenance/open")
    public String carMaintenanceOpen(Model model,Integer carMaintenanceId){

        String carMaintenanceId1 = Integer.toString(carMaintenanceId);
        model.addAttribute("carMaintenanceId",carMaintenanceId1);
        return "carMaintenance/open";
    }





    /**
     * 货车管理列表页面
     * @param model
     * @return
     */
    @RequestMapping("/truck/index")
    public String truckIndex(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }
        return "truck/index";
    }

    /**
     * 货车管理查看页面
     * @param model
     * @return
     */
    @RequestMapping("/truck/open")
    public String truckOpen(Model model,Integer truckId){

        String truckId1 = Integer.toString(truckId);
        model.addAttribute("truckId",truckId1);

        return "/truck/open";
    }


    /**
     * 车锁管理列表页面
     * @param model
     * @return
     */
    @RequestMapping("/carlock/index")
    public String carlockIndex(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }
        return "carlock/index";
    }

    /**
     * 车锁管理新增页面
     * @param model
     * @return
     */
    @RequestMapping("/carlock/add")
    public String carlockAdd(Model model){

        return "carlock/add";
    }

    /**
     * 小车维护编辑页面
     * @param model
     * @return
     */
    @RequestMapping("/carlock/edit")
    public String carlockEdit(Model model,Integer lockId){
        String lockId1 = Integer.toString(lockId);
        model.addAttribute("lockId",lockId1);

        return "carlock/edit";
    }








    /**
     * 转运管理列表页面
     * @param model
     * @return
     */
    @RequestMapping("/transportList/index")
    public String transportListIndex(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }
        return "/transportList/index";
    }

    /**
     * 转运管理查看页面
     * @param model
     * @return
     */
    @RequestMapping("/transportList/open")
    public String transportListOpen(Model model,Integer goodsId){

        String goodsId1 = Integer.toString(goodsId);
        model.addAttribute("goodsId",goodsId1);

        return "/transportList/open";
    }


    /**
     * 小车订单管理列表页面
     * @param model
     * @return
     */
    @RequestMapping("/carOrder/index")
    public String carOrderIndex(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }
        return "/carOrder/index";
    }

    /**
     * 小车订单管理添加页面
     * @param model
     * @return
     */
    @RequestMapping("/carOrder/add")
    public String carOrderAdd(Model model){

        return "/carOrder/add";
    }

    /**
     * 小车订单管理编辑页面
     * @param model
     * @return
     */
    @RequestMapping("/carOrder/edit")
    public String carOrderEdit(Model model){

        return "/carOrder/edit";
    }

    /**
     * 小车订单管理查看页面
     * @param model
     * @return
     */
    @RequestMapping("/carOrder/open")
    public String carOrderOpen(Model model,Integer taskOrderId){
        String taskOrderId1 = Integer.toString(taskOrderId);
        model.addAttribute("taskOrderId",taskOrderId1);


        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }
        return "/carOrder/open";
    }

    /**
     * 小车订单指派人页面
     * @param model
     * @return
     */
    @RequestMapping("/carOrder/assign")
    public String carOrderAssign(Model model,Integer taskOrderId,String date ){
        String taskOrderId1 = Integer.toString(taskOrderId);
        model.addAttribute("taskOrderId",taskOrderId1);
        model.addAttribute("date",date);

        return "/carOrder/assign";
    }

    /**
     * 小车订单指派车页面
     * @param model
     * @return
     */
    @RequestMapping("/carOrder/assignCar")
    public String carOrderAssigncar(Model model,Integer taskOrderId,String date ){
        String taskOrderId1 = Integer.toString(taskOrderId);
        model.addAttribute("taskOrderId",taskOrderId1);
        model.addAttribute("date",date);

        return "/carOrder/assignCar";
    }






    /**
     * 货车订单管理列表页面
     * @param model
     * @return
     */
    @RequestMapping("/truckOrder/index")
    public String truckOrderIndex(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }
        return "/truckOrder/index";
    }

    /**
     * 货车订单管理查看页面
     * @param model
     * @return
     */
    @RequestMapping("/truckOrder/open")
    public String truckOrderOpen(Model model,Integer taskOrderId){
        String taskOrderId1 = Integer.toString(taskOrderId);
        model.addAttribute("taskOrderId",taskOrderId1);
        return "/truckOrder/open";
    }

    /**
     * 货车订单指派司机页面
     * @param model
     * @return
     */
    @RequestMapping("/truckOrder/assign")
    public String truckOrderAssign(Model model,Integer taskOrderId,String date ){
        String taskOrderId1 = Integer.toString(taskOrderId);
        model.addAttribute("taskOrderId",taskOrderId1);
        model.addAttribute("date",date);

        return "/truckOrder/assign";
    }




    /**
     * 任务订单列表页面
     * @param model
     * @return
     */
    @RequestMapping("/taskOrder/index")
    public String taskOrderIndex(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }
        return "/taskOrder/index";
    }

    /**
     * 任务订单查看页面
     * @param model
     * @return
     */
    @RequestMapping("/taskOrder/open")
    public String taskOrderOpen(Model model,Integer taskOrderId){


        return "/taskOrder/open";
    }



    /**
     * 小车订单统计列表页面
     * @param model
     * @return
     */
    @RequestMapping("/carOrderStatistics/index")
    public String carOrderStatisticsIndex(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }
        return "/carOrderStatistics/index";
    }



    /**
     * 货车订单统计列表页面
     * @param model
     * @return
     */
    @RequestMapping("/truckOrderStatistics/index")
    public String truckOrderStatisticsIndex(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }
        return "/truckOrderStatistics/index";
    }






    /**
     * 财务流水页面
     * @param model
     * @return
     */
    @RequestMapping("/financialFlow/index")
    public String financialFlowIndex(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }
        return "/financialFlow/index";
    }




    /**
     * 工资发放列表页面
     * @param model
     * @return
     */
    @RequestMapping("/wages/index")
    public String wagesIndex(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }
        return "/wages/index";
    }

    /**
     * 工资发放查看页面
     * @param model
     * @return
     */
    @RequestMapping("/wages/open")
    public String wagesOpen(Model model,Integer payGiveId){
        String payGiveId1 = Integer.toString(payGiveId);
        model.addAttribute("payGiveId",payGiveId1);
        return "/wages/open";
    }

    /**
     * 工资发放编辑页面
     * @param model
     * @return
     */
    @RequestMapping("/wages/edit")
    public String wagesEdit(Model model,Integer payGiveId){

        String payGiveId1 = Integer.toString(payGiveId);
        model.addAttribute("payGiveId",payGiveId1);
        return "/wages/edit";
    }

    /**
     * 工资发放添加页面
     * @param model
     * @return
     */
    @RequestMapping("/wages/add")
    public String wagesAdd(Model model){

        return "/wages/add";
    }



    /**
     * 提交申请页面
     * @param model
     * @return
     */
    @RequestMapping("/cashWithdrawal/index")
    public String cashWithdrawalIndex(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }
        return "/cashWithdrawal/index";
    }



    /**
     * 国内物流地址参数设置列表页面
     * @param model
     * @return
     */
    @RequestMapping("/domesticAddress/index")
    public String domesticAddressIndex(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }
        return "/domesticAddress/index";
    }

    /**
     * 国内物流地址参数设置添加页面
     * @param model
     * @return
     */
    @RequestMapping("/domesticAddress/add")
    public String domesticAddressAdd(Model model){

        return "/domesticAddress/add";
    }

    /**
     *  国内物流地址参数设置编辑页面
     * @param model
     * @return
     */
    @RequestMapping("/domesticAddress/edit")
    public String domesticAddressEdit(Model model,Integer chinaAreaId){
        String chinaAreaId1 = Integer.toString(chinaAreaId);
        model.addAttribute("chinaAreaId",chinaAreaId1);
        return "domesticAddress/edit";
    }





    /**
     * 新加坡邮编管理列表页面
     * @param model
     * @return
     */
    @RequestMapping("/singaporeZipCode/index")
    public String singaporeZipCodeIndex(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }
        return "/singaporeZipCode/index";
    }

    /**
     * 新加坡邮编管理添加页面
     * @param model
     * @return
     */
    @RequestMapping("/singaporeZipCode/add")
    public String singaporeZipCodeAdd(Model model){
        return "/singaporeZipCode/add";
    }

    /**
     * 新加坡邮编管理编辑页面
     * @param model
     * @return
     */
    @RequestMapping("/singaporeZipCode/edit")
    public String singaporeZipCodeEdit(Model model,Integer saBuildingId){
        String saBuildingId1 = Integer.toString(saBuildingId);
        model.addAttribute("saBuildingId",saBuildingId1);
        return "singaporeZipCode/edit";
    }









    /**
     * 新加坡自定义区域列表页面
     * @param model
     * @return
     */
    @RequestMapping("/singaporeRegion/index")
    public String singaporeRegionIndex(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }
        return "/singaporeRegion/index";
    }

    /**
     * 新加坡自定义区域编辑页面
     * @param model
     * @return
     */
    @RequestMapping("/singaporeRegion/edit")
    public String singaporeRegionEdit(Model model,Integer singaporeAreaId){

        String singaporeAreaId1 = Integer.toString(singaporeAreaId);
        model.addAttribute("singaporeAreaId",singaporeAreaId);
        return "/singaporeRegion/edit";
    }
    /**
     * 新加坡自定义区域添加页面
     * @param model
     * @return
     */
    @RequestMapping("/singaporeRegion/add")
    public String singaporeRegionAdd(Model model){
        return "/singaporeRegion/add";
    }




    /**
     * 小车集结地管理列表页面
     * @param model
     * @return
     */
    @RequestMapping("/carRendezvous/index")
    public String carRendezvousIndex(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }
        return "/carRendezvous/index";
    }

    /**
     * 小车集结地管理添加页面
     * @param model
     * @return
     */
    @RequestMapping("/carRendezvous/add")
    public String carRendezvousAdd(Model model){
        return "/carRendezvous/add";
    }

    /**
     * 小车集结地管理编辑页面
     * @param model
     * @return
     */
    @RequestMapping("/carRendezvous/edit")
    public String carRendezvousEdit(Model model,Integer rallyPointId){
        String rallyPointId1 = Integer.toString(rallyPointId);
        model.addAttribute("rallyPointId",rallyPointId1);
        return "/carRendezvous/edit";
    }




    /**
     * 出入境物流途径参数设置页面
     * @param model
     * @return
     */
    @RequestMapping("/exitAndEntry/index")
    public String exitAndEntryIndex(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }
        return "exitAndEntry/index";
    }

    /**
     * 出入境物流途径参数设置新增页面
     * @param model
     * @return
     */
    @RequestMapping("/exitAndEntry/add")
    public String exitAndEntryAdd(Model model){
        return "exitAndEntry/add";
    }

    /**
     * 出入境物流途径参数设置编辑页面
     * @param model
     * @return
     */
    @RequestMapping("/exitAndEntry/edit")
    public String exitAndEntryEdit(Model model,Integer eaeId){
        String eaeId1 = Integer.toString(eaeId);
        model.addAttribute("eaeId",eaeId1);
        return "exitAndEntry/edit";
    }






    /**
     * 文案设置页面
     * @param model
     * @return
     */
    @RequestMapping("/copybookSettings/index")
    public String copybookSettingsIndex(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }
        return "copybookSettings/index";
    }

    /**
     * 文案设置新增页面
     * @param model
     * @return
     */
    @RequestMapping("/copybookSettings/add")
    public String copybookSettingsAdd(Model model){
        return "copybookSettings/add";
    }

    /**
     * 文案设置编辑页面
     * @param model
     * @return
     */
    @RequestMapping("/copybookSettings/edit")
    public String copybookSettingsEdit(Model model,Integer copyWriterId){
        String copyWriterId1 = Integer.toString(copyWriterId);
        model.addAttribute("copyWriterId",copyWriterId1);
        return "copybookSettings/edit";
    }


    /**
     * 文案设置查看页面
     * @param model
     * @return
     */
    @RequestMapping("/copybookSettings/open")
    public String copybookSettingsOpen(Model model,Integer copyWriterId){

        String copyWriterId1 = Integer.toString(copyWriterId);
        model.addAttribute("copyWriterId",copyWriterId1);
        return "copybookSettings/open";
    }





    /**
     * 数据字典页面
     * @param model
     * @return
     */
    @RequestMapping("/dataDictionary/index")
    public String dataDictionaryIndex(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }
        return "dataDictionary/index";
    }

    /**
     * 数据字典新增页面
     * @param model
     * @return
     */
    @RequestMapping("/dataDictionary/add")
    public String dataDictionaryAdd(Model model){
        return "dataDictionary/add";
    }

    /**
     * 数据字典编辑页面
     * @param model
     * @return
     */
    @RequestMapping("/dataDictionary/edit")
    public String dataDictionaryEdit(Model model,Integer id){
        String id1 = Integer.toString(id);
        model.addAttribute("id",id1);
        return "dataDictionary/edit";
    }



    /**
     * 系统参数设置页面
     * @param model
     * @return
     */
    @RequestMapping("/systemParameterSetting/index")
    public String systemParameterSettingIndex(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }
        return "systemParameterSetting/index";
    }



    /**
     * 没有权限页面
     * @param model
     * @return
     */
    @RequestMapping("/noAuthority/index")
    public String noAuthorityIndex(Model model){

        return "noAuthority/index";
    }

    /**
     * 货单管理
     * @param model
     * @return
     */
    @RequestMapping("/ManifestManagement/index")
    public String ManifestManagementIndex(Model model){
        AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AdminPermission> list = user.getPermissions();
        for (AdminPermission adminPermission:list){
            model.addAttribute(adminPermission.getPermissions(),adminPermission.getUrl());
        }
        return "ManifestManagement/index";
    }


    /**
     * 货单管理查看
     * @param model
     * @return
     */
    @RequestMapping("/ManifestManagement/open")
    public String ManifestManagementOpen(Model model,Integer formId,String type){
        String formId1 = Integer.toString(formId);
        model.addAttribute("formId",formId1);

        model.addAttribute("type",type);
        return "ManifestManagement/open";
    }

}
