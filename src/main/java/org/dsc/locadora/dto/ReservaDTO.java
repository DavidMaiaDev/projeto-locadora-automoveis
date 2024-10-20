package org.dsc.locadora.dto;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;

public class ReservaDTO {

    private Long id;

    @NotNull
    private Long clienteId;
    @NotNull
    private Long veiculoId;

    @NotNull
    @FutureOrPresent(message = "A data de reserva não pode estar no passado")
    private LocalDate dataReserva;

    @NotNull
    @FutureOrPresent(message = "A data de início não pode estar no passado")
    private LocalDate dataInicio;

    @NotNull
    @FutureOrPresent(message = "A data final não pode estar no passado")
    private LocalDate dataFim;

    @NotBlank
    private String status;

    public ReservaDTO(Long id, Long clienteId, Long veiculoId, LocalDate dataReserva, LocalDate dataInicio, LocalDate dataFim, String status) {
        this.id = id;
        this.clienteId = clienteId;
        this.veiculoId = veiculoId;
        this.dataReserva = dataReserva;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.status = status;
    }

    public ReservaDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getVeiculoId() {
        return veiculoId;
    }

    public void setVeiculoId(Long veiculoId) {
        this.veiculoId = veiculoId;
    }

    public LocalDate getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
