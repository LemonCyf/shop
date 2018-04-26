package com.zjitc.domain;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/11/28 10:48
 * @description:
 */
public class Car{
    private String uid;
    private Integer count;
    private String pid;

    private User user;

    public Car(String uid, Integer count, String pid, User user) {
        this.uid = uid;
        this.count = count;
        this.pid = pid;
        this.user = user;
    }

    public Car() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
