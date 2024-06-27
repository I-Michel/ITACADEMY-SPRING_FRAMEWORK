package cat.itacademy.barcelonactiva.Michel.S05.T01.N01.model.service.impl;

import cat.itacademy.barcelonactiva.Michel.S05.T01.N01.exception.BranchNotFoundException;
import cat.itacademy.barcelonactiva.Michel.S05.T01.N01.model.domain.Branch;
import cat.itacademy.barcelonactiva.Michel.S05.T01.N01.model.dto.BranchDTO;
import cat.itacademy.barcelonactiva.Michel.S05.T01.N01.model.repository.BranchRepository;
import cat.itacademy.barcelonactiva.Michel.S05.T01.N01.model.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return convertToDTO(branchRepository.save(convertToEntity(dto)));
    }

    @Override
    public Optional<Branch> getOptionalBranch(int id) {
        return branchRepository.findById(id);
    }

    @Override
    public BranchDTO updateBranch(BranchDTO dto) {
        Optional<Branch> optionalBranch = getOptionalBranch(dto.getPk_branchID());
        Branch okBranch = optionalBranch.orElseThrow(() -> new BranchNotFoundException("Branch not found with id: " + dto.getPk_branchID()));

        okBranch.setBranchName(dto.getBranchName());
        okBranch.setBranchCountry(dto.getBranchCountry());

        return convertToDTO(branchRepository.save(okBranch));
    }

    @Override
    public BranchDTO deleteBranch(int id) {
        Optional<Branch> optionalBranch = getOptionalBranch(id);
        Branch okBranch = optionalBranch.orElseThrow(() -> new BranchNotFoundException("Branch not found with id: " + id));

        branchRepository.deleteById(id);
        return convertToDTO(okBranch);
    }

    @Override
    public BranchDTO getOneBranch(int id) {
        Optional<Branch> optionalBranch = getOptionalBranch(id);

        return convertToDTO(optionalBranch.orElseThrow(() -> new BranchNotFoundException("Branch not found with id: " + id)));
    }

    @Override
    public List<BranchDTO> getAllBranches() {
        List<Branch> branches = branchRepository.findAll();
        return branches.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}