package com.projeto.sistemaIgreja.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O Nome é obrigatório.")
    private String nome;

    @NotBlank(message = "O CPF é obrigatório.")
    @Column(nullable = false, unique = true)
    private String cpf;

    @NotNull(message = "A Data de Nascimento é obrigatória.")
    @Past(message = "A data de nascimento deve ser no passado.")
    private LocalDate dataNascimento;

    @NotBlank(message = "O campo Sexo é obrigatório.")
    private String sexo;

    @ManyToOne
    @JoinColumn(name = "tipo_sacramento_id")
    @NotNull(message = "O Tipo de Sacramento deve ser selecionado.")
    private TipoSacramento tipoSacramento;

    @ManyToOne
    @JoinColumn(name = "comunidade_id")
    @NotNull(message = "A Comunidade deve ser selecionada.")
    private Comunidade comunidade;


    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "O Nome é obrigatório.") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "O Nome é obrigatório.") String nome) {
        this.nome = nome;
    }

    public @NotBlank(message = "O CPF é obrigatório.") String getCpf() {
        return cpf;
    }

    public void setCpf(@NotBlank(message = "O CPF é obrigatório.") String cpf) {
        this.cpf = cpf;
    }

    public @NotNull(message = "A Data de Nascimento é obrigatória.") @Past(message = "A data de nascimento deve ser no passado.") LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(@NotNull(message = "A Data de Nascimento é obrigatória.") @Past(message = "A data de nascimento deve ser no passado.") LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public @NotBlank(message = "O campo Sexo é obrigatório.") String getSexo() {
        return sexo;
    }

    public void setSexo(@NotBlank(message = "O campo Sexo é obrigatório.") String sexo) {
        this.sexo = sexo;
    }

    public @NotNull(message = "O Tipo de Sacramento deve ser selecionado.") TipoSacramento getTipoSacramento() {
        return tipoSacramento;
    }

    public void setTipoSacramento(@NotNull(message = "O Tipo de Sacramento deve ser selecionado.") TipoSacramento tipoSacramento) {
        this.tipoSacramento = tipoSacramento;
    }

    public @NotNull(message = "A Comunidade deve ser selecionada.") Comunidade getComunidade() {
        return comunidade;
    }

    public void setComunidade(@NotNull(message = "A Comunidade deve ser selecionada.") Comunidade comunidade) {
        this.comunidade = comunidade;
    }


    //    Relações das tabelas


    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contato> contato;
    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dizimo> dizimo;
    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> endereco;
    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Setor> setor;
    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MembroSetor> membroSetor;
    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MembroTurma> membroTurma;
    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Turma> turma;


    public List<Contato> getContato() {
        return contato;
    }

    public void setContato(List<Contato> contato) {
        this.contato = contato;
    }

    public List<Dizimo> getDizimo() {
        return dizimo;
    }

    public void setDizimo(List<Dizimo> dizimo) {
        this.dizimo = dizimo;
    }

    public List<Endereco> getEndereco() {
        return endereco;
    }

    public void setEndereco(List<Endereco> endereco) {
        this.endereco = endereco;
    }

    public List<Setor> getSetor() {
        return setor;
    }

    public void setSetor(List<Setor> setor) {
        this.setor = setor;
    }

    public List<MembroSetor> getMembroSetor() {
        return membroSetor;
    }

    public void setMembroSetor(List<MembroSetor> membroSetor) {
        this.membroSetor = membroSetor;
    }

    public List<MembroTurma> getMembroTurma() {
        return membroTurma;
    }

    public void setMembroTurma(List<MembroTurma> membroTurma) {
        this.membroTurma = membroTurma;
    }

    public List<Turma> getTurma() {
        return turma;
    }

    public void setTurma(List<Turma> turma) {
        this.turma = turma;
    }
}
