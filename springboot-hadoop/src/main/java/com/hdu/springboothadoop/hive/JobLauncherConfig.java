package com.hdu.springboothadoop.hive;

import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/12/13
 * @Time 下午5:30
 */
@Component
public class JobLauncherConfig {

    @Bean
    public SimpleJobLauncher jobLauncher(MapJobRepositoryFactoryBean repositoryFactoryBean) throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(repositoryFactoryBean.getObject());
        return jobLauncher;
    }
}
