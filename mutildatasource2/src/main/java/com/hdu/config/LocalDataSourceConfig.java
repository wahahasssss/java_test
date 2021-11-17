package com.hdu.config;

import com.hdu.annation.LocalDatasource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/5/7
 * @Time 11:20 AM
 */
@Configuration
@MapperScan(basePackages = "com.hdu.dao", annotationClass = LocalDatasource.class, sqlSessionFactoryRef = LocalDataSourceConfig.LOCAL_SESSION_FACTORY_NAME)
public class LocalDataSourceConfig {
    static final String LOCAL_SESSION_FACTORY_NAME = "local_session_factory";


    @Bean(name = "localDatasource")
    @Primary
    @ConfigurationProperties(prefix = "local.datasource")
    public DataSource localDataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = LocalDataSourceConfig.LOCAL_SESSION_FACTORY_NAME)
    @Primary
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(localDataSource());
        return sqlSessionFactoryBean.getObject();
    }

}
