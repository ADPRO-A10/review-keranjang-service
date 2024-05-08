package id.ac.ui.cs.advprog.reviewkeranjangservice.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Keranjang;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
public class KeranjangRepositoryTest {

    @Autowired
    KeranjangRepository keranjangRepository;
    List<Keranjang> keranjangList;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() throws JsonProcessingException {
        this.keranjangList = new ArrayList<>();

        Keranjang keranjang1 = new Keranjang();
        keranjang1.setId("5f7252e1-d5c3-4cb8-bdb2-58e4b3ab05db");
        Map<String, String> listProduk1 = new HashMap<>();
        keranjang1.setListProduk(listProduk1);

        Map<String, Object> produk1 = new HashMap<>();
        produk1.put("nama", "ak-47");
        produk1.put("id", "1e8aee36-8c0b-47a7-8248-bdc067b18d6d");
        produk1.put("harga", 100000);
        produk1.put("jumlah", 1);

        listProduk1.put("1e8aee36-8c0b-47a7-8248-bdc067b18d6d", objectMapper.writeValueAsString(produk1));

        Keranjang keranjang2 = new Keranjang();
        keranjang2.setId("7a49f82c-3bd4-4b80-af15-f22a8e90b1f5");
        Map<String, String> listProduk2 = new HashMap<>();
        keranjang2.setListProduk(listProduk2);

        Map<String, Object> produk2 = new HashMap<>();
        produk2.put("nama", "m4a1");
        produk2.put("id", "c9e5eacd-56b8-4e2c-b528-75481e8b50c8");
        produk2.put("harga", 200000);
        produk2.put("jumlah", 2);

        listProduk2.put("c9e5eacd-56b8-4e2c-b528-75481e8b50c8", objectMapper.writeValueAsString(produk2));

        keranjangList.add(keranjang1);
        keranjangList.add(keranjang2);
    }

    @Test
    void testSaveKeranjangSuccess() {
        Keranjang keranjang = keranjangList.get(1);
        Keranjang result = keranjangRepository.save(keranjang);

        Optional<Keranjang> findResult = keranjangRepository.findById(keranjangList.get(1).getId());
        assertTrue(findResult.isPresent(), "Keranjang not found");

        assertEquals(keranjang.getId(), result.getId());
        findResult.ifPresent(value ->
                assertEquals(keranjang.getId(), value.getId()));
    }

    @Test
    void testSaveUpdate() {
        Keranjang keranjang = keranjangList.get(1);
        keranjangRepository.save(keranjang);
        Keranjang newKeranjang = new Keranjang();
        newKeranjang.setId(keranjang.getId());
        newKeranjang.setListProduk(keranjang.getListProduk());
        Keranjang result = keranjangRepository.save(newKeranjang);

        Optional<Keranjang> findResult = keranjangRepository.findById(keranjangList.get(1).getId());
        assertTrue(findResult.isPresent(), "Keranjang not found");

        assertEquals(keranjang.getId(), result.getId());
        findResult.ifPresent(value ->
                assertEquals(keranjang.getId(), value.getId()));
    }

    @Test
    void testFindByIdIfIdFound() {
        keranjangRepository.saveAll(keranjangList);

        Optional<Keranjang> findResult = keranjangRepository.findById(keranjangList.get(1).getId());
        assertTrue(findResult.isPresent(), "Keranjang not found");

        findResult.ifPresent(value ->
                assertEquals(keranjangList.get(1).getId(), value.getId()));
    }

    @Test
    void testFindByIdIfIdNotFound() {
        keranjangRepository.saveAll(keranjangList);

        Optional<Keranjang> findResult = keranjangRepository.findById("zczc");
        assertFalse(findResult.isPresent());

        findResult.ifPresent(value ->
                assertEquals("zczc", value.getId()));
    }
}
