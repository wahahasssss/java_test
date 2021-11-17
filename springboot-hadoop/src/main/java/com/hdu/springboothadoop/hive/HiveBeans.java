package com.hdu.springboothadoop.hive;

import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/12/13
 * @Time 下午5:25
 */
@Configuration
public class HiveBeans {
    @Bean
    public MapJobRepositoryFactoryBean repositoryFactoryBean() {
        return new MapJobRepositoryFactoryBean();
    }

    @Bean
    public ResourcelessTransactionManager transactionManager() {
        return new ResourcelessTransactionManager();
    }


}
