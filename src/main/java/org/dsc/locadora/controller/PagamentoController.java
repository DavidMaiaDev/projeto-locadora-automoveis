package org.dsc.locadora.controller;

import jakarta.validation.Valid;
import org.dsc.locadora.dto.PagamentoDTO;
import org.dsc.locadora.models.Pagamento;
import org.dsc.locadora.services.PagamentoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api")
public class PagamentoController {

    private final PagamentoService pagamentoService;
    private final ModelMapper modelMapper;

    public PagamentoController(PagamentoService pagamentoService, ModelMapper modelMapper) {
        this.pagamentoService = pagamentoService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/pagamentos/{pagamentoId}")
    public PagamentoDTO getPagamento(@PathVariable Long pagamentoId) {
        Pagamento pagamento = pagamentoService.getPagamento(pagamentoId).orElse(null);
        return convertToDTO(pagamento);
    }

    @GetMapping("/pagamentos")
    public List<PagamentoDTO> listPagamentos() {
        List<Pagamento> pagamentos = pagamentoService.listPagamentos();
        return pagamentos.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @PostMapping("/pagamentos")
    public PagamentoDTO createPagamento(@Valid @RequestBody PagamentoDTO pagamentoDTO) {
        Pagamento pagamento = convertToEntity(pagamentoDTO);
        Pagamento pagamentoCreated = pagamentoService.savePagamento(pagamento);
        return convertToDTO(pagamentoCreated);
    }

    @PutMapping("/pagamentos/{pagamentoId}")
    public PagamentoDTO updatePagamento(@Valid @PathVariable Long pagamentoId, @RequestBody PagamentoDTO pagamentoDTO) {
        Pagamento pagamento = convertToEntity(pagamentoDTO);
        Pagamento pagamentoUpdated = pagamentoService.updatePagamento(pagamentoId, pagamento);
        return convertToDTO(pagamentoUpdated);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/pagamentos/{pagamentoId}")
    public void deletePagamento(@PathVariable Long pagamentoId) {
        pagamentoService.deletePagamento(pagamentoId);
    }

    // Converte o modelo Pagamento para PagamentoDTO usando ModelMapper
    private PagamentoDTO convertToDTO(Pagamento pagamento) {
        return modelMapper.map(pagamento, PagamentoDTO.class);
    }

    // Converte o DTO PagamentoDTO para o modelo Pagamento usando ModelMapper
    private Pagamento convertToEntity(PagamentoDTO pagamentoDTO) {
        return modelMapper.map(pagamentoDTO, Pagamento.class);
    }
}
