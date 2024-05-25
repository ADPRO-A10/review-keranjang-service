package id.ac.ui.cs.advprog.reviewkeranjangservice.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter @Setter
@Embeddable
public class CartItemKey implements Serializable {
    private UUID cartId;
    private UUID itemId;
}
