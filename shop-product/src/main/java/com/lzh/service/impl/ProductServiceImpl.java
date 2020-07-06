package com.lzh.service.impl;

import com.lzh.dao.ProductDao;
import com.lzh.domain.Product;
import com.lzh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: lizehui
 * @create: 2020-07-03 09:54
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product findByPid(Integer pid) {
        return productDao.findById(pid).get();
    }
}
