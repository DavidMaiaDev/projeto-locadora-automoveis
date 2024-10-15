package org.dsc.locadora.dto;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

public class LocacaoDTO {


    private Long id;
    @NotNull
    private Long clienteId;
    @NotNull
    private Long veiculoId;

    @NotNull
    @FutureOrPresent(message = "A data de locação não pode estar no passado")
    private LocalDate dataLocacao;

    @FutureOrPresent(message = "A data de devolução prevista não pode estar no passado")
    private LocalDate dataDevolucaoPrevista;

    private LocalDate dataDevolucaoReal;

    @PositiveOrZero
    private double valorPago;

    private String status;

    public LocacaoDTO() {}

    public LocacaoDTO(Long id, Long clienteId, Long veiculoId, LocalDate dataLocacao, LocalDate dataDevolucaoPrevista, LocalDate dataDevolucaoReal, double valorPago, String status) {
        this.id = id;
        this.clienteId = clienteId;
        this.veiculoId = veiculoId;
        this.dataLocacao = dataLocacao;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.dataDevolucaoReal = dataDevolucaoReal;
        this.valorPago = valorPago;
        this.status = status;
    }

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

    public LocalDate getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(LocalDate dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public void setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    public LocalDate getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }

    public void setDataDevolucaoReal(LocalDate dataDevolucaoReal) {
        this.dataDevolucaoReal = dataDevolucaoReal;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
