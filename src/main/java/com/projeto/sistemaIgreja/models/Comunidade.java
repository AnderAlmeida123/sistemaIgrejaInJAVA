package com.projeto.sistemaIgreja.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "comunidade")
public class Comunidade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "O nome da Comunidade é obrigatório.")
    private String nomeComunidade;

    @NotBlank(message = "O nome do Bairro é obrigatório.")
    private String bairro;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "O nome da Comunidade é obrigatório.") String getNomeComunidade() {
        return nomeComunidade;
    }

    public void setNomeComunidade(@NotBlank(message = "O nome da Comunidade é obrigatório.") String nomeComunidade) {
        this.nomeComunidade = nomeComunidade;
    }

    public @NotBlank(message = "O nome do Bairro é obrigatório.") String getBairro() {
        return bairro;
    }

    public void setBairro(@NotBlank(message = "O nome do Bairro é obrigatório.") String bairro) {
        this.bairro = bairro;
    }


//    Relações das tabelas

    @OneToMany(mappedBy = "comunidade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Calendario> calendario;
    @OneToMany(mappedBy = "comunidade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Setor> setor;
    @OneToMany(mappedBy = "comunidade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sacramento> sacramento;
    @OneToMany(mappedBy = "comunidade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tesouraria> tesouraria;

    public List<Calendario> getCalendario() {
        return calendario;
    }

    public void setCalendario(List<Calendario> calendari) {
        this.calendario = calendario;
    }

    public List<Setor> getSetor() {
        return setor;
    }

    public void setSetor(List<Setor> setor) {
        this.setor = setor;
    }

    public List<Sacramento> getSacramento() {
        return sacramento;
    }

    public void setSacramento(List<Sacramento> sacramento) {
        this.sacramento = sacramento;
    }

    public List<Tesouraria> getTesouraria() {
        return tesouraria;
    }

    public void setTesouraria(List<Tesouraria> tesouraria) {
        this.tesouraria = tesouraria;
    }
}
