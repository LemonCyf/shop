package com.zjitc.service;

import com.zjitc.domain.User; /**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/11/13 8:41
 * @description:
 */
public interface UserService {
    boolean register(User user);

    boolean active(String uid, String code);

    User login(String username, String password);
}
