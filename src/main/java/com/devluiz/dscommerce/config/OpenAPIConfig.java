package com.devluiz.dscommerce.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("DSCommerce API")
                        .version("1.0")
                        .description("Documentação interativa da API do projeto DSCommerce"))
                // 1. Diz ao Swagger para aplicar essa segurança em todos os endpoints
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                // 2. Define como essa segurança funciona (Neste caso, Bearer Token / JWT)
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }
}
