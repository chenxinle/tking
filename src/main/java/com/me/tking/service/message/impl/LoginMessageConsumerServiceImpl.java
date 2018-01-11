package com.me.tking.service.message.impl;

import com.me.tking.service.message.LoginMessageConsumerService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service("consumer")
public class LoginMessageConsumerServiceImpl implements LoginMessageConsumerService {
    @Override
    @JmsListener(destination = "login.queue")
    public void receiveQueue(String text) {
        System.out.println("Consumer收到的报文为:"+text);
    }
}
