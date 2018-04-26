package com.zjitc.service.imp;

import com.zjitc.dao.OrderDao;
import com.zjitc.dao.imp.OrderDaoImp;
import com.zjitc.domain.Order;
import com.zjitc.domain.Page;
import com.zjitc.service.OrderService;

import java.util.ArrayList;
import java.util.List;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/12/3 18:47
 * @description:
 */
public class OrderServiceImp implements OrderService {
    @Override
    public void add(Order order) {
        OrderDao orderDao = new OrderDaoImp();
        orderDao.add(order);
    }

    @Override
    public List<Order> findByUid(String uid) {
        OrderDao orderDao = new OrderDaoImp();
        return orderDao.findByUid(uid);
    }

    @Override
    public Page<Order> findLimit(String uid, int currPage) {
        List<Order> orders = findByUid(uid);

        Page<Order> page = new Page<>();

        // 统计一下一共由多少页
        // 1. 每页的产品数量不超过10个
        // 2. 如果超过10个订单，刚好是某一页的第一个，单独成一页
        // 3. 每页订单的总数，不小于2个
        List<Order> pageOds = new ArrayList<>();
        int pdCount = 0;       // 当前页的产品数量
        int odCount = 0;       // 当前页的订单数量
        int pageNum = 0;       // 分页数量

        for (Order o : orders) {
            pdCount += o.getItems().size();
            odCount++;

            if (pageNum == currPage) {
                pageOds.add(o);
            }

            // 只需要判断当前页面的产品数量是否大于10即可
            if (pdCount > 10) {
                if (odCount <= 2) {
                    // 如果订单数小于等于2
                    pdCount = 0;
                    odCount = 0;
                } else {
                    // 此时订单数量大于2，
                    // 最近的一个订单，应该属于下一个页面，所以pdCount = o.getItems().size();
                    pdCount = o.getItems().size();

                    if (pageOds.size() > 0) {
                        if (pageNum ==  currPage) {
                            pageOds.remove(o);
                        }
                    } else {
                        pageOds.add(o);
                    }

                    odCount = 1;
                }

                pageNum++;
            }
        }

        page.setCurrPage( currPage);
        page.setList(pageOds);
        page.setTotal(pageNum + 1);

        // 1 2 3 4 5 6 7 8 9
        // 用户选择的永远在中间
        // pageId = 0 1 2 3 4 5 6   7
        // start  = 0 0 0 0 0 1 2   3
        // end    = 8 8 8 8 8 9 10  11
        int start =  currPage - 4;
        if (start < 0) {
            start = 0;
        }

        int end = start + 8;
        if (end > page.getTotal()) {
            end = page.getTotal();
        }

        while (end - start < 7) {
            if (start > 0) {
                start--;
            }

            if (start == 0) {
                break;
            }
        }

        page.setStart(start);
        page.setEnd(end);
        return page;
    }

    @Override
    public Order findByOid(String oid) {
        OrderDao orderDao = new OrderDaoImp();
        return orderDao.findByOid(oid);
    }

    @Override
    public int updateByOid(String oid) {
        OrderDao orderDao = new OrderDaoImp();
        return orderDao.updateByOid(oid);
    }

    @Override
    public int updateByOid2(String oid) {
        OrderDao orderDao = new OrderDaoImp();
        return orderDao.updateByOid2(oid);
    }

    @Override
    public void delete(String oid) {
        OrderDao orderDao = new OrderDaoImp();
        orderDao.delete(oid);
    }


}
