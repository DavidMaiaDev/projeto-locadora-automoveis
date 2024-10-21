package org.dsc.locadora.services;


import org.dsc.locadora.models.Reserva;
import org.dsc.locadora.repository.ReservaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

class ReservaServiceTest {

    @Mock
    private ReservaRepository reservaRepository;

    @InjectMocks
    private ReservaService reservaService;

    private Reserva reserva;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        reserva = new Reserva();
        reserva.setId(1L);
        reserva.setValorTotal(1000.0);
        reserva.setStatus("CONFIRMADA");
    }

    @Test
    void testGetReservaById() {
        when(reservaRepository.findById(1L)).thenReturn(Optional.of(reserva));

        Optional<Reserva> result = reservaService.getReserva(1L);

        assertTrue(result.isPresent());
        assertEquals(reserva.getId(), result.get().getId());
        assertEquals(1000.0, result.get().getValorTotal());
    }

    @Test
    void testSaveReserva() {
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reserva);

        Reserva savedReserva = reservaService.saveReserva(reserva);

        assertNotNull(savedReserva);
        assertEquals(1000.0, savedReserva.getValorTotal());
    }

    @Test
    void testUpdateReserva() {
        when(reservaRepository.findById(1L)).thenReturn(Optional.of(reserva));
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reserva);

        Reserva updatedReserva = reservaService.updateReserva(1L, reserva);

        assertNotNull(updatedReserva);
        assertEquals("CONFIRMADA", updatedReserva.getStatus());
    }

    @Test
    void testDeleteReserva() {
        reservaService.deleteReserva(1L);

        verify(reservaRepository, times(1)).deleteById(1L);
    }
}
