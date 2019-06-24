package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.UploadFiles;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UploadFilesMapper {

    int deleteByPrimaryKey(Integer ufId);


    int insert(UploadFiles record);


    UploadFiles selectByPrimaryKey(Integer ufId);


    List<UploadFiles> selectAll();


    int updateByPrimaryKey(UploadFiles record);

    /**
     * 获取刚插入的id
     *
     * @return
     */
    Integer getLastId();
}