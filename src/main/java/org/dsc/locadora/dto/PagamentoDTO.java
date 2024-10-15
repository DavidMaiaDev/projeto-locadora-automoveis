package org.dsc.locadora.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class PagamentoDTO {

    private Long id;

    @NotNull
    @Positive
    private double valor;

    @PastOrPresent
    private LocalDate dataPagamento;


    public PagamentoDTO(Long id, double valor, LocalDate dataPagamento) {
        this.id = id;
        this.valor = valor;
        this.dataPagamento = dataPagamento;
    }
    public PagamentoDTO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
