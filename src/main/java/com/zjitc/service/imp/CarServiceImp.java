package com.zjitc.service.imp;

import com.zjitc.dao.CarDao;
import com.zjitc.dao.imp.CarDaoImp;
import com.zjitc.domain.CarItem;
import com.zjitc.service.CarService;

import java.util.List;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/11/30 8:43
 * @description:
 */
public class CarServiceImp implements CarService {
    @Override
    public void add(String uid, String pid, int count) {
        CarDao carDao = new CarDaoImp();
        carDao.add(uid,count,pid);
    }

    @Override
    public List<CarItem> findAll(String uid) {
        CarDao carDao = new CarDaoImp();
        return carDao.findAll(uid);
    }

    @Override
    public void deleteSingle(String uid, String pid) {
        CarDao carDao = new CarDaoImp();
        carDao.deleteSingle(uid,pid);
    }

    @Override
    public void deleteAll(String uid) {
        CarDao carDao = new CarDaoImp();
        carDao.deleteAll(uid);
    }
}
