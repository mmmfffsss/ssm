package com.deyuan.service.Impl;

import com.deyuan.dao.OrdersDao;
import com.deyuan.pojo.Orders;
import com.deyuan.service.OrdersService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 孟哥
 * <p>
 * 2020/10/29
 */
@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersDao ordersDao;

    @Override
    public List<Orders> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(String orderId) throws Exception {
        return ordersDao.findByOrderId(orderId);
    }


}
