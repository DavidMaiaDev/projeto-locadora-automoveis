package org.dsc.locadora.dto;

public class PagamentoDTO {

    private Long id;
    private double valor;
    private String dataPagamento;

    public PagamentoDTO(Long id, double valor, String dataPagamento) {
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

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
