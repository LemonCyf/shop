package com.zjitc.control;

import com.google.gson.Gson;
import com.zjitc.domain.Express;
import com.zjitc.domain.ExpressGson;
import com.zjitc.express.KdniaoTrackQueryAPI;
import com.zjitc.service.ExpressService;
import com.zjitc.service.imp.ExpressServiceImp;
import com.zjitc.servlet.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;
import java.io.IOException;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/12/8 14:45
 * @description:
 */
public class ExpressContorl {
    //DEMO
    @RequestMapping(url = "/search")
    public void search(HttpServletRequest request, HttpServletResponse response) throws HTTPException, ServletException, IOException {
        String oid = request.getParameter("oid");
        KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
        try {
            ExpressService expressService = new ExpressServiceImp();
            Express express = expressService.getByOid(oid);
            //String result = api.getOrderTracesByJson("YD", express.properties.getEid());
            String result = api.getOrderTracesByJson("YD", "3908640297037");
            Gson gson = new Gson();
            ExpressGson gsonObject = gson.fromJson(result, ExpressGson.class);
            if(gsonObject.isSuccess()) {
                request.setAttribute("gsonObject",gsonObject);
                request.getRequestDispatcher("/jsp/express.properties.jsp").forward(request,response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
