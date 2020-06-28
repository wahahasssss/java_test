package com.hdu.springboothadoop.hbase.config;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/12/13
 * @Time 下午4:24
 */
@org.springframework.context.annotation.Configuration
public class HBaseTemplateConfig {
    @Bean
    public HbaseTemplate hbaseTemplate(){
        HbaseTemplate hbaseTemplate = new HbaseTemplate();
        hbaseTemplate.setAutoFlush(true);
        hbaseTemplate.setConfiguration(HBaseConfiguration.create());
        return hbaseTemplate;
    }

    @Bean
    public Configuration configuration(){
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum","localhost,localhost");
        configuration.set("hbase.zookeeper.port","2181");
        return configuration;
    }

}
