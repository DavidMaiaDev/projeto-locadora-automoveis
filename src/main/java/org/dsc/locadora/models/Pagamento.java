package org.dsc.locadora.models;

import javax.persistence.*;


import java.time.LocalDate;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double valor;
    private LocalDate dataPagamento;

    @ManyToOne
    @JoinColumn(name = "locacao_id")
    private Locacao locacao;


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


    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao){
        this.locacao = locacao;
    }


}
