package com.zjitc.control;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zjitc.domain.Category;
import com.zjitc.service.CategoryService;
import com.zjitc.service.imp.CategoryServiceImp;
import com.zjitc.servlet.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.util.List;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/11/15 17:28
 * @description:
 */
public class CategoryContorl {
    @RequestMapping(url = "/category")
    public void category(HttpServletRequest request, HttpServletResponse response) throws HTTPException, IOException {
        CategoryService service = new CategoryServiceImp();
        List<Category> c = service.getAll();
        Gson gson = new GsonBuilder().create();
        String jsonText = gson.toJson(c);
        response.setHeader("Content-type","html/text;charset=utf-8");
        response.getWriter().println(jsonText);
    }

}
