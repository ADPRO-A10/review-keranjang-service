package id.ac.ui.cs.advprog.reviewkeranjangservice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

// dummy model product
@Getter @Setter
public class Product {
    private String productId;
    private String productName;
    private int productQuantity;

    public Product(){
        this.productId = UUID.randomUUID().toString();
    }
}