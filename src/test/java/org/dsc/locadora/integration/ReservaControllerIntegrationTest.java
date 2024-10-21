package org.dsc.locadora.integration;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReservaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void testCreateReserva() throws Exception {
        String newReserva = "{"
                + "\"dataInicio\":\"2024-01-01\","
                + "\"dataFim\":\"2024-01-05\","
                + "\"valorTotal\":200.00,"
                + "\"clienteId\":1,"
                + "\"veiculoId\":1"
                + "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/reservas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newReserva))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.valorTotal").value(200.00));
    }

    @Test
    @Order(2)
    public void testListReservas() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/reservas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(3)
    public void testGetReserva() throws Exception {
        Long reservaId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get("/api/reservas/" + reservaId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.valorTotal").value(200.00));
    }
    @Test
    @Order(4)
    public void testUpdateReserva() throws Exception {
        Long reservaId = 1L;
        String updatedReserva = "{"
                + "\"dataInicio\":\"2024-02-01\","
                + "\"dataFim\":\"2024-02-05\","
                + "\"valorTotal\":300.00,"
                + "\"clienteId\":1,"
                + "\"veiculoId\":1"
                + "}";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/reservas/" + reservaId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedReserva))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.valorTotal").value(300.00));
    }

    @Test
    @Order(5)
    public void testDeleteReserva() throws Exception {
        Long reservaId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/reservas/" + reservaId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
