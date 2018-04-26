package com.zjitc.control;

import com.google.gson.Gson;
import com.zjitc.domain.Category;
import com.zjitc.service.CategoryService;
import com.zjitc.service.imp.CategoryServiceImp;
import com.zjitc.servlet.RequestMapping;
import com.zjitc.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/12/23 14:31
 * @description:
 */
public class AdminCategoryContorl {
    @RequestMapping(url = "/adminGetAll")
    public void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryService service = new CategoryServiceImp();
        String ajaxData = request.getParameter("ajaxData");
        if(request.getParameter("clean").equals("yes")){
           service.setClean();
        }
        List<Category> list =  service.getAll();
        request.setAttribute("list",list);
        if(ajaxData == null){
            request.getRequestDispatcher("/admin/category/list.jsp").forward(request,response);
        }else {
            Gson gson = new Gson();
            String jsonString = gson.toJson(list);
            response.getWriter().print(jsonString);
            //request.getRequestDispatcher("admin/product/add.jsp").forward(request,response);
        }
    }
    @RequestMapping(url = "/adminAdd")
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cname = request.getParameter("cname");
        Category category  = new Category();
        category.setCid(Utils.getUUID());
        category.setCname(cname);
        CategoryService service = new CategoryServiceImp();
        service.add(category);
        response.sendRedirect(request.getContextPath()+"/adminGetAll?clean=yes");
    }

    @RequestMapping(url = "/adminGetById")
    public void getById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        CategoryService service = new CategoryServiceImp();
        Category category = service.findByCid(cid);
        request.setAttribute("category",category);
        request.getRequestDispatcher("/admin/category/edit.jsp").forward(request,response);
        System.out.println(category);
    }

    @RequestMapping(url = "/adminUpdate")
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        String cname = request.getParameter("cname");
        Category category = new Category(cid,cname);
        CategoryService service = new CategoryServiceImp();
        service.update(category);
        response.sendRedirect(request.getContextPath()+"/adminGetAll?clean=yes");
    }
    @RequestMapping(url = "/adminDeleteById")
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        CategoryService service = new CategoryServiceImp();
        service.delete(cid);
        response.sendRedirect(request.getContextPath()+"/adminGetAll?clean=yes");
    }
}
