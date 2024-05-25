package id.ac.ui.cs.advprog.reviewkeranjangservice.model;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
@Entity
public class CartItem {

    @EmbeddedId
    CartItemKey id;
    private int jumlah;
    private int harga;
    private String nama;
}
