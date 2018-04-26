package com.zjitc.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/12/8 17:16
 * @description:
 */
public class Trace {
    @SerializedName("AcceptStation")
    private String acceptStation;
    @SerializedName("AcceptTime")
    private String acceptTime;

    public Trace(String acceptStation, String acceptTime) {
        this.acceptStation = acceptStation;
        this.acceptTime = acceptTime;
    }

    public Trace() {
    }

    public String getAcceptStation() {
        return acceptStation;
    }

    public void setAcceptStation(String acceptStation) {
        this.acceptStation = acceptStation;
    }

    public String getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }
}
