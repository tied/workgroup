package com.samsungds.atlassian.jira.plugin.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryUtils {
  private static SqlSessionFactory sqlSessionFactory = null;

  public static SqlSessionFactory getInstance() {
    if (sqlSessionFactory == null) {
      try {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactoryUtils.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return sqlSessionFactory;
  }
}