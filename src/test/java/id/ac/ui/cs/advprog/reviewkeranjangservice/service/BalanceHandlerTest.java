package id.ac.ui.cs.advprog.reviewkeranjangservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BalanceHandlerTest {

    BalanceHandler balanceHandler;
    Handler handler;

    @BeforeEach
    void setUp() {
        balanceHandler = new BalanceHandler();
        handler = mock(Handler.class);
    }

    @Test
    void testHandleSaldoMencukupiTotalBiaya() {
        Map<String, Object> person = new HashMap<>();
        person.put("saldo", 20000);
        person.put("totalBiaya", 20000);

        balanceHandler.handleRequest(person);
        assertEquals("PASSED", balanceHandler.getStatus());
    }

    @Test
    void testHandleSaldoBiayaTidakCukup() {
        Map<String, Object> person = new HashMap<>();
        person.put("saldo", 20000);
        person.put("totalBiaya", 30000);

        assertThrows(IllegalArgumentException.class, () -> balanceHandler.handleRequest(person));
        assertEquals("FAILED", balanceHandler.getStatus());
    }

    @Test
    void testNextHandleInvokedWhenPassed() {
        Map<String, Object> person = new HashMap<>();
        person.put("saldo", 30000);
        person.put("totalBiaya", 20000);

        balanceHandler.setNext(handler);
        balanceHandler.handleRequest(person);
        assertEquals("PASSED", balanceHandler.getStatus());
        verify(handler).handleRequest(person);
    }
}
