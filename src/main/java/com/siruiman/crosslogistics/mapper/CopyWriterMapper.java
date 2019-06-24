package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.CopyWriter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CopyWriterMapper {

    int deleteByPrimaryKey(Integer cwId);


    int insert(CopyWriter record);


    CopyWriter selectByPrimaryKey(Integer cwId);


    List<CopyWriter> selectAll();


    int updateByPrimaryKey(CopyWriter record);

    /**
     * 获取设置列表
     *
     * @return
     */
    List<CopyWriter> selectCopyWritingList(@Param("language") String language);

    /**
     * 获取内容
     *
     * @param cwId
     * @param cwName
     * @return
     */
    CopyWriter selectContentById(@Param("cwId") int cwId, @Param("cwName") String cwName);

    /**
     * 获取文案列表总行数
     *
     * @return
     */
    Integer selectCopyWriterListCount();

    /**
     * 根据语言和类型获取文案内容
     *
     * @param language
     * @param type
     * @return
     */
    CopyWriter selectContentByType(@Param("language") String language, @Param("type") String type);

    /**
     * 获取文案类型列表
     *
     * @return
     */
    List<CopyWriter> selectCopyWriterTypeList();

    /**
     * 获取转运公司介绍
     *
     * @param type
     * @param language
     * @return
     */
    CopyWriter selectCopyWriter(@Param("language") String language, @Param("type") String type);
}