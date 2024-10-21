package org.dsc.locadora.services;

import org.dsc.locadora.models.Locacao;
import org.dsc.locadora.repository.LocacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LocacaoServiceTest {

    @Mock
    private LocacaoRepository locacaoRepository;

    @InjectMocks
    private LocacaoService locacaoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListLocacoes() {
        Locacao locacao1 = new Locacao();
        Locacao locacao2 = new Locacao();
        when(locacaoRepository.findAll()).thenReturn(Arrays.asList(locacao1, locacao2));

        List<Locacao> locacoes = locacaoService.listLocacoes();

        assertNotNull(locacoes);
        assertEquals(2, locacoes.size());
        verify(locacaoRepository, times(1)).findAll();
    }

    @Test
    void testGetLocacao() {
        Long locacaoId = 1L;
        Locacao locacao = new Locacao();
        when(locacaoRepository.findById(locacaoId)).thenReturn(Optional.of(locacao));

        Optional<Locacao> result = locacaoService.getLocacao(locacaoId);

        assertTrue(result.isPresent());
        assertEquals(locacao, result.get());
        verify(locacaoRepository, times(1)).findById(locacaoId);
    }

    @Test
    void testSaveLocacao() {
        Locacao locacao = new Locacao();
        when(locacaoRepository.save(locacao)).thenReturn(locacao);

        Locacao savedLocacao = locacaoService.saveLocacao(locacao);

        assertNotNull(savedLocacao);
        assertEquals(locacao, savedLocacao);
        verify(locacaoRepository, times(1)).save(locacao);
    }

    @Test
    void testUpdateLocacao() {
        Long locacaoId = 1L;
        Locacao locacaoExistente = new Locacao();
        Locacao locacaoAtualizada = new Locacao();
        when(locacaoRepository.findById(locacaoId)).thenReturn(Optional.of(locacaoExistente));
        when(locacaoRepository.save(locacaoExistente)).thenReturn(locacaoExistente);

        Locacao updatedLocacao = locacaoService.updateLocacao(locacaoId, locacaoAtualizada);

        assertNotNull(updatedLocacao);
        verify(locacaoRepository, times(1)).findById(locacaoId);
        verify(locacaoRepository, times(1)).save(locacaoExistente);
    }

    @Test
    void testUpdateLocacao_NotFound() {
        Long locacaoId = 1L;
        Locacao locacaoAtualizada = new Locacao();
        when(locacaoRepository.findById(locacaoId)).thenReturn(Optional.empty());

        Locacao updatedLocacao = locacaoService.updateLocacao(locacaoId, locacaoAtualizada);

        assertNull(updatedLocacao);
        verify(locacaoRepository, times(1)).findById(locacaoId);
        verify(locacaoRepository, never()).save(any(Locacao.class));
    }

    @Test
    void testDeleteLocacao() {
        Long locacaoId = 1L;

        locacaoService.deleteLocacao(locacaoId);

        verify(locacaoRepository, times(1)).deleteById(locacaoId);
    }
}
