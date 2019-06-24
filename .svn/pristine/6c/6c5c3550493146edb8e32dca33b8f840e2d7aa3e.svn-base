package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.GoodsFrom;
import com.siruiman.crosslogistics.model.GoodsFromInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GoodsFromService {

    /**
     * 修改货物表单打印状态
     *
     * @param formId
     * @param isPrint
     */
    void updateGoodsFromPrint(int formId, int isPrint);

    /**
     * 获取打印货物表单信息
     *
     * @return
     */
    GoodsFrom selectGoodsFromPrint();

    /**
     * 获取货物表单列表
     *
     * @return
     */
    List<GoodsFrom> selectGoodsFromList(String fromNumber, String driverName);

    /**
     * 获取货物表单总条数
     *
     * @return
     */
    int selectCountGoodsFromList(String fromNumber, String driverName);

    /**
     * 根据表单id货物货物详细列表
     *
     * @return
     */
    List<GoodsFromInfo> selectGoodsFromInfo(int formId);

    /**
     * 根据表单id货物货物详细列表总条数
     *
     * @return
     */
    int selectCountGoodsFromInfo(int formId);

}
