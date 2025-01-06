package com.projeto.sistemaIgreja.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.security.PrivateKey;


@Entity
@Table(name = "setor")
public class Setor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Nome do Setor é Obrigatorio")
    private String setorNome;

    @NotBlank(message = "Descrição do Setor é Obrigatorio")
    private String descricao;

    @ManyToOne
    @NotNull(message = "Nome da Pessoa é Obrigatorio")
    private Pessoa pessoa;

    @ManyToOne
    @NotNull(message = "Nome da Comunidade é Obrigatorio")
    private Comunidade comunidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Nome do Setor é Obrigatorio") String getSetorNome() {
        return setorNome;
    }

    public void setSetorNome(@NotBlank(message = "Nome do Setor é Obrigatorio") String setorNome) {
        this.setorNome = setorNome;
    }

    public @NotBlank(message = "Descrição do Setor é Obrigatorio") String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank(message = "Descrição do Setor é Obrigatorio") String descricao) {
        this.descricao = descricao;
    }

    public @NotNull(message = "Nome da Pessoa é Obrigatorio") Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(@NotNull(message = "Nome da Pessoa é Obrigatorio") Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public @NotNull(message = "Nome da Comunidade é Obrigatorio") Comunidade getComunidade() {
        return comunidade;
    }

    public void setComunidade(@NotNull(message = "Nome da Comunidade é Obrigatorio") Comunidade comunidade) {
        this.comunidade = comunidade;
    }
}
