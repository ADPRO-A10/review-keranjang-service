package id.ac.ui.cs.advprog.reviewkeranjangservice.controller;

import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Keranjang;
import id.ac.ui.cs.advprog.reviewkeranjangservice.service.KeranjangService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(KeranjangController.class)
public class KeranjangControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private KeranjangService keranjangService;

    @Test
    void testCreateKeranjang() throws Exception {
        Keranjang keranjang = new Keranjang();

        doReturn(keranjang).when(keranjangService).createKeranjang(any(Keranjang.class));

        mockMvc.perform(post("/api/keranjang"))
                .andExpect(status().isOk())
                .andReturn();
    }
}
