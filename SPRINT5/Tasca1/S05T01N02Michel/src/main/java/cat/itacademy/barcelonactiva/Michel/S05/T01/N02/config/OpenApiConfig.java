package cat.itacademy.barcelonactiva.Michel.S05.T01.N02.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
    public class OpenApiConfig {

        @Bean
        public OpenAPI openAPI() {
            return new OpenAPI()
                    .info(new Info().title("Flowers API Services")
                            .description("Flowers related services for IT Academy")
                            .version("1.0").contact(new Contact().name("I. Michel")
                                    .email( "michel.dev2024@gmail.com")));
        }
    }