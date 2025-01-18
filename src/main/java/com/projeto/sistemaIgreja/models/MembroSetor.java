package com.projeto.sistemaIgreja.models;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Table(name = "membroSetor")
public class MembroSetor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "A data de entrada é obrigatória.")
    @PastOrPresent(message = "A data de entrada deve ser no passado ou presente.")
    private LocalDate dataEntrada;

    @FutureOrPresent(message = "A data de saída deve ser no presente ou futuro.")
    private LocalDate dataSaida;


    @ManyToOne
    @JoinColumn(name = "membro_id", nullable = false)
    @NotNull (message = "A pessoa associada ao setor é obrigatória.")
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "setor_id", nullable = false)
    @NotNull (message = "O setor é obrigatório.")
    private Setor setor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "A data de entrada é obrigatória.") @PastOrPresent(message = "A data de entrada deve ser no passado ou presente.") LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(@NotNull(message = "A data de entrada é obrigatória.") @PastOrPresent(message = "A data de entrada deve ser no passado ou presente.") LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public @FutureOrPresent(message = "A data de saída deve ser no presente ou futuro.") LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(@FutureOrPresent(message = "A data de saída deve ser no presente ou futuro.") LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public @NotNull(message = "A pessoa associada ao setor é obrigatória.") Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(@NotNull(message = "A pessoa associada ao setor é obrigatória.") Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public @NotNull(message = "O setor é obrigatório.") Setor getSetor() {
        return setor;
    }

    public void setSetor(@NotNull(message = "O setor é obrigatório.") Setor setor) {
        this.setor = setor;
    }
}
