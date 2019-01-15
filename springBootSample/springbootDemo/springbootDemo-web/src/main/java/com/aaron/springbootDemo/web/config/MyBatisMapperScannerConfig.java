package com.aaron.springbootDemo.web.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//TODO 注意，由于MapperScannerConfigurer执行的比较早，所以必须有下面的注解
@AutoConfigureAfter(PersistenceConfig.class)
public class MyBatisMapperScannerConfig {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        //注入sqlSessionFactory
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        //mapperScannerConfigurer.setSqlSessionTemplateBeanName("sqlSessionTemplate");
        //给出需要扫描Dao接口包
        mapperScannerConfigurer.setBasePackage("com.aaron.springbootDemo.core.dao");
        return mapperScannerConfigurer;
    }

}
