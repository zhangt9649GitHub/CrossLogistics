package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.UploadFiles;
import org.springframework.stereotype.Service;

@Service
public interface UploadFilesService {
    /**
     * 根据id查询图片路径
     *
     * @param ufId
     * @return
     */
    UploadFiles selectUfPathById(Integer ufId);
}
