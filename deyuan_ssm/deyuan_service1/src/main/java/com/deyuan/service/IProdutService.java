package com.deyuan.service;

import com.deyuan.pojo.Product;

import java.util.List;

public interface IProdutService {
    List<Product> findAll(int page,int size);

    void save(Product product);
}
