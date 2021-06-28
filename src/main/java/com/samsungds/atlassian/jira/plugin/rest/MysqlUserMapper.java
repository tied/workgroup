package com.samsungds.atlassian.jira.plugin.rest;

import java.util.List;

public interface MysqlUserMapper {
    List<MysqlUser> selectAll();
}