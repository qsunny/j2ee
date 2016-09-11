package com.aaron.tools.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextHolder implements ApplicationContextAware {
	
  private static ApplicationContext applicationContext;

  public void setApplicationContext(ApplicationContext applicationContext)
  {
    this.applicationContext = applicationContext;
  }

  public static ApplicationContext getApplicationContext()
  {
    checkApplicationContext();
    return applicationContext;
  }

  public static <T> T getBean(String name)
  {
    checkApplicationContext();
    return (T) applicationContext.getBean(name);
  }

  public static <T> T getBean(Class<T> clazz)
  {
    checkApplicationContext();
    return (T) applicationContext.getBeansOfType(clazz);
  }

  public static void cleanApplicationContext()
  {
    applicationContext = null;
  }

  private static void checkApplicationContext() {
    if (applicationContext == null)
      throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder");
  }
}