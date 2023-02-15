package com.blog.blogappapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
public class SwaggerConfig {
    public static final String AUTHORIZATION_HEADER="Authorization";

    private ApiKey apiKeys(){
        return new ApiKey("JWT",AUTHORIZATION_HEADER,"header");
    }

    private List<SecurityContext> securityContextList(){
        return Arrays.asList(SecurityContext.builder().securityReferences(securityReferences()).build());
    }

    private List<SecurityReference> securityReferences(){

        AuthorizationScope scopes=new AuthorizationScope("global","accessEverthing");
        return Arrays.asList(new SecurityReference("JWT",new AuthorizationScope[]{scopes}));
    }

    @Bean
    public Docket api10(){


        return new Docket(DocumentationType.SWAGGER_2).groupName("api-1.0").apiInfo(new ApiInfoBuilder().version("1.0").title("Version 1.0 Apis").description("Documentation of Api V1.0").build())
                .securityContexts(securityContextList())
                .securitySchemes(Arrays.asList(apiKeys()))
                .select().
                apis(RequestHandlerSelectors
                        .any())
                .paths(regex("/api/v1.0.*"))
                .build();
    }

    @Bean
    public Docket api12(){


        return new Docket(DocumentationType.SWAGGER_2).groupName("api-1.2").apiInfo(new ApiInfoBuilder().version("1.2").title("Version 1.2 Apis").description("Documentation of Api V1.2").build())
                .securityContexts(securityContextList())
                .securitySchemes(Arrays.asList(apiKeys()))
                .select().
                apis(RequestHandlerSelectors
                        .any()).paths(regex("/api/v1.2.*"))
                .build();
    }

//    private ApiInfo getInfo() {
//        return new ApiInfo("Bloging Application","This Project is developed by shilpa pandey","1.0","Terms of Services",new Contact("Shilpa","https://shilpapandey.com","shilpa.pandey@mail.vinove.com"),"License of Apis","Api License Url", Collections.emptyList());
//    }
}
