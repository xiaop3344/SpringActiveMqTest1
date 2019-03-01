package com.xd.controller;

import com.xd.service.ConsumerService;
import com.xd.service.ProducerService;
import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * @ClassName controller
 * @Description
 * @Author xiaop
 * @Date 2018/11/3010:48
 * Version 1.0
 */
@Controller
public class MessageController {

    private Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Resource(name = "producerService")
    private ProducerService producerService;

    @Resource(name = "consumerService")
    private ConsumerService consumerService;

    @Resource(name = "demoQueueDestination")
    private Destination destination;  //为xml配置文件中的 目的队列 也可自行定义其他队列


    /**
    *
    *@author:xiaop
    *@Date:2019/3/1
    *@Description:  发送消息
    */
    @RequestMapping(value = "/sendMessage",method = RequestMethod.POST)
    @ResponseBody
    public void send(String msg){
        logger.info(Thread.currentThread().getName()+"------------send to jms Start");
        producerService.sendMessage(msg);
        logger.info(Thread.currentThread().getName()+"------------send to jms End");
    }

    @RequestMapping(value = "recieveMessage",method = RequestMethod.GET)
    @ResponseBody
    public String recieve(){
        logger.info(Thread.currentThread().getName()+"------------send to jms Start");
        TextMessage tm = consumerService.recive(destination);   //从某个队列中 取出消息
        logger.info(Thread.currentThread().getName()+"------------send to jms End");
        String message=null;
        try {
            message=tm.getText().toString();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return message;
    }


    @RequestMapping(value = "/sendMessageDemo",method = RequestMethod.POST)
    @ResponseBody
    public void send(String msg,String queueName){
        ActiveMQQueue destinationQueue =new ActiveMQQueue(queueName);
        logger.info(Thread.currentThread().getName()+"------------send to jms Start");
        producerService.sendMessage(destinationQueue,msg);
        logger.info(Thread.currentThread().getName()+"------------send to jms End");
    }



    @RequestMapping(value = "recieveMessageDemo",method = RequestMethod.GET)
    @ResponseBody
    public String recieve(String queueName){
        logger.info(Thread.currentThread().getName()+"------------send to jms Start");

        TextMessage tm = consumerService.recive(new ActiveMQQueue(queueName));   //从某个队列中 取出消息
        logger.info(Thread.currentThread().getName()+"------------send to jms End");
        String message=null;
        try {
            message=tm.getText().toString();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return message;
    }





}
