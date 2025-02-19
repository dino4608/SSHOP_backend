package com.dino.sshop._infrastructure.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI myOpenAPI() {
        return new OpenAPI()
                .info(createApiInfo())
                .servers(List.of(
                        createServer("http://localhost:8044/sshop", "The server for the buyers in development environment"),
                        createServer("http://localhost:8044/sshop", "The server for the sellers in development environment"),
                        createServer("http://localhost:8044/sshop", "The server for the manager  in development environment")))
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes("Bearer Authentication", createJwtScheme()));
    }

    private Server createServer(String url, String description) {
        Server server = new Server();
        server.setUrl(url);
        server.setDescription(description);
        return server;
    }

    private SecurityScheme createJwtScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }

    private Info createApiInfo() {
        return new Info()
                .title("SSHOP API Document")
                .version("1.0")
                .description("This document exposes all endpoints of SSHOP Spring boot Backend")
                .contact(createContact())
                .license(createLicense());
    }

    public Contact createContact() {
        Contact contact = new Contact();
        contact.setName("tn9th8@gmail.com");
        contact.setEmail("tn9th8@gmail.com");
        return contact;
    }

    public License createLicense() {
        License license = new License();
        license.setName("Github SSHOP_backend");
        license.setUrl("https://github.com/tn9th8/SSHOP_backend");
        return license;
    }

}
