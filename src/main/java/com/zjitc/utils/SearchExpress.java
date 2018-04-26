package com.zjitc.utils;

import com.zjitc.express.KdniaoTrackQueryAPI;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/12/9 15:00
 * @description:
 */
public class SearchExpress {
    public void search(String name, String num){
        InputStream is = SearchExpress.class.getClassLoader().getResourceAsStream("expressName.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
            String codeName = properties.getProperty(name);
            KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
            String result = api.getOrderTracesByJson(codeName, num);
            System.out.println(result);
            } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
