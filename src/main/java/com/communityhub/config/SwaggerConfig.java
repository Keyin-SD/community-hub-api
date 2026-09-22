package com.communityhub.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI communityHubOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Community Hub API")
                        .description("API for managing community resources")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Community Hub Team")
                                .email("tryhardman83@gmail.com")));
    }
}
