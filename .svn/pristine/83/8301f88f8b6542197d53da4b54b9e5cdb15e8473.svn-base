package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.RallyPoint;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RallyPointMapper {
    int deleteByPrimaryKey(Integer rallyPointId);

    int insert(RallyPoint record);

    RallyPoint selectByPrimaryKey(Integer rallyPointId);

    List<RallyPoint> selectAll(RallyPoint rallyPoint);

    int updateByPrimaryKey(RallyPoint record);

    int selectCountRallyPoint(RallyPoint rallyPoint);

    void delRallyPointById(int rallyPointId);

    List<RallyPoint> selectRallyPointListOfIdAndAdress();

    /**
     * 根据集结点id获取集结点编号
     * @param sgId
     * @return
     */
    List<RallyPoint> selectRallyPointListById(@Param("sgId") int sgId);


    /**
     * 获取小车集结点名字编号id列表根据所属区域id
     * @param singaporeAreaId
     * @return
     */
    List<RallyPoint> selectBySGId(int singaporeAreaId);


    /**
     *
     * @return
     */

    int selectByName(String rallyPointName);

    /**
     * 根据集结点名称查询集结点id
     * @param rallyPointName
     * @return
     */
    int selectIdByRallyPointName(@Param("rallyPointName") String rallyPointName);
}