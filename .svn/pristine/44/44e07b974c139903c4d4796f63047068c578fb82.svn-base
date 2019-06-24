package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.MessagePushMapper;
import com.siruiman.crosslogistics.model.MessagePush;
import com.siruiman.crosslogistics.service.MessagePushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessagePushServiceImpl implements MessagePushService {

    @Autowired
    private MessagePushMapper messagePushMapper;

    @Override
    @Transactional
    public MessagePush selectMessagePushByTime() {
            return messagePushMapper.selectMessagePushByTime();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateById(int messagePushId) {
        messagePushMapper.updateById(messagePushId);
    }
}
