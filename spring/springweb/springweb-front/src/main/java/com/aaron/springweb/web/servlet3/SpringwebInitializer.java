package com.aaron.springweb.web.servlet3;

import com.aaron.springweb.web.config.SpringwebConfig;
import com.aaron.springweb.web.config.SpringwebRootConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by aaron.qiu on 2016/9/25.
 */
public class SpringwebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.setInitParameter("spring.profiles.active", "prod");
/*	     FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
	      encodingFilter.setInitParameter("encoding", "UTF-8");
	      encodingFilter.setInitParameter("forceEncoding", "true");
	      encodingFilter.addMappingForUrlPatterns(null, false, "/*");*/

        super.onStartup(servletContext);
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { SpringwebRootConfig.class };
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
        return new Class<?>[] { SpringwebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
