package org.dsc.locadora.controller;

import jakarta.validation.Valid;
import org.dsc.locadora.dto.LocacaoDTO;
import org.dsc.locadora.models.Locacao;
import org.dsc.locadora.services.LocacaoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api")
public class LocacaoController {

    private final LocacaoService locacaoService;
    private final ModelMapper modelMapper;

    public LocacaoController(LocacaoService locacaoService, ModelMapper modelMapper) {
        this.locacaoService = locacaoService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/locacoes/{locacaoId}")
    public LocacaoDTO getLocacao(@PathVariable Long locacaoId) {
        Locacao locacao = locacaoService.getLocacao(locacaoId).orElse(null);
        return convertToDTO(locacao);
    }

    @GetMapping("/locacoes")
    public List<LocacaoDTO> listLocacoes() {
        List<Locacao> locacoes = locacaoService.listLocacoes();
        return locacoes.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @PostMapping("/locacoes")
    public LocacaoDTO createLocacao(@Valid @RequestBody LocacaoDTO locacaoDTO) {
        Locacao locacao = convertToEntity(locacaoDTO);
        Locacao locacaoCreated = locacaoService.saveLocacao(locacao);
        return convertToDTO(locacaoCreated);
    }

    @PutMapping("/locacoes/{locacaoId}")
    public LocacaoDTO updateLocacao(@PathVariable Long locacaoId,@Valid @RequestBody LocacaoDTO locacaoDTO) {
        Locacao locacao = convertToEntity(locacaoDTO);
        Locacao locacaoUpdated = locacaoService.updateLocacao(locacaoId, locacao);
        return convertToDTO(locacaoUpdated);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/locacoes/{locacaoId}")
    public void deleteLocacao(@PathVariable Long locacaoId) {
        locacaoService.deleteLocacao(locacaoId);
    }

    private LocacaoDTO convertToDTO(Locacao locacao) {
        return modelMapper.map(locacao, LocacaoDTO.class);
    }

    private Locacao convertToEntity(LocacaoDTO locacaoDTO) {
        return modelMapper.map(locacaoDTO, Locacao.class);
    }
}
