package com.zjitc.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/12/8 17:10
 * @description:
 */
public class ExpressGson {
    @SerializedName("LogisticCode")
    private String logisticCode;
    @SerializedName("ShipperCode")
    private String shipperCode;
    @SerializedName("Traces")
    private List<Trace> traces;
    @SerializedName("State")
    private String state;
    @SerializedName("EBusinessID")
    private String eBusinessID;
    @SerializedName("Success")
    private boolean success;

    public ExpressGson(String logisticCode, String shipperCode, List<Trace> traces, String state, String eBusinessID, boolean success) {
        this.logisticCode = logisticCode;
        this.shipperCode = shipperCode;
        this.traces = traces;
        this.state = state;
        this.eBusinessID = eBusinessID;
        this.success = success;
    }

    public ExpressGson() {
    }

    public String getLogisticCode() {
        return logisticCode;
    }

    public void setLogisticCode(String logisticCode) {
        this.logisticCode = logisticCode;
    }

    public String getShipperCode() {
        return shipperCode;
    }

    public void setShipperCode(String shipperCode) {
        this.shipperCode = shipperCode;
    }

    public List<Trace> getTraces() {
        return traces;
    }

    public void setTraces(List<Trace> traces) {
        this.traces = traces;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String geteBusinessID() {
        return eBusinessID;
    }

    public void seteBusinessID(String eBusinessID) {
        this.eBusinessID = eBusinessID;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
