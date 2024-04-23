package id.ac.ui.cs.advprog.reviewkeranjangservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class BalanceHandlerTest {

    BalanceHandler balanceHandler;

    @BeforeEach
    void setUp() {
        balanceHandler = new BalanceHandler();
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
    }
}
