package com.zjitc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.beanutils.Converter;

/**
 * Create By IntelliJ IDEA
 *
 * @author: jsonor
 * @create-Time: 2017/11/9 9:25
 * @description: ${description}
 */
public class DateConvert implements Converter {

  @Override
  public Object convert(Class aClass, Object o) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    try {
      Date date = sdf.parse(o.toString());
      return date;
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }
}
