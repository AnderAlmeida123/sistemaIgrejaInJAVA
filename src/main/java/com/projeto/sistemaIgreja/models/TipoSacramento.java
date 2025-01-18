package com.projeto.sistemaIgreja.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "tipoSacramento")
public class TipoSacramento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "O nome do evento é obrigatório.")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]+$", message = "O nome deve conter apenas letras")
    private String nomeSacramento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeSacramento() {
        return nomeSacramento;
    }

    public void setNomeSacramento(String nomeSacramento) {
        this.nomeSacramento = nomeSacramento;
    }


//    Relações das tabelas


    @OneToMany(mappedBy = "tipoSacramento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pessoa> pessoa;
    @OneToMany(mappedBy = "tipoSacramento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sacramento> sacramento;

    public List<Pessoa> getPessoa() {
        return pessoa;
    }

    public void setPessoa(List<Pessoa> pessoa) {
        this.pessoa = pessoa;
    }

    public List<Sacramento> getSacramento() {
        return sacramento;
    }

    public void setSacramento(List<Sacramento> sacramento) {
        this.sacramento = sacramento;
    }
}
