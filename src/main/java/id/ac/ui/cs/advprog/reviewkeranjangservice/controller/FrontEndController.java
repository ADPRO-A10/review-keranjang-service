package id.ac.ui.cs.advprog.reviewkeranjangservice.controller;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;


@Controller
@RequestMapping("/")
public class FrontEndController {
    RestTemplate restTemplate = new RestTemplate();
    @GetMapping("/keranjang/{id}")
    public String renderKeranjangPage(Model model, @PathVariable String id){
        String url = "http://35.198.245.243/api/keranjang/" + id;

        ResponseEntity<Iterable<?>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        Iterable<?> data = response.getBody();


        model.addAttribute("data", data);
        return "viewKeranjang";
    }
}
