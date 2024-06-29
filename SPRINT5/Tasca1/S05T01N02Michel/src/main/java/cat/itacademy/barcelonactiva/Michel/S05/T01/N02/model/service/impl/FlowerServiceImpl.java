package cat.itacademy.barcelonactiva.Michel.S05.T01.N02.model.service.impl;

import cat.itacademy.barcelonactiva.Michel.S05.T01.N02.exception.FlowerNotFoundException;
import cat.itacademy.barcelonactiva.Michel.S05.T01.N02.model.domain.Flower;
import cat.itacademy.barcelonactiva.Michel.S05.T01.N02.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.Michel.S05.T01.N02.model.repository.FlowerRepository;
import cat.itacademy.barcelonactiva.Michel.S05.T01.N02.model.service.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlowerServiceImpl implements FlowerService {
    @Autowired
    private FlowerRepository flowerRepository;

    private FlowerDTO convertToDTO(Flower flower) {
        return new FlowerDTO(flower.getPk_flowerID(), flower.getFlowerName(), flower.getFlowerCountry());
    }

    private Flower convertToEntity(FlowerDTO dto) {
        return new Flower(dto.getFlowerName(), dto.getFlowerCountry());
    }

    @Override
    public FlowerDTO addFlower(FlowerDTO dto) {
        return convertToDTO(flowerRepository.save(convertToEntity(dto)));
    }

    @Override
    public Optional<Flower> getOptionalFlower(int id) {
        return flowerRepository.findById(id);
    }

    @Override
    public FlowerDTO updateFlower(FlowerDTO dto) {
        Optional<Flower> optionalBranch = getOptionalFlower(dto.getPk_flowerID());
        Flower okFlower = optionalBranch.orElseThrow(() -> new FlowerNotFoundException(dto.getPk_flowerID()));

        okFlower.setFlowerName(dto.getFlowerName());
        okFlower.setFlowerCountry(dto.getFlowerCountry());

        return convertToDTO(flowerRepository.save(okFlower));
    }

    @Override
    public FlowerDTO deleteFlower(int id) {
        Optional<Flower> optionalBranch = getOptionalFlower(id);
        Flower okFlower = optionalBranch.orElseThrow(() -> new FlowerNotFoundException(id));

        flowerRepository.deleteById(id);
        return convertToDTO(okFlower);
    }

    @Override
    public FlowerDTO getOneFlower(int id) {
        Optional<Flower> optionalBranch = getOptionalFlower(id);

        return convertToDTO(optionalBranch.orElseThrow(() -> new FlowerNotFoundException(id)));
    }

    @Override
    public List<FlowerDTO> getAllFlowers() {
        List<Flower> branches = flowerRepository.findAll();
        return branches.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}