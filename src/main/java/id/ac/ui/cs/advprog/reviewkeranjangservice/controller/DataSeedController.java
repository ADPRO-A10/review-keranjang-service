package id.ac.ui.cs.advprog.reviewkeranjangservice.controller;

import id.ac.ui.cs.advprog.reviewkeranjangservice.service.DataSeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataSeedController {
    @Autowired
    private DataSeedService dataSeedService;

    @GetMapping("/seed-data-master")
    public ResponseEntity<String> seedMaster() {
        dataSeedService.seedKeranjang();

        return ResponseEntity.ok("Seeding data master completed successfully.");
    }

    @GetMapping("/seed-cart-item")
    public ResponseEntity<String> seedStudents() {
        dataSeedService.seedCartItem();

        return ResponseEntity.ok("Seeding student course completed successfully.");
    }
}
