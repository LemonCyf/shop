package com.zjitc.service.imp;

import com.zjitc.dao.ExpressDao;
import com.zjitc.dao.imp.ExpressDaoImp;
import com.zjitc.domain.Express;
import com.zjitc.service.ExpressService;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/12/8 17:28
 * @description:
 */
public class ExpressServiceImp implements ExpressService {
    @Override
    public void insertInExpress(String eid,String oid) {
        ExpressDao expressDao = new ExpressDaoImp();
        expressDao.insert(eid,oid);
    }

    @Override
    public void deleteByOid(String out_trade_no) {
        ExpressDao expressDao = new ExpressDaoImp();
        expressDao.deleteByOid(out_trade_no);
    }

    @Override
    public Express getByOid(String oid) {
        ExpressDao expressDao = new ExpressDaoImp();
        return expressDao.getByOid(oid);

    }
}
