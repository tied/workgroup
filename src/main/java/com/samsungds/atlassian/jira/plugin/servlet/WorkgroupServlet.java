package com.samsungds.atlassian.jira.plugin.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.templaterenderer.TemplateRenderer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkgroupServlet extends HttpServlet {
  private static final long serialVersionUID = 210276372915872291L;
  private static final Logger log = LoggerFactory.getLogger(WorkgroupServlet.class);

  @ComponentImport
  private final TemplateRenderer templateRenderer;

  public WorkgroupServlet(TemplateRenderer templateRenderer) {
    this.templateRenderer = templateRenderer;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    StringBuilder bannerSb = new StringBuilder();
    bannerSb.append("\n ▄▄▌ ▐ ▄▌      ▄▄▄  ▄ •▄  ▄▄ • ▄▄▄        ▄• ▄▌ ▄▄▄· ");
    bannerSb.append("\n ██· █▌▐█▪     ▀▄ █·█▌▄▌▪▐█ ▀ ▪▀▄ █·▪     █▪██▌▐█ ▄█ ");
    bannerSb.append("\n ██▪▐█▐▐▌ ▄█▀▄ ▐▀▀▄ ▐▀▀▄·▄█ ▀█▄▐▀▀▄  ▄█▀▄ █▌▐█▌ ██▀· ");
    bannerSb.append("\n ▐█▌██▐█▌▐█▌.▐▌▐█•█▌▐█.█▌▐█▄▪▐█▐█•█▌▐█▌.▐▌▐█▄█▌▐█▪·• ");
    bannerSb.append("\n  ▀▀▀▀ ▀▪ ▀█▄▀▪.▀  ▀·▀  ▀·▀▀▀▀ .▀  ▀ ▀█▄▀▪ ▀▀▀ .▀    ");
    bannerSb.append("\n                          WorkgroupServlet.java v1.0 ");
    log.debug("{}", bannerSb);
    resp.setContentType("text/html");
    Map<String, Object> dataMap = new HashMap<>();
    dataMap.put("workgroupResource", "../../download/resources/com.samsungds.atlassian.jira.plugin.workgroup:workgroup-resources");
    dataMap.put("workgroupClientResource", "../../download/resources/com.samsungds.atlassian.jira.plugin.workgroup:workgroup-client-resources");
    templateRenderer.render("index.vm", dataMap, resp.getWriter());
  }

}