package com.siruiman.crosslogistics.service;


import com.siruiman.crosslogistics.model.ChinaArea;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChinaAreaService {


    /**
     * 查询出中国所有的省直辖市自治区的个数分页
     * @return
     */
    int selectCountProvince();

    /**
     * 查询出所有的省直辖市自治区
     * @return
     */
    List<ChinaArea> selectProvince();

    /**
     * 根据id查询出省市区下级地区列表
     * @param chinaAreaId
     * @return
     */
    List<ChinaArea> selectChildById(int chinaAreaId);

    /**
     * 中国地区的添加
     * @param chinaArea
     */
    void insert(ChinaArea chinaArea);

    /**
     * 删除中国地区做逻辑删除
     * @param chinaArea
     */
    void delById(ChinaArea chinaArea);

    /**
     * 修改中国地区
     * @param chinaArea
     */
    void update(ChinaArea chinaArea);

    /**
     * 查出所有的地区
     * * @return
     */
    List<ChinaArea> selectAll();

    /**
     * 根据地区id查询地区
     * @param chinaAreaId
     * @return
     */
    ChinaArea selectChinaAreaById(int chinaAreaId);
}
