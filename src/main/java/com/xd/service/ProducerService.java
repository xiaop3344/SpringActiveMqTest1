package com.xd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * @ClassName ProducerService
 * @Description  生产者推送消息
 * @Author xiaop
 * @Date 2018/11/3010:29
 * Version 1.0
 */
@Service
public class ProducerService {
    @Resource(name = "jmsTemplate")
    private JmsTemplate jmsTemplate;

    //向某个队列发送消息
    public void sendMessage(Destination destination,final String msg){
        System.out.println(Thread.currentThread().getName()+"向队列"+destination+"发送消息--->"+msg);
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });



    }

    //向Mq.xml 中默认的目的地队列发送
    public void sendMessage(final String msg){
        System.out.println(Thread.currentThread().getName()+"向队列发送消息--->"+msg);
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });
    }






}
