package com.zjitc.domain;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/12/6 19:52
 * @description:
 */

public class AlipayItemList {
    private String fund_channe;
    private Integer amount;
    private Double real_amount;
    private String fund_type;

    public AlipayItemList(String fund_channe, Integer amount, Double real_amount, String fund_type) {
        this.fund_channe = fund_channe;
        this.amount = amount;
        this.real_amount = real_amount;
        this.fund_type = fund_type;
    }

    public AlipayItemList() {
    }

    public String getFund_channe() {
        return fund_channe;
    }

    public void setFund_channe(String fund_channe) {
        this.fund_channe = fund_channe;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getReal_amount() {
        return real_amount;
    }

    public void setReal_amount(Double real_amount) {
        this.real_amount = real_amount;
    }

    public String getFund_type() {
        return fund_type;
    }

    public void setFund_type(String fund_type) {
        this.fund_type = fund_type;
    }
}
