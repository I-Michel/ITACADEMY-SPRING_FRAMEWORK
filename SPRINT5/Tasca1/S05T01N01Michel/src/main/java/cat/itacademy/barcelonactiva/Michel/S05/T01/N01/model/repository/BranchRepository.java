package cat.itacademy.barcelonactiva.Michel.S05.T01.N01.model.repository;

import cat.itacademy.barcelonactiva.Michel.S05.T01.N01.model.domain.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {
}
