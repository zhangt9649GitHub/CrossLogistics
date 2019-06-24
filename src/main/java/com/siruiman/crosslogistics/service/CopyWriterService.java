package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.CopyWriter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface CopyWriterService {
    /**
     * 获取设置列表
     *
     * @return
     */
    List<CopyWriter> selectCopyWritingList(String language);

    /**
     * 获取内容
     *
     * @param cwId
     * @param cwName
     * @return
     */
    CopyWriter selectContentById(int cwId, String cwName);

    /**
     * 获取文案列表
     *
     * @return
     */
    List<CopyWriter> selectCopyWriterList();

    /**
     * 获取文案列表总行数
     *
     * @return
     */
    Integer selectCopyWriterListCount();

    /**
     * 根据id获取文案内容
     *
     * @param cwId
     * @return
     */
    CopyWriter selectcopyWriterById(int cwId);

    /**
     * 更新文案内容
     *
     * @param copyWriter
     */
    void updateCopyWriter(CopyWriter copyWriter);

    /**
     * 添加文案内容
     *
     * @param copyWriter
     */
    void insertCopyWriter(CopyWriter copyWriter);

    /**
     * 根据id删除文案
     *
     * @param cwId
     */
    void deleteCopyWriterById(int cwId);

    /**
     * 根据语言和类型获取文案内容
     *
     * @return
     */
    CopyWriter selectContentByType(String type, String language);

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
    CopyWriter selectCopyWriter(String type, String language);


}
