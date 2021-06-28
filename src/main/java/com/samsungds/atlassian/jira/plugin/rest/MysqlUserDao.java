package com.samsungds.atlassian.jira.plugin.rest;

import java.util.List;

import com.samsungds.atlassian.jira.plugin.util.SqlSessionFactoryUtils;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class MysqlUserDao {
  private SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getInstance();
  public List<MysqlUser> selectAll() {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    List<MysqlUser> list = sqlSession.selectList("org.javaboy.mybatis01.mapper.UserMapper.getAllUser");
    sqlSession.close();
    return list;
  }
}
