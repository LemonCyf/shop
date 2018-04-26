package com.zjitc.domain;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/12/5 11:33
 * @description:
 */
public class AlipayRefund {
    private AResponse alipay_trade_refund_response;
    private String sign;

    public AlipayRefund(AResponse alipayTradeRefundResponse, String sign) {
        this.alipay_trade_refund_response = alipayTradeRefundResponse;
        this.sign = sign;
    }

    public AlipayRefund() {
    }

    public AResponse getAlipayTradeRefundResponse() {
        return alipay_trade_refund_response;
    }

    public void setAlipayTradeRefundResponse(AResponse alipayTradeRefundResponse) {
        this.alipay_trade_refund_response = alipayTradeRefundResponse;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}



