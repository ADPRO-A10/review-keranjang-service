package id.ac.ui.cs.advprog.reviewkeranjangservice.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

import java.util.UUID;

@Getter @Setter
@Entity
public class Keranjang {
    @Id
    private String id;

    @ElementCollection
    private Map<String, String> listProduk;

    public void setId(String id) {
        if (!isValidUUID(id)) {
            throw new IllegalArgumentException("Invalid UUID");
        }
        this.id = id;
    }

    public boolean isValidUUID(String uuid) {
        try {
            UUID.fromString(uuid);
            return true;
        } catch (IllegalArgumentException exception) {
            return false;
        }
    }
}
