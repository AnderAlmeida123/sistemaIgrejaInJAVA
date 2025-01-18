package com.projeto.sistemaIgreja.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "turma")
public class Turma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)

    private Long id;

    @NotBlank(message = "Nome da Turma é obrigatório.")
    private String turmaNome;
    @NotBlank(message = "Nome da Descrição é obrigatório.")
    private String descricao;

    @ManyToOne
    @NotNull(message = "Pessoa é obrigatório")
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @ManyToOne
    @NotNull(message = "Setor é obrigatório.")
    @JoinColumn(name = "setor_id")
    private Setor setor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Nome da Turma é obrigatório.") String getTurmaNome() {
        return turmaNome;
    }

    public void setTurmaNome(@NotBlank(message = "Nome da Turma é obrigatório.") String turmaNome) {
        this.turmaNome = turmaNome;
    }

    public @NotBlank(message = "Nome da Descrição é obrigatório.") String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank(message = "Nome da Descrição é obrigatório.") String descricao) {
        this.descricao = descricao;
    }

    public @NotNull(message = "Pessoa é obrigatório") Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(@NotNull(message = "Pessoa é obrigatório") Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public @NotNull(message = "Setor é obrigatório.") Setor getSetor() {
        return setor;
    }

    public void setSetor(@NotNull(message = "Setor é obrigatório.") Setor setor) {
        this.setor = setor;
    }

    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MembroTurma> membroTurma;

    public List<MembroTurma> getMembroTurma() {
        return membroTurma;
    }

    public void setMembroTurma(List<MembroTurma> membroTurma) {
        this.membroTurma = membroTurma;
    }
}
