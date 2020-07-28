package com.lzh.service.impl;

import com.lzh.dao.OrderDao;
import com.lzh.dao.TxLogDao;
import com.lzh.domain.Order;
import com.lzh.domain.TxLog;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.UUID;

/**
 * @author: lizehui
 * @create: 2020-07-08 15:15
 */
@Service
public class OrderServiceImpl4 {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private TxLogDao txLogDao;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    public void createOrderBefore(Order order) {

        String txId = UUID.randomUUID().toString();

        //发送半事务消息
        rocketMQTemplate.sendMessageInTransaction(
                "tx_producer_group",
                "tx_topic",
                MessageBuilder.withPayload(order).setHeader("txId", txId).build(),
                order
        );
    }

    @Transactional
    public void createOrder(String txId, Order order) {
        //保存订单
        orderDao.save(order);

        TxLog txLog = new TxLog();
        txLog.setTxId(txId);
        //txLog.setDate(new Date());

        //记录事物日志
        txLogDao.save(txLog);
    }
}
