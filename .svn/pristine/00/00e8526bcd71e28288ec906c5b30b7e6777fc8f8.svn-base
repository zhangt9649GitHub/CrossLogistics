package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.SingaporeAreaBuilding;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Mapper
@Repository
public interface SingaporeAreaBuildingMapper {


    int deleteByPrimaryKey(Integer saBuildingId);

    int insert(SingaporeAreaBuilding record);

    SingaporeAreaBuilding selectByPrimaryKey(Integer saBuildingId);

    List<SingaporeAreaBuilding> selectAll(@Param("saZipCode")String saZipCode);

    int updateByPrimaryKey(SingaporeAreaBuilding record);

    int getCountSGBuilding(@Param("saZipCode")String saZipCode);

    void deleteById(@Param("saBuildingId") int saBuildingId);

    /**
     * 货物入库根据邮编确定所属区域
     * @param saZipCode
     * @return
     */
    SingaporeAreaBuilding selectSGBuildingByZipCode(@Param("saZipCode") String saZipCode);

    /**
     * 修改有大楼所属区域
     * @param a
     */
    void updateBuidingArea(SingaporeAreaBuilding a);


    /**
     * 查出未分区的大楼
     * @return
     */
    List<SingaporeAreaBuilding> selectNoAreaBuilding(@Param(value = "saZipCode") String saZipCode);



    Integer selectCountNoAreaBuilding(@Param(value = "saZipCode") String saZipCode);

    /**
     * 修改大楼所属的集结点
     * @param a
     */
    void updateBuidingRallyPoint(SingaporeAreaBuilding a);


    /**
     * 修改大楼所属的集结点和区域
     * @param a
     */
    void updateBuidingAreaAndRallyPoint(SingaporeAreaBuilding a);

    /**
     * 查出一个区域里所有的大楼
     * @param singaporeAreaId
     * @return
     */
    List<SingaporeAreaBuilding> selectBySaId(@Param(value = "singaporeAreaId") Integer singaporeAreaId);


    /**
     * 查出已经分好区域的大楼
     * @param saZipCode
     * @return
     */
    List<SingaporeAreaBuilding> selectInAreaBuilding(@Param(value = "saZipCode") String saZipCode);

    /**
     * 查出已经分好区域的大楼的个数
     * @param saZipCode
     * @return
     */
    int getCountInAreaBuilding(@Param(value = "saZipCode") String saZipCode);

    /**
     * 新加坡大楼复原
     * @param saBuildingId
     */
    void recoveryBuilding(int saBuildingId);
}