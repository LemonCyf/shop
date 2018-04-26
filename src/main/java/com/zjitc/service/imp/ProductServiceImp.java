package com.zjitc.service.imp;

import com.zjitc.dao.ProductDao;
import com.zjitc.dao.imp.ProductDaoImp;
import com.zjitc.domain.Page;
import com.zjitc.domain.Product;
import com.zjitc.service.ProductService;

import java.util.List;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/11/20 10:56
 * @description:
 */
public class ProductServiceImp implements ProductService{
    @Override
    public List<Product> findNew() {
        ProductDao productDao = new ProductDaoImp();
        return productDao.findNew();
    }

    @Override
    public List<Product> findHot() {
        ProductDao productDao = new ProductDaoImp();
        return productDao.findHot();
    }

    @Override
    public Product findByPid(int pid) {
        ProductDao productDao = new ProductDaoImp();
        return productDao.findByPid(pid);
    }

    @Override
    public Page<Product> findByPage(int currPage,String cid,int count) {
        ProductDao productDao = new ProductDaoImp();
        Page<Product> page = new Page<Product>();
        /*属性有
        传入的有：       currPage 当前页数
                        count 每页显示几条

        未传入 要查询：  list 当前页数据
                        total 页数 要根据allCount(总条数需要查询)/count(每页显示几条)
        计算：
         */
        int limitStart = currPage * count;
        page.setCurrPage(currPage);
        List<Product> list = productDao.findByPage(cid,limitStart,count);


        page.setList(list);
        int allCount = productDao.findTotalCount(cid);
        int pageCount = (int) Math.ceil(allCount*1.0/count);
        page.setTotal(pageCount);

        // 1 2 3 4 5 6 7 8 9
        // 用户选择的永远在中间
        // currPage = 0 1 2 3 4 5 6   7
        // start  = 0 0 0 0 0 1 2   3
        // end    = 8 8 8 8 8 9 10  11
        int start = currPage - 4;
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
    public Page<Product> findByPage(int currPage,int count) {
        ProductDao productDao = new ProductDaoImp();
        Page<Product> page = new Page<Product>();
        /*属性有
        传入的有：       currPage 当前页数
                        count 每页显示几条

        未传入 要查询：  list 当前页数据
                        total 页数 要根据allCount(总条数需要查询)/count(每页显示几条)
        计算：
         */
        int limitStart = currPage * count;
        page.setCurrPage(currPage);
        List<Product> list = productDao.findByPage(limitStart,count);


        page.setList(list);
        int allCount = productDao.findTotalCount();
        int pageCount = (int) Math.ceil(allCount*1.0/count);
        page.setTotal(pageCount);

        // 1 2 3 4 5 6 7 8 9
        // 用户选择的永远在中间
        // currPage = 0 1 2 3 4 5 6   7
        // start  = 0 0 0 0 0 1 2   3
        // end    = 8 8 8 8 8 9 10  11
        int start = currPage - 4;
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

}
