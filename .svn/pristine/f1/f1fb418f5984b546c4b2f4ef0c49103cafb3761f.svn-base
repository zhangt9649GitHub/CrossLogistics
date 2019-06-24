package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.ChinaArea;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface ChinaAreaMapper {
    int deleteByPrimaryKey(Integer chinaAreaId);

    int insert(ChinaArea record);

    ChinaArea selectByPrimaryKey(Integer chinaAreaId);

    List<ChinaArea> selectAll();

    int updateByPrimaryKey(ChinaArea record);


    List<ChinaArea> selectAllProvince();

    int selectCountProvince();

    List<ChinaArea> selectChildById(int chinaAreaId);

    List<ChinaArea> selectByType(int chinaAreaType );

    void delById(ChinaArea chinaArea);
}