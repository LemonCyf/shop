package com.zjitc.service.imp;

import com.zjitc.dao.UserDao;
import com.zjitc.dao.imp.UserDaoImp;
import com.zjitc.domain.User;
import com.zjitc.service.UserService;
import com.zjitc.utils.Mail;
import org.apache.log4j.Logger;

import javax.mail.MessagingException;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/11/13 8:41
 * @description:
 */
public class UserServiceImp implements UserService {
    private static final Logger logger = Logger.getLogger(UserServiceImp.class);
    @Override
    public boolean register(User user) {
        UserDao userDao = new UserDaoImp();
        if(userDao.getByName(user.getUsername())!=null){
            return false;
        }
        boolean success = userDao.add(user);
        if(success){
            Mail mail = new Mail();
            String url = "http://localhost:8080/active?uid=" + user.getUid() + "&code=" + user.getCode();
            logger.info("active url = " + url);
            StringBuilder builder = new StringBuilder(1024);
            builder.append("<h3>").append("激活邮件，请点击以下链接进行账号激活").append("</h3>");
            builder.append("<a href='").append(url).append("'>").append(url).append("</a>");
            try {
                mail.send(user.getEmail(), "激活邮件", builder.toString());
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
        return success;
    }

    @Override
    public boolean active(String uid, String code) {
        UserDao userDao = new UserDaoImp();
        User user = userDao.getById(uid);
        if(user!=null && code.equals(user.getCode())){
            user.setState(1);
            userDao.update(user);
            return true;
        }
        return false;
    }


    @Override
    public User login(String username, String password) {
        UserDao dao = new UserDaoImp();
        User user = dao.getByName(username);
        if (user.getState() == 1) {
            if (password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}
