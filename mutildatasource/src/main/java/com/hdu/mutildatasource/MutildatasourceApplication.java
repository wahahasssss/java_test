package com.hdu.mutildatasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
public class MutildatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MutildatasourceApplication.class, args);
    }
}
