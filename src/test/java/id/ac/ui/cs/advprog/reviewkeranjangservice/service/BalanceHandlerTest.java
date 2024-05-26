package id.ac.ui.cs.advprog.reviewkeranjangservice.service;

import id.ac.ui.cs.advprog.reviewkeranjangservice.model.CartItem;
import id.ac.ui.cs.advprog.reviewkeranjangservice.model.CartItemKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.endsWith;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class BalanceHandlerTest {

    @Spy
    @InjectMocks
    BalanceHandler balanceHandler;
    Handler handler;
    @Mock
    RestTemplate restTemplate;
    List<CartItem> cartItems = new ArrayList<>();

    @BeforeEach
    void setUp() {
        balanceHandler = new BalanceHandler(restTemplate);
        handler = mock(Handler.class);

        CartItemKey cartItemKey1 = new CartItemKey();
        cartItemKey1.setCartId(UUID.fromString("0a1b2c3d-4e5f-6789-0abc-def123456789"));
        CartItem cartItem1 = new CartItem();
        cartItem1.setId(cartItemKey1);
        cartItem1.setJumlah(10);
        cartItem1.setHarga(10000);

        cartItems.add(cartItem1);
    }

    @Test
    void testHandleSaldoMencukupiTotalBiaya() {
        when(restTemplate.getForObject(endsWith("9"), eq(String.class)))
                .thenReturn("{\"saldo\": " + 200000 + "}");

        balanceHandler.handleRequest(cartItems);
        assertEquals("PASSED", balanceHandler.getStatus());
    }

    @Test
    void testHandleSaldoBiayaTidakCukup() {
        when(restTemplate.getForObject(endsWith("9"), eq(String.class)))
                .thenReturn("{\"saldo\": " + 50000 + "}");

        assertThrows(IllegalArgumentException.class, () -> balanceHandler.handleRequest(cartItems));
        assertEquals("FAILED", balanceHandler.getStatus());
    }

    @Test
    void testNextHandleInvokedWhenPassed() {
        when(restTemplate.getForObject(endsWith("9"), eq(String.class)))
                .thenReturn("{\"saldo\": " + 200000 + "}");

        balanceHandler.setNext(handler);
        balanceHandler.handleRequest(cartItems);
        assertEquals("PASSED", balanceHandler.getStatus());
        verify(handler).handleRequest(cartItems);
    }
}
