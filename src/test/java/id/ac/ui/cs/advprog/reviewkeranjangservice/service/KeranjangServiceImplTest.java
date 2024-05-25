package id.ac.ui.cs.advprog.reviewkeranjangservice.service;
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

    @BeforeEach
    void setUp() {
        this.keranjangList = new ArrayList<>();

        Keranjang keranjang1 = new Keranjang();
        keranjang1.setId(UUID.randomUUID());

        Keranjang keranjang2 = new Keranjang();
        keranjang2.setId(UUID.randomUUID());

        keranjangList.add(keranjang1);
        keranjangList.add(keranjang2);
    }

    @Test
    void testCreateKeranjang() {
        Keranjang keranjang1 = keranjangList.getFirst();
        doReturn(keranjang1).when(keranjangRepository).save(keranjang1);

        Keranjang result = keranjangService.createKeranjang(keranjang1);
        verify(keranjangRepository, times(1)).save(keranjang1);
        assertEquals(keranjang1.getId(), result.getId());
    }

    @Test
    void testCreateKeranjangIfAlreadyExists() {
        Keranjang keranjang1 = keranjangList.getFirst();
        doReturn(Optional.of(keranjang1)).when(keranjangRepository).findById(keranjang1.getId());

        assertNull(keranjangService.createKeranjang(keranjang1));
        verify(keranjangRepository, times(0)).save(keranjang1);
    }


    @Test
    void testFindByIdIfIdFound() {
        Keranjang keranjang = keranjangList.get(1);
        doReturn(Optional.of(keranjang)).when(keranjangRepository).findById(keranjang.getId());
        Keranjang result = keranjangService.findById(keranjang.getId());
        assertEquals(keranjang.getId(), result.getId());
    }
}
