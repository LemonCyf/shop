package com.zjitc.domain;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/12/8 19:16
 * @description:
 */
public class Express {
    private String eid;
    private String oid;

    public Express(String eid, String oid) {
        this.eid = eid;
        this.oid = oid;
    }

    public Express() {
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }
}
