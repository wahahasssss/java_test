package com.example.springbootdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Set;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/6/6
 * @Time 下午5:19
 */

/**
 * swagger配置类
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket apis() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .produces(produces())
                .apiInfo(apiInfo())
                .enableUrlTemplating(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.springbootdemo.controller"))
                .paths(PathSelectors.any())
                .build();
    }


    private Set<String> produces() {
        Set<String> produces = new HashSet<String>();
        produces.add("application/json");
        return produces;
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("说明1", "说明2", "说明3", "说明4", new Contact("shusf", "", ""), "说明5", "说明6");
    }
}
