package com.projeto.sistemaIgreja.models;


import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;
    private String nome;

    @Column(nullable = false, unique = true) // Garante que o valor não seja nulo e unico
    private String cpf;
    private String dataNascimento;
    private String sexo;

    @ManyToOne
    private TipoSacramento tipoSacramento;
    @ManyToOne
    private Comunidade comunidade;

    public TipoSacramento getTipoSacramento() {
        return tipoSacramento;
    }

    public void setTipoSacramento(TipoSacramento tipoSacramento) {
        this.tipoSacramento = tipoSacramento;
    }

    public Comunidade getComunidade() {
        return comunidade;
    }

    public void setComunidade(Comunidade comunidade) {
        this.comunidade = comunidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
