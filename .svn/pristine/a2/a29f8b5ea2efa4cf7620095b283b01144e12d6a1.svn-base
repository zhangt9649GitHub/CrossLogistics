package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.AppUserPreferencesArea;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AppUserPreferencesAreaMapper {

    int deleteByPrimaryKey(Integer preferencesId);


    int insert(AppUserPreferencesArea record);


    AppUserPreferencesArea selectByPrimaryKey(Integer preferencesId);

    List<AppUserPreferencesArea> selectAll();

    int updateByPrimaryKey(AppUserPreferencesArea record);

    /**
     * 根据用户id和用户类型查询默认的偏好区域
     *
     * @param appUserId
     * @param userType
     * @return
     */
    List<AppUserPreferencesArea> selectDefaultAreaByUserId(@Param("appUserId") int appUserId, @Param("userType") String userType);


    /**
     * 根据区域删除 用户偏好区域
     * @param areaId
     */
    void deleteByAreaId(@Param("areaId")int areaId );
}