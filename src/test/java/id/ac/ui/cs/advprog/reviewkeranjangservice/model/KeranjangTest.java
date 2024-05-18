package id.ac.ui.cs.advprog.reviewkeranjangservice.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class KeranjangTest {

    private List<Keranjang> listKeranjang;
    private List<CartItem> listCartItem;

    @BeforeEach
    void setUp() {
        this.listKeranjang = new ArrayList<>();

        Keranjang keranjang = new Keranjang();
        keranjang.setId(UUID.randomUUID());

        CartItem cartItem1 = new CartItem();
        CartItem cartItem2 = new CartItem();

        CartItemKey cartItemKey1 = new CartItemKey();
        CartItemKey cartItemKey2 = new CartItemKey();

        Map<String, Object> produk1 = new HashMap<>();
        produk1.put("nama", "ak-47");
        produk1.put("id", "1e8aee36-8c0b-47a7-8248-bdc067b18d6d");
        produk1.put("harga", 100000);
        produk1.put("jumlah", 1);

        cartItemKey1.setItemId(UUID.fromString("1e8aee36-8c0b-47a7-8248-bdc067b18d6d"));
        cartItemKey1.setCartId(keranjang.getId());
        cartItem1.setId(cartItemKey1);

        Map<String, Object> produk2 = new HashMap<>();
        produk2.put("nama", "m4a1");
        produk2.put("id", "c9e5eacd-56b8-4e2c-b528-75481e8b50c8");
        produk2.put("harga", 200000);
        produk2.put("jumlah", 2);

        cartItemKey2.setItemId(UUID.fromString("c9e5eacd-56b8-4e2c-b528-75481e8b50c8"));
        cartItemKey2.setCartId(keranjang.getId());
        cartItem2.setId(cartItemKey2);

        listKeranjang.add(keranjang);

        this.listCartItem = new ArrayList<>();
        listCartItem.add(cartItem1);
        listCartItem.add(cartItem2);
    }

    @Test
    void testGetId() {
        Keranjang keranjang = listKeranjang.get(0);
        try {
            UUID uuid = UUID.fromString(keranjang.getId().toString());
            assertTrue(true);
        } catch (IllegalArgumentException exception) {
            fail("Expected valid UUID, but got invalid UUID");
        }
    }

    @Test
    void testGetKeranjangFromCartItem() throws JsonProcessingException {
        Keranjang keranjang = listKeranjang.get(0);

        UUID idKeranjang1 = listCartItem.get(0).getId().getCartId();
        UUID idKeranjang2 = listCartItem.get(1).getId().getCartId();

        assertEquals(keranjang.getId(), idKeranjang1);
        assertEquals(keranjang.getId(), idKeranjang2);
    }
}
