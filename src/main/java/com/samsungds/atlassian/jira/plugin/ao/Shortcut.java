package com.samsungds.atlassian.jira.plugin.ao;

import net.java.ao.Entity;

public interface Shortcut extends Entity {
    String getName();
    void setName(String name);
    String getUrl();
    void setUrl(String url);
    String getUserName();
    void setUserName(String userName);
}
