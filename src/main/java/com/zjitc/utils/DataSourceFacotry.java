package com.zjitc.utils;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;

/**
 * Create By IntelliJ IDEA
 *
 * @author: jsonor
 * @create-Time: 2017/11/7 10:27
 * @description: ${description}
 */
public class DataSourceFacotry {

  private static final DataSource DATA_SOURCE = new ComboPooledDataSource();

  public static DataSource getDataSource() {
    return DATA_SOURCE;
  }

  public static QueryRunner getRunner() {
    return new QueryRunner(getDataSource());
  }
}
