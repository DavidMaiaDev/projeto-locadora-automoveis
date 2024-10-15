package org.dsc.locadora.dto;


import javax.validation.constraints.*;

public class VeiculoDTO {

    private Long id;

    @NotBlank
    private String modelo;

    @NotBlank
    private String marca;

    @NotBlank
    @Pattern(regexp = "[A-Z]{3}-\\d{4}", message = "A placa deve estar no formato correto (ex: ABC-1234)")
    private String placa;

    @Min(1886)
    private int ano;

    @NotNull
    private boolean disponibilidade;


    @PositiveOrZero
    private double quilometragem;


    public VeiculoDTO(Long id, String modelo, String marca, String placa, int ano, boolean disponibilidade, double quilometragem) {
        this.id = id;
        this.modelo = modelo;
        this.marca = marca;
        this.placa = placa;
        this.ano = ano;
        this.disponibilidade = disponibilidade;
        this.quilometragem = quilometragem;
    }

    public VeiculoDTO() {}

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