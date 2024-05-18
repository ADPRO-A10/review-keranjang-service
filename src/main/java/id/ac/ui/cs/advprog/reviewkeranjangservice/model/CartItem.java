package id.ac.ui.cs.advprog.reviewkeranjangservice.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
@Entity
public class CartItem {
    @Id
    private UUID cartId;
    @Id
    private UUID itemId;

    private int jumlah;
    private int harga;
}
