package com.projeto.sistemaIgreja.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O CEP é obrigatório.")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve conter exatamente 8 números, com hífen entre o quinto e o sexto número.")
    private String cep;

    @NotBlank(message = "O nome do Estado é obrigatório.")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]+$", message = "O nome deve conter apenas letras.")
    private String estado;

    @NotBlank(message = "O nome da Cidade é obrigatório.")
    private String cidade;

    @NotBlank(message = "O nome do Bairro é obrigatório.")
    private String bairro;

    @NotBlank(message = "O nome da Rua é obrigatório.")
    private String rua;

    @NotBlank(message = "O campo número é obrigatório.")
    @Column(nullable = false)
    private String numero = "S/N";

    private String referencia;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false) // Chave estrangeira obrigatória
    private Pessoa pessoa;

    // Getters e Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "O CEP é obrigatório.") @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve conter exatamente 8 números, com hífen entre o quinto e o sexto número.") String getCep() {
        return cep;
    }

    public void setCep(@NotBlank(message = "O CEP é obrigatório.") @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve conter exatamente 8 números, com hífen entre o quinto e o sexto número.") String cep) {
        this.cep = cep;
    }

    public @NotBlank(message = "O nome do Estado é obrigatório.") @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]+$", message = "O nome deve conter apenas letras.") String getEstado() {
        return estado;
    }

    public void setEstado(@NotBlank(message = "O nome do Estado é obrigatório.") @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]+$", message = "O nome deve conter apenas letras.") String estado) {
        this.estado = estado;
    }

    public @NotBlank(message = "O nome da Cidade é obrigatório.") String getCidade() {
        return cidade;
    }

    public void setCidade(@NotBlank(message = "O nome da Cidade é obrigatório.") String cidade) {
        this.cidade = cidade;
    }

    public @NotBlank(message = "O nome do Bairro é obrigatório.") String getBairro() {
        return bairro;
    }

    public void setBairro(@NotBlank(message = "O nome do Bairro é obrigatório.") String bairro) {
        this.bairro = bairro;
    }

    public @NotBlank(message = "O nome da Rua é obrigatório.") String getRua() {
        return rua;
    }

    public void setRua(@NotBlank(message = "O nome da Rua é obrigatório.") String rua) {
        this.rua = rua;
    }

    public @NotBlank(message = "O campo número é obrigatório.") String getNumero() {
        return numero;
    }

    public void setNumero(@NotBlank(message = "O campo número é obrigatório.") String numero) {
        this.numero = numero;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
