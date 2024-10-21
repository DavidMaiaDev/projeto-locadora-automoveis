package org.dsc.locadora.integration;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
public class ClienteControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void testCreateCliente() throws Exception {
        String newCliente = "{"
                + "\"nome\":\"João Silva\","
                + "\"cpf\":\"12345678900\","
                + "\"email\":\"joao@gmail.com\","
                + "\"endereco\":\"Rua ABC, 123\","
                + "\"telefone\":\"(11) 98765-4321\""
                + "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newCliente))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value("João Silva"))
                .andExpect(jsonPath("$.cpf").value("12345678900"));
    }

    @Test
    @Order(2)
    public void testListClientes() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(3)
    public void testGetCliente() throws Exception {
        Long clienteId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get("/api/clientes/" + clienteId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value("João Silva"))
                .andExpect(jsonPath("$.cpf").value("12345678900"));
    }

    @Test
    @Order(4)
    public void testUpdateCliente() throws Exception {
        Long clienteId = 1L;
        String updatedCliente = "{"
                + "\"nome\":\"Maria Souza\","
                + "\"cpf\":\"98765432100\"," //
                + "\"email\":\"maria@gmail.com\","
                + "\"endereco\":\"Rua DEF, 456\","
                + "\"telefone\":\"(21) 98765-4321\""
                + "}";


        mockMvc.perform(MockMvcRequestBuilders.put("/api/clientes/" + clienteId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedCliente))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value("Maria Souza"))
                .andExpect(jsonPath("$.cpf").value("98765432100"));
    }

    @Test
    @Order(5)
    public void testDeleteCliente() throws Exception {
        Long clienteId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/clientes/" + clienteId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
