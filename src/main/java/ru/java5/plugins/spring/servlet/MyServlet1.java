package ru.java5.plugins.spring.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.java5.plugins.spring.api.HelloWorld;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyServlet1 extends HttpServlet{
  private static final Logger log = LoggerFactory.getLogger(MyServlet1.class);

  private final HelloWorld helloWorld;

  @Inject
  public MyServlet1(@Qualifier("helloWorldBeforeProxy") HelloWorld helloWorld) {
    this.helloWorld = helloWorld;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    log.debug("MyServlet1 called");
    resp.setContentType("text/html");
    String message = "<html><body>" + helloWorld.getMessage() + "</body></html>";
    helloWorld.setMessage("message changed MyServlet");
    resp.getWriter().write(message);
  }
}