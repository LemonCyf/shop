package com.zjitc.service.imp;

import com.zjitc.dao.CategoryDao;
import com.zjitc.dao.imp.CategoryDaoImp;
import com.zjitc.domain.Category;
import com.zjitc.service.CategoryService;
import org.apache.log4j.Logger;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/11/15 14:46
 * @description:
 */
public class CategoryServiceImp implements CategoryService {
    private static final Logger logger = Logger.getLogger(CategoryServiceImp.class);
    private static List<Category> categories;
    private static LocalDateTime lastUpTime;
    private static int MAX_TIME = 1000 * 10;
    private boolean clean = false;

    @Override
    public void setClean() {
        this.clean = true;
   }


    @Override
    public List<Category> getAll() {
        if(this.clean = true){
            CategoryServiceImp.categories = null;
        }
        if(categories == null){
            CategoryDao dao = new CategoryDaoImp();
            categories = dao.findAll();
            lastUpTime = LocalDateTime.now();
        }
        Duration duration = Duration.between(lastUpTime,LocalDateTime.now());
        if(Math.abs(duration.toMillis()) > MAX_TIME ){
            CategoryDao dao = new CategoryDaoImp();
            List<Category> c = dao.findAll();
            lastUpTime = LocalDateTime.now();
            logger.warn("read form db>>>>>>>>>>>");
        }
        return categories;
    }

    //在ProductContorl的ProductInfo中用于通过一个Product对象的cid查找到对应分类信息
    @Override
    public Category findByCid(String cid) {
        CategoryDao dao = new CategoryDaoImp();
        return dao.findByCid(cid);
    }

    @Override
    public void add(Category category) {
        CategoryDao dao = new CategoryDaoImp();
        dao.add(category);
    }

    @Override
    public void update(Category category) {
        CategoryDao dao = new CategoryDaoImp();
        dao.update(category);
    }

    @Override
    public void delete(String cid) {
        CategoryDao dao = new CategoryDaoImp();
        dao.deleteById(cid);
    }


}
