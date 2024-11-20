package org.example.swaggerpr.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
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
public class OpenApiConfiguration {

    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI()
                .info(apiInfo())
                .externalDocs(externalDocs())
                .servers(apiServers())
                .components(securityComponents())
                .addSecurityItem(securityRequirements());
    }

    private Info apiInfo() {
        return new Info()
                .title("PDP Online Java(SpringDOC)")
                .description("This Document Designed For Teaching SpringDOC Project")
                .version("10")
                .contact(new Contact()
                        .name("Elmurodov Javohir")
                        .email("john.lgd65@gmail.com")
                        .url("https://github.com/jlkesh"))
                .license(new License()
                        .name("Apache 2.0")
                        .url("http://springdoc.org"))
                .termsOfService("http://swagger.io/terms/");
    }

    private ExternalDocumentation externalDocs() {
        return new ExternalDocumentation()
                .description("SpringShop Wiki Documentation")
                .url("https://springshop.wiki.github.org/docs");
    }

    private List<Server> apiServers() {
        return List.of(
                new Server().url("http://localhost:8080").description("Production Server"),
                new Server().url("http://localhost:9090").description("Test Server")
        );
    }

    private Components securityComponents() {
        return new Components()
                .addSecuritySchemes("basicAuth", new SecurityScheme()
                        .name("basicAuth")
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("basic"))
                .addSecuritySchemes("bearerAuth", new SecurityScheme()
                        .name("bearerAuth")
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT"));
    }

    private SecurityRequirement securityRequirements() {
        return new SecurityRequirement()
                .addList("basicAuth")
                .addList("bearerAuth");
    }
}