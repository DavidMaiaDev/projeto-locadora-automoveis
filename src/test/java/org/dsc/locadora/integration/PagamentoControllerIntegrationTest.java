package org.dsc.locadora.integration;

import org.dsc.locadora.models.Locacao;
import org.dsc.locadora.repository.LocacaoRepository;
import org.junit.jupiter.api.BeforeEach;
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
public class PagamentoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LocacaoRepository locacaoRepository;

    @BeforeEach
    public void setup() {

        Locacao locacao = new Locacao();
        locacao.setId(1L);
        locacaoRepository.save(locacao);
    }

    @Test
    @Order(1)
    public void testCreatePagamento() throws Exception {
        String newPagamento = "{"
                + "\"dataPagamento\":\"2024-01-15\","
                + "\"valor\":100.00,"
                + "\"locacaoId\":1"
                + "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/pagamentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newPagamento))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.valor").value(100.00));
    }

    @Test
    @Order(2)
    public void testListPagamentos() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/pagamentos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(3)
    public void testGetPagamento() throws Exception {
        Long pagamentoId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get("/api/pagamentos/" + pagamentoId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.valor").value(100.00));
    }

    @Test
    @Order(4)
    public void testUpdatePagamento() throws Exception {
        Long pagamentoId = 1L;
        String updatedPagamento = "{"
                + "\"dataPagamento\":\"2024-02-15\","
                + "\"valor\":150.00,"
                + "\"locacaoId\":1"
                + "}";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/pagamentos/" + pagamentoId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedPagamento))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.valor").value(150.00));
    }

    @Test
    @Order(5)
    public void testDeletePagamento() throws Exception {
        Long pagamentoId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/pagamentos/" + pagamentoId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
