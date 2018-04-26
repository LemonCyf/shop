package com.zjitc.service;

import com.zjitc.domain.Order;
import com.zjitc.domain.Page;

import java.util.List;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/12/3 18:44
 * @description:
 */
public interface OrderService {
    void add(Order order);
    List<Order> findByUid(String uid);

    Page<Order> findLimit(String uid, int currPage);

    Order findByOid(String oid);

    int updateByOid(String oid);

    int updateByOid2(String oid);

    void delete(String oid);
}
