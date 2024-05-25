package id.ac.ui.cs.advprog.reviewkeranjangservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

// dummy model product
@Getter @Setter @Entity
public class Product {
    @Id
    private String productId;
    private String productName;
    private int productQuantity;

    public Product(){
        this.productId = UUID.randomUUID().toString();
    }
}