package id.ac.ui.cs.advprog.reviewkeranjangservice.repository;

import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Keranjang;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class KeranjangRepositoryTest {
    KeranjangRepository keranjangRepository;
    List<Keranjang> keranjangList;

    @BeforeEach
    void setUp() {
        this.keranjangRepository = new KeranjangRepository();
        this.keranjangList = new ArrayList<>();

        Keranjang keranjang1 = new Keranjang();
        keranjang1.setId("5f7252e1-d5c3-4cb8-bdb2-58e4b3ab05db");
        List<Map<String, Object>> listProduk1 = new ArrayList<>();
        keranjang1.setListProduk(listProduk1);

        Map<String, Object> produk1 = new HashMap<>();
        produk1.put("nama", "ak-47");
        produk1.put("id", "1e8aee36-8c0b-47a7-8248-bdc067b18d6d");
        produk1.put("harga", 100000);
        produk1.put("jumlah", 1);

        listProduk1.add(produk1);

        Keranjang keranjang2 = new Keranjang();
        keranjang2.setId("7a49f82c-3bd4-4b80-af15-f22a8e90b1f5");
        List<Map<String, Object>> listProduk2 = new ArrayList<>();
        keranjang2.setListProduk(listProduk2);

        Map<String, Object> produk2 = new HashMap<>();
        produk2.put("nama", "m4a1");
        produk2.put("id", "c9e5eacd-56b8-4e2c-b528-75481e8b50c8");
        produk2.put("harga", 200000);
        produk2.put("jumlah", 2);

        listProduk2.add(produk2);

        keranjangList.add(keranjang1);
        keranjangList.add(keranjang2);
    }

    @Test
    void testSaveKeranjangSuccess() {
        Keranjang keranjang = keranjangList.get(1);
        Keranjang result = keranjangRepository.save(keranjang);

        Keranjang findResult = keranjangRepository.findById(keranjangList.get(1).getId());
        assertEquals(keranjang.getId(), result.getId());
        assertEquals(keranjang.getId(), findResult.getId());
    }

    @Test
    void testSaveUpdate() {
        Keranjang keranjang = keranjangList.get(1);
        keranjangRepository.save(keranjang);
        Keranjang newKeranjang = new Keranjang();
        newKeranjang.setId(keranjang.getId());
        newKeranjang.setListProduk(keranjang.getListProduk());
        Keranjang result = keranjangRepository.save(newKeranjang);

        Keranjang findResult = keranjangRepository.findById(keranjangList.get(1).getId());
        assertEquals(keranjang.getId(), result.getId());
        assertEquals(keranjang.getListProduk(), findResult.getListProduk());
    }

    @Test
    void testFindByIdIfIdFound() {
        for (Keranjang keranjang : keranjangList) {
            keranjangRepository.save(keranjang);
        }

        Keranjang findResult = keranjangRepository.findById(keranjangList.get(1).getId());
        assertEquals(keranjangList.get(1).getId(), findResult.getId());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        for (Keranjang keranjang : keranjangList) {
            keranjangRepository.save(keranjang);
        }

        Keranjang findResult = keranjangRepository.findById("zczc");
        assertNull(findResult);
    }
}
