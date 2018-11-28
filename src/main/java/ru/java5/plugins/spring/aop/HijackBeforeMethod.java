package ru.java5.plugins.spring.aop;

import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.MethodBeforeAdvice;

public class HijackBeforeMethod implements MethodBeforeAdvice {
  private static final Logger LOG = LoggerFactory.getLogger(HijackBeforeMethod.class);

  public void before(Method method, Object[] objects, Object o) throws Throwable {
    LOG.debug("HijackBeforeMethod : method {} in", method.toString());
  }
}