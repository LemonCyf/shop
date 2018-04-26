package com.zjitc.dao;

import com.zjitc.domain.User;


/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/11/13 8:42
 * @description:
 */
public interface UserDao {
    User getByName(String username);

    boolean add(User user);

    User getById(String uid);

    void update(User user);
}


