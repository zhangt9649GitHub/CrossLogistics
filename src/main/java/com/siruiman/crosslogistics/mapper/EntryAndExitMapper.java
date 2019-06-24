package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.EntryAndExit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EntryAndExitMapper {

    int deleteByPrimaryKey(Integer eaeId);


    int insert(EntryAndExit record);


    EntryAndExit selectByPrimaryKey(Integer eaeId);


    List<EntryAndExit> selectAll();


    int updateByPrimaryKey(EntryAndExit record);

    /**
     * 获取出入境物流途径参数配置列表
     *
     * @param exitWay
     * @return
     */
    List<EntryAndExit> selectEntryAndExitList(@Param("exitWay") Integer exitWay);

    /**
     * 获取出入境物流途径参数配置列表总行数
     *
     * @param exitWay
     * @return
     */
    int selectEntryAndExitCount(@Param("exitWay") Integer exitWay);
}