package com.aaron.springbootDemo.web.config;

import com.aaron.springbootDemo.core.pager.DaoTimeConsumingMonitor;
import com.aaron.springbootDemo.core.pager.PageInterceptor;
import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by Aaron.Qiu on 2019-01-14.
 */
@Configuration
@EnableTransactionManagement
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class PersistenceConfig implements TransactionManagementConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(PersistenceConfig.class);

    @Autowired
    private Environment env;

    @Autowired
    private DataSource dataSource;

    //通过下面实现TransactionManagementConfigurer接口的方式管理事务
    /*
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) throws SQLException {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
    */

    @Bean(initMethod = "init",destroyMethod = "close")
	public DataSource dataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
        //配置初始化大小、最小、最大
        dataSource.setInitialSize(30);
        dataSource.setMinIdle(3);
        dataSource.setMaxActive(120);
        //配置获取连接等待超时的时间
        dataSource.setMaxWait(60000);
        //配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        //配置一个连接在池中最小生存的时间，单位是毫秒
        dataSource.setMinEvictableIdleTimeMillis(30000);
        dataSource.setValidationQuery("SELECT 'x'");
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        //配置监控统计拦截的filters
        dataSource.setFilters("stat");
        // 通过connectProperties属性来打开mergeSql功能；慢SQL记录
        //spring.datasource.connectionProperties=druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
        //合并多个DruidDataSource的监控数据
        //spring.datasource.useGlobalDataSourceStat=true
		return dataSource;
	}

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        try {
            sqlSessionFactory.setDataSource(dataSource);
            sqlSessionFactory.setConfigLocation(new ClassPathResource("mybatis.xml"));
//            sqlSessionFactory.setTypeAliasesPackage("com.aaron.springbootDemo.bean");
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] classPathResources = resolver.getResources("classpath*:com/aaron/springbootDemo/core/dao/*/*.xml");
            sqlSessionFactory.setMapperLocations(classPathResources);
            //分页拦截器
            PageInterceptor pageInterceptor = new PageInterceptor();
            pageInterceptor.setDatabaseType("mysql");
            Interceptor[] plugins = new Interceptor[]{pageInterceptor,new DaoTimeConsumingMonitor()};
            sqlSessionFactory.setPlugins(plugins);
            return sqlSessionFactory.getObject();
        } catch (Exception e) {
            logger.error("初始化SqlSessionFactory失败", e);
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }

    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}
