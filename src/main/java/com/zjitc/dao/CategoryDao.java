package com.zjitc.dao;

import com.zjitc.domain.Category;

import java.util.List;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/11/15 14:51
 * @description:
 */
public interface CategoryDao {
    List<Category> findAll();

    Category findByCid(String cid);

    void add(Category category);

    void update(Category category);

    void deleteById(String cid);
}
