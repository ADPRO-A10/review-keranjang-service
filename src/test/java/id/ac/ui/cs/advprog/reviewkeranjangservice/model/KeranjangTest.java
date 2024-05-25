package id.ac.ui.cs.advprog.reviewkeranjangservice.model;

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

        cartItemKey1.setItemId(UUID.fromString("1e8aee36-8c0b-47a7-8248-bdc067b18d6d"));
        cartItemKey1.setCartId(keranjang.getId());
        cartItem1.setId(cartItemKey1);

        cartItemKey2.setItemId(UUID.fromString("c9e5eacd-56b8-4e2c-b528-75481e8b50c8"));
        cartItemKey2.setCartId(keranjang.getId());
        cartItem2.setId(cartItemKey2);

        listKeranjang.add(keranjang);

        this.listCartItem = new ArrayList<>();
        listCartItem.add(cartItem1);
        listCartItem.add(cartItem2);
    }

    @Test
    @SuppressWarnings("unused")
    void testGetId() {
        Keranjang keranjang = listKeranjang.getFirst();
        try {
            UUID uuid = UUID.fromString(keranjang.getId().toString());
            assertTrue(true);
        } catch (IllegalArgumentException exception) {
            fail("Expected valid UUID, but got invalid UUID");
        }
    }

    @Test
    void testGetKeranjangFromCartItem() {
        Keranjang keranjang = listKeranjang.getFirst();

        UUID idKeranjang1 = listCartItem.get(0).getId().getCartId();
        UUID idKeranjang2 = listCartItem.get(1).getId().getCartId();

        assertEquals(keranjang.getId(), idKeranjang1);
        assertEquals(keranjang.getId(), idKeranjang2);
    }
}
