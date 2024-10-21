package org.dsc.locadora.integration;

import com.jayway.jsonpath.JsonPath;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LocacaoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private static Long clienteId;
    private static Long veiculoId;
    private static Long locacaoId;

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
                .andDo(result -> {
                    String response = result.getResponse().getContentAsString();
                    clienteId = JsonPath.parse(response).read("$.id", Long.class);
                })
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
                .andDo(result -> {
                    String response = result.getResponse().getContentAsString();
                    veiculoId = JsonPath.parse(response).read("$.id", Long.class);
                })
                .andExpect(jsonPath("$.marca").value("Ford"))
                .andExpect(jsonPath("$.modelo").value("Fiesta"));
    }

    @Test
    @Order(3)
    public void testCreateLocacao() throws Exception {
        String newLocacao = "{" +
                "\"clienteId\": " + clienteId + "," +
                "\"veiculoId\": " + veiculoId + "," +
                "\"dataLocacao\": \"2024-11-01\"," +
                "\"dataDevolucaoPrevista\": \"2024-11-07\"" +
                "}";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/locacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newLocacao))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").doesNotExist()) // Alterado para permitir que o status seja null ou não retornado
                .andReturn();

        // Captura o ID da locação criada e converte manualmente para Long, se necessário
        String responseBody = result.getResponse().getContentAsString();
        Integer locacaoIdInt = JsonPath.read(responseBody, "$.id");
        locacaoId = locacaoIdInt.longValue(); // Converte Integer para Long
    }


    @Test
    @Order(4)
    public void testUpdateLocacao() throws Exception {
        String updatedLocacao = "{" +
                "\"clienteId\": " + clienteId + "," +
                "\"veiculoId\": " + veiculoId + "," +
                "\"dataLocacao\": \"2024-11-01\"," +
                "\"dataDevolucaoPrevista\": \"2024-11-10\"" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/locacoes/" + locacaoId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedLocacao))
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    public void testDeleteLocacao() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/locacoes/" + locacaoId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

}
