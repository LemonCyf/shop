package com.zjitc.service;

import com.zjitc.domain.Category;

import java.util.List;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/11/15 14:45
 * @description:
 */
public interface CategoryService {
    List<Category> getAll();

    Category findByCid(String cid);

    void add(Category category);

    void setClean();

    void update(Category category);

    void delete(String cid);
}
