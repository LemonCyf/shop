package com.zjitc.control;

import com.zjitc.domain.Page;
import com.zjitc.domain.Product;
import com.zjitc.service.ProductService;
import com.zjitc.service.imp.ProductServiceImp;
import com.zjitc.servlet.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/12/24 18:48
 * @description:
 */
public class AdminProductContorl {
    @RequestMapping(url = "/adminProduct_findAll")
    public void getByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int currPage = Integer.parseInt(request.getParameter("currPage"));
        ProductService service = new ProductServiceImp();
        Page<Product> page = service.findByPage(currPage, 10);
        request.setAttribute("page",page);
        request.getRequestDispatcher("/admin/product/list.jsp").forward(request,response);
    }
    @RequestMapping(url = "/adminProduct_add")
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService service = new ProductServiceImp();
        request.getRequestDispatcher("/adminProduct_findAll?currPage=0").forward(request,response);

    }

}
