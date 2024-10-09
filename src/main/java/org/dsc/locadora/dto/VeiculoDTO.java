package org.dsc.locadora.dto;


public class VeiculoDTO {

    private Long id;
    private String modelo;
    private String marca;
    private String placa;
    private int ano;
    private boolean disponibilidade;
    private String categoria;
    private double quilometragem;

    public VeiculoDTO(Long id, String modelo, String marca, String placa, int ano, boolean disponibilidade, String categoria, double quilometragem) {
        this.id = id;
        this.modelo = modelo;
        this.marca = marca;
        this.placa = placa;
        this.ano = ano;
        this.disponibilidade = disponibilidade;
        this.categoria = categoria;
        this.quilometragem = quilometragem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;

    }
}