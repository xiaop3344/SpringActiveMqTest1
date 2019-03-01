package com.xd.filter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @ClassName QueueMessageListener
 * @Description
 * @Author xiaop
 * @Date 2018/11/30 13:13
 * Version 1.0
 *想要使用监听器 需要打开mq xml中的监听器 配置文件
 *
 */
public class QueueMessageListener implements MessageListener {


    @Override
    public void onMessage(Message message) {
        TextMessage tm= (TextMessage) message;
        try {
            String msg = tm.getText();
            System.out.println("监听器监听到消息"+msg);
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
