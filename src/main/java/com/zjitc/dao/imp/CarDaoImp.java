package com.zjitc.dao.imp;

import com.zjitc.dao.CarDao;
import com.zjitc.domain.Car;
import com.zjitc.domain.CarItem;
import com.zjitc.domain.Product;
import com.zjitc.utils.DataSourceFacotry;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/11/30 8:53
 * @description:
 */
public class CarDaoImp implements CarDao {
    @Override
    public void add(String uid, int count, String pid) {
        QueryRunner runner = DataSourceFacotry.getRunner();
        String sql = "select * from car where uid = ? and pid = ?";
        try {
            Car car = runner.query(sql, new BeanHandler<Car>(Car.class), uid, pid);
            if(car == null){
               sql = "insert into car(uid,count,pid) values(?,?,?)";
               runner.update(sql,uid,count,pid);
            }else{
                count += car.getCount();
                sql = "update car set count = ? where uid = ? and pid = ?";
                runner.update(sql,count,uid,pid);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<CarItem> findAll(String uid) {
        QueryRunner runner = DataSourceFacotry.getRunner();
        String sql = "select * from car where uid = ?";
        try {
            List<Car> cars = runner.query(sql, new BeanListHandler<Car>(Car.class), uid);
            sql = "select * from product where pid = ?";
            List<CarItem> items = new ArrayList<>();
            for(Car car: cars){
                Product p = runner.query(sql, new BeanHandler<Product>(Product.class), car.getPid());
                items.add(new CarItem(car.getCount(),car.getCount()*p.getShop_price(),p));
            }
            return items;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteSingle(String uid, String pid) {
        QueryRunner runner = DataSourceFacotry.getRunner();
        String sql = "delete from car where uid = ? and pid = ?";
        try {
            runner.update(sql,uid,pid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAll(String uid) {
        QueryRunner runner = DataSourceFacotry.getRunner();
        String sql = "delete from car where uid = ?";
        try {
            runner.update(sql,uid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
