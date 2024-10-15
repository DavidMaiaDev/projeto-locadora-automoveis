package org.dsc.locadora.controller;

import jakarta.validation.Valid;
import org.dsc.locadora.dto.ClienteDTO;
import org.dsc.locadora.models.Cliente;
import org.dsc.locadora.services.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api")
public class ClienteController {

    private final ClienteService clienteService;
    private final ModelMapper modelMapper;

    public ClienteController(ClienteService clienteService, ModelMapper modelMapper) {
        this.clienteService = clienteService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/clientes/{clienteId}")
    public ClienteDTO getCliente(@PathVariable Long clienteId) {
        Cliente cliente = clienteService.getCliente(clienteId).orElse(null);
        return convertToDTO(cliente);
    }

    @GetMapping("/clientes")
    public List<ClienteDTO> listClientes() {
        List<Cliente> clientes = clienteService.listClientes();
        return clientes.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @PostMapping("/clientes")
    public ClienteDTO createCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = convertToEntity(clienteDTO);
        Cliente clienteCreated = clienteService.saveCliente(cliente);
        return convertToDTO(clienteCreated);
    }

    @PutMapping("/clientes/{clienteId}")
    public ClienteDTO updateCliente(@PathVariable Long clienteId, @Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = convertToEntity(clienteDTO);
        Cliente clienteUpdated = clienteService.updateCliente(clienteId, cliente);
        return convertToDTO(clienteUpdated);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/clientes/{clienteId}")
    public void deleteCliente(@PathVariable Long clienteId) {
        clienteService.deleteCliente(clienteId);
    }

    private ClienteDTO convertToDTO(Cliente cliente) {
        return modelMapper.map(cliente, ClienteDTO.class);
    }

    private Cliente convertToEntity(ClienteDTO clienteDTO) {
        return modelMapper.map(clienteDTO, Cliente.class);
    }
}
