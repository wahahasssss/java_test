package com.hdu.config;

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
 * @Time 11:41 AM
 */
@Configuration
@MapperScan(basePackages = "com.hdu.dao", annotationClass = com.hdu.annation.DevDatasource.class, sqlSessionFactoryRef = DevDatasourceConfig.DEV_SESSION_FACTORY_NAME)
public class DevDatasourceConfig {
    static final String DEV_SESSION_FACTORY_NAME = "dev_session_factory";

    @Bean(name = "devDatasource")
    @ConfigurationProperties(prefix = "dev.datasource")
    public DataSource devDatasource() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = DevDatasourceConfig.DEV_SESSION_FACTORY_NAME)
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(devDatasource());
        return sqlSessionFactoryBean.getObject();
    }
}
