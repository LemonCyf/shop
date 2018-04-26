package com.zjitc.domain;

import java.util.List;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/11/21 11:29
 * @description:
 */
public class Page<T> {
    /**
     * 当前分页需要显示的产品信息 最大长度为12 */
    private List<T> list;
    /**
     * 当前分页索引号*/
    private Integer currPage;
    /**
     * 总的分页数量 (产品总数 / 12) -> 向上取整 */
    private Integer total;
    /**
     * 在页面中分页选项的开始索引号*/
    private Integer start;
    /**
     * 在页面中分页选项的结束索引号*/
    private Integer end;

    public Page(List<T> list, Integer currPage, Integer total, Integer start, Integer end) {
        this.list = list;
        this.currPage = currPage;
        this.total = total;
        this.start = start;
        this.end = end;
    }

    public Page() {
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getCurrPage() {
        return currPage;
    }

    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }
}
