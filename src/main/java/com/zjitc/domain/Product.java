package com.zjitc.domain;

import java.util.Date;

/**
 * Create By IntelliJ IDEA
 *
 * @author: jsonor
 * @create-Time: 2017/11/14 9:39
 * @description: ${description}
 */
public class Product {

  private String pid;
  private String pname;
  private Double market_price;
  private Double shop_price;
  private String pimage;
  private Date pdate;
  private Integer is_hot;
  private String pdesc;
  private Integer pflag;
  private String cid;

  private Category category;

  public Product() {
  }

  public Product(String pid, String pname, Double market_price, Double shop_price, String pimage, Date pdate, Integer is_hot, String pdesc, Integer pflag, String cid) {
    this.pid = pid;
    this.pname = pname;
    this.market_price = market_price;
    this.shop_price = shop_price;
    this.pimage = pimage;
    this.pdate = pdate;
    this.is_hot = is_hot;
    this.pdesc = pdesc;
    this.pflag = pflag;
    this.cid = cid;
  }

  public String getPid() {
    return pid;
  }

  public void setPid(String pid) {
    this.pid = pid;
  }

  public String getPname() {
    return pname;
  }

  public void setPname(String pname) {
    this.pname = pname;
  }

  public Double getMarket_price() {
    return market_price;
  }

  public void setMarket_price(Double market_price) {
    this.market_price = market_price;
  }

  public Double getShop_price() {
    return shop_price;
  }

  public void setShop_price(Double shop_price) {
    this.shop_price = shop_price;
  }

  public String getPimage() {
    return pimage;
  }

  public void setPimage(String pimage) {
    this.pimage = pimage;
  }

  public Date getPdate() {
    return pdate;
  }

  public void setPdate(Date pdate) {
    this.pdate = pdate;
  }

  public Integer getIs_hot() {
    return is_hot;
  }

  public void setIs_hot(Integer is_hot) {
    this.is_hot = is_hot;
  }

  public String getPdesc() {
    return pdesc;
  }

  public void setPdesc(String pdesc) {
    this.pdesc = pdesc;
  }

  public Integer getPflag() {
    return pflag;
  }

  public void setPflag(Integer pflag) {
    this.pflag = pflag;
  }

  public String getCid() {
    return cid;
  }

  public void setCid(String cid) {
    this.cid = cid;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }
}
