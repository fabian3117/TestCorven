package com.vof.user.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI myOpenAPI() {
        Contact contact = new Contact();
        contact.setEmail("fabian3117@frba.utn.edu.ar");
        contact.setName("Test Tecnico para corven");
        contact.setUrl("https://github.com/fabian3117/TestCorven");
        Info info = new Info()
                .title("CRUD DOCS")
                .version("1.0")
                .contact(contact)
                .description("Test CRUD Springboot con H2 y servidor Azure web App");
        return new OpenAPI().info(info);
    }
}
