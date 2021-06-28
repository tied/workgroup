package com.samsungds.atlassian.jira.plugin.rest;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class WorkgroupRestResourceModel {

  @XmlElement(name = "value")
  private String message;

  public WorkgroupRestResourceModel() {
  }

  public WorkgroupRestResourceModel(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}