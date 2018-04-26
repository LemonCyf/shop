package com.zjitc.control;

import com.zjitc.domain.Category;
import com.zjitc.domain.History;
import com.zjitc.domain.Page;
import com.zjitc.domain.Product;
import com.zjitc.service.CategoryService;
import com.zjitc.service.ProductService;
import com.zjitc.service.imp.CategoryServiceImp;
import com.zjitc.service.imp.ProductServiceImp;
import com.zjitc.servlet.RequestMapping;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/11/16 9:36
 * @description:
 */
public class ProductControl {
    @RequestMapping(url = "/productInfo")
    public void productInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pid = Integer.parseInt(request.getParameter("pid"));
        ProductService service = new ProductServiceImp();
        Product p = service.findByPid(pid);
        CategoryService categoryService = new CategoryServiceImp();
        Category category = categoryService.findByCid(p.getCid());
        p.setCategory(category);
        request.setAttribute("p",p);

        makeHistory(request.getSession(),p);
        request.getRequestDispatcher("/jsp/product_info.jsp").forward(request,response);
    }
    public void makeHistory(HttpSession session, Product product){
        Object o = session.getAttribute("history");
        History history = null;
        if(o == null){
            history = new History();
            session.setAttribute("history",history);
        }else{
            history =(History)o;
        }

        history.add(product);
    }


    @RequestMapping(url = "/productList")
    public void productList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获得当前页数currPage
        int currPage = Integer.parseInt(request.getParameter("currPage"));
        //获得分类类别
        String cid = request.getParameter("cid");
        ProductService service = new ProductServiceImp();
        Page<Product> page = service.findByPage(currPage,cid,12);
        request.setAttribute("page",page);
        request.setAttribute("cid", cid);
        request.getRequestDispatcher("/jsp/product_list.jsp").forward(request,response);
    }
}
