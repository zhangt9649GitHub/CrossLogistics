package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.SingaporePointMapper;
import com.siruiman.crosslogistics.mapper.TruckTaskMapper;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.model.dto.EditTruckTaskDto;
import com.siruiman.crosslogistics.model.dto.TruckTaskDto;
import com.siruiman.crosslogistics.model.dto.TruckTaskRouteDto;
import com.siruiman.crosslogistics.service.TruckTaskService;
import com.siruiman.crosslogistics.util.RandomCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TruckTaskServiceImpl implements TruckTaskService {
    @Autowired
    private TruckTaskMapper truckTaskMapper;
    @Autowired
    private SingaporePointMapper singaporePointMapper;

    @Override
    public List<TruckTask> selectTruckTaskAll(String search) {
        List<TruckTask> selectTruckTaskAll = truckTaskMapper.selectTruckTaskAll(search);
        for(TruckTask truckTask : selectTruckTaskAll){
            String[] truckDriverIds = truckTask.getTruckDriverId().split(",");
            String truckDriverName = "";
            if(truckDriverIds.length != 0){
                for(int i = 0; i < truckDriverIds.length; i++){
                    String driverName = truckTaskMapper.driverName(Integer.parseInt(truckDriverIds[i]));
                    if(truckDriverName.length() == 0){
                        truckDriverName = driverName;
                    }else {
                        truckDriverName = truckDriverName + "," + driverName;
                    }
                }
                truckTask.setTruckDriverName(truckDriverName);
            }
        }
        return selectTruckTaskAll;
    }

    @Override
    public Integer count(String search) {
        return truckTaskMapper.count(search);
    }

    @Transactional
    @Override
    public Integer addTruckTask(TruckTaskDto truckTaskDto) {
        try{
            /*查询新增货车模板是否重复*/
            Integer selectTruckTaskByTruckTaskName = truckTaskMapper.selectTruckTaskByTruckTaskName(truckTaskDto.getTruckTaskName());
            if(selectTruckTaskByTruckTaskName > 0){
                return 2;
            }
            String number = "09" + RandomCodeUtil.getSixRandomCode();
            truckTaskDto.setTruckTaskNumber(number);
            Integer addTruckTask = truckTaskMapper.addTruckTask(truckTaskDto);
            if(addTruckTask < 1){
                throw new RuntimeException("新增货车模板失败");
            }
            String taskRoutes = truckTaskDto.getTruckTaskRoutes();
            String[] truckTaskRoutes = taskRoutes.split(",");
            for(int i = 0; i < truckTaskRoutes.length; i++){
                String truckTaskRoute = truckTaskRoutes[i];
                String[] str = truckTaskRoute.split(";");
                TruckTaskRouteDto truckTaskRouteDto = new TruckTaskRouteDto();
                truckTaskRouteDto.setRouteId(Integer.parseInt(str[0]));
                truckTaskRouteDto.setStatus(Integer.parseInt(str[1]));
                truckTaskRouteDto.setSort(Integer.parseInt(str[2]));
                Integer addTruckTaskRoute = truckTaskMapper.addTruckTaskRoute(truckTaskDto.getTruckTaskId(), truckTaskRouteDto);
                if(addTruckTaskRoute < 1){
                    throw new RuntimeException("新增货车路线失败");
                }

            }
            return addTruckTask;
        }catch (Exception e){
            throw new RuntimeException("新增货车模板失败");
        }
    }

    @Override
    public List<SingaporeArea> selectSingaporeAreaAll() {
        List<SingaporeArea> selectSingaporeAreaAll = truckTaskMapper.selectSingaporeAreaAll();
        for(SingaporeArea singaporeArea : selectSingaporeAreaAll){
            List<SingaporePoint> selectBySGAreaId = singaporePointMapper.selectBySGAreaId(singaporeArea.getSingaporeAreaId());
            singaporeArea.setSingaporePoints(selectBySGAreaId);
        }
        return selectSingaporeAreaAll;
    }

    @Override
    public List<RallyPoint> selectRallyPointBySA(int singaporeAreaId) {
        return truckTaskMapper.selectRallyPointBySA(singaporeAreaId);
    }

    @Override
    public List<AppUser> selectAppUserByTruck() {
        return truckTaskMapper.selectAppUserByTruck();
    }

    @Override
    public List<Warehouse> selectWarehouseAll() {
        return truckTaskMapper.selectWarehouseAll();
    }

    @Override
    public Integer deleteTruckTask(int truckTaskId) {
        return truckTaskMapper.deleteTruckTask(truckTaskId);
    }

    @Override
    public TruckTaskDetails truckTaskDetails(int truckTaskId) {
        /*根据货车任务模板id查询详情*/
        TruckTaskDetails truckTaskDetails = truckTaskMapper.selectTruckTaskDetails(truckTaskId);
        /*取出货车司机id*/
        String truckDriverId = truckTaskDetails.getTruckDriverId();
        List truckDrivers = new ArrayList(Arrays.asList(truckDriverId.split(",")));
        String truckDriverName = "";
        /*处理司机姓名*/
        for(int i = 0; i < truckDrivers.size(); i++){
            int driverId = Integer.parseInt(truckDrivers.get(i).toString());
            String userName = truckTaskMapper.selectUserName(driverId);
            if(truckDriverName.length() == 0){
                truckDriverName = userName;
            }else {
                truckDriverName = truckDriverName + "," + userName;
            }
        }
        truckTaskDetails.setTruckDriverName(truckDriverName);
        /*查询货车路线*/
        List<TruckTaskRouteDto> selectTruckTaskRoute = truckTaskMapper.selectTruckTaskRoute(truckTaskId);
        List<TruckTaskRoute> truckTaskRoutes = new ArrayList<>();
        for(TruckTaskRouteDto truckTaskRouteDto : selectTruckTaskRoute){
            /*1小车集结点 2仓库*/
            int status = truckTaskRouteDto.getStatus();
            int routeId = truckTaskRouteDto.getRouteId();
            int sort = truckTaskRouteDto.getSort();
            if(status == 1){
                /*查询小车集结点名称和经纬度*/
                TruckTaskRoute truckTaskRouteByCar = truckTaskMapper.selectTruckTaskRouteByCar(routeId);
                if(truckTaskRouteByCar != null){
                    truckTaskRouteByCar.setStatus(status);
                    truckTaskRouteByCar.setSort(sort);
                    truckTaskRoutes.add(truckTaskRouteByCar);
                }
            }
            if(status == 2){
                /*查询仓库名称和经纬度*/
                TruckTaskRoute truckTaskRouteByWarehouse = truckTaskMapper.selectTruckTaskRouteByWarehouse(routeId);
                if(truckTaskRouteByWarehouse != null){
                    truckTaskRouteByWarehouse.setStatus(status);
                    truckTaskRouteByWarehouse.setSort(sort);
                    truckTaskRoutes.add(truckTaskRouteByWarehouse);
                }
            }
        }
        truckTaskDetails.setTruckTaskRoutes(truckTaskRoutes);
        return truckTaskDetails;
    }

    @Override
    public Integer editTruckTaskStatus(int truckTaskId, int status) {
        return truckTaskMapper.editTruckTaskStatus(truckTaskId, status);
    }

    @Override
    public EditTruckTask selectEditTruckTask(int truckTaskId) {
        return truckTaskMapper.selectEditTruckTask(truckTaskId);
    }

    @Transactional
    @Override
    public Integer editTruckTask(EditTruckTaskDto editTruckTaskDto) {
        try{
            Integer editTruckTask = truckTaskMapper.editTruckTask(editTruckTaskDto);
            if(editTruckTask < 1){
                throw new RuntimeException("编辑货车模板信息失败");
            }
            Integer delTruckTaskRoute = truckTaskMapper.delTruckTaskRoute(editTruckTaskDto.getTruckTaskId());
            if(delTruckTaskRoute < 1){
                throw new RuntimeException("删除货车模板路线失败");
            }
            String taskRoutes = editTruckTaskDto.getTruckTaskRoutes();
            String[] truckTaskRoutes = taskRoutes.split(",");
            for(int i = 0; i < truckTaskRoutes.length; i++){
                String truckTaskRoute = truckTaskRoutes[i];
                String[] str = truckTaskRoute.split(";");
                TruckTaskRouteDto truckTaskRouteDto = new TruckTaskRouteDto();
                truckTaskRouteDto.setRouteId(Integer.parseInt(str[0]));
                truckTaskRouteDto.setStatus(Integer.parseInt(str[1]));
                truckTaskRouteDto.setSort(Integer.parseInt(str[2]));
                Integer addTruckTaskRoute = truckTaskMapper.addTruckTaskRoute(editTruckTaskDto.getTruckTaskId(), truckTaskRouteDto);
                if(addTruckTaskRoute < 1){
                    throw new RuntimeException("新增货车路线失败");
                }

            }
            return editTruckTask;
        }catch (Exception e){
            throw new RuntimeException("编辑货车模板信息失败");
        }
    }

    @Override
    public TruckTaskDto selectSingaporeAreaById(int truckTaskId) {
        try{
            return truckTaskMapper.selectSingaporeAreaById(truckTaskId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
