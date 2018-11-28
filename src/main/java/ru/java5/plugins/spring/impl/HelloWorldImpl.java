package ru.java5.plugins.spring.impl;


//import com.atlassian.jira.config.ConstantsManager;
//import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.sal.api.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.java5.plugins.spring.api.HelloWorld;

public class HelloWorldImpl implements HelloWorld {
  private static final Logger LOG = LoggerFactory.getLogger(HelloWorldImpl.class);
  private String message = "Hello World!!!";
  private final ApplicationProperties applicationProperties;

  public HelloWorldImpl(ApplicationProperties applicationProperties) {
    this.applicationProperties = applicationProperties;
  }

  public String getMessage() {
    LOG.debug("getMessage executed");
    return applicationProperties.getDisplayName() + " " + this.message;
  }

  public void setMessage(String value) {
    LOG.debug("setMessage executed");
    message = value;
  }
}