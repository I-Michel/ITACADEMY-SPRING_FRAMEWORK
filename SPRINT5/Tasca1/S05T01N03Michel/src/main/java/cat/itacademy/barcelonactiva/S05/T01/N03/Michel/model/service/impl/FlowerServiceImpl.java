package cat.itacademy.barcelonactiva.S05.T01.N03.Michel.model.service.impl;

import cat.itacademy.barcelonactiva.S05.T01.N03.Michel.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.S05.T01.N03.Michel.model.service.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

@Service
public class FlowerServiceImpl implements FlowerService {
    @Autowired
    private WebClient webClient;

    @Override
    public FlowerDTO addFlower(FlowerDTO dto) {
            return webClient.post()
                    .uri("/flower/add")
                    .bodyValue(dto)
                    .retrieve()
                    .bodyToMono(FlowerDTO.class)
                    .block();
    }

    @Override
    public FlowerDTO updateFlower(FlowerDTO dto) {
        return webClient.put()
                .uri("/flower/update")
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(FlowerDTO.class)
                .block();
    }

    @Override
    public FlowerDTO deleteFlower(int id) {
        return webClient.delete()
                .uri("/flower/delete/{id}", id)
                .retrieve()
                .bodyToMono(FlowerDTO.class)
                .block();
    }

    @Override
    public FlowerDTO getOneFlower(int id) {
        return webClient.get()
                .uri("/flower/getOne/{id}", id)
                .retrieve()
                .bodyToMono(FlowerDTO.class)
                .block();
    }

    @Override
    public List<FlowerDTO> getAllFlowers() {
        return webClient.get()
                .uri("/flower/getAll")
                .retrieve()
                .bodyToFlux(FlowerDTO.class)
                .collectList()
                .block();
    }
}