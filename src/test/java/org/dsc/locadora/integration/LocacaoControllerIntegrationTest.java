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
public class LocacaoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void testCreateCliente() throws Exception {
        String newCliente = "{" +
                "\"nome\":\"João Silva\"," +
                "\"cpf\":\"12345678900\"," +
                "\"email\":\"joao@gmail.com\"," +
                "\"endereco\":\"Rua ABC, 123\"," +
                "\"telefone\":\"(11) 98765-4321\"" +
                "}";

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
    public void testCreateVeiculo() throws Exception {
        String newVeiculo = "{" +
                "\"marca\":\"Ford\"," +
                "\"modelo\":\"Fiesta\"," +
                "\"ano\":2020," +
                "\"categoria\":\"Hatch\"," +
                "\"disponibilidade\":true," +
                "\"placa\":\"XYZ-1234\"," +
                "\"quilometragem\":1000" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/veiculos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newVeiculo))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.marca").value("Ford"))
                .andExpect(jsonPath("$.modelo").value("Fiesta"));
    }

    @Test
    @Order(3)
    public void testCreateLocacao() throws Exception {
        String newLocacao = "{" +
                "\"clienteId\":1," +
                "\"veiculoId\":1," +
                "\"dataLocacao\":\"2024-10-20\"," +
                "\"dataDevolucaoPrevista\":\"2024-10-27\"" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/locacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newLocacao))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.clienteId").value(1))
                .andExpect(jsonPath("$.veiculoId").value(1))
                .andExpect(jsonPath("$.status").value("EM_ANDAMENTO")); // Verifica se o status está definido corretamente
    }

    @Test
    @Order(4)
    public void testUpdateLocacao() throws Exception {
        Long locacaoId = 1L;
        String updatedLocacao = "{" +
                "\"clienteId\": 1," +
                "\"veiculoId\": 1," +
                "\"dataLocacao\": \"2024-10-20\"," +
                "\"dataDevolucaoPrevista\":\"2024-10-30\"" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/locacoes/" + locacaoId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedLocacao))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("EM_ANDAMENTO")); // Verifica se o status ainda está correto
    }

    @Test
    @Order(5)
    public void testDeleteLocacao() throws Exception {
        Long locacaoId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/locacoes/" + locacaoId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Locação deletada com sucesso")); // Verifica se a mensagem é a esperada
    }
}
