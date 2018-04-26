package com.zjitc.dao.imp;

import com.zjitc.dao.CategoryDao;
import com.zjitc.dao.ProductDao;
import com.zjitc.domain.Category;
import com.zjitc.utils.DataSourceFacotry;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/11/15 14:53
 * @description:
 */
public class CategoryDaoImp implements CategoryDao {
    @Override
    public List<Category> findAll() {
        QueryRunner runner = DataSourceFacotry.getRunner();
        String sql = "select * from category";
        try {
            return runner.query(sql,new BeanListHandler<Category>(Category.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Category findByCid(String cid) {
        QueryRunner runner = DataSourceFacotry.getRunner();
        String sql = "select * from category where cid = ?";
        try {
            return runner.query(sql,new BeanHandler<Category>(Category.class),cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(Category category) {
        QueryRunner runner = DataSourceFacotry.getRunner();
        String sql = "insert into category(cid,cname) values(?,?)";
        try {
            runner.update(sql,category.getCid(),category.getCname());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Category category) {
        QueryRunner runner = DataSourceFacotry.getRunner();
        String sql = "update category set cname = ? where cid = ?";
        try {
            runner.update(sql,category.getCname(),category.getCid());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(String cid) {
        QueryRunner runner = DataSourceFacotry.getRunner();
        ProductDao productDao = new ProductDaoImp();
        int i = productDao.updateCategory(cid);
        String sql = "delete from category where cid = ?";
        try {
            runner.update(sql,cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
