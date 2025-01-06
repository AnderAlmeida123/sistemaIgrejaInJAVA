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

//    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Endereco> endereco; // Uma pessoa pode ter vários endereços
//
//    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Contato> contato; // Uma pessoa pode ter vários contatos

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


//    public List<Endereco> getEnderecos() {
//        return endereco;
//    }
//
//    public void setEnderecos(List<Endereco> enderecos) {
//        this.endereco = enderecos;
//    }
}
