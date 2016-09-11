package com.aaron.tools.utils;

import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

public class ServletContextUtil implements ServletContextAware {
	private static ServletContext servletContext;

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public static ServletContext getServletContext() {
		return servletContext;
	}

	public static Object getAppObject(String name) {
		if (servletContext != null) {
			return servletContext.getAttribute(name);
		}
		return null;
	}
}
