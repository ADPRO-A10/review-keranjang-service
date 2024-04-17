package id.ac.ui.cs.advprog.reviewkeranjangservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
public class HomeController {

    @GetMapping
    public String renderHomePage() {
        return "Hello World";
    }

}
