package pl.dsw.dwolkowski.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swaggerBeanConfiguration(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/api/library/v1/**"))
                .apis(RequestHandlerSelectors.basePackage("pl.dsw.dwolkowski.api"))
                .build()
                .apiInfo(getInfoConfiguration());
    }

    private ApiInfo getInfoConfiguration(){
        return new ApiInfoBuilder()
                .title("Library Management")
                .description("Basic API for library management.")
                .license("License: dwolkowski")
                .build();
    }

}
