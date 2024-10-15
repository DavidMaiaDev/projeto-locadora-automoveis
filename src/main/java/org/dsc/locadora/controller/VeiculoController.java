package org.dsc.locadora.controller;

import jakarta.validation.Valid;
import org.dsc.locadora.dto.VeiculoDTO;
import org.dsc.locadora.models.Veiculo;
import org.dsc.locadora.services.VeiculoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api")
public class VeiculoController {

    private final VeiculoService veiculoService;
    private final ModelMapper modelMapper;

    public VeiculoController(VeiculoService veiculoService, ModelMapper modelMapper) {
        this.veiculoService = veiculoService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/veiculos/{veiculoId}")
    public VeiculoDTO getVeiculo(@PathVariable Long veiculoId) {
        Veiculo veiculo = veiculoService.getVeiculo(veiculoId).orElse(null);
        return convertToDTO(veiculo);
    }

    @GetMapping("/veiculos")
    public List<VeiculoDTO> listVeiculos() {
        List<Veiculo> veiculos = veiculoService.listVeiculos();
        return veiculos.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @PostMapping("/veiculos")
    public VeiculoDTO createVeiculo(@Valid @RequestBody VeiculoDTO veiculoDTO) {
        Veiculo veiculo = convertToEntity(veiculoDTO);
        Veiculo veiculoCreated = veiculoService.saveVeiculo(veiculo);
        return convertToDTO(veiculoCreated);
    }

    @PutMapping("/veiculos/{veiculoId}")
    public VeiculoDTO updateVeiculo(@PathVariable Long veiculoId, @Valid @RequestBody VeiculoDTO veiculoDTO) {
        Veiculo veiculo = convertToEntity(veiculoDTO);
        Veiculo veiculoUpdated = veiculoService.updateVeiculo(veiculoId, veiculo);
        return convertToDTO(veiculoUpdated);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/veiculos/{veiculoId}")
    public void deleteVeiculo(@PathVariable Long veiculoId) {
        veiculoService.deleteVeiculo(veiculoId);
    }

    private VeiculoDTO convertToDTO(Veiculo veiculo) {
        return modelMapper.map(veiculo, VeiculoDTO.class);
    }

    private Veiculo convertToEntity(VeiculoDTO veiculoDTO) {
        return modelMapper.map(veiculoDTO, Veiculo.class);
    }
}
