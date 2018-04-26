package com.zjitc.domain;

import java.util.Date;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/12/6 19:51
 * @description:
 */
public class AResponse{
    private String code;
    private String msg;
    private String trade_no;
    private String out_trade_no;
    private String buyer_logon_id;
    private String fund_change;
    private Double refund_fee;
    private Date gmt_refund_pay;
    private AlipayItemList[] refund_detail_item_list;
    private String store_name;
    private String buyer_user_id;

    public AResponse(String code, String msg, String trade_no, String out_trade_no, String buyer_logon_id, String fund_change, Double refund_fee, Date gmt_refund_pay, AlipayItemList[] refund_detail_item_list, String store_name, String buyer_user_id) {
        this.code = code;
        this.msg = msg;
        this.trade_no = trade_no;
        this.out_trade_no = out_trade_no;
        this.buyer_logon_id = buyer_logon_id;
        this.fund_change = fund_change;
        this.refund_fee = refund_fee;
        this.gmt_refund_pay = gmt_refund_pay;
        this.refund_detail_item_list = refund_detail_item_list;
        this.store_name = store_name;
        this.buyer_user_id = buyer_user_id;
    }

    public AResponse() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getBuyer_logon_id() {
        return buyer_logon_id;
    }

    public void setBuyer_logon_id(String buyer_logon_id) {
        this.buyer_logon_id = buyer_logon_id;
    }

    public String getFund_change() {
        return fund_change;
    }

    public void setFund_change(String fund_change) {
        this.fund_change = fund_change;
    }

    public Double getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(Double refund_fee) {
        this.refund_fee = refund_fee;
    }

    public Date getGmt_refund_pay() {
        return gmt_refund_pay;
    }

    public void setGmt_refund_pay(Date gmt_refund_pay) {
        this.gmt_refund_pay = gmt_refund_pay;
    }

    public AlipayItemList[] getRefund_detail_item_list() {
        return refund_detail_item_list;
    }

    public void setRefund_detail_item_list(AlipayItemList[] refund_detail_item_list) {
        this.refund_detail_item_list = refund_detail_item_list;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getBuyer_user_id() {
        return buyer_user_id;
    }

    public void setBuyer_user_id(String buyer_user_id) {
        this.buyer_user_id = buyer_user_id;
    }
}