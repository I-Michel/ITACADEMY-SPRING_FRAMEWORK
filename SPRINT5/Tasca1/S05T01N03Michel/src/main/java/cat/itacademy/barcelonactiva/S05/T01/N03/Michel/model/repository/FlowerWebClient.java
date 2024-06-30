package cat.itacademy.barcelonactiva.S05.T01.N03.Michel.model.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class FlowerWebClient {

    @Bean
    public WebClient webClient() {

        return WebClient.builder().baseUrl("http://localhost:9001").build();
    }
}