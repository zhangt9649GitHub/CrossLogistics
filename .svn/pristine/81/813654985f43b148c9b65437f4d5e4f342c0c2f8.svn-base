package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.SingaporeAreaBuilding;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SingaporeAreaBuildingService {

    /**
     * 添加新加坡大楼
     * @param s
     */
    void insert(SingaporeAreaBuilding s);

    /**
     * 获得所有的新加坡大楼
     * @return
     */
    List<SingaporeAreaBuilding> selectSGBuildingList(String saZipCode);

    /**
     * 根据大楼id获取新加坡大楼
     * @param saBuildingId
     * @return
     */
    SingaporeAreaBuilding selectSGBuildingById(int saBuildingId);

    /**
     * 获取新加坡大楼的个数分页
     * @return
     */
    int getCountSGBuilding(String saZipCode);

    /**
     * 删除新加坡大楼
     * @param saBuildingId
     */
    void deleteById(int saBuildingId);

    /**
     * 新加坡大楼
     * @param singaporeAreaBuilding
     */
    void updateSGBuilding(SingaporeAreaBuilding singaporeAreaBuilding);

    /**
     * 货物入库根据邮编确定所属区域
     * @param saZipCode
     * @return
     */
    SingaporeAreaBuilding selectSGBuildingByZipCode(String saZipCode);

    /**
     * 修改新加坡区域所属id
     * @param a
     */
    void updateBuidingArea(SingaporeAreaBuilding a);

    /**
     * 查出未分区的大楼
     * @return
     */
    List<SingaporeAreaBuilding> selectNoAreaBuilding(String saZipCode);


    /**
     * 查出未分区的大楼的个数
     * @param saZipCode
     * @return
     */
    int getCountNoAreaBuilding(String saZipCode);

    /**
     * 查出一个区域下所有的大楼
     * @param singaporeAreaId
     * @return
     */
    List<SingaporeAreaBuilding> selectBySaId(Integer singaporeAreaId);


    /**
     * 修改大楼所属的集结点
     * @param a
     */
    void updateBuidingRallyPoint(SingaporeAreaBuilding a);

    /**
     * 查询所有的已经绑定区域的大楼
     * @param saZipCode
     * @return
     */
    List<SingaporeAreaBuilding> selectInAreaBuilding(String saZipCode);

    /**
     * 查询所有的已经绑定区域的大楼的个数
     * @param saZipCode
     * @return
     */
    int getCountInAreaBuilding(String saZipCode);


    /**
     * 校验新加坡邮编是否重复
     * @param saZipCode
     * @param singaporeAreaId
     * @return
     */
    boolean checkZipCode(String saZipCode, int singaporeAreaId);

    /**
     * 校验新加坡邮编是否重复
     * @param saBuildingId
     * @return
     */
    void recoveryBuilding(int saBuildingId);
}
