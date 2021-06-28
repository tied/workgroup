package com.samsungds.atlassian.jira.plugin.util;

import com.samsungds.atlassian.jira.plugin.rest.MysqlUser;
import junit.framework.TestCase;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SqlSessionFactoryUtilsTest {

    @Test
    public void userListTest() {
        SqlSessionFactory factory = SqlSessionFactoryUtils.getInstance();
        SqlSession sqlSession = factory.openSession();
        List<MysqlUser> list = sqlSession.selectList("com.samsungds.atlassian.jira.plugin.rest.MysqlUserMapper.selectAll");
        for (MysqlUser user : list) {
            System.out.println("user = " + user);
        }
        sqlSession.close();
        assertEquals(5, list.size());
    }
}