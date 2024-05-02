package id.ac.ui.cs.advprog.reviewkeranjangservice.model;

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
    @BeforeEach
    void setUp() {
        this.listKeranjang = new ArrayList<>();

        Keranjang keranjang = new Keranjang();
        keranjang.setId("5f7252e1-d5c3-4cb8-bdb2-58e4b3ab05db");
        List<Map<String, Object>> listProduk = new ArrayList<>();
        keranjang.setListProduk(listProduk);

        Map<String, Object> produk1 = new HashMap<>();
        produk1.put("nama", "ak-47");
        produk1.put("id", "1e8aee36-8c0b-47a7-8248-bdc067b18d6d");
        produk1.put("harga", 100000);
        produk1.put("jumlah", 1);

        listProduk.add(produk1);

        Map<String, Object> produk2 = new HashMap<>();
        produk2.put("nama", "m4a1");
        produk2.put("id", "c9e5eacd-56b8-4e2c-b528-75481e8b50c8");
        produk2.put("harga", 200000);
        produk2.put("jumlah", 2);

        listProduk.add(produk2);

        listKeranjang.add(keranjang);
    }

    @Test
    void testGetId() {
        Keranjang keranjang = listKeranjang.get(0);
        assertEquals("5f7252e1-d5c3-4cb8-bdb2-58e4b3ab05db", keranjang.getId());
    }

    @Test
    void testGetListProduk() {
        Keranjang keranjang = listKeranjang.get(0);
        List<Map<String, Object>> listProduk = keranjang.getListProduk();
        assertEquals(2, listProduk.size());
        assertEquals("ak-47", listProduk.get(0).get("nama"));
        assertEquals("1e8aee36-8c0b-47a7-8248-bdc067b18d6d", listProduk.get(0).get("id"));
        assertEquals(100000, listProduk.get(0).get("harga"));
        assertEquals(1, listProduk.get(0).get("jumlah"));

        assertEquals("m4a1", listProduk.get(1).get("nama"));
        assertEquals("c9e5eacd-56b8-4e2c-b528-75481e8b50c8", listProduk.get(1).get("id"));
        assertEquals(200000, listProduk.get(1).get("harga"));
        assertEquals(2, listProduk.get(1).get("jumlah"));
    }

    @Test
    void testCreateKeranjangInvalidId() {
        assertThrows(IllegalArgumentException.class, () -> {
            Keranjang keranjang = new Keranjang();
            keranjang.setId("");
        });
    }
}
