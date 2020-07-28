package com.lzh.test;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * @author: lizehui
 * @create: 2020-07-08 11:30
 */
public class RocketMQSendMessageTest {
    public static void main(String[] args) throws Exception {
        // 1.创建消息生产者，并设置生产组名
        DefaultMQProducer producer = new DefaultMQProducer("myproducer-group");
        // 2.为生产者设置NameServer的地址
        producer.setNamesrvAddr("172.18.10.10:9876");
        // 3.启动生产者
        producer.start();

        //4. 创建消息对象，指定主题、标签和消息体
        Message msg = new Message("myTopic", "myTag", ("RocketMQ Message").getBytes());

        //5. 发送消息
        SendResult sendResult = producer.send(msg, 10000);
        System.out.println(sendResult);

        //6. 关闭生产者
        producer.shutdown();
    }
}
