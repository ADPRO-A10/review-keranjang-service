package id.ac.ui.cs.advprog.reviewkeranjangservice.service;

import id.ac.ui.cs.advprog.reviewkeranjangservice.model.CartItem;
import id.ac.ui.cs.advprog.reviewkeranjangservice.model.CartItemKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StockHandlerTest {

    @Spy
    @InjectMocks
    StockHandler stockHandler;
    @Mock
    Handler handler;
    @Mock
    RestTemplate restTemplate;
    List<CartItem> cartItems = new ArrayList<>();

    @BeforeEach
    void setupUp() {
        stockHandler = new StockHandler(restTemplate);
        stockHandler.setNext(handler);

        CartItemKey cartItemKey1 = new CartItemKey();
        cartItemKey1.setItemId(UUID.fromString("0a1b2c3d-4e5f-6789-0abc-def123456789"));
        CartItem cartItem1 = new CartItem();
        cartItem1.setId(cartItemKey1);
        cartItem1.setJumlah(10);

        CartItemKey cartItemKey2 = new CartItemKey();
        cartItemKey2.setItemId(UUID.fromString("4f12c7e3-89a6-42d7-b0f7-2d9c9e5f89fa"));
        CartItem cartItem2 = new CartItem();
        cartItem2.setId(cartItemKey2);
        cartItem2.setJumlah(20);

        when(restTemplate.getForObject(endsWith("9"), eq(String.class)))
                .thenReturn("{\"stokTersedia\": " + 10 + "}");
        lenient().when(restTemplate.getForObject(endsWith("a"), eq(String.class)))
                .thenReturn("{\"stokTersedia\": " + 20 + "}");

        cartItems.add(cartItem1);
        cartItems.add(cartItem2);
    }

    @Test
    void testStockTiapProdukDiKeranjangTersedia() {
        stockHandler.handleRequest(cartItems);
        assertEquals("PASSED", stockHandler.getStatus());
    }

    @Test
    void testStockAdaProdukYangStockTidakTersedia() {
        cartItems.getFirst().setJumlah(15);

        assertThrows(IllegalArgumentException.class, () -> stockHandler.handleRequest(cartItems));
        assertEquals("FAILED", stockHandler.getStatus());
    }

    @Test
    void testNextHandleInvokedWhenPassed() {

        stockHandler.handleRequest(cartItems);
        assertEquals("PASSED", stockHandler.getStatus());
        verify(handler).handleRequest(cartItems);

    }
}
