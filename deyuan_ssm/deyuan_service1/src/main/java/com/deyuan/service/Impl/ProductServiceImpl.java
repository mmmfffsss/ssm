package com.deyuan.service.Impl;

import com.deyuan.dao.ProductDao;
import com.deyuan.pojo.Product;
import com.deyuan.service.IProdutService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 孟哥
 * <p>
 * 2020/10/28
 */
@Service
@Transactional
public class ProductServiceImpl implements IProdutService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> findAll(int page,int size) {
        PageHelper.startPage(page,size);
        return productDao.findAll();
    }

    @Override
    public void save(Product product) {
        productDao.save(product);

    }

}
