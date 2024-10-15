package org.dsc.locadora.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ClienteDTO {

    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @Size(min = 11, max = 11)

    private String cpf;

    @Email
    private String email;

    @NotBlank
    private String endereco;

    public ClienteDTO(Long id, String nome, String cpf, String email, String endereco) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.endereco = endereco;
    }
    public ClienteDTO() {}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
