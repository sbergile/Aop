package ru.java5.plugins.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;

public class HijackAroundMethod implements MethodInterceptor {
  private static final Logger LOG = LoggerFactory.getLogger(HijackAroundMethod.class);

  public Object invoke(MethodInvocation methodInvocation) throws Throwable {
    LOG.debug("HijackAroundMethod : Method name : " + methodInvocation.getMethod().getName());
    LOG.debug("HijackAroundMethod : Method arguments : " + Arrays.toString(methodInvocation.getArguments()));
    LOG.debug("HijackAroundMethod : Before method hijacked!");

    try {
      Object result = methodInvocation.proceed();
      LOG.debug("HijackAroundMethod : Before after hijacked!");
      return result;
    } catch (IllegalArgumentException e) {
      LOG.debug("HijackAroundMethod : Throw exception hijacked!");
      throw e;
    }
  }
}