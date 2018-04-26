package com.zjitc.service;

import com.zjitc.domain.CarItem;

import java.util.List;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/11/30 8:43
 * @description:
 */
public interface CarService {

    void add(String uid, String pid, int count);

    List<CarItem> findAll(String uid);

    void deleteSingle(String uid, String pid);

    void deleteAll(String uid);
}
