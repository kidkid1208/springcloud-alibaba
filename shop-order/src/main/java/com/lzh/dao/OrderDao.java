package com.lzh.dao;

import com.lzh.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: lizehui
 * @create: 2020-07-03 09:54
 */
public interface OrderDao extends JpaRepository<Order,Integer> {
}
