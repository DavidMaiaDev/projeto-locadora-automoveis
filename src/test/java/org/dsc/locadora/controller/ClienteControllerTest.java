package org.dsc.locadora.controller;

import org.dsc.locadora.dto.ClienteDTO;
import org.dsc.locadora.models.Cliente;
import org.dsc.locadora.services.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @MockBean
    private ModelMapper modelMapper;

    private Cliente cliente;
    private ClienteDTO clienteDTO;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Test Cliente");

        clienteDTO = new ClienteDTO();
        clienteDTO.setId(1L);
        clienteDTO.setNome("Test Cliente");
        clienteDTO.setCpf("12345678910");
        clienteDTO.setEndereco("Rua um do lado da rua dois");

        Mockito.when(modelMapper.map(Mockito.any(Cliente.class), Mockito.eq(ClienteDTO.class))).thenReturn(clienteDTO);
        Mockito.when(modelMapper.map(Mockito.any(ClienteDTO.class), Mockito.eq(Cliente.class))).thenReturn(cliente);
    }

    @Test
    @WithMockUser(username = "testuser")
    void testGetCliente() throws Exception {
        Mockito.when(clienteService.getCliente(1L)).thenReturn(Optional.of(cliente));

        mockMvc.perform(get("/api/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(clienteDTO.getId()))
                .andExpect(jsonPath("$.nome").value(clienteDTO.getNome()));
    }

    @Test
    @WithMockUser(username = "testuser")
    void testListClientes() throws Exception {
        Mockito.when(clienteService.listClientes()).thenReturn(Arrays.asList(cliente));

        mockMvc.perform(get("/api/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(clienteDTO.getId()))
                .andExpect(jsonPath("$[0].nome").value(clienteDTO.getNome()));
    }

    @Test
    @WithMockUser(username = "testuser")
    void testCreateCliente() throws Exception {
        Mockito.when(clienteService.saveCliente(Mockito.any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(post("/api/clientes")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(clienteDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(clienteDTO.getId()))
                .andExpect(jsonPath("$.nome").value(clienteDTO.getNome()));
    }

    @Test
    @WithMockUser(username = "testuser")
    void testUpdateCliente() throws Exception {
        Mockito.when(clienteService.updateCliente(Mockito.eq(1L), Mockito.any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(put("/api/clientes/1")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(clienteDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(clienteDTO.getId()))
                .andExpect(jsonPath("$.nome").value(clienteDTO.getNome()));
    }

    @Test
    @WithMockUser(username = "testuser")
    void testDeleteCliente() throws Exception {
        Mockito.doNothing().when(clienteService).deleteCliente(1L);

        mockMvc.perform(delete("/api/clientes/1")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().isOk());
    }
}
