package com.kshrd.warehouse_master;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@OpenAPIDefinition(servers = {@Server(url = "/", description = "default server url instead of call local ip"), @Server(url = "https://spring.hanyeaktong.site", description = "HTTPS Route")})
@SecurityScheme(
        name = "bearer",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer"
)
public class WarehouseMasterApplication {

    public static void main(String[] args) {
        SpringApplication.run(WarehouseMasterApplication.class, args);
    }

}
