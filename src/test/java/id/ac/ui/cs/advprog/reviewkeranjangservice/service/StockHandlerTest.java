package id.ac.ui.cs.advprog.reviewkeranjangservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class StockHandlerTest {

    StockHandler stockHandler;
    Handler handler;

    @BeforeEach
    void setupUp() {
        stockHandler = mock(StockHandler.class);
        handler = mock(Handler.class);

        stockHandler.setNext(handler);

        Map<String, Object> produkToko1 = new HashMap<>();
        produkToko1.put("id", 1);
        produkToko1.put("stok", 10);
        Map<String, Object> produkToko2 = new HashMap<>();
        produkToko2.put("id", 2);
        produkToko2.put("stok", 20);

        when(stockHandler.getStock("1")).thenReturn((int) produkToko1.get("stok"));
        when(stockHandler.getStock("2")).thenReturn((int) produkToko2.get("stok"));
    }

    @Test
    void testStockTiapProdukDiKeranjangTersedia() {
        Map<String, Object> keranjang = new HashMap<>();
        keranjang.put("1", 8);
        keranjang.put("2", 15);

        stockHandler.handleRequest(keranjang);
        assertEquals("PASSED", stockHandler.getStatus());
    }

    @Test
    void testStockAdaProdukYangStockTidakTersedia() {
        Map<String, Object> keranjang = new HashMap<>();
        keranjang.put("1", 8);
        keranjang.put("2", 30);

        assertThrows(IllegalArgumentException.class, () -> stockHandler.handleRequest(keranjang));
        assertEquals("FAILED", stockHandler.getStatus());
    }

    @Test
    void testNextHandleInvokedWhenPassed() {
        Map<String, Object> keranjang = new HashMap<>();
        keranjang.put("1", 8);
        keranjang.put("2", 15);

        stockHandler.handleRequest(keranjang);
        assertEquals("PASSED", stockHandler.getStatus());
        verify(handler).handleRequest(keranjang);

    }
}
