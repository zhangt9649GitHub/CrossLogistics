package com.siruiman.crosslogistics.service;


import com.siruiman.crosslogistics.model.RallyPoint;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author 张占伟
 * @date 2018/12/20
 */
@Service
public interface RallyPointService {

    /**
     * 查询所有的集结点
     * @return
     */
    List<RallyPoint> selectRallyPointList(RallyPoint rallyPoint);

    /**
     * 查询所有集结点的个数
     * @return
     */
    int selectCountRallyPoint(RallyPoint rallyPoint);

    /**
     * 添加集结点
     * @param rallyPoint
     */
    void insertRallyPoint(RallyPoint rallyPoint);

    /**
     * 修改集结点
     * @param rallyPoint
     */
    void updateRallyPoint(RallyPoint rallyPoint);

    /**
     * 删除小车集结点物理删除
     * @param rallyPointId
     */
    void delRallyPoint(int rallyPointId);

    /**
     * 获取小车集结点列表id地址做小车绑定大货车绑定
     * @return
     */
    List<RallyPoint> selectRallyPointListOfIdAndAdress();

    /**
     * 获取单个集结点 修改使用
     * @param rallyPointId
     * @return
     */
    RallyPoint selectById(int rallyPointId);

    /**
     * 根据集结点id获取集结点编号
     * @param sgId
     * @return
     */
    List<RallyPoint> selectRallyPointListById(int sgId);

    /**
     * 获取小车集结点名字编号id列表根据所属区域id
     * @param singaporeAreaId
     * @return
     */
    List<RallyPoint> selectBySGId(int singaporeAreaId);

    /**
     * 小车集结点名字
     * @param rallyPoint
     * @return
     */
    boolean checkName(RallyPoint rallyPoint);

    /**
     * 根据集结点名称查询集结点id
     * @param rallyPointName
     * @return
     */
    int selectIdByRallyPointName(String rallyPointName);
}
