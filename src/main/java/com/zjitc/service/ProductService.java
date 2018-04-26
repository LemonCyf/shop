package com.zjitc.service;

import com.zjitc.domain.Page;
import com.zjitc.domain.Product;

import java.util.List;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/11/20 10:56
 * @description:
 */
public interface ProductService {
    List<Product> findNew();
    List<Product> findHot();
    Product findByPid(int pid);
    Page<Product> findByPage(int pageIndex,String cid,int i);
    Page<Product> findByPage(int pageIndex,int i);
}
