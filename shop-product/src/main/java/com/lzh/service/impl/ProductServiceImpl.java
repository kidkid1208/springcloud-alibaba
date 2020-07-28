package com.lzh.service.impl;

import com.lzh.dao.ProductDao;
import com.lzh.domain.Product;
import com.lzh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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

    @Override
    @Transactional(readOnly = false)
    public void reduceInventory(Integer pid, int num) {
        // 查询
        Product product = productDao.findById(pid).get();
        // 内存中扣减
        product.setStock(product.getStock() - num);

        // 模拟异常
        int i = 1 / 0;

        //减库存
        productDao.save(product);
    }
}
