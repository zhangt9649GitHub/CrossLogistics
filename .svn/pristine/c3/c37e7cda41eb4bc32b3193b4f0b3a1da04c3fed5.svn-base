package com.siruiman.crosslogistics.controller;

import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.enums.HtCode;
import com.siruiman.crosslogistics.enums.ZwCode;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.*;
import com.siruiman.crosslogistics.util.DateUtil;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.siruiman.crosslogistics.util.RandomCodeUtil.getRandomCode;

/**
 * @author 张占伟
 * @date 2018/12/21
 */
@Api(value = "Car-API", description = "小车管理-API", tags = {"小车管理"})
@RestController
@RequestMapping("car")
public class CarController {
    @Autowired
    private CarService carService;
    @Autowired
    private BagSerivce bagSerivce;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private PositionInfoService positionInfoService;
    @Autowired
    private CarLockService carLockService;
    @Autowired
    private TaskCarOrderService taskCarOrderService;
    @Autowired
    private LogisticInfoService logisticInfoService;

    @ApiOperation(value = "获取小车管理列表", notes = "获取小车管理列表", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页容量", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "carNumber", value = "小车编号", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "state", value = "小车使用状态", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "singaporeAreaId", value = "新加坡地区id", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "rallyPointId", value = "集结点id", paramType = "query", dataType = "Integer")
    })
    @RequestMapping(value = "getCarInfoList", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getCarInfoList"})
    public LayuiCommonResponse getCarInfoList(@Validated @RequestParam(defaultValue = "1") int page,
                                              @Validated @RequestParam(defaultValue = "10") int limit,
                                              @Validated String carNumber, @Validated Integer state,
                                              @Validated Integer singaporeAreaId, @Validated Integer rallyPointId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            PageHelper.startPage(page, limit);
            List<Car> carList = carService.selectCarInfoList(carNumber, state, singaporeAreaId, rallyPointId);
            for (Car car : carList
            ) {
                if (!(car.getAddTime() == null) && !(car.getAddTime().equals(""))) {
                    car.setAddTime(car.getAddTime().substring(0, car.getAddTime().indexOf(".")));
                }
                if (car.getState() == 2) {
                    int state1 = 3;
                    TaskCarOrder taskCarOrder = taskCarOrderService.selectAppTaskCarOrderByCarId(car.getCarId(), state1);
                    if (taskCarOrder != null) {
                        car.setOrderNumber(taskCarOrder.getOrderNumber());
                    }
                    byte state2 = 7;
                    Bag bag = bagSerivce.selectBagId(car.getCarId(), state2);
                    if (bag != null) {
                        car.setBagNumber(bag.getBagNumber());
                    }
                }
                if (car.getState() == 4) {
                    byte state3 = 9;
                    Bag bag1 = bagSerivce.selectBagId(car.getCarId(), state3);
                    if (bag1 != null) {
                        car.setBagNumber(bag1.getBagNumber());
                    }
                }
                List<CarLock> carLockList = carLockService.selectCarLockByCarId(car.getCarId());
                for (CarLock carLock : carLockList
                ) {
                    if (carLock.getLockPosition() == 1 && carLock.getStatus() == 1) {
                        car.setCarLockBoxNumber(carLock.getLockNumber());
                    } else if (carLock.getLockPosition() == 2 && carLock.getStatus() == 1) {
                        car.setCarLockNumber(carLock.getLockNumber());
                    }
                }
            }
            int count = carService.selectCountCarInfoList(carNumber, state, singaporeAreaId, rallyPointId);
            response.setMsg("success");
            response.setCode(0);
            response.setCount(count);
            response.setData(carList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
        }
        return response;
    }

    @ApiOperation(value = "获取小车详细信息", notes = "获取小车详细信息", tags = {"@郝腾"})
    @ApiImplicitParam(name = "carId", value = "小车id", paramType = "query", required = true, dataType = "int")
    @RequestMapping(value = "getCarDetails", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getCarDetails"})
    public LayuiCommonResponse getCarDetails(@Validated Integer carId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            Car car = carService.selectCarDetailsById(carId);
            if (car.getState() == 2) {
                int state1 = 3;
                TaskCarOrder taskCarOrder = taskCarOrderService.selectAppTaskCarOrderByCarId(carId, state1);
                if (taskCarOrder != null) {
                    car.setOrderNumber(taskCarOrder.getOrderNumber());
                }
                byte state2 = 7;
                Bag bag = bagSerivce.selectBagId(car.getCarId(), state2);
                if (bag != null) {
                    car.setBagNumber(bag.getBagNumber());
                }
            }
            if (car.getState() == 4) {
                byte state3 = 9;
                Bag bag1 = bagSerivce.selectBagId(car.getCarId(), state3);
                if (bag1 != null) {
                    car.setBagNumber(bag1.getBagNumber());
                }
            }
            List<CarLock> carLockList = carLockService.selectCarLockByCarId(car.getCarId());
            for (CarLock carLock : carLockList
            ) {
                if (carLock.getLockPosition() == 1 && carLock.getStatus() == 1) {
                    car.setCarLockBoxNumber(carLock.getLockNumber());
                    car.setCarLockBoxId(carLock.getLockId());
                } else if (carLock.getLockPosition() == 2 && carLock.getStatus() == 1) {
                    car.setCarLockNumber(carLock.getLockNumber());
                    car.setCarLockId(carLock.getLockId());
                }
            }
            response.setMsg("success");
            response.setCode(0);
            response.setData(car);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
        }
        return response;
    }

    @ApiOperation(value = "获取小车内货物清单", notes = "获取小车内货物清单", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页容量", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "carId", value = "小车id", paramType = "query", required = true, dataType = "int"),
    })
    @RequestMapping(value = "getCarGoodsList", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getCarDetails"})
    public LayuiCommonResponse getCarGoodsList(@Validated @RequestParam(defaultValue = "1") int page,
                                               @Validated @RequestParam(defaultValue = "10") int limit,
                                               @Validated Integer carId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            PageHelper.startPage(page, limit);
            Car car = carService.selectCarDetailsById(carId);
            Bag bag = new Bag();
            if (car != null) {
                if (car.getState() == 2) {
                    byte state = 7;
                    bag = bagSerivce.selectBagId(carId, state);
                } else if (car.getState() == 4) {
                    byte state = 9;
                    bag = bagSerivce.selectBagId(carId, state);
                }
            }
            List<Goods> goodsList = new ArrayList<>();
            int count = 0;
            if (bag != null && bag.getBagId() != null) {
                if (bag.getState() != null && bag.getState() == 7) {
                    goodsList = goodsService.selectCarGoodsList(bag.getBagId());
                    for (Goods goods1 : goodsList
                    ) {
                        LogisticInfo logisticInfo = logisticInfoService.selectLogisticInfoByGoodsId(goods1.getGoodsId());
                        if (logisticInfo != null && logisticInfo.getOperateType() != null) {
                            goods1.setOperateResult(logisticInfo.getOperateType());
                        }
                    }
                    count = goodsService.selectCountCarGoodsList(bag.getBagId());
                } else if (bag.getState() != null && bag.getState() == 9) {
                    goodsList = goodsService.selectCarGoodsWarningList(bag.getBagId());
                    for (Goods goods1 : goodsList
                    ) {
                        LogisticInfo logisticInfo = logisticInfoService.selectLogisticInfoByGoodsId(goods1.getGoodsId());
                        if (logisticInfo != null && logisticInfo.getOperateType() != null) {
                            goods1.setOperateResult(logisticInfo.getOperateType());
                        }
                    }
                    count = goodsService.selectCountCarGoodsWarningList(bag.getBagId());
                }

            }
            response.setMsg("success");
            response.setCode(0);
            response.setCount(count);
            response.setData(goodsList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
        }
        return response;
    }

    @ApiOperation(value = "查看小车实时位置", notes = "查看小车实时位置", tags = {"@郝腾"})
    @ApiImplicitParam(name = "carId", value = "小车id", required = true, dataType = "Integer", paramType = "query")
    @RequestMapping(value = "/getCarPosition", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getCarDetails"})
    public LayuiCommonResponse getCarPosition(Integer carId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            PositionInfo positionInfo = positionInfoService.selectCarPosition(carId);
            response.setMsg("success");
            response.setCode(0);
            response.setData(positionInfo);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
        }
        return response;
    }

    @ApiOperation(value = "添加小车", notes = "添加小车", tags = {"@郝腾"})
    @ApiImplicitParam(name = "car", value = "小车实体类", required = true, dataType = "Car")
    @RequestMapping(value = "/insertCar", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = {"insertCar"})
    public LayuiCommonResponse insertCar(@RequestBody Car car) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            String carNumber = "07" + getRandomCode();
            car.setCarNumber(carNumber);
            car.setState(1);
            String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            car.setAddTime(dateStr);
            car.setCarReturnState(1);
            car.setSingaporeAreaId(car.getSingaporeAreaId());
            car.setRallyPointId(car.getRallyPointId());
            car.setDisableState(1);
            carService.insertCar(car);
            //添加都是空置的锁
            int carId = carService.selectCarLastId();
            CarLock carLock1 = carLockService.selectCarLockById(car.getCarLockId());
            carLock1.setCarId(carId);
            carLock1.setStatus((byte) 1);
            carLock1.setLockPosition(2);
            carLockService.updateCarLock(carLock1);
            CarLock carLock2 = carLockService.selectCarLockById(car.getCarLockBoxId());
            carLock2.setCarId(carId);
            carLock2.setStatus((byte) 1);
            carLock2.setLockPosition(1);
            carLockService.updateCarLock(carLock2);
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

    @ApiOperation(value = "获取所有空置的小车锁编号", notes = "获取所有空置的小车锁编号", tags = {"@郝腾"})
    @RequestMapping(value = "/getVacancyCarLock", method = RequestMethod.GET)
    @ResponseBody
    public LayuiCommonResponse getVacancyCarLock() {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            int lockPostion = 2;
            byte status = 3;
            List<CarLock> carLockList = carLockService.selectVacancyCarLock(lockPostion, status);
            response.setMsg("success");
            response.setCode(0);
            response.setData(carLockList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
        }
        return response;
    }

    @ApiOperation(value = "获取所有空置的小车后箱锁编号", notes = "获取所有空置的小车后箱锁编号", tags = {"@郝腾"})
    @RequestMapping(value = "/getVacancyCarLockBox", method = RequestMethod.GET)
    @ResponseBody
    public LayuiCommonResponse getVacancyCarLockBox() {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            int lockPostion = 1;
            byte status = 3;
            List<CarLock> carLockList = carLockService.selectVacancyCarLock(lockPostion, status);
            response.setMsg("success");
            response.setCode(0);
            response.setData(carLockList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
        }
        return response;
    }

    @ApiOperation(value = "更新小车数据", notes = "更新小车数据", tags = {"@郝腾"})
    @ApiImplicitParam(name = "car", value = "小车实体类", dataType = "Car")
    @RequestMapping(value = "/updateCar", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = {"updateCar"})
    public LayuiCommonResponse updateCar(@Validated @RequestBody Car car) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            Car car1 = carService.selectCarDetailsById(car.getCarId());
            if (car1 != null && car1.getSingaporeAreaId() != null && !(car1.getSingaporeAreaId().equals(car.getSingaporeAreaId()))
                    || car1 != null && car1.getRallyPointId() != null && !(car1.getRallyPointId().equals(car.getRallyPointId()))) {
                String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + " 00:00:00";
                List<TaskCarOrder> taskCarOrderList = taskCarOrderService.selectTaskCarOrderByCarId(car.getCarId(), date);
                if (taskCarOrderList.size() > 0) {
                    response.setCode(HtCode.FAIL_CAR_UPDATE.getCode());
                    response.setMsg(HtCode.FAIL_CAR_UPDATE.getInfo());
                    return response;
                }
            }
            car1.setSingaporeAreaId(car.getSingaporeAreaId());
            car1.setRallyPointId(car.getRallyPointId());
            String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            car1.setUpdateTime(dateStr);
            car1.setDisableState(1);
            carService.updateCar(car1);
            int lockPosition = 2;
            List<CarLock> oldCarLockList = carLockService.selectCarLock(car.getCarId(), lockPosition);
            if (oldCarLockList != null) {
                for (CarLock oldCarLock : oldCarLockList
                ) {
                    if (oldCarLock.getStatus() == 1) {
                        oldCarLock.setStatus((byte) 3);
                        carLockService.updateCarLock(oldCarLock);
                    }
                }
            }
            CarLock carLock1 = carLockService.selectCarLockById(car.getCarLockId());
            carLock1.setCarId(car.getCarId());
            carLock1.setStatus((byte) 1);
            carLock1.setLockPosition(2);
            carLockService.updateCarLock(carLock1);
            lockPosition = 1;
            List<CarLock> oldCarLockBoxList = carLockService.selectCarLock(car.getCarId(), lockPosition);
            if (oldCarLockBoxList != null) {
                for (CarLock oldCarLockBox : oldCarLockBoxList
                ) {
                    if (oldCarLockBox.getStatus() == 1) {
                        oldCarLockBox.setStatus((byte) 3);
                        carLockService.updateCarLock(oldCarLockBox);
                    }
                }
            }
            CarLock carLock2 = carLockService.selectCarLockById(car.getCarLockBoxId());
            carLock2.setCarId(car.getCarId());
            carLock2.setStatus((byte) 1);
            carLock2.setLockPosition(1);
            carLockService.updateCarLock(carLock2);
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

    @ApiOperation(value = "删除小车", notes = "删除小车", tags = {"@郝腾"})
    @ApiImplicitParam(name = "carId", value = "小车id", required = true, dataType = "Integer", paramType = "query")
    @RequestMapping(value = "/deleteCarById", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"deleteCarById"})
    public LayuiCommonResponse deleteCarById(Integer carId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + " 00:00:00";
            List<TaskCarOrder> taskCarOrderList = taskCarOrderService.selectTaskCarOrderByCarId(carId, date);
            if (taskCarOrderList.size() > 0) {
                response.setCode(HtCode.FAIL_CAR_DELETE.getCode());
                response.setMsg(HtCode.FAIL_CAR_DELETE.getInfo());
                return response;
            } else {
                carService.deleteCarById(carId);
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
}
