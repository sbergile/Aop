package ru.java5.plugins.spring.config;

//import com.atlassian.jira.config.ConstantsManager;
//import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import org.springframework.context.annotation.Scope;
import ru.java5.plugins.spring.aop.HijackAroundMethod;
import ru.java5.plugins.spring.aop.HijackBeforeMethod;
import com.atlassian.sal.api.ApplicationProperties;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.java5.plugins.spring.api.HelloWorld;
import ru.java5.plugins.spring.impl.HelloWorldImpl;
import javax.inject.Named;

@Named
@Configuration
public class Config{
  @Bean(name = "helloWorld")
  @Scope("prototype")
  public HelloWorld helloWorld(@ComponentImport ApplicationProperties applicationProperties) {
    return new HelloWorldImpl(applicationProperties);
  }

  @Bean(name="hijackBeforeMethodBean")
  public HijackBeforeMethod hijackBeforeMethod() {
    return new HijackBeforeMethod();
  }

  @Bean(name="hijackAroundMethodBean")
  public HijackAroundMethod hijackAroudnMethod() {
    return new HijackAroundMethod();
  }

  @Bean (name = "helloWorldBeforeProxy")
  @Scope("prototype")
  public ProxyFactoryBean proxyBeforeFactoryBean(@ComponentImport ApplicationProperties applicationProperties) {
    ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
    proxyFactoryBean.setTarget(helloWorld(applicationProperties));
    proxyFactoryBean.setProxyTargetClass(true);
    proxyFactoryBean.setInterceptorNames("hijackBeforeMethodBean");
    return proxyFactoryBean;
  }

  @Bean (name = "helloWorldAroundProxy")
  @Scope("prototype")
  public ProxyFactoryBean proxyAroundFactoryBean(@ComponentImport ApplicationProperties applicationProperties) {
    ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
    proxyFactoryBean.setTarget(helloWorld(applicationProperties));
    proxyFactoryBean.setProxyTargetClass(true);
    proxyFactoryBean.setInterceptorNames("hijackAroundMethodBean");
    return proxyFactoryBean;
  }
}