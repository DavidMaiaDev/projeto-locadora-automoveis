package org.dsc.locadora.controller;

import jakarta.validation.Valid;
import org.dsc.locadora.dto.ReservaDTO;
import org.dsc.locadora.models.Reserva;
import org.dsc.locadora.services.ReservaService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api")
public class ReservaController {

    private final ReservaService reservaService;
    private final ModelMapper modelMapper;

    public ReservaController(ReservaService reservaService, ModelMapper modelMapper) {
        this.reservaService = reservaService;
        this.modelMapper = modelMapper;


        modelMapper.addMappings(new PropertyMap<Reserva, ReservaDTO>() {
            @Override
            protected void configure() {
                map().setDataInicio(source.getDataInicio());
                map().setDataFim(source.getDataFim());
            }
        });
    }

    @GetMapping("/reservas/{reservaId}")
    public ReservaDTO getReserva(@PathVariable Long reservaId) {
        Reserva reserva = reservaService.getReserva(reservaId).orElse(null);
        return convertToDTO(reserva);
    }

    @GetMapping("/reservas")
    public List<ReservaDTO> listReservas() {
        List<Reserva> reservas = reservaService.listReservas();
        return reservas.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @PostMapping("/reservas")
    public ReservaDTO createReserva(@Valid @RequestBody ReservaDTO reservaDTO) {
        Reserva reserva = convertToEntity(reservaDTO);
        Reserva reservaCreated = reservaService.saveReserva(reserva);
        return convertToDTO(reservaCreated);
    }

    @PutMapping("/reservas/{reservaId}")
    public ReservaDTO updateReserva(@Valid @PathVariable Long reservaId, @RequestBody ReservaDTO reservaDTO) {
        Reserva reserva = convertToEntity(reservaDTO);
        Reserva reservaUpdated = reservaService.updateReserva(reservaId, reserva);
        return convertToDTO(reservaUpdated);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/reservas/{reservaId}")
    public void deleteReserva(@PathVariable Long reservaId) {
        reservaService.deleteReserva(reservaId);
    }

    private ReservaDTO convertToDTO(Reserva reserva) {
        return modelMapper.map(reserva, ReservaDTO.class);
    }

    private Reserva convertToEntity(ReservaDTO reservaDTO) {
        return modelMapper.map(reservaDTO, Reserva.class);
    }
}
