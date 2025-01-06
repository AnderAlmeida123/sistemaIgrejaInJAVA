package com.projeto.sistemaIgreja.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Table(name = "membroTurma")
public class MembroTurma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)

    private Long id;

    @NotNull(message = "A data de entrada é obrigatória.")
    @PastOrPresent(message = "A data de entrada deve ser no passado ou presente.")
    private LocalDate dataInicio;

    @FutureOrPresent(message = "A data de saída deve ser no presente ou futuro.")
    private LocalDate dataTermino;

    @ManyToOne
    @JoinColumn(name = "membroId", nullable = false)
    @NotNull (message = "A pessoa associada ao setor é obrigatória.")
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "turmaId", nullable = false)
    @NotNull (message = "O turma é obrigatório.")
    private Turma turma;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "A data de entrada é obrigatória.") @PastOrPresent(message = "A data de entrada deve ser no passado ou presente.") LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(@NotNull(message = "A data de entrada é obrigatória.") @PastOrPresent(message = "A data de entrada deve ser no passado ou presente.") LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public @FutureOrPresent(message = "A data de saída deve ser no presente ou futuro.") LocalDate getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(@FutureOrPresent(message = "A data de saída deve ser no presente ou futuro.") LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }

    public @NotNull(message = "A pessoa associada ao setor é obrigatória.") Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(@NotNull(message = "A pessoa associada ao setor é obrigatória.") Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public @NotNull(message = "O turma é obrigatório.") Turma getTurma() {
        return turma;
    }

    public void setTurma(@NotNull(message = "O turma é obrigatório.") Turma turma) {
        this.turma = turma;
    }
}
