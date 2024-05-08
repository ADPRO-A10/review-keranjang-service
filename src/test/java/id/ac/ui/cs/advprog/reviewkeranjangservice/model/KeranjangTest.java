package id.ac.ui.cs.advprog.reviewkeranjangservice.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class KeranjangTest {

    private List<Keranjang> listKeranjang;
    ObjectMapper objectMapper = new ObjectMapper();
    TypeReference<Map<String, Object>> typeRef = new TypeReference<>() {};

    @BeforeEach
    void setUp() throws JsonProcessingException {
        this.listKeranjang = new ArrayList<>();

        Keranjang keranjang = new Keranjang();
        keranjang.setId("5f7252e1-d5c3-4cb8-bdb2-58e4b3ab05db");
        Map<String, String> listProduk = new HashMap<>();
        keranjang.setListProduk(listProduk);

        Map<String, Object> produk1 = new HashMap<>();
        produk1.put("nama", "ak-47");
        produk1.put("id", "1e8aee36-8c0b-47a7-8248-bdc067b18d6d");
        produk1.put("harga", 100000);
        produk1.put("jumlah", 1);

        listProduk.put("1e8aee36-8c0b-47a7-8248-bdc067b18d6d", objectMapper.writeValueAsString(produk1));

        Map<String, Object> produk2 = new HashMap<>();
        produk2.put("nama", "m4a1");
        produk2.put("id", "c9e5eacd-56b8-4e2c-b528-75481e8b50c8");
        produk2.put("harga", 200000);
        produk2.put("jumlah", 2);

        listProduk.put("c9e5eacd-56b8-4e2c-b528-75481e8b50c8", objectMapper.writeValueAsString(produk2));

        listKeranjang.add(keranjang);
    }

    @Test
    void testGetId() {
        Keranjang keranjang = listKeranjang.get(0);
        assertEquals("5f7252e1-d5c3-4cb8-bdb2-58e4b3ab05db", keranjang.getId());
    }

    @Test
    void testGetListProduk() throws JsonProcessingException {
        Keranjang keranjang = listKeranjang.get(0);
        Map<String, String> listProduk = keranjang.getListProduk();

        Map<String, Object> produk1 = objectMapper.readValue(listProduk.get("1e8aee36-8c0b-47a7-8248-bdc067b18d6d"), typeRef);
        assertEquals(2, listProduk.size());
        assertEquals("ak-47", produk1.get("nama"));
        assertEquals("1e8aee36-8c0b-47a7-8248-bdc067b18d6d", produk1.get("id"));
        assertEquals(100000, produk1.get("harga"));
        assertEquals(1, produk1.get("jumlah"));

        Map<String, Object> produk2 = objectMapper.readValue(listProduk.get("c9e5eacd-56b8-4e2c-b528-75481e8b50c8"), typeRef);
        assertEquals("m4a1", produk2.get("nama"));
        assertEquals("c9e5eacd-56b8-4e2c-b528-75481e8b50c8", produk2.get("id"));
        assertEquals(200000, produk2.get("harga"));
        assertEquals(2, produk2.get("jumlah"));
    }

    @Test
    void testCreateKeranjangInvalidId() {
        assertThrows(IllegalArgumentException.class, () -> {
            Keranjang keranjang = new Keranjang();
            keranjang.setId("");
        });
    }
}
