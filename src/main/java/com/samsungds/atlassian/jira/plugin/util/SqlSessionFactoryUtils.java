package com.samsungds.atlassian.jira.plugin.util;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryUtils {
  private static SqlSessionFactory SQLSESSIONFACTORY = null;

  public static SqlSessionFactory getInstance() {
    if (SQLSESSIONFACTORY == null) {
      try {
        SQLSESSIONFACTORY = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mapper/mybatis-config.xml"));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return SQLSESSIONFACTORY;
  }
}