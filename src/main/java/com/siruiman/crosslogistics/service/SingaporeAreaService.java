package com.siruiman.crosslogistics.service;


import com.siruiman.crosslogistics.model.SingaporeArea;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SingaporeAreaService {

    /**
     * 查询出所有的新加坡区域
     * @return
     */
    List<SingaporeArea> selectAll(String singaporeAreaName);

    /**
     * 查询出所有的新加坡自定义区域的个数做分页使用
     * @return
     */
    int getCount(String singaporeAreaName);

    /**
     * 新加坡自定义区域删除
     * @param singaporeArea
     */
    void insertSingaporeArea(SingaporeArea singaporeArea);

    /**
     * 修改新加坡区域
     * @param singaporeArea
     */
    void editSingaporeArea(SingaporeArea singaporeArea);

    /**
     * 逻辑删除新加坡自定义区域
     * @param singaporeAreaId
     */
    void deleteSingaporeArea(int singaporeAreaId);

    /**
     * 查询所有的新加坡自定义区域id和名字做下拉选使用
     * @return
     */
    List<SingaporeArea> getSingaporeAreaIdAndName();

    /**
     * 查出新加坡id
     * @param singaporeAreaName
     * @return
     */
    int selectSGIdBySingaporeName(String singaporeAreaName);

    /**
     * 检验名字是否存在
     * @param singaporeAreaId
     * @param singaporeAreaName
     * @return
     */
    boolean checkSingaporeAreaName(int singaporeAreaId, String singaporeAreaName);

    /**
     * 查出新加坡区域
     * @param singaporeAreaId
     * @return
     */
    SingaporeArea selectById(int singaporeAreaId);

    /**
     * 获取所有区域名称
     * @return
     */
    List<SingaporeArea> selectSingaporeAreaNameList();

    /**
     * 获取所有区域名称id
     * @return
     */
    List<SingaporeArea> selectSGAreaList();
}
