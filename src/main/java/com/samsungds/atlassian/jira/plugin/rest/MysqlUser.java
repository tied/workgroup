package com.samsungds.atlassian.jira.plugin.rest;

import org.codehaus.jackson.annotate.JsonProperty;

public class MysqlUser {
  @JsonProperty
  private String host;
  @JsonProperty
  private String user;

  public MysqlUser(String host, String user) {
    this.host = host;
    this.user = user;
  }

  public String getHost() {
    return this.host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public String getUser() {
    return this.user;
  }

  public void setUser(String user) {
    this.user = user;
  }

}
