package com.zjitc.domain;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/12/2 17:39
 * @description:
 */
public class OrderItem {
    private String itemid;
    private Integer count;
    private Double subtotal;
    private String oid;
    private String pid;

    private Product product;

    public OrderItem() {
    }

    public OrderItem(String itemid, Integer count, Double subtotal, String oid, String pid, Product product) {
        this.itemid = itemid;
        this.count = count;
        this.subtotal = subtotal;
        this.oid = oid;
        this.pid = pid;
        this.product = product;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
