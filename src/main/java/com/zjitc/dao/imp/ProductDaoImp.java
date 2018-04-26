package com.zjitc.dao.imp;

import com.zjitc.dao.ProductDao;
import com.zjitc.domain.Product;
import com.zjitc.utils.DataSourceFacotry;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/11/20 10:57
 * @description:
 */
public class ProductDaoImp implements ProductDao {
    @Override
    public List<Product> findNew() {
        QueryRunner runner = DataSourceFacotry.getRunner();
        String sql = "select * from product order by pdate desc limit 9";
        try {
            return runner.query(sql,new BeanListHandler<Product>(Product.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> findHot() {
        QueryRunner runner = DataSourceFacotry.getRunner();
        String sql = "select * from product where is_hot = 1 limit 9";
        try {
            return runner.query(sql,new BeanListHandler<Product>(Product.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product findByPid(int pid) {
        QueryRunner runner = DataSourceFacotry.getRunner();
        String sql = "select * from product where pid = ?";
        try {
            return runner.query(sql,new BeanHandler<Product>(Product.class),pid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> findByPage(String cid, int limitStart, int count) {
        QueryRunner runner = DataSourceFacotry.getRunner();
        String sql = "select * from product where cid = ? limit ?,?";
        try {
            return runner.query(sql,new BeanListHandler<Product>(Product.class),cid,limitStart,count);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public int findTotalCount(String cid) {
        QueryRunner runner = DataSourceFacotry.getRunner();
        String sql = "select count(*) from product where cid = ?;";
        try {
            return Integer.parseInt(String.valueOf(runner.query(sql,new ScalarHandler(),cid)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateCategory(String cid) {
        QueryRunner runner = DataSourceFacotry.getRunner();
        String sql = "update product set cid = null where cid = ?";
        try {
            return runner.update(sql,cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> findByPage(int limitStart, int count) {
        QueryRunner runner = DataSourceFacotry.getRunner();
        String sql = "select * from product limit ?,?";
        try {
            return runner.query(sql,new BeanListHandler<Product>(Product.class),limitStart,count);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public int findTotalCount() {
        QueryRunner runner = DataSourceFacotry.getRunner();
        String sql = "select count(*) from product;";
        try {
            return Integer.parseInt(String.valueOf(runner.query(sql,new ScalarHandler())));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
