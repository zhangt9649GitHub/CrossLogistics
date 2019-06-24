package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.SingaporeArea;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface SingaporeAreaMapper {

    int deleteByPrimaryKey(Integer singaporeAreaId);

    int insert(SingaporeArea record);

    SingaporeArea selectByPrimaryKey(Integer singaporeAreaId);

    List<SingaporeArea> selectAll(@Param("singaporeAreaName") String singaporeAreaName);

    int updateByPrimaryKey(SingaporeArea record);

    int selectCount(@Param("singaporeAreaName")String singaporeAreaName);
//  仅作物理删除
    void delById(int singaporeAreaId);

    List<SingaporeArea> getSingaporeAreaIdAndName();

//    查出新加坡的id
    int selectSGIdBySingaporeName(String singaporeAreaName);

    /**
     * 获取所有区域名称
     * @return
     */
    List<SingaporeArea> selectSingaporeAreaNameList();

    /**
     * 获取所有新加坡区域名字id
     * @return
     */
    List<SingaporeArea> selectSGAreaList();
}