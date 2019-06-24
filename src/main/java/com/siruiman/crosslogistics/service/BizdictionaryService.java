package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.Bizdictionary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典数据业务层接口
 */
@Service
public interface BizdictionaryService {
    /**
     * 获取字典数据
     */
    List<Bizdictionary> getBizdictionaryList();

    /**
     * 查询字节点数据
     *
     * @param parentId
     * @return
     */
    List<Bizdictionary> selectBizdictionaryByParentId(Integer parentId);

    /**
     * 添加数据字典
     */
    void insertBizdictionary(Bizdictionary bizdictionary);

    /**
     * 根据id查询旧的数据字典信息
     */
    Bizdictionary selectBizdictionary(Integer id);

    /**
     * 修改数据字典信息
     *
     * @param bizdictionary
     */
    void updateBizdictionary(Bizdictionary bizdictionary);

    /**
     * 根据id删除以此id为父节点的所有id
     */
    //void deleteBizdictionaryByParentId(Integer id);

    /**
     * 根据id删除数据字典信息
     */
    void deleteBizdictionaryById(Integer id);

    /**
     * 获取转运须知物流信息模板
     *
     * @param
     * @return
     */
    Bizdictionary getBizdictionaryById(Integer id);


}
