package Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.print.Doc;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by B41-80 on 2016/12/15.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/api/")
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .produces(produces())
                .apiInfo(apiInfo())
                .enableUrlTemplating(true);
    }

    private Set<String> produces() {
        Set<String> produces = new HashSet<String>();
        produces.add("application/json");
        return produces;
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "MySwagger",
                "FAEWFWEFAWE",
                "V.3.3",
                null,
                new Contact("shusf", null, "fefwefew"),
                null,
                null
        );
    }
}
