package com.zjitc.dao;

import com.zjitc.domain.Order;

import java.util.List;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/12/3 18:48
 * @description:
 */
public interface OrderDao {
    void add(Order order);

    List<Order> findByUid(String uid);

    Order findByOid(String oid);

    int updateByOid(String oid);

    int updateByOid2(String oid);

    void delete(String oid);
}
