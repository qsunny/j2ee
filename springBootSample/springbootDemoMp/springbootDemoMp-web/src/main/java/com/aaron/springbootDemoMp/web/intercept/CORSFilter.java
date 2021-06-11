package com.aaron.springbootDemoMp.web.intercept;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CORS，跨域处理过滤器
 * @author 天风
 * @version 20-08-11
 * 
 */
@Component
public class CORSFilter extends OncePerRequestFilter {
	private static Logger log = LoggerFactory.getLogger(CORSFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.error(request.getContextPath() + request.getRequestURI() + "getRequestURI"  + " getMethod:" + request.getMethod());
		// if (request.getHeader("Access-Control-Request-Method") != null
		// && "OPTIONS".equals(request.getMethod())) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		
		
		response.addHeader("Access-Control-Allow-Methods","GET, POST, PUT, DELETE, PATCH");
		response.addHeader("Access-Control-Allow-Headers","YX-AccessToken,YUA,YX-Guid,X-ClientType,X-ClientVersion,X-Requested-With,Content-Type,Accept,YX-User-Agent");
		response.addHeader("Access-Control-Max-Age", "1800");
		response.addHeader("Cache-Control","no-cache");
		// }
		filterChain.doFilter(request, response);
	}

}
