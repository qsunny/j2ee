package com.aaron.springbootDemo.core.config;

//import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//注意，由于MapperScannerConfigurer执行的比较早，所以必须有下面的注解
@Configuration
@AutoConfigureAfter(PersistenceConfig.class)
public class MyBatisMapperScannerConfig {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        //注入sqlSessionFactory
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        //mapperScannerConfigurer.setSqlSessionTemplateBeanName("sqlSessionTemplate");
        //给出需要扫描Dao接口包
        mapperScannerConfigurer.setBasePackage("com.aaron.springbootDemo.dao");
        return mapperScannerConfigurer;
    }

}
