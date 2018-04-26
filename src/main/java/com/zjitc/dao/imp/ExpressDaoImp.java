package com.zjitc.dao.imp;

import com.zjitc.dao.ExpressDao;
import com.zjitc.domain.Express;
import com.zjitc.utils.DataSourceFacotry;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/12/8 18:59
 * @description:
 */
public class ExpressDaoImp implements ExpressDao {
    @Override
    public void insert(String eid, String oid) {
        QueryRunner runner = DataSourceFacotry.getRunner();
        String sql = "insert into express.properties(eid,oid) values(?,?)";
        try {
            runner.update(sql,eid,oid);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteByOid(String out_trade_no) {
        QueryRunner runner = DataSourceFacotry.getRunner();
        String sql = "delete from express.properties where oid = ?";
        try {
            runner.update(sql,out_trade_no);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Express getByOid(String oid) {
        QueryRunner runner = DataSourceFacotry.getRunner();
        String sql = "select *  from express.properties where oid = ?";
        try {
            return runner.query(sql,new BeanHandler<>(Express.class),oid);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
