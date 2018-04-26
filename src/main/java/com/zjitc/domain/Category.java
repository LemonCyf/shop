package com.zjitc.domain;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/11/15 13:58
 * @description:分类
 */
public class Category {
    private String cid;
    private String cname;

    public Category(String cid, String cname) {
        this.cid = cid;
        this.cname = cname;
    }

    public Category() {
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

}
