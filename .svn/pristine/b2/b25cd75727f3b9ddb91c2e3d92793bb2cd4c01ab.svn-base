package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.SingaporePoint;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SingaporePointMapper {

    int deleteByPrimaryKey(Integer singaporeAreaId);


    int insert(SingaporePoint record);


    SingaporePoint selectByPrimaryKey(Integer singaporeAreaId);


    List<SingaporePoint> selectAll();


    int updateByPrimaryKey(SingaporePoint record);

    List<SingaporePoint> selectBySGAreaId(Integer singaporeAreaId);

    void insertSGPoints(@Param("singaporePoints") List<SingaporePoint> singaporePoints);

    void deleteBySGAreaId(@Param("singaporeAreaId")int singaporeAreaId );
}