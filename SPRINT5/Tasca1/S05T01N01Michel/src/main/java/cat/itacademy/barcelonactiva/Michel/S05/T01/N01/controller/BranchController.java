package cat.itacademy.barcelonactiva.Michel.S05.T01.N01.controller;

import cat.itacademy.barcelonactiva.Michel.S05.T01.N01.model.dto.BranchDTO;
import cat.itacademy.barcelonactiva.Michel.S05.T01.N01.model.service.impl.BranchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branch")
public class BranchController {

    @Autowired
    private BranchServiceImpl branchService;

    // http://localhost:9000/sucursal/add
    @PostMapping("/add")
    public ResponseEntity<BranchDTO> addBranch(@RequestBody BranchDTO dto) {
        BranchDTO addedDto = branchService.addBranch(dto);
        return ResponseEntity.ok(addedDto);
    }

    //http://localhost:9000/sucursal/update
    @PutMapping("/update")
    public ResponseEntity<BranchDTO> updateBranch(@RequestBody BranchDTO dto) {
        BranchDTO updatedDto = branchService.updateBranch(dto);
        return ResponseEntity.ok(updatedDto);
    }

    //http://localhost:9000/sucursal/delete/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BranchDTO> deleteBranch(@PathVariable int id) {
        BranchDTO deletedDto = branchService.deleteBranch(id);
        return ResponseEntity.ok(deletedDto);
    }

    //http://localhost:9000/sucursal/getOne/{id}
    @GetMapping("/getOne/{id}")
    public ResponseEntity<BranchDTO> getOneBranch(@PathVariable int id) {
        BranchDTO gottenDto = branchService.getOneBranch(id);
        return ResponseEntity.ok(gottenDto);
    }

    //http://localhost:9000/sucursal/getAll
    @GetMapping("getAll")
    public ResponseEntity<List<BranchDTO>> getAllBranches() {
        return ResponseEntity.ok(branchService.getAllBranches());
    }
}