package com.zjitc.domain;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/11/22 20:47
 * @description:
 */
public class History {
   private static final int MAX_COUNT = 8;
   private Map<String,Product> productMap = new HashMap<String, Product>();
   Queue<String> keys = new LinkedList<String>();

    public History(Map<String, Product> productMap) {
        this.productMap = productMap;
    }

    public History() {
    }

    public Map<String, Product> getProductMap() {
        return productMap;
    }

    public void setProductMap(Map<String, Product> productMap) {
        this.productMap = productMap;
    }

    public void add(Product product){
        String pid = product.getPid();
        if(keys.contains(pid)){
            return;
        }
        productMap.put(pid,product);
        keys.add(pid);
        while (keys.size()>=MAX_COUNT){
            String key = keys.poll();
            productMap.remove(key);
        }
    }
}
