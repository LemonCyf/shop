package com.zjitc.control;

import com.zjitc.domain.CarItem;
import com.zjitc.domain.User;
import com.zjitc.service.CarService;
import com.zjitc.service.imp.CarServiceImp;
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
 * @create-Time:2017/11/30 8:38
 * @description:
 */
public class CarContorl {
    @RequestMapping(url = "/add2Car")
    public void add(HttpServletRequest request, HttpServletResponse response) throws HTTPException, ServletException, IOException {
        Object o = request.getSession().getAttribute("user");
        if(o == null){
            String msg= "请先登陆，再操作购物车";
            String address = request.getContextPath()+"/jsp/login.jsp";
            request.setAttribute("msg",msg);
            request.setAttribute("address",address);
            request.getRequestDispatcher("/jsp/msg.jsp").forward(request,response);
        }else{
            User user = (User)o;
            String pid = request.getParameter("pid");
            int count = Integer.parseInt(request.getParameter("count"));
            CarService service = new CarServiceImp();
            service.add(user.getUid(),pid,count);
            response.sendRedirect("/shopCar");
        }
    }

    @RequestMapping(url = "/shopCar")
    public void shopCar(HttpServletRequest request, HttpServletResponse response) throws HTTPException, ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        CarService service = new CarServiceImp();
        List<CarItem> items = service.findAll(user.getUid());
        //总价需要计算
        double total = 0.0;
        for(CarItem item :items){
            total += item.getPrice();
        }
        request.setAttribute("car",items);
        request.setAttribute("total" ,total);
        request.getRequestDispatcher("/jsp/cart.jsp").forward(request,response);
    }
    @RequestMapping(url = "/deleteSingle")
    public void deleteSingle(HttpServletRequest request, HttpServletResponse response) throws HTTPException, ServletException, IOException {
        String pid = request.getParameter("pid");
        String uid = request.getParameter("uid");
        CarService service = new CarServiceImp();
        service.deleteSingle(uid,pid);
        response.getWriter().println(pid);
    }
    @RequestMapping(url = "/deleteAll")
    public void deleteAll(HttpServletRequest request, HttpServletResponse response) throws HTTPException, ServletException, IOException {
        String uid = request.getParameter("uid");
        CarService service = new CarServiceImp();
        service.deleteAll(uid);
        response.getWriter().println();
    }
}
