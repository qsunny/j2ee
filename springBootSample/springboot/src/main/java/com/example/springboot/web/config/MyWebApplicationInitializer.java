//package com.example.springboot.web.config;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletRegistration;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.WebApplicationInitializer;
//import org.springframework.web.context.support.XmlWebApplicationContext;
//import org.springframework.web.servlet.DispatcherServlet;
//
//@Configuration
//public class MyWebApplicationInitializer implements WebApplicationInitializer {
//
//    @Override
//    public void onStartup(ServletContext container) {
////        XmlWebApplicationContext appContext = new XmlWebApplicationContext();
////        appContext.setConfigLocation("/WEB-INF/springmvc-dispatcher-servlet.xml");
//
//        ServletRegistration.Dynamic registration = container.addServlet("dispatcher", new DispatcherServlet());
//        registration.setLoadOnStartup(1);
//        registration.addMapping("*.jhtml");
//    }
//
//}
