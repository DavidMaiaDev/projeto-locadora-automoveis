package org.dsc.locadora.services;

import org.dsc.locadora.models.Cliente;
import org.dsc.locadora.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getCliente(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente updateCliente(Long id, Cliente clienteAtualizado) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);
        if (clienteOpt.isPresent()) {
            Cliente clienteExistente = clienteOpt.get();
            clienteExistente.setNome(clienteAtualizado.getNome());
            clienteExistente.setCpf(clienteAtualizado.getCpf());
            clienteExistente.setEmail(clienteAtualizado.getEmail());
            clienteExistente.setEndereco(clienteAtualizado.getEndereco());
            return clienteRepository.save(clienteExistente);
        }
        return null;
    }

    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
