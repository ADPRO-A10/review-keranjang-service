package id.ac.ui.cs.advprog.reviewkeranjangservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/keranjang")
public class KeranjangController {

    @GetMapping
    public String renderKeranjangPage() {
        return "Hello World Keranjang!!";
    }

}
