package com.zjitc.dao;

import com.zjitc.domain.CarItem;

import java.util.List;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/11/30 8:51
 * @description:
 */
public interface CarDao {
    void add(String uid, int count, String pid);

    List<CarItem> findAll(String uid);

    void deleteSingle(String uid, String pid);

    void deleteAll(String uid);
}
