package id.ac.ui.cs.advprog.reviewkeranjangservice.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Keranjang;
import id.ac.ui.cs.advprog.reviewkeranjangservice.service.KeranjangService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(KeranjangController.class)
public class KeranjangControllerTest {
    ObjectMapper objectMapper = new ObjectMapper();
    TypeReference<Map<String, Object>> typeRef = new TypeReference<>() {};
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private KeranjangService keranjangService;

    @Test
    void testGetAllKeranjang() throws Exception {
        mockMvc.perform(get("/keranjang"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateKeranjang() throws Exception {
        Keranjang keranjang = new Keranjang();

        doReturn(keranjang).when(keranjangService).createKeranjang(any(Keranjang.class));

        MvcResult mvcResult = mockMvc.perform(post("/api/keranjang"))
                .andExpect(status().isOk())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        Map<String, Object> responseMap = objectMapper.readValue(response, typeRef);

        assertEquals("Keranjang berhasil dibuat", responseMap.get("message"));
    }
}
