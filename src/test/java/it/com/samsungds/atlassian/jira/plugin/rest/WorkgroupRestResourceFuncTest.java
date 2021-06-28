package it.com.samsungds.atlassian.jira.plugin.rest;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.mockito.Mockito;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import com.samsungds.atlassian.jira.plugin.rest.WorkgroupRestResource;
import com.samsungds.atlassian.jira.plugin.rest.WorkgroupRestResourceModel;
import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;

public class WorkgroupRestResourceFuncTest {

  @Before
  public void setup() {

  }

  @After
  public void tearDown() {

  }

  @Test
  public void messageIsValid() {

    String baseUrl = System.getProperty("baseurl");
    String resourceUrl = baseUrl + "/rest/workgrouprestresource/1.0/workgroup/message";

    RestClient client = new RestClient();
    Resource resource = client.resource(resourceUrl);

    WorkgroupRestResourceModel message = resource.get(WorkgroupRestResourceModel.class);

    assertEquals("wrong message", "Hello World", message.getMessage());
  }
}
