package org.dsc.locadora.services;



import org.dsc.locadora.models.Veiculo;
import org.dsc.locadora.repository.VeiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class VeiculoServiceTest {

    @Mock
    private VeiculoRepository veiculoRepository;

    @InjectMocks
    private VeiculoService veiculoService;

    private Veiculo veiculo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        veiculo = new Veiculo();
        veiculo.setId(1L);
        veiculo.setModelo("Honda Civic");
        veiculo.setMarca("Honda");
        veiculo.setPlaca("XYZ-1234");
        veiculo.setAno(2022);
        veiculo.setDisponibilidade(true);
        veiculo.setCategoria("Sedan");
        veiculo.setQuilometragem(12000);
    }

    @Test
    void testGetVeiculoById() {
        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));

        Optional<Veiculo> result = veiculoService.getVeiculo(1L);

        assertTrue(result.isPresent());
        assertEquals(veiculo.getId(), result.get().getId());
        assertEquals("Honda Civic", result.get().getModelo());
    }

    @Test
    void testSaveVeiculo() {
        when(veiculoRepository.save(any(Veiculo.class))).thenReturn(veiculo);

        Veiculo savedVeiculo = veiculoService.saveVeiculo(veiculo);

        assertNotNull(savedVeiculo);
        assertEquals("Honda Civic", savedVeiculo.getModelo());
    }

    @Test
    void testUpdateVeiculo() {
        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));
        when(veiculoRepository.save(any(Veiculo.class))).thenReturn(veiculo);

        Veiculo updatedVeiculo = veiculoService.updateVeiculo(1L, veiculo);

        assertNotNull(updatedVeiculo);
        assertEquals("Honda Civic", updatedVeiculo.getModelo());
    }

    @Test
    void testDeleteVeiculo() {
        veiculoService.deleteVeiculo(1L);

        Mockito.verify(veiculoRepository, Mockito.times(1)).deleteById(1L);
    }
}
