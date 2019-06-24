package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.*;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.model.dto.AdminUserLoginDto;
import com.siruiman.crosslogistics.service.AppUserCertificationService;
import com.siruiman.crosslogistics.service.BagSerivce;
import com.siruiman.crosslogistics.util.DateUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.plaf.basic.BasicGraphicsUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author 张占伟
 * @date 2018/12/20
 */
@Service
public class BagSericeImpl implements BagSerivce {

    @Autowired
    private BagMapper bagMapper;
    @Autowired
    private TruckMapper truckMapper;
    @Autowired
    private CarMapper carMapper;
    @Autowired
    private AppUserCertificationMapper appUserCertificationMapper;


    @Override
    public int getCountBag(Bag bag) {
        return bagMapper.selectCountBag(bag);
    }

    @Override
    public List<Bag> getAll(Bag bag) {
        return bagMapper.selectAll(bag);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertBag(Bag bag) {
        bagMapper.insert(bag);
    }

    @Override
    public Bag selectBagDetailedOfWarehouseById(int bagId) {
        return bagMapper.selectBagDetailedOfWarehouseById(bagId);
    }

    @Override
    public List<Bag> selectBagListByTruckId(Integer truckId) {
        try {
            return bagMapper.selectBagListByTruckId(truckId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int selectCountBagListByTruckId(Integer truckId) {
        try {
            return bagMapper.selectCountBagListByTruckId(truckId);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Truck selectBagDetailedOfTruckById(int bagId) {
        return truckMapper.selectBagDetailedOfTruckById(bagId);
    }

    @Override
    public Car getBagDetailedOfCar(int bagId) {
        return carMapper.getBagDetailedOfCar(bagId);
    }

    @Override
    public int selectByBagNumber(String bagNumber) {
        return bagMapper.selectByBagNumber(bagNumber);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateBagState(Bag bag) {
        bagMapper.updateBagState(bag);
    }

    @Override
    public int selectBagIdByBagNumber(String bagNumber) {
        return bagMapper.selectBagIdByBagNumber(bagNumber);
    }


    @Override
    public Bag selectBagDetailedByBagNumber(String bagNumber) {
        return bagMapper.selectBagDetailedByBagNumber(bagNumber);
    }

    @Override
    public void updateBag(Bag bag) {
        bagMapper.updateBag(bag);
    }


    @Override
    public Bag selectBagById(int bagId) {
        try {
            return bagMapper.selectBagById(bagId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void updateBagInitWarehouse(int warehouseId, int wpId, int bagId) {
        bagMapper.updateBagInitWarehouse(warehouseId, wpId, DateUtil.getDateTime(), bagId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateBagLastWarehouse(int warehouseId, int wpId, int bagId) {
        bagMapper.updateBagLastWarehouse(warehouseId, wpId, DateUtil.getDateTime(), bagId);
    }


    @Override
    public Bag selectBagByNumber(String bagNumber) {
        return bagMapper.selectBagByNumber(bagNumber);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateBagInTruck(int bagId, int truckId) {
        Bag bag = new Bag();
        bag.setBagId(bagId);
        bag.setUpdateTime(DateUtil.getDateTime());
        bag.setTruckId(truckId);
        bagMapper.updateBagInTruck(bag);
    }


    @Override
    public Bag selectBagPrint() {
        return bagMapper.selectBagPrint();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateBagPrintState(int bagId, int printState) {
        bagMapper.updateBagPrintState(bagId, printState);
    }


    @Override
    public boolean checkBagOutBound(int bagId) {
        try {
            bagMapper.selectTruckIdByBagId(bagId);
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    @Override
    public Bag selectBagStateById(int bagId) {
        return bagMapper.selectBagStateById(bagId);
    }

    @Override
    public Bag selectBagId(Integer carId, Byte state) {
        try {
            return bagMapper.selectBagId(carId, state);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Bag> selectBagList(Bag bag) {
        try {
            AdminUserLoginDto user = (AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("bagNumber", bag.getBagNumber());
            map.put("carNumber", bag.getCarNumber());
            map.put("state", bag.getState());
            map.put("singaporeAreaId", bag.getSingaporeAreaId());
            map.put("licensePlate", bag.getLicensePlate());
            map.put("rallyPointId", bag.getRallyPointId());
            if(user.getWarehouseId()>0){
                map.put("warehouseId",user.getWarehouseId());
            }
            return bagMapper.selectBagList(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int selectCountBagList(Bag bag) {
        try {
            AdminUserLoginDto user = (AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("bagNumber", bag.getBagNumber());
            map.put("carNumber", bag.getCarNumber());
            map.put("state", bag.getState());
            map.put("singaporeAreaId", bag.getSingaporeAreaId());
            map.put("licensePlate", bag.getLicensePlate());
            map.put("rallyPointId", bag.getRallyPointId());
            if(user.getWarehouseId()>0){
                map.put("warehouseId",user.getWarehouseId());
            }
            return bagMapper.selectCountBagList(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Bag selectBagDetailsById(int bagId) {
        try {
            return bagMapper.selectBagDetailsById(bagId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteBagById(Integer bagId) {
        Bag bag = bagMapper.selectBagDetailsById(bagId);
        bag.setDelState((short) 2);
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        bag.setUpdateTime(dateStr);
        AdminUserLoginDto user = (AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        bag.setAdminUid(Integer.parseInt(user.getAdminUId()));
        bagMapper.updateByPrimaryKey(bag);
    }

    @Override
    public boolean checkBagInSGByBagId(int bagId) {
        try {
            bagMapper.selectSGWarehouseById(bagId);
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    @Override
    public List<Bag> selectByRallyPointId(int rallyPointId) {
        return bagMapper.selectByRallyPointId(rallyPointId);
    }

    @Override
    public List<Bag> selectBagBySingaporeAreaId(int singaporeAreaId) {
        try {
            return bagMapper.selectBagBySingaporeAreaId(singaporeAreaId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateBagDirect(int bagId, int appUserId) {
        Bag bag = new Bag();
        bag.setBagId(bagId);
        bag.setTruckDriverId(appUserId);
        bag.setUpdateTime(DateUtil.getDateTime());
        bagMapper.updateBagDirect(bag);
    }

    @Override
    public List<Bag> selectBagListByUserId(int appUserId) {
        List<Bag> bagList = new ArrayList<>();
        try{
            String userType = "货车";
           AppUserCertification appUserCertification = appUserCertificationMapper .selectUserCertificationByUserId(appUserId,userType);
            Truck truck =truckMapper.selectTruckDetailsByBag(appUserCertification.getLicensePlate());
            bagList = bagMapper.selectBagListByTruckIdANDState(truck.getTruckId(),4);
            return bagList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return bagList;
    }
}
