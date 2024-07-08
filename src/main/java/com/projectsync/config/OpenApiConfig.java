package com.projectsync.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig implements WebMvcConfigurer {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Teste")
                        .version("1.0")
                        .description("API para Teste.")
                        .contact(new Contact()
                                .name("Erick Scolar")
                                .email("erick.scolar@outlook.com"))
                        .license(new License()
                                .url("http://springdoc.org")));
    }
}
