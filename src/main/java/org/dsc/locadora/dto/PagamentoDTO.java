package org.dsc.locadora.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

public class PagamentoDTO {

    private Long id;

    @NotNull(message = "O locacaoId é obrigatório")
    private Long locacaoId;

    @NotNull
    @Positive(message = "O valor deve ser positivo")
    private double valor;

    @PastOrPresent(message = "A data de pagamento deve ser uma data no passado ou presente")
    private LocalDate dataPagamento;

    public PagamentoDTO(Long id, Long locacaoId, double valor, LocalDate dataPagamento) {
        this.id = id;
        this.locacaoId = locacaoId;
        this.valor = valor;
        this.dataPagamento = dataPagamento;
    }

    public PagamentoDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLocacaoId() {
        return locacaoId;
    }

    public void setLocacaoId(Long locacaoId) {
        this.locacaoId = locacaoId;
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
