package com.aaron.springweb.web.config;

import com.aaron.springweb.bean.Pizza;
import com.aaron.springweb.web.viewresolver.*;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by aaron.qiu on 2016/9/25.
 */

@Configuration
@EnableWebMvc //<mvc:annotation-driven />
@ImportResource("classpath*:commons.xml")
@ComponentScan({ "com.aaron.springweb.web.controller" })
public class SpringwebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		/*
		 * Jackson2ObjectMapperBuilder builder = new
		 * Jackson2ObjectMapperBuilder() .indentOutput(true) .dateFormat(new
		 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss")) .modulesToInstall(new
		 * ParameterNamesModule()); converters.add(new
		 * MappingJackson2HttpMessageConverter(builder.build()));
		 * converters.add(new
		 * MappingJackson2XmlHttpMessageConverter(builder.xml().build()));
		 */

		/**/
        // http
        Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
        HttpMessageConverter<?> converter = new StringHttpMessageConverter(DEFAULT_CHARSET);
        converters.add(converter);

        // string
        converter = new FormHttpMessageConverter();
        converters.add(converter);

        // json
        converter = new MappingJackson2HttpMessageConverter();
        converters.add(converter);

        // xml
        converters.add(new MappingJackson2XmlHttpMessageConverter());

    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

        configurer.mediaType("json", MediaType.APPLICATION_JSON);
    }

    /*
     * Configure ContentNegotiatingViewResolver
     */
    @Bean
    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setOrder(0);
        resolver.setContentNegotiationManager(manager);

        // Define all possible view resolvers
        List<ViewResolver> resolvers = new ArrayList<ViewResolver>();

        resolvers.add(jaxb2MarshallingXmlViewResolver());
        resolvers.add(jsonViewResolver());
        resolvers.add(pdfViewResolver());
        resolvers.add(excelViewResolver());
        //resolvers.add(excelXlsxlViewResolver());

        resolver.setViewResolvers(resolvers);
        return resolver;
    }

    /*
    * Configure View resolver to provide XML output Uses JAXB2 marshaller to
    * marshall/unmarshall POJO's (with JAXB annotations) to XML
    */
    @Bean
    public ViewResolver jaxb2MarshallingXmlViewResolver() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(Pizza.class);
        return new Jaxb2MarshallingXmlViewResolver(marshaller);
    }


    /*
     * Configure View resolver to provide JSON output using JACKSON library to
     * convert object in JSON format.
     */
    @Bean
    public ViewResolver jsonViewResolver() {
        return new JsonViewResolver();
    }

    /*
     * Configure View resolver to provide PDF output using lowagie pdf library to
     * generate PDF output for an object content
     */
    @Bean
    public ViewResolver pdfViewResolver() {
        return new PdfViewResolver();
    }

    /*
     * Configure View resolver to provide XLS output using Apache POI library to
     * generate XLS output for an object content
     */
    @Bean
    public ViewResolver excelViewResolver() {
        return new ExcelViewResolver();
    }

    /*
    @Bean
    public XlsxViewResolver excelXlsxlViewResolver() {
        return new XlsxViewResolver();
    }
    */

	/*
	 * 拦截器使用
	 *
	 * @Override public void addInterceptors(InterceptorRegistry registry) {
	 * registry.addInterceptor(new
	 * ThemeInterceptor()).addPathPatterns("/**").excludePathPatterns(
	 * "/admin/**"); }
	 */

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
        registry.addViewController("login/form").setViewName("login");
        registry.addViewController("welcome").setViewName("welcome");
        registry.addViewController("admin").setViewName("admin");
    }

    /**/
    @Bean
    public ViewResolver resolver() {
        InternalResourceViewResolver urvr = new InternalResourceViewResolver();
        urvr.setOrder(1);
        urvr.setViewClass(JstlView.class);
        urvr.setPrefix("/WEB-INF/page/");
        urvr.setSuffix(".jsp");
        return urvr;
    }

	/*
	 //针对freemarker的视图配置
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
    	Map<String,String> attributes = new HashMap<String,String>();
    	attributes.put("contentType", "text/html;charset=UTF-8");
    	attributes.put("requestContextAttribute", "request");
    	attributes.put("exposeSpringMacroHelpers", "true");
    	attributes.put("exposeRequestAttributes", "true");
    	attributes.put("exposeSessionAttributes", "true");
        registry.enableContentNegotiation(new MappingJackson2JsonView());
        registry.freeMarker().cache(true).suffix(".ftl")
        .attributes(attributes);
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath("/WEB-INF/page/");
        Properties settings = new Properties();
        settings.setProperty("template_update_delay", "0");
        settings.setProperty("default_encoding", "UTF-8");
        settings.setProperty("number_format", "###.#####");
        settings.setProperty("datetime_format", "yyyy-MM-dd HH:mm:ss");
        settings.setProperty("classic_compatible", "true");
        settings.setProperty("template_exception_handler", "ignore");
        configurer.setFreemarkerSettings(settings);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("SystemSn", "webApp");
        map.put("xml_escape", new XmlEscape());
        configurer.setFreemarkerVariables(map);
        return configurer;
    }
    */

    /**
     * 注意跟静态资源数据相匹配
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean(name = "messageSource")
    public MessageSource configureMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setCacheSeconds(5);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver b = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        mappings.put("org.springframework.dao.DataAccessException", "error");
        b.setExceptionMappings(mappings);
        return b;
    }

}
