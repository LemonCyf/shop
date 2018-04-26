package com.zjitc.dao;

import com.zjitc.domain.Express;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/12/8 18:58
 * @description:
 */
public interface ExpressDao {
    void insert(String eid, String oid);

    void deleteByOid(String out_trade_no);

    Express getByOid(String oid);
}
