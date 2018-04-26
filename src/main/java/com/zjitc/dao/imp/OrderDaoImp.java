package com.zjitc.dao.imp;

import com.zjitc.dao.OrderDao;
import com.zjitc.domain.Order;
import com.zjitc.domain.OrderItem;
import com.zjitc.domain.Product;
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
 * @create-Time:2017/12/3 18:48
 * @description:
 */
public class OrderDaoImp implements OrderDao{

    @Override
    public void add(Order order) {
        QueryRunner runner = DataSourceFacotry.getRunner();
        String sql = "insert into `order`(oid,ordertime,total,state,address,name,telephone,uid) values(?,?,?,?,?,?,?,?)";
        try {
            runner.update(sql,order.getOid(),order.getOrdertime(),order.getTotal(),order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getUid());
            sql = "insert into orderitem(itemid,count,subtotal,pid,oid) values(?,?,?,?,?)";
            for(OrderItem item:order.getItems()){
                runner.update(sql,item.getItemid(),item.getCount(),item.getSubtotal(),item.getPid(),item.getOid());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> findByUid(String uid){
        QueryRunner runner = DataSourceFacotry.getRunner();
        String sql = "select * from `order` where uid = ? order by ordertime desc";
        try {
            List<Order> orders = runner.query(sql,new BeanListHandler<>(Order.class),uid);
            for(int i = 0;i < orders.size();i++){
                Order o = orders.get(i);
                List<OrderItem> orderItems = findOrderItemsByOid(runner,o.getOid());
                for(int j = 0 ;j < orderItems.size();j++){
                    OrderItem item = orderItems.get(j);
                    Product pd = findProductByPid(runner, item.getPid());
                    item.setProduct(pd);
                }
                o.setItems(orderItems);
            }
            return orders;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Order findByOid(String oid) {
        QueryRunner runner = DataSourceFacotry.getRunner();
        String sql = "select * from `order` where oid = ?";
        try {
            Order order = runner.query(sql, new BeanHandler<Order>(Order.class), oid);
            List<OrderItem> orderItems = findOrderItemsByOid(runner, order.getOid());
            for(int i = 0;i < orderItems.size();i++){
                OrderItem orderItem = orderItems.get(i);
                Product pd = findProductByPid(runner, orderItem.getPid());
                orderItem.setProduct(pd);
                order.setItems(orderItems);
            }
            return order;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public int updateByOid(String oid) {
        QueryRunner runner = DataSourceFacotry.getRunner();
        String sql = "update `order` set state = 1 where oid = ?";
        try {
            return runner.update(sql,oid);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public int updateByOid2(String oid) {
        QueryRunner runner = DataSourceFacotry.getRunner();
        //2表示已经退款
        String sql = "update `order` set state = 2 where oid = ?";
        try {
            return runner.update(sql,oid);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void delete(String oid) {
        QueryRunner runner = DataSourceFacotry.getRunner();
        String sql = "delete from orderitem where oid = ?";
        try {
            runner.update(sql,oid);
            sql = "delete from `order` where oid = ?";
            runner.update(sql,oid);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }


    private Product findProductByPid(QueryRunner runner, String pid) throws SQLException {
        String sql = "select * from product where pid = ?";
        return runner.query(sql,new BeanHandler<>(Product.class),pid);
    }
    private List<OrderItem> findOrderItemsByOid(QueryRunner runner, String oid) throws SQLException {
        String sql = "select * from orderitem where oid = ?";
        return runner.query(sql,new BeanListHandler<>(OrderItem.class),oid);
    }
}
