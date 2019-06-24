package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.CopyWriterMapper;
import com.siruiman.crosslogistics.model.CopyWriter;
import com.siruiman.crosslogistics.service.CopyWriterService;
import com.siruiman.crosslogistics.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
public class CopyWriterServiceImpl implements CopyWriterService {
    @Autowired
    private CopyWriterMapper copyWriterMapper;

    @Override
    public List<CopyWriter> selectCopyWritingList(String language) {
        try {
            return copyWriterMapper.selectCopyWritingList(language);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public CopyWriter selectContentById(int cwId, String cwName) {
        try {
            return copyWriterMapper.selectContentById(cwId, cwName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<CopyWriter> selectCopyWriterList() {
        try {
            return copyWriterMapper.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer selectCopyWriterListCount() {
        try {
            return copyWriterMapper.selectCopyWriterListCount();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public CopyWriter selectcopyWriterById(int cwId) {
        try {
            return copyWriterMapper.selectByPrimaryKey(cwId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateCopyWriter(CopyWriter copyWriter) {
        copyWriterMapper.updateByPrimaryKey(copyWriter);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertCopyWriter(CopyWriter copyWriter) {
        copyWriterMapper.insert(copyWriter);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteCopyWriterById(int cwId) {
        copyWriterMapper.deleteByPrimaryKey(cwId);
    }

    @Override
    public CopyWriter selectContentByType(String type, String language) {
        try {
            return copyWriterMapper.selectContentByType(language, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CopyWriter> selectCopyWriterTypeList() {
        try {
            return copyWriterMapper.selectCopyWriterTypeList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CopyWriter selectCopyWriter(String type, String language) {
        try {
            return copyWriterMapper.selectCopyWriter(language, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
