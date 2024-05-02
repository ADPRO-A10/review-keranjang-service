package id.ac.ui.cs.advprog.reviewkeranjangservice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

import java.util.UUID;

@Getter @Setter
public class Keranjang {
    private String id;
    private List<Map<String, Object>> listProduk;

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
