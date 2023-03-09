package tn.devteam.immonexus.Configurations;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(infoAPI());
    }
    public Info infoAPI() {
        return new Info().title("SpringDoc-Demo")
                .description("Application ImmoNexus")
                .contact(contactAPI());
    }
    public Contact contactAPI() {
        Contact contact = new Contact().name("DevTeam")
                .email("bejaoui.mariem@esprit.tn")
                .url("https://www.linkedin.com/in/**********/");
        return contact;
    }


/*    @Bean
    public GroupedOpenApi productPublicApi() {
        return GroupedOpenApi.builder()
                .group("Only Contrat Management API")
                .pathsToMatch("/contrat/**")
                //.pathsToExclude("**")
                .build();
    }*/


}
