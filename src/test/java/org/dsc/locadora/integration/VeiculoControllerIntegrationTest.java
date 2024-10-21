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
public class VeiculoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void testCreateVeiculo() throws Exception {
        String newVeiculo = "{"
                + "\"modelo\":\"Ford Fiesta\","
                + "\"placa\":\"ABC-1234\","
                + "\"ano\":2020,"
                + "\"quilometragem\":45000,"
                + "\"categoria\":\"Hatch\","
                + "\"disponibilidade\":true,"
                + "\"marca\":\"Ford\""
                + "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/veiculos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newVeiculo))
                .andExpect(status().isOk())  // Expectativa de retorno 200 OK
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"modelo\":\"Ford Fiesta\",\"placa\":\"ABC-1234\"}"));
    }


    @Test
    @Order(2)
    public void testListVeiculos() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/veiculos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  // Verifica se retorna 200 OK
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


    @Test
    @Order(3)
    public void testGetVeiculo() throws Exception {
        Long veiculoId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get("/api/veiculos/" + veiculoId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  // Verifica se retorna 200 OK
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.modelo").value("Ford Fiesta"))
                .andExpect(jsonPath("$.placa").value("ABC-1234"));
    }

    // Teste para atualizar um veículo existente
    @Test
    @Order(4)
    public void testUpdateVeiculo() throws Exception {
        Long veiculoId = 1L;
        String updatedVeiculo = "{"
                + "\"modelo\":\"Ford Focus\","
                + "\"placa\":\"DEF-5678\","
                + "\"ano\":2021,"
                + "\"quilometragem\":30000,"
                + "\"categoria\":\"Sedan\","
                + "\"disponibilidade\":true,"
                + "\"marca\":\"Ford\""
                + "}";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/veiculos/" + veiculoId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedVeiculo))
                .andExpect(status().isOk())  // Expectativa de retorno 200 OK
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.modelo").value("Ford Focus"))
                .andExpect(jsonPath("$.placa").value("DEF-5678"));
    }

    // Teste para deletar um veículo por ID
    @Test
    @Order(5)
    public void testDeleteVeiculo() throws Exception {
        Long veiculoId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/veiculos/" + veiculoId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
