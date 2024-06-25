package cat.itacademy.barcelonactiva.Michel.S05.T01.N01.model.service.impl;

import cat.itacademy.barcelonactiva.Michel.S05.T01.N01.exception.BranchNotFoundException;
import cat.itacademy.barcelonactiva.Michel.S05.T01.N01.model.domain.Branch;
import cat.itacademy.barcelonactiva.Michel.S05.T01.N01.model.dto.BranchDTO;
import cat.itacademy.barcelonactiva.Michel.S05.T01.N01.model.repository.BranchRepository;
import cat.itacademy.barcelonactiva.Michel.S05.T01.N01.model.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BranchServiceImpl implements BranchService {
    @Autowired
    private BranchRepository branchRepository;

    private BranchDTO convertToDTO(Branch branch) {
        return new BranchDTO(branch.getPk_branchID(), branch.getBranchName(), branch.getBranchCountry());
    }

    private Branch convertToEntity(BranchDTO dto) {
        return new Branch(dto.getBranchName(), dto.getBranchCountry());
    }

    @Override
    public BranchDTO addBranch(BranchDTO dto) {
        try {
            return convertToDTO(branchRepository.save(convertToEntity(dto)));
        } catch (DataAccessException e) {
            throw new RuntimeException("Error when adding branch: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while adding branch: " + e.getMessage());
        }
    }

    @Override
    public Optional<Branch> getOptionalBranch(int id) throws BranchNotFoundException {
        Optional<Branch> optionalBranch = branchRepository.findById(id);

        if (optionalBranch.isEmpty()) {
            throw new BranchNotFoundException("Branch not found: ID " + id + ".");
        }
        return optionalBranch;
    }

    @Override
    public BranchDTO updateBranch(BranchDTO dto) throws BranchNotFoundException {
        Optional<Branch> optionalBranch = getOptionalBranch(dto.getPk_branchID());
        Branch okBranch = optionalBranch.get();
        okBranch.setBranchName(dto.getBranchName());
        okBranch.setBranchCountry(dto.getBranchCountry());

        try {
            return convertToDTO(branchRepository.save(okBranch));
        } catch (DataAccessException e) {
            throw new RuntimeException("Error when updating branch with ID " + okBranch.getPk_branchID() +
                    ": " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating branch with ID " +
                    okBranch.getPk_branchID() + ": " + e.getMessage());
        }
    }

    @Override
    public BranchDTO deleteBranch(int id) throws BranchNotFoundException {
        Optional<Branch> optionalBranch = getOptionalBranch(id);

        try {
            branchRepository.deleteById(id);
            return convertToDTO(optionalBranch.get());
        } catch (DataAccessException e) {
            throw new RuntimeException("Error when deleting branch with ID " + id +
                    ": " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while deleting branch with ID " +
                    id + ": " + e.getMessage());
        }
    }

    @Override
    public BranchDTO getOneFruit(int id) throws BranchNotFoundException {
        Optional<Branch> optionalBranch = getOptionalBranch(id);

        try {
            return convertToDTO(optionalBranch.get());
        } catch (DataAccessException e) {
            throw new RuntimeException("Error when getting branch with ID " + id +
                    ": " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while getting branch with ID " +
                    id + ": " + e.getMessage());
        }
    }

    @Override
    public List<BranchDTO> getAllBranches() {
        List<Branch> branches = branchRepository.findAll();
        return branches.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}