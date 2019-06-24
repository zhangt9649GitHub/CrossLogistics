package com.siruiman.crosslogistics.controller;

import com.github.wxpay.sdk.WXPayUtil;
import com.siruiman.crosslogistics.enums.HtCode;
import com.siruiman.crosslogistics.enums.ZwCode;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.model.dto.TruckDriverOrder;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.*;
import com.siruiman.crosslogistics.util.DateUtil;
import com.siruiman.crosslogistics.util.RandomCodeUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * @author 张占伟
 * @date 2018/12/27 19:53
 */
@RestController
@Api(value="PDA-BAG",description = "PDA货袋管理-API",tags={"PDA货袋管理"})
@RequestMapping("/pda/bag")
public class PDABagController {


    @Autowired
    private TruckDriverOrderService truckDriverOrderService;


    @Autowired
    private AppUserService appUserService;

    @Autowired
    private GoodsDetailsService goodsDetailsService;
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private BagSerivce bagSerivce;
    @Autowired
    private StaffService staffService;

    @Autowired
    private LogisticInfoService logisticInfoService;

    @Autowired
    private BizdictionaryService bizdictionaryService;

    @Autowired
    private GoodsPrintFormService goodsPrintFormService;


    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private WarehousePositionService warehousePositionService;

    @Autowired
    private CarService carService;

    @Autowired
    private TruckService truckService;

    @Autowired
    private SingaporeAreaService singaporeAreaService;

    @Autowired
    private RallyPointService rallyPointService;

    @Autowired
    private AppUserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private GoodsPrintFormMidService goodsPrintFormMidService;

