package com.zjitc.dao.imp;

import com.zjitc.dao.UserDao;
import com.zjitc.domain.User;
import com.zjitc.utils.DataSourceFacotry;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/11/13 8:42
 * @description:
 */
public class UserDaoImp implements UserDao{
    @Override
    public User getByName(String username){
        QueryRunner runner = DataSourceFacotry.getRunner();
        String sql = "select * from user where username = ?";
        try {
            User u = runner.query(sql, new BeanHandler<User>(User.class), username);
            return u;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean add(User user) {
        QueryRunner runner = DataSourceFacotry.getRunner();
        String sql = "insert into user(uid,username,password,name,email,telephone,birthday,sex,state,code) values(?,?,?,?,?,?,?,?,?,?)";
        try {
            int num = runner.update(sql, user.getUid(), user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), user.getTelephone(), user.getBirthday(), user.getSex(), user.getState(), user.getCode());
            return num==1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getById(String uid) {
        QueryRunner runner = DataSourceFacotry.getRunner();
        String sql = "select * from user where uid = ?";
        try {
            User query = runner.query(sql, new BeanHandler<User>(User.class), uid);
            return query;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user) {
        QueryRunner runner = DataSourceFacotry.getRunner();
        String sql = "update user set username=?,password=?,name=?,email=?,telephone=?,birthday=?,sex=?,state=?,code=? where uid = ?";
        try {
            runner.update(sql, user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), user.getTelephone(), user.getBirthday(), user.getSex(), user.getState(), user.getCode(),user.getUid());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
