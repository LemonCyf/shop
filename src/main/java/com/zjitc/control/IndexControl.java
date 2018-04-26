package com.zjitc.control;


import com.zjitc.domain.Product;
import com.zjitc.service.ProductService;
import com.zjitc.service.imp.ProductServiceImp;
import com.zjitc.servlet.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.util.List;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/11/12 16:35
 * @description:
 */
public class IndexControl {
    @RequestMapping(url = "/index")
    public void index(HttpServletRequest request, HttpServletResponse response) throws HTTPException, ServletException, IOException {
        // CategoryService service = new CategoryServiceImp();
        // List<Category> c = service.getAll();
        // request.setAttribute("c",c);
        // request.getRequestDispatcher("/jsp/payIndex.jsp").forward(request,response);

        ProductService service = new ProductServiceImp();

        //最新商品
        List<Product> newList = service.findNew();
        //热门商品
        List<Product> hotList = service.findHot();
        //放入域中
        request.setAttribute("new1",newList);
        request.setAttribute("hot",hotList);
        request.getRequestDispatcher("/jsp/index.jsp").forward(request,response);
    }
}
