package org.dsc.locadora.dto;

public class LocacaoDTO {

    private Long id;
    private Long clienteId;
    private Long veiculoId;
    private String dataInicio;
    private String dataFim;
    private double valorTotal;

    public LocacaoDTO(Long id, Long clienteId, Long veiculoId, String dataInicio, String dataFim, double valorTotal) {
        this.id = id;
        this.clienteId = clienteId;
        this.veiculoId = veiculoId;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.valorTotal = valorTotal;
    }
    public LocacaoDTO() {}


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

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
