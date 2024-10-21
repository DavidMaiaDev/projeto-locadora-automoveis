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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReservaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private static Long reservaId;
    private static Long clienteId;
    private static Long veiculoId;


    @Test
    @Order(0)
    public void setupTestData() throws Exception {
        // Criar cliente com todos os campos obrigatórios
        String newCliente = "{ \"nome\": \"Cliente Teste\", \"cpf\": \"12345678900\", \"email\": \"cliente@teste.com\", \"endereco\": \"Rua Teste, 123\", \"telefone\": \"(11) 98765-4321\" }";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newCliente))
                .andExpect(status().isOk())
                .andDo(result -> {
                    String response = result.getResponse().getContentAsString();
                    clienteId = JsonPath.parse(response).read("$.id", Long.class);
                });

        // Criar veiculo
        String newVeiculo = "{ \"marca\": \"Carro Teste\", \"modelo\": \"Modelo Teste\", \"ano\": 2020, \"categoria\": \"Sedan\", \"disponibilidade\": true, \"placa\": \"ABC-1234\", \"quilometragem\": 10000.0 }";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/veiculos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newVeiculo))
                .andExpect(status().isOk())
                .andDo(result -> {
                    String response = result.getResponse().getContentAsString();
                    veiculoId = JsonPath.parse(response).read("$.id", Long.class);
                });
    }


    @Test
    @Order(1)
    public void testCreateReserva() throws Exception {
        LocalDate dataReserva = LocalDate.now().plusDays(1);
        LocalDate dataInicio = LocalDate.now().plusDays(10);
        LocalDate dataFim = LocalDate.now().plusDays(15);

        String newReserva = "{"
                + "\"clienteId\":" + clienteId + ","
                + "\"veiculoId\":" + veiculoId + ","
                + "\"dataReserva\":\"" + dataReserva + "\","
                + "\"dataInicio\":\"" + dataInicio + "\","
                + "\"dataFim\":\"" + dataFim + "\","
                + "\"status\":\"Ativa\""
                + "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/reservas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newReserva))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(result -> {
                    String response = result.getResponse().getContentAsString();
                    reservaId = JsonPath.parse(response).read("$.id", Long.class);
                });
    }

    // Teste de busca da reserva
    @Test
    @Order(2)
    public void testGetReserva() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/reservas/" + reservaId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("Ativa"));
    }


    @Test
    @Order(3)
    public void testUpdateReserva() throws Exception {
        LocalDate updatedDataInicio = LocalDate.now().plusDays(12);
        LocalDate updatedDataFim = LocalDate.now().plusDays(20);

        String updatedReserva = "{"
                + "\"clienteId\":" + clienteId + ","
                + "\"veiculoId\":" + veiculoId + ","
                + "\"dataReserva\":\"" + LocalDate.now().plusDays(1) + "\","
                + "\"dataInicio\":\"" + updatedDataInicio + "\","
                + "\"dataFim\":\"" + updatedDataFim + "\","
                + "\"status\":\"Atualizada\""
                + "}";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/reservas/" + reservaId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedReserva))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("Atualizada"));
    }


    @Test
    @Order(4)
    public void testDeleteReserva() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/reservas/" + reservaId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
