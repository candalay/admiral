package com.ingenico.account;

import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
@EnableSwagger2
public class AccountApi {
    public static void main(String[] args) {
        SpringApplication.run(AccountApi.class, args);
    }
    
    @Bean
	public Docket swaggerSpringMvcPlugin() {
		return new Docket(SWAGGER_2)
				.groupName("account-api")
                .apiInfo(apiInfo())
				.select()
				.apis(withClassAnnotation(RestController.class))
                .paths(any())
                .build();
	}
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Account API")
                .description("API service for account operations")
                .license("Account-Api")
                //.licenseUrl("github")
                .build();
    }
}
