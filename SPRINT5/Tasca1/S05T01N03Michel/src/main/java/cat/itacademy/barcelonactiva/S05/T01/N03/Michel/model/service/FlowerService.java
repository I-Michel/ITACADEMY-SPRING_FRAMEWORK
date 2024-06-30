package cat.itacademy.barcelonactiva.S05.T01.N03.Michel.model.service;

import cat.itacademy.barcelonactiva.S05.T01.N03.Michel.model.domain.Flower;
import cat.itacademy.barcelonactiva.S05.T01.N03.Michel.model.dto.FlowerDTO;
import java.util.List;
import java.util.Optional;

public interface FlowerService {

    FlowerDTO addFlower(FlowerDTO dto);
    FlowerDTO updateFlower(FlowerDTO dto);
    FlowerDTO deleteFlower(int id);
    FlowerDTO getOneFlower(int id);
    List<FlowerDTO> getAllFlowers();
}
