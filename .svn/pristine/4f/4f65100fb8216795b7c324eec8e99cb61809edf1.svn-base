package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.ForwardingMapper;
import com.siruiman.crosslogistics.model.Forwarding;
import com.siruiman.crosslogistics.service.ForwardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForwardingServiceImpl implements ForwardingService {

    @Autowired
    private ForwardingMapper forwardingMapper;

    @Override
    public List<Forwarding> selectPublishedForwardingList(String state, int appUserId) {
        try {
            return forwardingMapper.selectPublishedForwardingList(state, appUserId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int selectPublishedForwardingListCount(String state, int appUserId) {
        try {
            return forwardingMapper.selectPublishedForwardingListCount(state, appUserId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


}
