package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.UploadFilesMapper;
import com.siruiman.crosslogistics.model.UploadFiles;
import com.siruiman.crosslogistics.service.UploadFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadFilesServiceImpl implements UploadFilesService {
    @Autowired
    private UploadFilesMapper uploadFilesMapper;

    @Override
    public UploadFiles selectUfPathById(Integer ufId) {
        try {
            return uploadFilesMapper.selectByPrimaryKey(ufId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
