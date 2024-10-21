package org.dsc.locadora.integration;

import org.dsc.locadora.models.Locacao;
import org.dsc.locadora.models.Pagamento;
import org.dsc.locadora.repository.LocacaoRepository;
import org.dsc.locadora.repository.PagamentoRepository;
import org.junit.jupiter.api.BeforeEach;
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

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PagamentoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LocacaoRepository locacaoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    private Long pagamentoId;
    private Long locacaoId; // Adicionado para armazenar o ID da locação

    @BeforeEach
    public void setup() {
        // Cria uma locação para associar ao pagamento
        Locacao locacao = new Locacao();
        locacao = locacaoRepository.save(locacao);

        // Armazena o ID da locação
        locacaoId = locacao.getId();

        // Cria um pagamento e o associa à locação
        Pagamento pagamento = new Pagamento();
        pagamento.setDataPagamento(LocalDate.now());
        pagamento.setValor(100.00);
        pagamento.setLocacao(locacao);
        pagamento = pagamentoRepository.save(pagamento);

        // Guarda o ID do pagamento para uso nos testes
        pagamentoId = pagamento.getId();
    }

    @Test
    @Order(1)
    public void testCreatePagamento() throws Exception {
        String newPagamento = "{"
                + "\"dataPagamento\":\"2024-01-15\","
                + "\"valor\":200.00,"
                + "\"locacaoId\":" + locacaoId // Utiliza o ID da locação gerado
                + "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/pagamentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newPagamento))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.valor").value(200.00));
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
        mockMvc.perform(MockMvcRequestBuilders.get("/api/pagamentos/" + pagamentoId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.valor").value(100.00)); // Valor inicial do pagamento
    }

    @Test
    @Order(4)
    public void testUpdatePagamento() throws Exception {
        String updatedPagamento = "{"
                + "\"dataPagamento\":\"2024-02-15\","
                + "\"valor\":150.00,"
                + "\"locacaoId\":" + locacaoId // Usa locacaoId em vez de locacao.getId()
                + "}";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/pagamentos/" + pagamentoId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedPagamento))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.valor").value(150.00)); // Valor atualizado
    }

    @Test
    @Order(5)
    public void testDeletePagamento() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/pagamentos/" + pagamentoId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
