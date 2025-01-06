package com.projeto.sistemaIgreja.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Entity
@Table(name = "contato")
public class Contato implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "O número do celular é obrigatório.")
    @Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}", message = "O celular deve estar no formato (XX) XXXXX-XXXX.")
    private String celular;

    @Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}", message = "O telefone de contato deve estar no formato (XX) XXXXX-XXXX.")
    private String telContato;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "O e-mail informado é inválido.")
    private String email;

    @ManyToOne
    @JoinColumn(name = "pessoaId", nullable = false)
    @NotNull(message = "A pessoa associada ao contato é obrigatória.")
    private Pessoa pessoa;

    // Getters e Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "O número do celular é obrigatório.") @Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}", message = "O celular deve estar no formato (XX) XXXXX-XXXX.") String getCelular() {
        return celular;
    }

    public void setCelular(@NotBlank(message = "O número do celular é obrigatório.") @Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}", message = "O celular deve estar no formato (XX) XXXXX-XXXX.") String celular) {
        this.celular = celular;
    }

    public @Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}", message = "O telefone de contato deve estar no formato (XX) XXXXX-XXXX.") String getTelContato() {
        return telContato;
    }

    public void setTelContato(@Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}", message = "O telefone de contato deve estar no formato (XX) XXXXX-XXXX.") String telContato) {
        this.telContato = telContato;
    }

    public @NotBlank(message = "O e-mail é obrigatório.") @Email(message = "O e-mail informado é inválido.") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "O e-mail é obrigatório.") @Email(message = "O e-mail informado é inválido.") String email) {
        this.email = email;
    }

    public @NotNull(message = "A pessoa associada ao contato é obrigatória.") Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(@NotNull(message = "A pessoa associada ao contato é obrigatória.") Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
