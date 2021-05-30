package com.aaron.springbootDemoMp.core.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableSwaggerBootstrapUi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Aaron.Qiu
 */
@Profile({"dev","test"})
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUi
public class Swagger2Config {
    @Value("${swagger.show}")
    private boolean showSwagger;

    @Bean
    public Docket createRestApi() {
        return  new Docket(DocumentationType.SWAGGER_2)
                //是否开启swagger
                .enable(showSwagger)
                .useDefaultResponseMessages(false)
                .directModelSubstitute(LocalDateTime.class, Date.class)
                .directModelSubstitute(LocalDate.class, String.class)
                .directModelSubstitute(LocalTime.class, String.class)
                .directModelSubstitute(ZonedDateTime.class, String.class)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.aaron"))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(this.setHeaderToken());

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("系统接口文档")
                .description("系统")
                .contact(new Contact("Aaron Qiu",null,"aaron.qiu@yunlang.com"))
                .version("1.0.0")
                .build();
    }

    private List<Parameter> setHeaderToken() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("token").description("token").
                modelRef(new ModelRef("string"))
                .parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return pars;
    }

}
