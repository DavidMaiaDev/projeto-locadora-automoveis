package org.dsc.locadora.services;

import org.dsc.locadora.models.Pagamento;
import org.dsc.locadora.repository.PagamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    public List<Pagamento> listPagamentos() {
        return pagamentoRepository.findAll();
    }

    public Optional<Pagamento> getPagamento(Long id) {
        return pagamentoRepository.findById(id);
    }

    public Pagamento savePagamento(Pagamento pagamento) {
        return pagamentoRepository.save(pagamento);
    }

    public Pagamento updatePagamento(Long id, Pagamento pagamentoAtualizado) {
        Optional<Pagamento> pagamentoOpt = pagamentoRepository.findById(id);
        if (pagamentoOpt.isPresent()) {
            Pagamento pagamentoExistente = pagamentoOpt.get();
            pagamentoExistente.setValor(pagamentoAtualizado.getValor());
            pagamentoExistente.setDataPagamento(pagamentoAtualizado.getDataPagamento());
            return pagamentoRepository.save(pagamentoExistente);
        }
        return null;
    }

    public void deletePagamento(Long id) {
        pagamentoRepository.deleteById(id);
    }
}