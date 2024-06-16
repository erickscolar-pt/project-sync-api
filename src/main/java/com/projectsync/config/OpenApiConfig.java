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
                        .title("Gestão de Projetos API")
                        .version("1.0")
                        .description("API para gerenciamento de projetos da Microsoft.")
                        .termsOfService("http://swagger.io/terms/")
                        .contact(new Contact()
                                .name("Erick Scolar")
                                .url("http://www.microsoft.com")
                                .email("erick.scolar@outlook.com"))
                        .license(new License()
                                .url("http://springdoc.org")));
    }
}
