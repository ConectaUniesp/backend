package br.com.redeuniesp.api.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Rede Uniesp Api")
                        .version("v3")
                        .description("Api desenvolvida para sistema de Rede Social referente ao projeto de extensão uniesp")
                        .termsOfService("")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://thestartlaw.com/termo-de-uso/")
                        )
                );
    }

}