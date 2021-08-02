package com.liujun.schedule.infrastructure.repository.task.mapper.config;

import com.github.pagehelper.PageInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 单元测试的mybatis的配制
 *
 * @author liujun
 * @version 0.0.1
 */
@Configuration
@MapperScan("com.liujun.schedule.infrastructure.repository.task.mapper")
public class MyBatisScanConfiguration {


    @Bean(name = "testTransactionManager")
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public PageInterceptor pageCfg() {
        PageInterceptor cfg = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql");
        cfg.setProperties(properties);
        return cfg;
    }
}