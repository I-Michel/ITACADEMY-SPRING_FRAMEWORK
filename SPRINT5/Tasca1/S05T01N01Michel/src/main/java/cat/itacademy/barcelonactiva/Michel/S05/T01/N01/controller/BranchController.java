package cat.itacademy.barcelonactiva.Michel.S05.T01.N01.controller;

import cat.itacademy.barcelonactiva.Michel.S05.T01.N01.model.dto.BranchDTO;
import cat.itacademy.barcelonactiva.Michel.S05.T01.N01.model.service.impl.BranchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    //http://localhost:9000/sucursal/delete/{id}

    //http://localhost:9000/sucursal/getOne/{id}

    //http://localhost:9000/sucursal/getAll
}
