package org.dsc.locadora.services;

import org.dsc.locadora.exception.ErroMessage;
import org.dsc.locadora.exception.EntityNotFoundException;
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

    public  Optional<Cliente> getCliente(Long id) {
        return this.getOptionalClienteOrThrows(id);
    }

    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente updateCliente(Long id, Cliente clientUpdate) {
        Optional<Cliente> clientOpt = this.getOptionalClienteOrThrows(id);

        if (clientOpt.isPresent()) {
            Cliente clientSaved = clientOpt.get();
            clientSaved.setNome(clientUpdate.getNome());
            clientSaved.setCpf(clientUpdate.getCpf());
            clientSaved.setEmail(clientUpdate.getEmail());
            clientSaved.setEndereco(clientUpdate.getEndereco());
            return clienteRepository.save(clientSaved);
        }
        return null;
    }

    public void deleteCliente(Long id) {
        this.getOptionalClienteOrThrows(id);
        clienteRepository.deleteById(id);
    }

    private Optional<Cliente> getOptionalClienteOrThrows(Long id) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);

        if (!clienteOpt.isPresent()) {
            throw new EntityNotFoundException(ErroMessage.CLIENTE_NOT_FOUND);
        }
        return clienteOpt;
    }
}
