package com.hdu.mutildatasource.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.hdu.mutildatasource.constant.DatabaseType;
import com.hdu.mutildatasource.datasource.DynamicDataSourceConfig;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/8/2
 * @Time 下午2:05
 */
@Configuration
@PropertySource("classpath:application.properties")
@MapperScan(basePackages = "com.hdu.mutildatasource.mapper")
public class MybatisConfig {


    @Value("${db1.datasource.driver-class-name}")
    private String db1DriverClass;

    @Value("${db1.datasource.url}")
    private String db1Url;

    @Value("${db1.datasource.username}")
    private String userName;

    @Value("${db1.datasource.password}")
    private String password;


    @Value("${db2.datasource.driver-class-name}")
    private String db2DriverClass;

    @Value("${db2.datasource.url}")
    private String db2Url;

    @Value("${db2.datasource.username}")
    private String db2UserName;

    @Value("${db2.datasource.password}")
    private String db2Password;

    @Bean(value = "db1databaseSource")
    public DataSource db1databaseSource() throws Exception {
        Properties properties = new Properties();
        properties.put("driverClassName", db1DriverClass);
        properties.put("url", db1Url);
        properties.put("username", userName);
        properties.put("password", password);
        return DruidDataSourceFactory.createDataSource(properties);
    }


    @Bean(value = "db2databaseSource")
    public DataSource db2databaseSource() throws Exception {
        Properties properties = new Properties();
        properties.put("driverClassName", db2DriverClass);
        properties.put("url", db2Url);
        properties.put("username", db2UserName);
        properties.put("password", db2Password);
        return DruidDataSourceFactory.createDataSource(properties);
    }

    @Bean
    @Primary
    public DynamicDataSourceConfig dynamicDataSourceConfig(@Qualifier("db1databaseSource") DataSource db1DataSource,
                                                           @Qualifier("db2databaseSource") DataSource db2DataSource) throws Exception {
        Map<Object, Object> dataSources = new HashMap<>();
        dataSources.put(DatabaseType.db1, db1DataSource);
        dataSources.put(DatabaseType.db2, db2DataSource);
        DynamicDataSourceConfig dynamicDataSource = new DynamicDataSourceConfig();
        dynamicDataSource.setTargetDataSources(dataSources);
        dynamicDataSource.setDefaultTargetDataSource(db1DataSource);
        return dynamicDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory1(DynamicDataSourceConfig dynamicDataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dynamicDataSource);
//        factoryBean.setTypeAliasesPackage(environment.getProperty("mybatis.typeAliasesPackage"));
//        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(environment.getProperty("mybaitslocakiont")));
        return factoryBean.getObject();
    }

//
//    @Bean
//    public DataSourceTransactionManager transactionManager(DynamicDataSourceConfig dynamicDataSource){
//        return new DataSourceTransactionManager(dynamicDataSource);
//    }


}
