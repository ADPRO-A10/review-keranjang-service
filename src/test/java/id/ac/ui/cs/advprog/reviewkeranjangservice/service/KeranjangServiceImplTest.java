package id.ac.ui.cs.advprog.reviewkeranjangservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Keranjang;
import id.ac.ui.cs.advprog.reviewkeranjangservice.repository.KeranjangRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class KeranjangServiceImplTest {

    @InjectMocks
    KeranjangServiceImpl keranjangService;
    @Mock
    KeranjangRepository keranjangRepository;
    List<Keranjang> keranjangList;
    ObjectMapper objectMapper = new ObjectMapper();
    TypeReference<Map<String, Object>> typeRef = new TypeReference<>() {};

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
    void testCreateKeranjang() {
        Keranjang keranjang1 = keranjangList.get(0);
        doReturn(keranjang1).when(keranjangRepository).save(keranjang1);

        Keranjang result = keranjangService.createKeranjang(keranjang1);
        verify(keranjangRepository, times(1)).save(keranjang1);
        assertEquals(keranjang1.getId(), result.getId());
    }

    @Test
    void testCreateKeranjangIfAlreadyExists() {
        Keranjang keranjang1 = keranjangList.get(0);
        doReturn(Optional.of(keranjang1)).when(keranjangRepository).findById(keranjang1.getId());

        assertNull(keranjangService.createKeranjang(keranjang1));
        verify(keranjangRepository, times(0)).save(keranjang1);
    }

    @Test
    void testUpdateKeranjang() throws JsonProcessingException {
        Keranjang keranjang1 = keranjangList.get(0);
        Map<String, String> listProduk1 = keranjang1.getListProduk();
        Map<String, Object> produk1 = objectMapper.readValue(listProduk1.get("1e8aee36-8c0b-47a7-8248-bdc067b18d6d"), typeRef);

        produk1.put("jumlah", 2);
        listProduk1.put("1e8aee36-8c0b-47a7-8248-bdc067b18d6d", objectMapper.writeValueAsString(produk1));

        doReturn(keranjang1).when(keranjangRepository).save(keranjang1);
        doReturn(Optional.of(keranjang1)).when(keranjangRepository).findById(keranjang1.getId());

        Keranjang result = keranjangService.updateKeranjang("5f7252e1-d5c3-4cb8-bdb2-58e4b3ab05db", keranjang1);
        Map<String, String> listProdukResult = result.getListProduk();
        Map<String, Object> produkResult = objectMapper.readValue(listProdukResult.get("1e8aee36-8c0b-47a7-8248-bdc067b18d6d"), typeRef);
        assertEquals("5f7252e1-d5c3-4cb8-bdb2-58e4b3ab05db", result.getId());
        assertEquals("ak-47", produkResult.get("nama"));
        assertEquals(2, produkResult.get("jumlah"));
        verify(keranjangRepository, times(1)).save(keranjang1);
    }

    @Test
    void testUpdateKeranjangIfNotExists() {
        doReturn(Optional.empty()).when(keranjangRepository).findById("zczc");

        assertThrows(IllegalArgumentException.class, () -> {
            keranjangService.updateKeranjang("zczc", keranjangList.get(0));
        });
        verify(keranjangRepository, times(0)).save(any(Keranjang.class));
    }

    @Test
    void testFindByIdIfIdFound() {
        Keranjang keranjang = keranjangList.get(1);
        doReturn(Optional.of(keranjang)).when(keranjangRepository).findById(keranjang.getId());
        Keranjang result = keranjangService.findById(keranjang.getId());
        assertEquals(keranjang.getId(), result.getId());
    }
}
