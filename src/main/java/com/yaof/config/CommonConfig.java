package com.yaof.config;

import java.io.IOException;
import java.sql.SQLException;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;


/**
 * 基于configuration注解 ，bean注解实现连接池的初始化
 *
 * @author Administrator
 */
//@Configuration
public class CommonConfig {

    @Bean(name = "dataSource")
    @Qualifier
    public DruidDataSource getDataSource() {
        System.out.println("初始化dataSource");
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/r-c-a?useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        dataSource.setMaxActive(20);
        dataSource.setInitialSize(0);
        dataSource.setMinIdle(0);
        dataSource.setMaxWait(60000);
        dataSource.setValidationQuery("select 1");
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setTestWhileIdle(true);
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setMinEvictableIdleTimeMillis(25200000);
        dataSource.setRemoveAbandoned(true);
        dataSource.setRemoveAbandonedTimeout(1800);
        dataSource.setLogAbandoned(true);
        try {
            dataSource.setFilters("mergeStat");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dataSource;
    }

    @Bean(name = "sqlSessionFactoryBean")
    @Qualifier
    public SqlSessionFactoryBean getSqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(getDataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath*:mapper/*.xml");
        sqlSessionFactory.setMapperLocations(resources);

        return sqlSessionFactory;
    }

    @Bean
    @Qualifier
    public MapperScannerConfigurer getMapperScannerConfigurer() {
        MapperScannerConfigurer msc = new MapperScannerConfigurer();
        msc.setBasePackage("com.yaof.mapper");
        msc.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
        return msc;
    }
}
