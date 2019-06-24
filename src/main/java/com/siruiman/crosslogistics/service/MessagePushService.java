package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.MessagePush;
import org.springframework.stereotype.Service;

@Service
public interface MessagePushService {
    /**
     * 查询今日最早需要再次推送的消息
     *
     * @return
     */
    MessagePush selectMessagePushByTime();

    /**
     * 修改是否需要再次推送为否
     */
    void updateById(int messagePushId);
}
