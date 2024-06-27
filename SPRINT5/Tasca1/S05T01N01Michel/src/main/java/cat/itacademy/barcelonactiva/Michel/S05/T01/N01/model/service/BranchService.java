package cat.itacademy.barcelonactiva.Michel.S05.T01.N01.model.service;

import cat.itacademy.barcelonactiva.Michel.S05.T01.N01.model.domain.Branch;
import cat.itacademy.barcelonactiva.Michel.S05.T01.N01.model.dto.BranchDTO;
import java.util.List;
import java.util.Optional;

public interface BranchService {

    BranchDTO addBranch(BranchDTO dto);
    Optional<Branch> getOptionalBranch(int id);
    BranchDTO updateBranch(BranchDTO dto);
    BranchDTO deleteBranch(int id);
    BranchDTO getOneBranch(int id);
    List<BranchDTO> getAllBranches();
}
