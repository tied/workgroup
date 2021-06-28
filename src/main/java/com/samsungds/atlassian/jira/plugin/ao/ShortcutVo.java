package com.samsungds.atlassian.jira.plugin.ao;

import net.java.ao.Entity;
import org.codehaus.jackson.annotate.JsonProperty;

public class ShortcutVo {
    @JsonProperty
    private int id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String url;
    @JsonProperty
    private String userName;

    public ShortcutVo() {
    }

    public ShortcutVo(int id, String name, String url, String userName) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
