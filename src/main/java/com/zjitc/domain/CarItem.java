package com.zjitc.domain;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/11/30 8:35
 * @description:
 */
public class CarItem {
    private Integer count;
    private Product product;
    private Double price;

    public CarItem(Integer count,Double price,Product product) {
        this.count = count;
        this.product = product;
        this.price = price;
    }

    public CarItem() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
