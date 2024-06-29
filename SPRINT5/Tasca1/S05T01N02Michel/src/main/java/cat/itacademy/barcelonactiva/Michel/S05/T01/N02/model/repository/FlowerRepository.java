package cat.itacademy.barcelonactiva.Michel.S05.T01.N02.model.repository;

import cat.itacademy.barcelonactiva.Michel.S05.T01.N02.model.domain.Flower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowerRepository extends JpaRepository<Flower, Integer> {
}
