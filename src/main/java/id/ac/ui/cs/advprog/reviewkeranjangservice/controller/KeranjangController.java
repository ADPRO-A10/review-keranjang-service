package id.ac.ui.cs.advprog.reviewkeranjangservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Keranjang;
import id.ac.ui.cs.advprog.reviewkeranjangservice.service.KeranjangService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/keranjang")
public class KeranjangController {
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    KeranjangService keranjangService;

    @GetMapping
    public String renderKeranjangPage() {
        return "Hello World Keranjang!!";
    }

    @PostMapping
    public ResponseEntity<String> createKeranjang() throws JsonProcessingException {
        Keranjang keranjang = new Keranjang();

        Keranjang result = keranjangService.createKeranjang(keranjang);
        JSONObject response = new JSONObject();

        if (result == null) {
            response.put("message", "Keranjang gagal dibuat");
            return ResponseEntity.badRequest().body(response.toString());
        }

        String data = objectMapper.writeValueAsString(keranjang);
        response.put("message", "Keranjang berhasil dibuat");
        response.put("data", data);
        return ResponseEntity.ok(response.toString());
    }

}
