package cat.itacademy.barcelonactiva.Michel.S05.T01.N02.model.service;

import cat.itacademy.barcelonactiva.Michel.S05.T01.N02.model.domain.Flower;
import cat.itacademy.barcelonactiva.Michel.S05.T01.N02.model.dto.FlowerDTO;

import java.util.List;
import java.util.Optional;

public interface FlowerService {

    FlowerDTO addFlower(FlowerDTO dto);
    Optional<Flower> getOptionalFlower(int id);
    FlowerDTO updateFlower(FlowerDTO dto);
    FlowerDTO deleteFlower(int id);
    FlowerDTO getOneFlower(int id);
    List<FlowerDTO> getAllFlowers();
}
