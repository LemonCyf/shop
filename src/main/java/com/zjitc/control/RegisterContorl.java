package com.zjitc.control;

import com.zjitc.domain.User;
import com.zjitc.service.UserService;
import com.zjitc.service.imp.UserServiceImp;
import com.zjitc.servlet.RequestMapping;
import com.zjitc.utils.DateConvert;
import com.zjitc.utils.Utils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Service;
import javax.xml.ws.http.HTTPException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Random;

import static com.zjitc.utils.Utils.encoderByMd5;
import static com.zjitc.utils.Utils.getUUID;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/11/13 8:39
 * @description:注册
 */
public class RegisterContorl {
    private static final Logger log = Logger.getLogger(RegisterContorl.class);

    @RequestMapping(url = "/register")
    public void register(HttpServletRequest request, HttpServletResponse response) throws HTTPException, InvocationTargetException, IllegalAccessException, IOException, NoSuchAlgorithmException, ServletException {
        User user = new User();
        //注册字符串转时间的格式转换器
        ConvertUtils.register(new DateConvert(), Date.class);
        //BeanUtils对javaBean操作
        BeanUtils.populate(user,request.getParameterMap());
        //随机生成一个uid
        user.setUid(getUUID());
        //对密码加密
        user.setPassword(encoderByMd5(user.getPassword()));
        //生成随机注册码
        user.setCode(getUUID());
        //0表示未激活
        user.setState(0);
        //激活码
        user.setCode(getUUID());
        //打印，查看数据
        log.warn(user);
        String sessionCode = (String) request.getSession().getAttribute("c");
        if((sessionCode).equalsIgnoreCase(request.getParameter("code"))){
            //new一个service
            UserService service = new UserServiceImp();
            boolean success = service.register(user);
            //jsp页面输出的内容
            String msg;
            //jsp页面将跳转的地址
            String address;
            //注册成功
            if(success){
                msg = "注册成功，请去邮箱激活";
                address = request.getContextPath() +"/jsp/login.jsp";
            }else{
                msg = "注册失败";
                address = request.getContextPath()+"/jsp/register.jsp";
            }
            request.setAttribute("msg",msg);
            request.setAttribute("address",address);
            request.getRequestDispatcher("/jsp/msg.jsp").forward(request,response);
        }else{
            request.setAttribute("error","验证码输入错误");
            request.getRequestDispatcher("/jsp/register.jsp").forward(request,response);
        }

    }
    @RequestMapping(url="/active")
    public void active(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String uid = request.getParameter("uid");
        String code = request.getParameter("code");
        UserService service = new UserServiceImp();
        boolean success = service.active(uid, code);
        String msg =  success ? "激活成功" : "激活失败";
        String address = request.getContextPath() + "/jsp/login.jsp";
        request.setAttribute("msg",msg);
        request.setAttribute("address",address);
        request.getRequestDispatcher("/jsp/msg.jsp").forward(request,response);
    }

    @RequestMapping(url = "/login")
    public void login(HttpServletRequest request,HttpServletResponse response) throws IOException, NoSuchAlgorithmException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //获取password得重新加密，再与数据库中存的比较
        password = Utils.encoderByMd5(password);
        String sessionCode = (String) request.getSession().getAttribute("c");
        if(request.getParameter("code").equalsIgnoreCase(sessionCode)){
            UserService service = new UserServiceImp();
            User user = service.login(username, password);
            if (user != null) {
                request.getSession().setAttribute("user", user);
                request.getRequestDispatcher("/index").forward(request, response);
            } else {
                request.setAttribute("msg", "登陆失败，请检查登录名和密码");
                request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
            }
        }else{
            request.setAttribute("msg", "验证码错误");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }
    }
    @RequestMapping(url="/loginOut")
    public void loginOut(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        request.getSession().invalidate();
        request.getRequestDispatcher("/index").forward(request,response);
    }


    @RequestMapping(url = "/code")
    public void code(HttpServletRequest request,HttpServletResponse response) throws IOException {

        // 使用java图形界面技术绘制一张图片

        int charNum = 4;
        int width = 30 * 4;
        int height = 30;

        // 1. 创建一张内存图片
        BufferedImage bufferedImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);

        // 2.获得绘图对象
        Graphics graphics = bufferedImage.getGraphics();

        // 3、绘制背景颜色
        graphics.setColor(Color.YELLOW);
        graphics.fillRect(0, 0, width, height);

        // 4、绘制图片边框
        graphics.setColor(Color.BLUE);
        graphics.drawRect(0, 0, width - 1, height - 1);

        // 5、输出验证码内容
        graphics.setColor(Color.RED);
        graphics.setFont(new Font("宋体", Font.BOLD, 20));

        // 随机输出4个字符
        Graphics2D graphics2d = (Graphics2D) graphics;
        String s = "ABCDEFGHGKLMNPQRSTUVWXYZ23456789";
        Random random = new Random();
        //session中要用到
        String msg="";
        int x = 5;
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(32);
            String content = String.valueOf(s.charAt(index));
            msg+=content;
            double theta = random.nextInt(45) * Math.PI / 180;
            //让字体扭曲
            graphics2d.rotate(theta, x, 18);
            graphics2d.drawString(content, x, 18);
            graphics2d.rotate(-theta, x, 18);
            x += 30;
        }
        request.getSession().setAttribute("c",msg);
        // 6、绘制干扰线
        graphics.setColor(Color.GRAY);
        for (int i = 0; i < 5; i++) {
            int x1 = random.nextInt(width);
            int x2 = random.nextInt(width);

            int y1 = random.nextInt(height);
            int y2 = random.nextInt(height);
            graphics.drawLine(x1, y1, x2, y2);
        }

        // 释放资源
        graphics.dispose();

        // 图片输出 ImageIO
        ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
    }
}
