package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.Forwarding;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public interface ForwardingService {

    /**
     * 根据代运状态与app用户id获取转运列表
     *
     * @param state
     * @param appUserId
     * @return
     */
    List<Forwarding> selectPublishedForwardingList(String state, int appUserId);

    /**
     * 根据代运状态与app用户id获取转运列表总行数
     *
     * @param state
     * @param appUserId
     * @return
     */
    int selectPublishedForwardingListCount(String state, int appUserId);


}
