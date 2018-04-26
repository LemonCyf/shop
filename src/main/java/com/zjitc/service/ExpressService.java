package com.zjitc.service;

import com.zjitc.domain.Express;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/12/8 17:28
 * @description:
 */
public interface ExpressService {
    void insertInExpress(String eid,String oid);

    void deleteByOid(String out_trade_no);

    Express getByOid(String oid);
}
