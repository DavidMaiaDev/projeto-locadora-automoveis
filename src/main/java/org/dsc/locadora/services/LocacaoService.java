package org.dsc.locadora.services;


import org.dsc.locadora.models.Locacao;
import org.dsc.locadora.repository.ClienteRepository;
import org.dsc.locadora.repository.LocacaoRepository;
import org.dsc.locadora.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocacaoService {

    private final LocacaoRepository locacaoRepository;

    public LocacaoService(LocacaoRepository locacaoRepository, ClienteRepository clienteRepository, VeiculoRepository veiculoRepository) {
        this.locacaoRepository = locacaoRepository;
    }

    public List<Locacao> listLocacoes() {
        return locacaoRepository.findAll();
    }

    public Optional<Locacao> getLocacao(Long id) {
        return locacaoRepository.findById(id);
    }

    public Locacao saveLocacao(Locacao locacao) {
        return locacaoRepository.save(locacao);
    }

    public Optional<Locacao> getLocacaoById(Long id) {
        return locacaoRepository.findById(id);
    }

    public Locacao updateLocacao(Long id, Locacao locacaoAtualizada) {
        Optional<Locacao> locacaoOpt = locacaoRepository.findById(id);
        if (locacaoOpt.isPresent()) {
            Locacao locacaoExistente = locacaoOpt.get();
            locacaoExistente.setDataLocacao(locacaoAtualizada.getDataLocacao());
            locacaoExistente.setDataDevolucaoPrevista(locacaoAtualizada.getDataDevolucaoPrevista());
            locacaoExistente.setDataDevolucaoReal(locacaoAtualizada.getDataDevolucaoReal());
            locacaoExistente.setValorPago(locacaoAtualizada.getValorPago());
            locacaoExistente.setStatus(locacaoAtualizada.getStatus());
            return locacaoRepository.save(locacaoExistente);
        }
        return null;
    }

    public void deleteLocacao(Long id) {
        locacaoRepository.deleteById(id);
    }
}
