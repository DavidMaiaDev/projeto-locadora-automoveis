package org.dsc.locadora.services;

import org.dsc.locadora.models.Veiculo;
import org.dsc.locadora.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;


    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public List<Veiculo> listVeiculos() {
        return veiculoRepository.findAll();
    }

    public Optional<Veiculo> getVeiculo(Long id) {
        return veiculoRepository.findById(id);
    }

    public Veiculo saveVeiculo(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public Veiculo updateVeiculo(Long id, Veiculo veiculoAtualizado) {
        Optional<Veiculo> veiculoOpt = veiculoRepository.findById(id);
        if (veiculoOpt.isPresent()) {
            Veiculo veiculoExistente = veiculoOpt.get();
            veiculoExistente.setModelo(veiculoAtualizado.getModelo());
            veiculoExistente.setMarca(veiculoAtualizado.getMarca());
            veiculoExistente.setPlaca(veiculoAtualizado.getPlaca());
            veiculoExistente.setAno(veiculoAtualizado.getAno());
            veiculoExistente.setDisponibilidade(veiculoAtualizado.isDisponibilidade());
            veiculoExistente.setCategoria(veiculoAtualizado.getCategoria());
            veiculoExistente.setQuilometragem(veiculoAtualizado.getQuilometragem());
            return veiculoRepository.save(veiculoExistente);
        }
        return null;
    }

    public void deleteVeiculo(Long id) {
        veiculoRepository.deleteById(id);
    }
}
