package cat.itacademy.barcelonactiva.Michel.S05.T01.N01.controller;

import cat.itacademy.barcelonactiva.Michel.S05.T01.N01.model.dto.BranchDTO;
import cat.itacademy.barcelonactiva.Michel.S05.T01.N01.model.service.impl.BranchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class WebController {

    // For web app & view

    @Autowired
    private BranchServiceImpl branchService;

    @GetMapping({"/", "/welcome", "/home"})
    public String home(Model model) {
        model.addAttribute("message", "Welcome to the Branch Manager App!");
        return "home";
    }

    @GetMapping("/web/add")
    public String showAddForm(Model model) {
        model.addAttribute("branchDTO", new BranchDTO());
        return "add-form";
    }

    @PostMapping("/web/add")
    public String addFormBranch(@ModelAttribute("branchDTO") BranchDTO dto, Model model) {
        branchService.addBranch(dto);
        model.addAttribute("operation", "add");
        return "success";
    }

    @GetMapping("/web/branches")
    public String listBranches(Model model) {
        List<BranchDTO> branches = branchService.getAllBranches();
        model.addAttribute("branches", branches);
        return "branch-list";
    }

    @GetMapping("/web/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        BranchDTO branchDTO = branchService.getOneBranch(id);
        model.addAttribute("branchDTO", branchDTO);
        return "update-form";
    }

    @PostMapping("/web/update/{id}")
    public String updateBranch(@ModelAttribute("branchDTO") BranchDTO dto, Model model) {
        branchService.updateBranch(dto);
        model.addAttribute("operation", "update");
        return "success";
    }

    @PostMapping("/web/delete/{id}")
    public String deleteBranch(@PathVariable("id") int id, Model model) {
        branchService.deleteBranch(id);
        model.addAttribute("operation", "delete");
        return "success";
    }

    @GetMapping("/success")
    public String successPage() {
        return "success";
    }

}
