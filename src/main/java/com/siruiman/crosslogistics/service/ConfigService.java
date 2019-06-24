package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.SysConfig;

import java.util.List;

/**
 * @author 张占伟
 * @date 2019/1/15 14:07
 */
public interface ConfigService {


    /**
     * 查出所有的系统参数
     * @return
     */
    List<SysConfig> selectAll();

    /**
     * 修改系统参数
     * @param key
     * @param value
     */
    void findAndUpdateByKey(String key,String value);

    /**
     * 初始化加载配置
     */
    void loadConfig();
}
