package cat.itacademy.barcelonactiva.Michel.S05.T01.N01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping({"/", "/welcome"})
    public String home(Model model) {
        model.addAttribute("message", "Welcome to the Branch manager app!");
        return "index";
    }
}
