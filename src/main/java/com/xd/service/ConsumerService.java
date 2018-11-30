package com.xd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * @ClassName ConsumerService
 * @Description
 * @Author xiaop
 * @Date 2018/11/3010:44
 * Version 1.0
 */
@Service
public class ConsumerService {

    @Resource(name = "jmsTemplate")
    private JmsTemplate jmsTemplate;

    public TextMessage recive(Destination destination){
        TextMessage textMessage= (TextMessage) jmsTemplate.receive(destination);
        try {
            System.out.println("从队列中"+destination.toString()+"收到消息"+textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return textMessage;


    }

}


























