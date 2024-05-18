package id.ac.ui.cs.advprog.reviewkeranjangservice.service;
import id.ac.ui.cs.advprog.reviewkeranjangservice.model.CartItem;
import id.ac.ui.cs.advprog.reviewkeranjangservice.model.CartItemKey;
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
        CartItem cartItem1 = new CartItem();
        CartItem cartItem2 = new CartItem();
        keranjang1.setId(UUID.randomUUID());
        CartItemKey cartItemKey1 = new CartItemKey();
        CartItemKey cartItemKey2 = new CartItemKey();

        Map<String, Object> produk1 = new HashMap<>();
        produk1.put("nama", "ak-47");
        produk1.put("id", "1e8aee36-8c0b-47a7-8248-bdc067b18d6d");
        produk1.put("harga", 100000);
        produk1.put("jumlah", 1);

        cartItemKey1.setItemId(UUID.fromString("1e8aee36-8c0b-47a7-8248-bdc067b18d6d"));
        cartItemKey1.setCartId(keranjang1.getId());
        cartItem1.setId(cartItemKey1);

        Keranjang keranjang2 = new Keranjang();
        keranjang2.setId(UUID.randomUUID());

        Map<String, Object> produk2 = new HashMap<>();
        produk2.put("nama", "m4a1");
        produk2.put("id", "c9e5eacd-56b8-4e2c-b528-75481e8b50c8");
        produk2.put("harga", 200000);
        produk2.put("jumlah", 2);

        cartItemKey2.setItemId(UUID.fromString("c9e5eacd-56b8-4e2c-b528-75481e8b50c8"));
        cartItemKey2.setCartId(keranjang2.getId());
        cartItem1.setId(cartItemKey2);

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
    void testFindByIdIfIdFound() {
        Keranjang keranjang = keranjangList.get(1);
        doReturn(Optional.of(keranjang)).when(keranjangRepository).findById(keranjang.getId());
        Keranjang result = keranjangService.findById(keranjang.getId());
        assertEquals(keranjang.getId(), result.getId());
    }
}
