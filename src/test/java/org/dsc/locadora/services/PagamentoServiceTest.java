package org.dsc.locadora.services;

import org.dsc.locadora.models.Pagamento;
import org.dsc.locadora.repository.PagamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PagamentoServiceTest {

    @Mock
    private PagamentoRepository pagamentoRepository;

    @InjectMocks
    private PagamentoService pagamentoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListPagamentos() {
        Pagamento pagamento1 = new Pagamento();
        Pagamento pagamento2 = new Pagamento();
        when(pagamentoRepository.findAll()).thenReturn(Arrays.asList(pagamento1, pagamento2));

        List<Pagamento> pagamentos = pagamentoService.listPagamentos();

        assertNotNull(pagamentos);
        assertEquals(2, pagamentos.size());
        verify(pagamentoRepository, times(1)).findAll();
    }

    @Test
    void testGetPagamento() {
        Long pagamentoId = 1L;
        Pagamento pagamento = new Pagamento();
        when(pagamentoRepository.findById(pagamentoId)).thenReturn(Optional.of(pagamento));

        Optional<Pagamento> result = pagamentoService.getPagamento(pagamentoId);

        assertTrue(result.isPresent());
        assertEquals(pagamento, result.get());
        verify(pagamentoRepository, times(1)).findById(pagamentoId);
    }

    @Test
    void testSavePagamento() {
        Pagamento pagamento = new Pagamento();
        when(pagamentoRepository.save(pagamento)).thenReturn(pagamento);

        Pagamento savedPagamento = pagamentoService.savePagamento(pagamento);

        assertNotNull(savedPagamento);
        assertEquals(pagamento, savedPagamento);
        verify(pagamentoRepository, times(1)).save(pagamento);
    }

    @Test
    void testUpdatePagamento() {
        Long pagamentoId = 1L;
        Pagamento pagamentoExistente = new Pagamento();
        Pagamento pagamentoAtualizado = new Pagamento();
        pagamentoAtualizado.setValor(200.00);
        pagamentoAtualizado.setDataPagamento(LocalDate.now());

        when(pagamentoRepository.findById(pagamentoId)).thenReturn(Optional.of(pagamentoExistente));
        when(pagamentoRepository.save(pagamentoExistente)).thenReturn(pagamentoExistente);

        Pagamento updatedPagamento = pagamentoService.updatePagamento(pagamentoId, pagamentoAtualizado);

        assertNotNull(updatedPagamento);
        verify(pagamentoRepository, times(1)).findById(pagamentoId);
        verify(pagamentoRepository, times(1)).save(pagamentoExistente);
    }

    @Test
    void testUpdatePagamento_NotFound() {
        Long pagamentoId = 1L;
        Pagamento pagamentoAtualizado = new Pagamento();
        when(pagamentoRepository.findById(pagamentoId)).thenReturn(Optional.empty());

        Pagamento updatedPagamento = pagamentoService.updatePagamento(pagamentoId, pagamentoAtualizado);

        assertNull(updatedPagamento);
        verify(pagamentoRepository, times(1)).findById(pagamentoId);
        verify(pagamentoRepository, never()).save(any(Pagamento.class));
    }

    @Test
    void testDeletePagamento() {
        Long pagamentoId = 1L;

        pagamentoService.deletePagamento(pagamentoId);

        verify(pagamentoRepository, times(1)).deleteById(pagamentoId);
    }
}
