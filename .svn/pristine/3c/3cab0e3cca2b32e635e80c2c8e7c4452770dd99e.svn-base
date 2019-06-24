package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.SysConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysConfigMapper {

    int deleteByPrimaryKey(Integer configId);

    int insert(SysConfig record);

    SysConfig selectByPrimaryKey(Integer configId);

    List<SysConfig> selectAll();

    int updateByPrimaryKey(SysConfig record);

    SysConfig selectByKey(@Param("key") String key);
}