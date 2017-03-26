package com.demo.configs;

import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${demo.token.header}")
    String tokenHeader;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getInfo())
                .globalOperationParameters(Lists.newArrayList(get()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.ant("/error")))
                .build();
    }

    private Parameter get() {
        return new ParameterBuilder()
                .name(tokenHeader)
                .description("Put JWT token here")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .build();
    }

    private ApiInfo getInfo() {
        return new ApiInfoBuilder()
                .title("Plant application API demo")
                .description("REST API DEMO")
                .version("0.1")
                .build();
    }
}