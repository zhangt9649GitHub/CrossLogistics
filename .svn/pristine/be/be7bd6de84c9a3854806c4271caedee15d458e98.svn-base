package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.EntryAndExit;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 出入境物流途径业务层接口
 */
@Service
public interface EntryAndExitService {
    /**
     * 获取出入境物流途径参数配置列表
     *
     * @param exitWay
     * @return
     */
    List<EntryAndExit> getEntryAndExitList(Integer exitWay);

    /**
     * 根据Id查询出入境物流途径参数配置信息
     *
     * @param eaeId
     * @return
     */
    EntryAndExit selectEntryAndExitById(Integer eaeId);

    /**
     * 更新出入境物流途径参数配置信息
     *
     * @param entryAndExit
     * @return
     */
    void updateEntryAndExit(EntryAndExit entryAndExit);

    /**
     * 获取出入境物流途径参数配置列表总行数
     *
     * @param exitWay
     * @return
     */
    int selectEntryAndExitCount(Integer exitWay);

    /**
     * 添加出入境物流途径参数
     *
     * @param entryAndExit
     */
    void insertBizdictionary(EntryAndExit entryAndExit);

    /**
     * 删除出入境物流途径参数
     *
     * @param eaeId
     */
    void deleteEntryAndExitById(Integer eaeId);
}
