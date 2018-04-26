package com.zjitc.dao;

import com.zjitc.domain.Product;

import java.util.List;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/11/20 10:57
 * @description:
 */
public interface ProductDao {
    List<Product> findNew();
    List<Product> findHot();

    Product findByPid(int pid);

    List<Product> findByPage(String cid, int limitStart, int count);
    int findTotalCount(String cid);

    int updateCategory(String cid);

    List<Product> findByPage(int limitStart, int count);
    int findTotalCount();
}
