package com.aaron.springbootDemoMp.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
public class WebMvcConfig  extends WebMvcConfigurationSupport {

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(requestApiInterceptor).addPathPatterns("/**").excludePathPatterns("/api/mobile/**");
//        super.addInterceptors(registry);
//        registry.addInterceptor(requestLimitIntercept);
//    }

//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        configurer.defaultContentType(MediaType.APPLICATION_JSON_UTF8);
//    }


 /*   @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        converter.setWriteAcceptCharset(false);
        converters.add(converter);

        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        //设置日期格式
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);

        //设置中文编码格式
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.APPLICATION_JSON);
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(list);
        converters.add(mappingJackson2HttpMessageConverter);
//        addDefaultHttpMessageConverters(converters);
    }*/

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorParameter(false);
    }


}
