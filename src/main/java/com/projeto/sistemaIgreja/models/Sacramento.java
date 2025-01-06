package com.projeto.sistemaIgreja.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table(name = "sacramento")
public class Sacramento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @NotNull(message = "A data e hora do sacramento são obrigatórias.")
    private LocalDateTime dataHoraSacramento;

    @ManyToOne
    @NotNull(message = "A pessoa é obrigatória.")
    private Pessoa pessoa;

    @ManyToOne
    @NotNull(message = "O tipo de sacramento é obrigatório.")
    private TipoSacramento tipoSacramento;

    @ManyToOne
    @NotNull(message = "O local do sacramento é obrigatório.")
    private Comunidade comunidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "A data e hora do sacramento são obrigatórias.") LocalDateTime getDataHoraSacramento() {
        return dataHoraSacramento;
    }

    public void setDataHoraSacramento(@NotNull(message = "A data e hora do sacramento são obrigatórias.") LocalDateTime dataHoraSacramento) {
        this.dataHoraSacramento = dataHoraSacramento;
    }

    public @NotNull(message = "A pessoa é obrigatória.") Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(@NotNull(message = "A pessoa é obrigatória.") Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public @NotNull(message = "O tipo de sacramento é obrigatório.") TipoSacramento getTipoSacramento() {
        return tipoSacramento;
    }

    public void setTipoSacramento(@NotNull(message = "O tipo de sacramento é obrigatório.") TipoSacramento tipoSacramento) {
        this.tipoSacramento = tipoSacramento;
    }

    public @NotNull(message = "O local do sacramento é obrigatório.") Comunidade getComunidade() {
        return comunidade;
    }

    public void setComunidade(@NotNull(message = "O local do sacramento é obrigatório.") Comunidade comunidade) {
        this.comunidade = comunidade;
    }
}