    @ApiOperation(value = "货袋打包", nickname = "packingGoods", notes = "货物放入货袋内", tags = {"@占伟"})
    @RequestMapping(value = "packingGoods", method = RequestMethod.POST)
    public CommonResponse packingGoods(@Validated @RequestParam int staffId, @Validated @RequestParam int bagId, @Validated @RequestParam int goodsId) {
        CommonResponse response = new CommonResponse();

        //              查出货物是否已经装袋
        try {
            int bId = goodsService.selectBagIdByGdId(goodsId);
            if (bId != 0) {
//                货物已经装袋
                response.setCode(ZwCode.ERROR_GOODS_PACKGING.getCode());
                response.setMessage(ZwCode.ERROR_GOODS_PACKGING.getInfo());
                return response;
            }
        } catch (Exception e) {

        }
        try {
            GoodsDetails goodsDetails = goodsDetailsService.selectGoodsDetailById(goodsId);
            Bag bag  =bagSerivce.selectBagDetailsById(bagId);
            if(goodsDetails!=null&&goodsDetails.getSingaporeArea().getSingaporeAreaId()!=null&&bag!=null&&bag.getSingaporeAreaId()!=null){
                if(goodsDetails.getSingaporeArea().getSingaporeAreaId() != bag.getSingaporeAreaId()){
                    response.setMessage(HtCode.FAIL_EQUAL_SINGAPOREAREAID.getInfo());
                    response.setCode(HtCode.FAIL_EQUAL_SINGAPOREAREAID.getCode());
                    return  response;
                }
            }else{
                response.setMessage(HtCode.FAIL_NULL_SINGAPOREAREAID.getInfo());
                response.setCode(HtCode.FAIL_NULL_SINGAPOREAREAID.getCode());
                return  response;
            }
            if(goodsDetails!=null&&goodsDetails.getRallyPoint().getRallyPointId()!=null&&bag!=null&&bag.getRallyPointId()!=null){
                if(goodsDetails.getRallyPoint().getRallyPointId() != bag.getRallyPointId()){
                    response.setMessage(HtCode.FAIL_EQUAL_RALLYPOINTID.getInfo());
                    response.setCode(HtCode.FAIL_EQUAL_RALLYPOINTID.getCode());
                    return  response;
                }
            }else{
                response.setMessage(HtCode.FAIL_NULL_RALLYPOINTID.getInfo());
                response.setCode(HtCode.FAIL_NULL_RALLYPOINTID.getCode());
                return  response;
            }
            Goods goods = new Goods();
            goods.setGoodsId(goodsId);
            goods.setBagId(bagId);
//                添加修改时间
            goods.setUpdateTime(new Date());
//              货物与货袋绑定
            goodsService.putGoodsInBag(goods);

//                记录表记录
//                根据操作人查出操作人
            Staff staff = staffService.selectStaffDetail(staffId);
            LogisticInfo info = new LogisticInfo();
//                货袋打包记录到物流进程表
            info.setCreateTime(new Date());
            info.setGoodsId(goodsId);
            info.setStaffId(staffId);
            info.setOperateName(staff.getStaffName());
            info.setOperateComment("货物打包成功");
            info.setOperateType("货物打包成功");
            if(staff!=null&&staff.getAttribution()!=null&&staff.getAttribution().equals("新加坡")){
                info.setOperateResult(staff.getAttribution()+"发货仓：倉庫打包成功，待運輸");
            }else if(staff!=null&&staff.getAttribution()!=null){
                info.setOperateResult(staff.getAttribution()+"：倉庫打包成功，待運輸");
            }
            logisticInfoService.insertLogisticInfo(info);
            response.setMessage(ZwCode.SUCCESS_PACKAGING.getInfo());
            response.setCode(ZwCode.SUCCESS_PACKAGING.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(ZwCode.FAIL_PACKAGING.getInfo());
            response.setCode(ZwCode.FAIL_PACKAGING.getCode());
        }
        return response;
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "singaporeAreaId", value = "新加坡自定义区域id", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "rallyPointId", value = "集结点id", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "staffId", value = "员工id", required = true, dataType = "int", paramType = "query"),
    })
    @ApiOperation(value = "更换货袋", nickname = "creatNewBag", notes = "创建新货袋返回货袋编号等基础信息", tags = {"@占伟"})
    @RequestMapping(value = "creatNewBag", method = RequestMethod.POST)
    public CommonResponse creatNewBag(@Validated @RequestParam int singaporeAreaId, @Validated @RequestParam int rallyPointId,
                                      @Validated @RequestParam int staffId) {
        CommonResponse response = new CommonResponse();
        Bag bag = new Bag();
        boolean flag = true;
        String bagNumber = null;
        while (flag) {
//         检验货袋编号是否重复 重复就再次生成一个
            bagNumber = "06" + RandomCodeUtil.getRandomCode();
            try {
                Bag results = bagSerivce.selectBagByNumber(bagNumber);
                if (results == null) {
                    flag = false;
                }
            } catch (Exception e) {

            }
        }
        bag.setBagNumber(bagNumber);
//        根据货袋在数据字典上的id查出货袋的长宽高载重量
        try {
            Staff staff = staffService.selectStaffDetail(staffId);
            List<Bizdictionary> bizdictionaries = bizdictionaryService.selectBizdictionaryByParentId(153);
            for (Bizdictionary bizdictionary : bizdictionaries) {
                String bizName = bizdictionary.getBizName();
                Double value = Double.valueOf(bizdictionary.getValue());
                if ("货袋长度".equals(bizName)) {
                    bag.setLength(value);
                }
                if ("货袋宽度".equals(bizName)) {
                    bag.setWidth(value);
                }
                if ("货袋高度".equals(bizName)) {
                    bag.setHigh(value);
                }
                if ("货袋载重量".equals(bizName)) {
                    bag.setLoad(value);
                }
            }
            bag.setSingaporeAreaId(singaporeAreaId);
            bag.setRallyPointId(rallyPointId);
            bag.setCreateTime(DateUtil.getDateTime());
            bag.setStaffId(staffId);
            bag.setWarehouseId(staff.getWarehouseId());
            bagSerivce.insertBag(bag);
            int bagId = bagSerivce.selectBagIdByBagNumber(bagNumber);
            LogisticInfo info = new LogisticInfo();
            info.setCreateTime(new Date());
            info.setOperateName(staff.getStaffName());
            info.setOperateComment("创建一个新货袋");
            info.setOperateType("创建货袋");
            info.setOperateResult("创建货袋成功");
            info.setStaffId(staffId);
            info.setBagId(bagId);
            logisticInfoService.insertLogisticInfo(info);
            response.setCode(ZwCode.SUCCESS_CREATBAG.getCode());
            response.setMessage(ZwCode.SUCCESS_CREATBAG.getInfo());
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(ZwCode.FAIL_CREATBAG.getCode());
            response.setMessage(ZwCode.FAIL_CREATBAG.getInfo());
        }
        return response;
    }


    /*@ApiImplicitParams({
            @ApiImplicitParam(name = "bagId", value = "货袋id", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "staffId", value = "操作人id", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "wpNumber", value = "仓位编号", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "warehouseNumber", value = "仓库编号", required = true, dataType = "String", paramType = "query"),
    })
    @ApiOperation(value = "货袋入中国库", nickname = "bagStorageCNWarehouse", notes = "货袋入中国仓库", tags = {"@占伟"})

    @RequestMapping(value = "bagStorageCNWarehouse", method = RequestMethod.POST)
    public CommonResponse bagStorageCNWarehouse(String wpNumber, String warehouseNumber, int bagId, int staffId) {
        CommonResponse response = new CommonResponse();
        try {
            //仓位id
            int wpId = warehousePositionService.selectWpIdByWpNumber(wpNumber);
            //仓库id
            int warehouseId = warehouseService.selectWarehouseIdByWarehouseNumber(warehouseNumber);
//               1,根据货袋编号查出货袋里的货物快递单号
            List<Goods> goodsList = goodsService.selectByBagId(bagId);
            for (int i = 0; i < goodsList.size(); i++) {
//                根据货物快递单号修改货物在中国仓库id仓位id
                goodsService.updateGoodsInToWarehouse(warehouseId, wpId, goodsList.get(i).getDeliveryNumber());
            }
//            2.修改货袋在中国仓库id仓位id
            bagSerivce.updateBagInitWarehouse(warehouseId, wpId, bagId);
//            3.记录操作人信息
            Staff staff = staffService.selectStaffDetail(staffId);
            LogisticInfo info = new LogisticInfo();
            info.setCreateTime(new Date());
            info.setOperateName(staff.getStaffName());
            info.setOperateComment("货袋已入中国仓库:" + warehouseNumber + "仓位" + wpNumber);
            info.setOperateType("货袋入库");
            info.setOperateResult("货袋入库成功");
            info.setStaffId(staffId);
            info.setBagId(bagId);
            logisticInfoService.insertLogisticInfo(info);
            response.setMessage(ZwCode.SUCCESS_STORAGE.getInfo());
            response.setCode(ZwCode.SUCCESS_STORAGE.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(ZwCode.FAIL_STORAGE.getInfo());
            response.setCode(ZwCode.FAIL_STORAGE.getCode());
        }
        return response;
    }
*/

    @ApiImplicitParams({
            @ApiImplicitParam(name = "bagId", value = "货袋id", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "staffId", value = "操作人id", required = true, dataType = "int", paramType = "query")
    })

    @ApiOperation(value = "货袋出库", nickname = "bagOutBoundChina", notes = "根据货袋id将所有货物出中国仓", tags = {"@占伟"})
    @RequestMapping(value = "bagOutBoundChina", method = RequestMethod.POST)
    public CommonResponse bagOutBoundChina(int staffId, int bagId) {
        CommonResponse response = new CommonResponse();
        Bag bag;
//        查出货袋状态 在打包状态下才能进行出库操作
        try {
            bag = bagSerivce.selectBagStateById(bagId);
            if (bag.getState() != 2) {
                response.setCode(ZwCode.ERROR_BAG_OPERATION.getCode());
                response.setMessage(ZwCode.ERROR_BAG_OPERATION.getInfo());
                return response;
            }
        } catch (Exception e) {
            response.setCode(ZwCode.ERROR_BAG_ID.getCode());
            response.setMessage(ZwCode.ERROR_BAG_ID.getInfo());
            return response;
        }
//      1.  根据货袋编号查出所有货物id
        try {
            List<Goods> list = goodsService.selectByBagId(bagId);
            Staff staff = staffService.selectStaffDetail(staffId);
//      2.  再根据货物编号修改货物状态
            for (Goods goods : list) {
//                记录货物操作进程
                LogisticInfo goodsInfo = new LogisticInfo();
                goodsInfo.setCreateTime(new Date());
                goodsInfo.setGoodsId(goods.getGoodsId());
                goodsInfo.setStaffId(staffId);
                goodsInfo.setOperateName(staff.getStaffName());
                goodsInfo.setOperateType("货物出库成功");
                if(staff!=null&&staff.getAttribution()!=null&&staff.getAttribution().equals("新加坡")){
                    goodsInfo.setOperateResult(staff.getAttribution()+"发货仓已操作出庫");
                    goodsInfo.setOperateComment("货物出"+staff.getAttribution()+"发货仓");
                }else if(staff!=null&&staff.getAttribution()!=null){
                    goodsInfo.setOperateResult(staff.getAttribution()+"仓库已操作出庫");
                    goodsInfo.setOperateComment("货物出国内仓库");
                }
                logisticInfoService.insertLogisticInfo(goodsInfo);
            }
//      3.  修改货袋状态为出仓
            bag = new Bag();
            bag.setBagId(bagId);
            bag.setState((short) 4);
            bag.setUpdateTime(DateUtil.getDateTime());
            bagSerivce.updateBagState(bag);
//        4.保存操作人记录
//                记录货袋操作进程
            LogisticInfo info = new LogisticInfo();
            info.setCreateTime(new Date());
            info.setOperateName(staff.getStaffName());
            info.setOperateComment("货袋出库");
            info.setOperateType("货袋出库");
            info.setOperateResult("货袋出库成功");
            info.setStaffId(staffId);
            info.setBagId(bagId);
            logisticInfoService.insertLogisticInfo(info);
            response.setMessage(ZwCode.SUCCESS_OUTBOUND.getInfo());
            response.setCode(ZwCode.SUCCESS_OUTBOUND.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(ZwCode.FAIL_OUTBOUND.getInfo());
            response.setCode(ZwCode.FAIL_OUTBOUND.getCode());
        }
        return response;
    }


    /**
     * 货袋送出
     *
     * @param bagId
     * @param staffId
     * @return
     */
    @ApiOperation(value = "货袋送出", nickname = "sendOutBag", notes = "货袋送出", tags = {"@占伟"})
    @RequestMapping(value = "sendOutBag", method = RequestMethod.POST)
    public CommonResponse sendOutBag(@Validated @RequestParam int bagId, @Validated @RequestParam int staffId) {
        CommonResponse response = new CommonResponse();
//            查出货袋状态是否已经送出
        try {
            Bag bag = bagSerivce.selectBagStateById(bagId);
//                如果货袋状态是已经送出
            if (bag.getState() == 5) {
                response.setCode(ZwCode.ERROR_SEND_OUT_BAG.getCode());
                response.setMessage(ZwCode.ERROR_SEND_OUT_BAG.getInfo());
                return response;
            }
//
            if (bag.getState() != 4) {
                response.setCode(ZwCode.ERROR_BAG_OPERATION.getCode());
                response.setMessage(ZwCode.ERROR_BAG_OPERATION.getInfo());
                return response;
            } else {
//                    1.  根据货袋id查出所有货物id
                List<Goods> list = goodsService.selectByBagId(bagId);
//      2.  再根据货物编号修改货物状态
                Staff staff = staffService.selectStaffDetail(staffId);
                GoodsDetails details = new GoodsDetails();
                for (Goods goods : list) {
                    //                记录货物操作进程
                    LogisticInfo goodsInfo = new LogisticInfo();
                    goodsInfo.setCreateTime(new Date());
                    goodsInfo.setGoodsId(goods.getGoodsId());
                    goodsInfo.setStaffId(staffId);
                    goodsInfo.setOperateName(staff.getStaffName());
                    goodsInfo.setOperateType("货物送出");
                    if(staff!=null&&staff.getAttribution()!=null&&staff.getAttribution().equals("新加坡")){
                        goodsInfo.setOperateResult(staff.getAttribution()+"发货仓已操作送出，运输中");
                        goodsInfo.setOperateComment("货物送出"+staff.getAttribution()+"发货仓");
                    }else if(staff!=null&&staff.getAttribution()!=null){
                        goodsInfo.setOperateResult(staff.getAttribution()+"仓库已操作送出，运输中");
                        goodsInfo.setOperateComment("货物送出"+staff.getAttribution()+"仓库");
                    }
                    logisticInfoService.insertLogisticInfo(goodsInfo);
                    if ("转运订单".equals(goods.getFrom())) {
                        details.setGoodState("转运中");
                        details.setGoodsId(goods.getGoodsId());
                        details.setUpdateTime(new Date());
                        goodsDetailsService.updateGoodsState(details);
                    }
                }
//                  3.  修改货袋状态为送出
                bag.setBagId(bagId);
                bag.setState((short) 5);
                bagSerivce.updateBagState(bag);
//                  4.保存货袋操作人记录
                LogisticInfo info = new LogisticInfo();
                info.setCreateTime(new Date());
                info.setOperateName(staff.getStaffName());
                if(staff!=null&&staff.getAttribution()!=null&&staff.getAttribution().equals("新加坡")){
                    info.setOperateComment("货物送出"+staff.getAttribution()+"发货仓");
                }else if(staff!=null&&staff.getAttribution()!=null){
                    info.setOperateComment("货物送出"+staff.getAttribution()+"仓库");
                }
                info.setOperateType("货袋送出");
                info.setOperateResult("货袋送出成功");
                info.setStaffId(staffId);
                info.setBagId(bagId);
                logisticInfoService.insertLogisticInfo(info);
                response.setMessage(ZwCode.SUCCESS_SEND_OUT_BAG.getInfo());
                response.setCode(ZwCode.SUCCESS_SEND_OUT_BAG.getCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(ZwCode.FAIL_SEND_OUT_BAG.getInfo());
            response.setCode(ZwCode.FAIL_SEND_OUT_BAG.getCode());
        }

        return response;
    }


    @ApiImplicitParam(name = "bagNumber", value = "货袋编号", required = true, dataType = "String", paramType = "query")
    @ApiOperation(value = "货袋查看", nickname = "getBagDetails", notes = "根据货袋编号查看货袋详情", tags = {"@占伟"})
    @RequestMapping(value = "getBagDetails", method = RequestMethod.GET)
    public CommonResponse getBagDetailsBybagNumber(String bagNumber) {
        CommonResponse response = new CommonResponse();
        try {
            Bag bag = bagSerivce.selectBagDetailedByBagNumber(bagNumber);
            if (bag == null) {
                response.setMessage(ZwCode.ERROR_BAG_NOT_EXIT.getInfo());
                response.setCode(ZwCode.ERROR_BAG_NOT_EXIT.getCode());
            } else {
                LogisticInfo info = logisticInfoService.selectBagInfoByBagId(bag.getBagId());
                bag.setInfo(info);
                response.setData(bag);
                response.setMessage(ZwCode.SUCCESS_GET.getInfo());
                response.setCode(ZwCode.SUCCESS_GET.getCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(ZwCode.FAIL_GET.getInfo());
            response.setCode(ZwCode.FAIL_GET.getCode());
        }
        return response;
    }

    @ApiImplicitParam(name = "bagId", value = "货袋id", required = true, dataType = "int", paramType = "query")
    @ApiOperation(value = "货袋查看", nickname = "getGoodsDetailsByBagId", notes = "根据货袋编号查看货袋详情包括货袋里的货物详细信息", tags = {"@占伟"})
    @RequestMapping(value = "getGoodsDetailsByBagId", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse getGoodsDetailsByBagId(int bagId) {
        CommonResponse response = new CommonResponse();
        try {
            List<GoodsDetails> list = carService.selectGoodsDetailsByBagId(bagId);
            response.setData(list);
            response.setMessage(ZwCode.SUCCESS_GET.getInfo());
            response.setCode(ZwCode.SUCCESS_GET.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(ZwCode.FAIL_GET.getInfo());
            response.setCode(ZwCode.FAIL_GET.getCode());
        }
        return response;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "bagId", value = "货袋id", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "staffId", value = "操作人id", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "wpId", value = "仓位id", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "warehouseId", value = "仓库id", required = true, dataType = "String", paramType = "query"),
    })
    @ApiOperation(value = "货袋入新加坡库", nickname = "bagStorageSGWarehouse", notes = "货袋入新加坡库", tags = {"@占伟"})
    @RequestMapping(value = "bagStorageSGWarehouse", method = RequestMethod.POST)
    public CommonResponse bagStorageSGWarehouse(@Validated @RequestParam int wpId, @Validated @RequestParam
            int warehouseId, @Validated @RequestParam int bagId, int staffId) {


        CommonResponse response = new CommonResponse();
//            查出货袋是否已经入库
        Bag bag = bagSerivce.selectBagStateById(bagId);
//        如果状态为已入库提示 已入库
        if (bag.getState() == 3) {
            response.setCode(ZwCode.ERROR_BAG_PUT_IN_STORAGE.getCode());
            response.setMessage(ZwCode.ERROR_BAG_PUT_IN_STORAGE.getInfo());
            return response;
        }
//        如果状态不为 货袋已送出提示 正确的货袋操作
        if (bag.getState() != 5) {
            response.setCode(ZwCode.ERROR_BAG_OPERATION.getCode());
            response.setMessage(ZwCode.ERROR_BAG_OPERATION.getInfo());
            return response;
        }
        try {
//               1,根据货袋id查出货袋里的货物快递单号
            Staff staff = staffService.selectStaffDetail(staffId);
            List<Goods> goodsList = goodsService.selectByBagId(bagId);
            for (Goods goods : goodsList) {
//                根据货物快递单号修改货物在新加坡仓库id仓位id
                goodsService.updateGoodsInOutWarehouse(warehouseId, wpId, goods.getDeliveryNumber());
                //                记录货物操作进程
                LogisticInfo goodsInfo = new LogisticInfo();
                goodsInfo.setCreateTime(new Date());
                goodsInfo.setGoodsId(goods.getGoodsId());
                goodsInfo.setStaffId(staffId);
                goodsInfo.setOperateName(staff.getStaffName());
                if(staff!=null&&staff.getAttribution()!=null&&staff.getAttribution().equals("新加坡")){
                    goodsInfo.setOperateResult("貨物已到達新加坡收货仓");
                    goodsInfo.setOperateComment("货物入新加坡收货仓");
                }else if(staff!=null&&staff.getAttribution()!=null){
                    goodsInfo.setOperateResult("貨物已到達新加坡倉庫");
                    goodsInfo.setOperateComment("货物入新加坡仓库");
                }
                goodsInfo.setOperateType("货物入新加坡仓库");
                logisticInfoService.insertLogisticInfo(goodsInfo);
            }
//        2.修改货袋
            bagSerivce.updateBagLastWarehouse(warehouseId, wpId, bagId);
//        3.记录货袋操作人信息
            LogisticInfo info = new LogisticInfo();
            info.setCreateTime(new Date());
            info.setOperateName(staff.getStaffName());
            info.setOperateComment("货袋入新加坡仓库");
            info.setOperateType("货袋入新加坡仓库");
            info.setOperateResult("貨袋已到達新加坡倉庫");
            info.setBagId(bagId);
            info.setStaffId(staffId);
            logisticInfoService.insertLogisticInfo(info);
            response.setMessage(ZwCode.SUCCESS_STORAGE.getInfo());
            response.setCode(ZwCode.SUCCESS_STORAGE.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(ZwCode.FAIL_STORAGE.getInfo());
            response.setCode(ZwCode.FAIL_STORAGE.getCode());
        }
        return response;

    }


    @ApiImplicitParam(name = "number", value = "大货车司机编号", required = true, dataType = "int", paramType = "query")
    @ApiOperation(value = "扫描司机二维码获取所抢订单路线及司机基础信息", nickname = "getTruckDriverOrder", notes = "扫描司机二维码获取所抢订单路线及司机基础信息", tags = {"@占伟"})
    @RequestMapping(value = "getTruckDriverOrder", method = RequestMethod.GET)
    public CommonResponse getTruckDriverOrder(@Validated @RequestParam String number) {
        CommonResponse response = new CommonResponse();
//        获取当天时间
        String date = DateUtil.getDate();
        int appUserId = 0;
        try {
             appUserId = userService.selectUIdByNumber(number);
        }catch (Exception e){
            response.setMessage(ZwCode.WARNING_NOT_USER.getInfo());
            response.setCode(ZwCode.WARNING_NOT_USER.getCode());
            return response;
        }

        try {
            TruckDriverOrder truckDriverOrder = truckDriverOrderService.selectByUserId(appUserId, date);
//          如果查出来的为空就发出警告当前司机没有订单
            if (truckDriverOrder == null) {
                response.setMessage(ZwCode.WARNING_USER_NOT_ORDER.getInfo());
                response.setCode(ZwCode.WARNING_USER_NOT_ORDER.getCode());
                return response;
            }
//            查出货袋编号货袋id
//           根据订单id查出集结点id
            List<Integer> rallyPointIds = truckDriverOrderService.selectRallyPointIds(truckDriverOrder.getTaskOrderId());
            List<Bag> bags = new ArrayList<>();
            for (Integer rallyPointId : rallyPointIds) {
                List<Bag> bag = bagSerivce.selectByRallyPointId(rallyPointId);
//                合并集合
                bags.addAll(bag);
            }
            if (bags.size() == 0) {
                response.setData(truckDriverOrder);
                response.setCode(ZwCode.NO_BAGS_IN_ORDER.getCode());
                response.setMessage(ZwCode.NO_BAGS_IN_ORDER.getInfo());
                return response;
            }
            truckDriverOrder.setBags(bags);
            response.setCode(ZwCode.SUCCESS_GET.getCode());
            response.setMessage(ZwCode.SUCCESS_GET.getInfo());
            response.setData(truckDriverOrder);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(ZwCode.FAIL_GET.getCode());
            response.setMessage(ZwCode.FAIL_GET.getInfo());
        }
        return response;
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "staffId", value = "操作人id", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "bagIds", value = "货袋ids(1,2,3)", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "appUserId", value = "司机id", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "orderType", value = "新增参数 订单类型（1正常 2货车送货）", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "orderNumber", value = "新增参数 订单编号", required = true, dataType = "string", paramType = "query"),
    })
    @ApiOperation(value = "货袋装车出库", nickname = "bagOutBoundSG", notes = "货袋装车出库", tags = {"@占伟"})
    @RequestMapping(value = "bagOutBoundSG", method = RequestMethod.POST)
//    流程新增货车司机直接配送添加form 表单 在绑定货物
    public CommonResponse bagOutBoundSG(@Validated @RequestParam String bagIds, @Validated @RequestParam int appUserId,
                                        @Validated @RequestParam  String orderNumber,  @Validated @RequestParam int orderType, @Validated @RequestParam int staffId) {
        CommonResponse response = new CommonResponse();

//        检查货袋是否已经出库
//        1.先绑定货袋与信息
//          查出司机所抢订单id
        try {
            Staff staff = staffService.selectStaffDetail(staffId);
//            查出大货车id
            int truckId = truckService.selectTruckIdByUId(appUserId);
            TruckDriverOrder user = truckDriverOrderService.getUserByUID(appUserId);
            String[] split = bagIds.split(",");
//            如果正常流程为货车直接配送
            if(orderType==2){
                GoodsPrintForm form = new GoodsPrintForm();
                form.setDriverName(user.getUserTrueName());
                form.setFormNumber(orderNumber);
                form.setPrintState(1);
                form.setAddTime(DateUtil.getDateTime());
//            添加直接配送打印货物编号
                goodsPrintFormService.add2(form);
//            查出刚才添加 打印货物单 id
                int formId = goodsPrintFormService.selectByNum(orderNumber);
                GoodsPrintFormMid goodsPrintFormMid = new GoodsPrintFormMid();
                goodsPrintFormMid.setFormId(formId);
                for (String id : split) {

                    if ("".equals(id) && id == null) {
                        break;
                    }
                    Integer bagId = Integer.valueOf(id);
//            更新货袋状态为出库装车

                    bagSerivce.updateBagInTruck(bagId, truckId);
//            根据货袋id查出货物

                    List<Goods> list = goodsService.selectByBagId(bagId);
//      2.  再根据货物编号修改货物状态

                    for (Goods goods : list) {
//                记录货物操作人
                        LogisticInfo goodsInfo = new LogisticInfo();
                        goodsInfo.setCreateTime(new Date());
                        goodsInfo.setGoodsId(goods.getGoodsId());
                        goodsInfo.setStaffId(staffId);
                        goodsInfo.setOperateName(staff.getStaffName());
                        goodsInfo.setOperateComment("货物装车出库");
                        goodsInfo.setOperateType("货物出库");
                        goodsInfo.setOperateResult("交付司機派送中");
                        goodsPrintFormMid.setGoodsId(goods.getGoodsId());
                        logisticInfoService.insertLogisticInfo(goodsInfo);
                        goodsPrintFormMidService.add(goodsPrintFormMid);
                        messageService.insertMessage(14, 0, 0, goods.getGoodsId(), 0, 0, 0);
                    }

//                记录货袋操作进程
                    LogisticInfo info = new LogisticInfo();
                    info.setCreateTime(new Date());
                    info.setOperateName(staff.getStaffName());
                    info.setOperateComment("货袋装车出库");
                    info.setOperateType("货袋出库");
                    info.setOperateResult("货袋装车出库成功");
                    info.setStaffId(staffId);
                    info.setBagId(bagId);
                    logisticInfoService.insertLogisticInfo(info);
                }

            }else {
//            绑定货袋与信息单

                for (String id : split) {

                    if ("".equals(id) && id == null) {
                    break;

                    }

                    Integer bagId = Integer.valueOf(id);

//            更新货袋状态为出库装车

                    bagSerivce.updateBagInTruck(bagId, truckId);
//            根据货袋id查出货物

                    List<Goods> list = goodsService.selectByBagId(bagId);
//      2.  再根据货物编号修改货物状态

                    for (Goods goods : list) {
//                记录货物操作人
                        LogisticInfo goodsInfo = new LogisticInfo();
                        goodsInfo.setCreateTime(new Date());
                        goodsInfo.setGoodsId(goods.getGoodsId());
                        goodsInfo.setStaffId(staffId);
                        goodsInfo.setOperateName(staff.getStaffName());
                        goodsInfo.setOperateComment("货物装车出库");
                        goodsInfo.setOperateType("货物出库");
                        goodsInfo.setOperateResult("交付司機派送中");
                        logisticInfoService.insertLogisticInfo(goodsInfo);

                    }

//                记录货袋操作进程
                    LogisticInfo info = new LogisticInfo();
                    info.setCreateTime(new Date());
                    info.setOperateName(staff.getStaffName());
                    info.setOperateComment("货袋装车出库");
                    info.setOperateType("货袋出库");
                    info.setOperateResult("货袋装车出库成功");
                    info.setStaffId(staffId);
                    info.setBagId(bagId);
                    logisticInfoService.insertLogisticInfo(info);
                }

            }
            response.setCode(ZwCode.SUCCESS_OUTBOUND.getCode());
            response.setMessage(ZwCode.SUCCESS_OUTBOUND.getInfo());
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(ZwCode.FAIL_OUTBOUND.getCode());
            response.setMessage(ZwCode.FAIL_OUTBOUND.getInfo());
        }
        return response;
    }


    @ApiOperation(value = "货袋修改打印状态", notes = "修改货袋打印状态", tags = {"@占伟"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bagId", value = "货袋id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "printState", value = "1,0", required = true, paramType = "query", dataType = "int")
    })
    @RequestMapping(value = "/updateBagPrint", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse updateBagPrint(@RequestParam(defaultValue = "0") int bagId, @RequestParam(defaultValue = "0") int printState) {
        CommonResponse response = new CommonResponse();
        if (bagId == 0) {
            response.setCode(0);
            response.setMessage(ZwCode.SUCCESS_EDIT.getInfo());
        } else {
            try {
                bagSerivce.updateBagPrintState(bagId, printState);
                response.setCode(0);
                response.setMessage(ZwCode.SUCCESS_EDIT.getInfo());
            } catch (Exception e) {
                e.printStackTrace();
                response.setCode(-1);
                response.setMessage(ZwCode.FAIL_EDIT.getInfo());
            }
        }
        return response;
    }

    @ApiOperation(value = "获取打印货袋信息", notes = "获取打印货袋信息", nickname = "getBagPrint", tags = {"@占伟"})
    @RequestMapping(value = "/getBagPrint", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse getBagPrint() {
        CommonResponse response = new CommonResponse();
        try {
            Bag bag = bagSerivce.selectBagPrint();
            response.setData(bag);
            response.setCode(0);
            response.setMessage(ZwCode.SUCCESS_GET.getInfo());
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMessage(ZwCode.FAIL_GET.getInfo());
        }
        return response;
    }


    @ApiOperation(value = "下拉选所属集结点", notes = "下拉选所属集结点", nickname = "getRallyPointList", tags = {"@占伟"})
    @RequestMapping(value = "/getRallyPointList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse getRallyPointList(int singaporeAreaId) {
        CommonResponse response = new CommonResponse();
        try {
            List<RallyPoint> list = rallyPointService.selectBySGId(singaporeAreaId);
            response.setMessage(ZwCode.SUCCESS_GET.getInfo());
            response.setCode(ZwCode.SUCCESS_GET.getCode());
            response.setData(list);
        } catch (Exception e) {
            response.setMessage(ZwCode.FAIL_GET.getInfo());
            response.setCode(ZwCode.FAIL_GET.getCode());
        }
        return response;
    }

    @ApiOperation(value = "下拉选所属新加坡自定义区域", notes = "下拉选所属新加坡自定义区域", nickname = "getSGAreaList", tags = {"@占伟"})
    @RequestMapping(value = "/getSGAreaList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse getSGAreaList() {
        CommonResponse response = new CommonResponse();
        try {
            List<SingaporeArea> list = singaporeAreaService.selectSGAreaList();
            response.setMessage(ZwCode.SUCCESS_GET.getInfo());
            response.setCode(ZwCode.SUCCESS_GET.getCode());
            response.setData(list);
        } catch (Exception e) {
            response.setMessage(ZwCode.FAIL_GET.getInfo());
            response.setCode(ZwCode.FAIL_GET.getCode());
        }
        return response;
    }

    @ApiOperation(value = "下拉选仓库名", notes = "下拉选仓库名", nickname = "getWarehouseList", tags = {"@占伟"})
    @RequestMapping(value = "/getWarehouseList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse getWarehouseList() {
        CommonResponse response = new CommonResponse();
        try {
            List<Warehouse> list = warehouseService.selectAllNameAndId();
            response.setMessage(ZwCode.SUCCESS_GET.getInfo());
            response.setCode(ZwCode.SUCCESS_GET.getCode());
            response.setData(list);
        } catch (Exception e) {
            response.setMessage(ZwCode.FAIL_GET.getInfo());
            response.setCode(ZwCode.FAIL_GET.getCode());
        }
        return response;
    }


    @ApiImplicitParam(name = "warehouseId", value = "仓库id", paramType = "query", dataType = "int")
    @ApiOperation(value = "下拉选货袋所属仓位", nickname = "getWarehousePositionsListByID", notes = "仓位列表", tags = {"@占伟"})
    @RequestMapping(value = "getWarehousePositionsListByID", method = RequestMethod.GET)
    @ResponseBody
    public LayuiCommonResponse getWarehousePositionsListByID(@RequestParam(defaultValue = "0") int warehouseId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            List<WarehousePositions> list = warehousePositionService.getAll(warehouseId);
            response.setCode(0);
            response.setMsg(ZwCode.SUCCESS_GET.getInfo());
            response.setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg(ZwCode.FAIL_GET.getInfo());
        }
        return response;
    }


    @ApiOperation(value = "获取用户id根据用户编号", notes = "获取用户id", nickname = "getUserId", tags = {"@占伟"})
    @RequestMapping(value = "/getUserId", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse getUserId(String number) {
        CommonResponse response = new CommonResponse();
        try {
            int userId = userService.selectUIdByNumber(number);
            response.setData(userId);
            response.setMessage(ZwCode.SUCCESS_GET.getInfo());
            response.setCode(ZwCode.SUCCESS_GET.getCode());
        } catch (Exception e) {
            response.setMessage(ZwCode.FAIL_GET.getInfo());
            response.setCode(ZwCode.FAIL_GET.getCode());
        }
        return response;
    }


   /* @ApiImplicitParams({
            @ApiImplicitParam(name = "bagIds", value = "货袋id组,如 1,2,3,5, ", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "staffId", value = "操作人员id", required = true, dataType = "int", paramType = "query"),
//            @ApiImplicitParam(name = "appUserId", value = "司机id", required = true, dataType = "int", paramType = "query"),
    })
    @ApiOperation(value = "直接装车", nickname = "bagDirect", notes = "扫描货袋 直接装车", tags = {"@占伟"})
    @RequestMapping(value = "bagDirect", method = RequestMethod.POST)
    public CommonResponse bagDirect(@Validated @RequestParam String bagIds, @Validated @RequestParam int staffId,@Validated @RequestParam int appUserId ) {
        CommonResponse response = new CommonResponse();
        String[] split = bagIds.split(",");
        String formNumber = appUserId+""+WXPayUtil.getCurrentTimestamp();
        Staff staff = staffService.selectStaffDetail(staffId);
//        AppUser user = appUserService.selectAppUserDetail(appUserId);
        GoodsPrintForm form = new GoodsPrintForm();
//        form.setDriverName(user.getActualName());
        form.setFormNumber(formNumber);
        form.setPrintState(1);
        form.setAddTime(DateUtil.getDateTime());
        goodsPrintFormService.add(form);
        int formId = goodsPrintFormService.selectByNum(formNumber);
        GoodsPrintFormMid goodsPrintFormMid = new GoodsPrintFormMid();
        goodsPrintFormMid.setFormId(formId);
        try {
            for (String id : split) {
                if ("".equals(id) && id == null) {
                    break;
                }
                Integer bagId = Integer.valueOf(id);
                Bag bag = bagSerivce.selectBagStateById(bagId);
//        如果状态不为已入库提示 请进行正确的货袋操作
                if (bag.getState() != 3) {
                    response.setCode(ZwCode.ERROR_BAG_OPERATION.getCode());
                    response.setMessage(ZwCode.ERROR_BAG_OPERATION.getInfo());
                    return response;
                }
//                修改货袋状态为直接配送
                bagSerivce.updateBagDirect(bagId,appUserId);
                List<Goods> list = goodsService.selectByBagId(bagId);
                for (Goods goods : list) {
                    goodsPrintFormMid.setFormId(goods.getGoodsId());
                    LogisticInfo goodsInfo = new LogisticInfo();
                    goodsInfo.setCreateTime(new Date());
                    goodsInfo.setGoodsId(goods.getGoodsId());
                    goodsInfo.setStaffId(staffId);
                    goodsInfo.setOperateName(staff.getStaffName());
                    goodsInfo.setOperateComment("货物直接配送中");
                    goodsInfo.setOperateType("货物直接配送");
                    goodsInfo.setOperateResult("交付司機派送中");
                    messageService.insertMessage(14, 0, 0, goods.getGoodsId(), 0, 0, 0);
                    logisticInfoService.insertLogisticInfo(goodsInfo);
//                    新增货物id 与货物配送打印关联
                    goodsPrintFormMidService.add(goodsPrintFormMid);
                }
                LogisticInfo info = new LogisticInfo();
                info.setCreateTime(new Date());
                info.setOperateName(staff.getStaffName());
                info.setOperateComment("货袋直接配送中");
                info.setOperateType("货袋直接配送");
                info.setOperateResult("货袋配送");
                info.setStaffId(staffId);
                info.setBagId(bagId);
                logisticInfoService.insertLogisticInfo(info);
                response.setCode(ZwCode.SUCCESS_BAG_DIRECT.getCode());
                response.setMessage(ZwCode.SUCCESS_BAG_DIRECT.getInfo());
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(ZwCode.FAIL_BAG_DIRECT.getCode());
            response.setMessage(ZwCode.FAIL_BAG_DIRECT.getInfo());
        }
        return response;
    }
*/

    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsIds", value = "货物id组,如 1,2,3,5, ", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "staffId", value = "操作人员id", required = true, dataType = "int", paramType = "query"),
//            @ApiImplicitParam(name = "appUserId", value = "司机id", required = true, dataType = "int", paramType = "query"),
    })
    @ApiOperation(value = "货物直接装车配送", nickname = "goodsDirect", notes = "扫描货物 直接装车", tags = {"@占伟"})
    @RequestMapping(value = "goodsDirect", method = RequestMethod.POST)
    // 暂时去掉司机名字
    public CommonResponse goodsDirect(@Validated @RequestParam String goodsIds, @Validated @RequestParam int staffId ) {
        CommonResponse response = new CommonResponse();
        String formNumber = WXPayUtil.getCurrentTimestamp()+"";
        String[] split = goodsIds.split(",");
        try {
            Staff staff = staffService.selectStaffDetail(staffId);
//            AppUser user = appUserService.selectAppUserDetail(appUserId);
            GoodsPrintForm form = new GoodsPrintForm();
//            form.setDriverName(user.getActualName());
            form.setFormNumber(formNumber);
            form.setPrintState(1);
            form.setAddTime(DateUtil.getDateTime());
//            添加直接配送打印货物编号
            goodsPrintFormService.add(form);
//            查出刚才添加 打印货物单 id
            int formId = goodsPrintFormService.selectByNum(formNumber);
            GoodsPrintFormMid goodsPrintFormMid = new GoodsPrintFormMid();
            goodsPrintFormMid.setFormId(formId);

            HashSet<Integer> hashSet = new HashSet<>();
            for (String id : split) {
                if ("".equals(id) && id == null) {
                    break;
                }
                Integer goodsId = Integer.valueOf(id);
                int bagId = goodsService.selectBagIdByGdId(goodsId);
                hashSet.add(bagId);
                LogisticInfo goodsInfo = new LogisticInfo();
                goodsPrintFormMid.setGoodsId(goodsId);
                goodsInfo.setCreateTime(new Date());
                goodsInfo.setGoodsId(goodsId);
                goodsInfo.setStaffId(staffId);
                goodsInfo.setOperateName(staff.getStaffName());
                goodsInfo.setOperateComment("货物直接配送中");
                goodsInfo.setOperateType("货物直接配送");
                goodsInfo.setOperateResult("交付司機派送中");
                messageService.insertMessage(14, 0, 0, goodsId, 0, 0, 0);
//                记录货物物流进程
                logisticInfoService.insertLogisticInfo(goodsInfo);
//                新增货物id 与货物配送打印关联
                goodsPrintFormMidService.add(goodsPrintFormMid);
            }
            for (Integer id :hashSet
                 ) {
                //将货袋改为包裹直接配送模式
                Bag bag = new Bag();
                bag.setBagId(id);
                bag.setState((short)10);
                bag.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                bagSerivce.updateBagState(bag);
            }
            response.setCode(ZwCode.SUCCESS_GOODS_DIRECT.getCode());
            response.setMessage(ZwCode.SUCCESS_GOODS_DIRECT.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            response.setCode(ZwCode.FAIL_GOODS_DIRECT.getCode());
            response.setMessage(ZwCode.FAIL_GOODS_DIRECT.getInfo());
        }
        return response;

    }

    @ApiImplicitParam(name = "wildMatch", value = "货袋编号或者货物编号 06... or 04... ", required = true, dataType = "String", paramType = "query")
    @ApiOperation(value = "扫码获取货物或者货袋详情", nickname = "goodsDirect", notes = "扫码获取货物或者货袋详情", tags = {"@占伟"})
    @RequestMapping(value = "getGoodsDetais", method = RequestMethod.POST)
    public CommonResponse getGoodsDetais(@Validated @RequestParam String wildMatch ) {
        CommonResponse response = new CommonResponse();
//

//        扫的二维码不以06 04 开头提示扫描正确的二维码
        if(!wildMatch.startsWith("06")&&!wildMatch.startsWith("04")){
            response.setCode(ZwCode.ERROR_QR_CODE.getCode());
            response.setMessage(ZwCode.ERROR_QR_CODE.getInfo());
            return response;
        }
//        如果以06开头是货袋查出所有货物
        ArrayList<GoodsDetails> list = new ArrayList<>();
        if(wildMatch.startsWith("06")){
            try {
                ArrayList<GoodsDetails> goodsDetailss =   goodsDetailsService.selectGoodsInfoByBagNumber(wildMatch);
                list.addAll(goodsDetailss);
                response.setCode(0);
                response.setMessage(ZwCode.SUCCESS_GET.getInfo());
            }catch (Exception e){
                e.printStackTrace();
                response.setCode(-1);
                response.setMessage(ZwCode.FAIL_GET.getInfo());
            }

         }
//        如果以04开头是货物详情查询
        if (wildMatch.startsWith("04")){
            try {
                GoodsDetails goodsDetails = goodsDetailsService.selectGoodsInfoByDeliveryNumber(wildMatch);
                list.add(goodsDetails);
                response.setCode(0);
                response.setMessage(ZwCode.SUCCESS_GET.getInfo());
            }catch (Exception e){
                e.printStackTrace();
                response.setCode(-1);
                response.setMessage(ZwCode.FAIL_GET.getInfo());
            }
        }
        for (GoodsDetails details : list) {
            if(details.getStatus()!=1){
                response.setCode(ZwCode.WARNING_GOODS_BAG.getCode());
                response.setMessage(ZwCode.WARNING_GOODS_BAG.getInfo());
            }
        }
        response.setData(list);
        return response;
    }
}