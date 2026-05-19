package com.solar.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;

import static java.awt.SystemColor.info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI solarOpenAPI(){

        return new OpenAPI()
                .info(new Info()
                        .title("Smart Solar Monitoring System API")
                        .description("Professional Backend APIs for Smart Solar Monitoring System")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Jaymin Patel")
                                .email("jaymin18@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                        )
                );
    }
}
