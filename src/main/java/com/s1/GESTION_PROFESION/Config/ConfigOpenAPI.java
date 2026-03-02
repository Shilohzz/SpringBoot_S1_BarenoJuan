package com.s1.GESTION_PROFESION.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact; // CAMBIADO
import io.swagger.v3.oas.models.info.Info;    // CAMBIADO
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigOpenAPI {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gestión de Profesiones y Ventas")
                        .version("1.0")
                        .description("Sistema para la gestión de personas, productos y procesamiento de ventas con control de inventario."));
    }
}