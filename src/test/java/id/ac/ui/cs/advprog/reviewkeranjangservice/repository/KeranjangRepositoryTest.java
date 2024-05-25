package id.ac.ui.cs.advprog.reviewkeranjangservice.repository;

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


    @BeforeEach
    void setUp() {
        this.keranjangList = new ArrayList<>();

        Keranjang keranjang1 = new Keranjang();
        keranjangRepository.save(keranjang1);

        Keranjang keranjang2 = new Keranjang();
        keranjangRepository.save(keranjang2);


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

}
