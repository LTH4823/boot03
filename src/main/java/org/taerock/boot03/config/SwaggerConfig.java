package org.taerock.boot03.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi getAllApi() {

        return GroupedOpenApi
                .builder()
                .group("all")
                .pathsToMatch("/**")
                .build();

    }

    @Bean
    public GroupedOpenApi getMemberApi() {

        return GroupedOpenApi
                .builder()
                .group("sample")
                .pathsToMatch("/sample/**")
                .build();

    }

    @Bean
    public OpenAPI getOpenApi() {

        return new OpenAPI().components(new Components())
                .info(getInfo());

    }

    private Info getInfo() {
        return new Info()
                .version("1.0.0")
                .title("Boot 01 Project Swagger")
                .description("Rest API Test");
    }


}
