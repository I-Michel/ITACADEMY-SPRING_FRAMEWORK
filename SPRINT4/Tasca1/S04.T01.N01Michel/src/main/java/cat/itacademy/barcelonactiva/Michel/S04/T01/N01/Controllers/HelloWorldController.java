package cat.itacademy.barcelonactiva.Michel.S04.T01.N01.Controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {

    @GetMapping("/hello-world")
    public String hello(@RequestParam(value = "name", defaultValue = "UNKNOWN") String name) {
        return "Hello, " + name + ". You are running a Maven project.";
    }

    // http://localhost:9000/hello-world
    // http://localhost:9000/hello-world?name=Michel

    @GetMapping({"/hello-world2", "/hello-world2/{name}"})
    public String hello2(@PathVariable(required = false) String name) {
        return "Hello, " + (name != null ? name : "UNKNOWN") + ". You are running a Maven project.";
    }

    // http://localhost:9000/hello-world2
    // http://localhost:9000/hello-world2/Michel
}