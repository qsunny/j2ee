package com.aaron.winkwebapp.restful.config;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.wink.server.internal.servlet.RestServlet;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebappConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		ServletRegistration.Dynamic dynamic = servletContext.addServlet("restServlet", RestServlet.class);
		dynamic.addMapping("/rest/*");
		dynamic.setLoadOnStartup(1);
		
		super.onStartup(servletContext);
	}
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { WinkContextConfig.class };
	}
	
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("utf-8");
		encodingFilter.setForceEncoding(true);
		Filter[] filters = new Filter[]{encodingFilter};
		return filters;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { /*WebMvcConfig.class*/ };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { /*"*.jhtml"*/ };
	}

}
