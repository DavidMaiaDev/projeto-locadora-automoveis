package org.dsc.locadora.controller;

import javax.validation.Valid;
import org.dsc.locadora.dto.PagamentoDTO;
import org.dsc.locadora.models.Pagamento;
import org.dsc.locadora.models.Locacao;
import org.dsc.locadora.services.PagamentoService;
import org.dsc.locadora.services.LocacaoService; // Importação do LocacaoService
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api")
public class PagamentoController {

    private final PagamentoService pagamentoService;
    private final LocacaoService locacaoService;
    private final ModelMapper modelMapper;

    public PagamentoController(PagamentoService pagamentoService, LocacaoService locacaoService, ModelMapper modelMapper) {
        this.pagamentoService = pagamentoService;
        this.locacaoService = locacaoService; // Inicializa LocacaoService
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


    private PagamentoDTO convertToDTO(Pagamento pagamento) {
        return modelMapper.map(pagamento, PagamentoDTO.class);
    }


    private Pagamento convertToEntity(PagamentoDTO pagamentoDTO) {
        Pagamento pagamento = modelMapper.map(pagamentoDTO, Pagamento.class);

        if (pagamentoDTO.getLocacaoId() != null) {
            Locacao locacao = locacaoService.getLocacaoById(pagamentoDTO.getLocacaoId())
                    .orElseThrow(() -> new RuntimeException("Locação não encontrada"));
            pagamento.setLocacao(locacao);
        }
        return pagamento;
    }
}
