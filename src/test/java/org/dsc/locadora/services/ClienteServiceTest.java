package org.dsc.locadora.services;


import org.dsc.locadora.models.Cliente;
import org.dsc.locadora.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("João Silva");
        cliente.setCpf("12345678900");
        cliente.setEmail("joao.silva@example.com");
        cliente.setTelefone("81999999999");
    }

    @Test
    void testGetClienteById() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Optional<Cliente> result = clienteService.getCliente(1L);

        assertTrue(result.isPresent());
        assertEquals(cliente.getId(), result.get().getId());
        assertEquals("João Silva", result.get().getNome());
    }

    @Test
    void testSaveCliente() {
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente savedCliente = clienteService.saveCliente(cliente);

        assertNotNull(savedCliente);
        assertEquals("João Silva", savedCliente.getNome());
    }

    @Test
    void testUpdateCliente() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente updatedCliente = clienteService.updateCliente(1L, cliente);

        assertNotNull(updatedCliente);
        assertEquals("João Silva", updatedCliente.getNome());
    }

    @Test
    void testDeleteCliente() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        clienteService.deleteCliente(1L);
        verify(clienteRepository, times(1)).deleteById(1L);
    }


}
