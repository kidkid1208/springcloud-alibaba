package com.lzh.dao;

import com.lzh.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: lizehui
 * @create: 2020-07-03 09:54
 */
public interface ProductDao extends JpaRepository<Product,Integer> {
}
