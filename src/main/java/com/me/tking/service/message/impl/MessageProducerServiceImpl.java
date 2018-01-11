package com.me.tking.service.message.impl;

import com.me.tking.service.message.MessageProducerService;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

@Service("producer")
public class MessageProducerServiceImpl implements MessageProducerService {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void sendMessage(String message, String destinationName, String messageType) {
        Destination destination = "queue".equals(messageType) ? new ActiveMQQueue(destinationName) : new ActiveMQTopic(destinationName);
        jmsTemplate.convertAndSend(destination, message);
    }
}
