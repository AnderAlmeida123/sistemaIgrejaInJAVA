package com.projeto.sistemaIgreja.models;


import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table(name = "sacramento")
public class Sacramento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @Column(name = "localSsacramento", length = 255, nullable = false)
    private String localSacramento;

    @Column(name = "dataHoraSacramento", nullable = false)
    private LocalDateTime dataHoraSacramento;

    @ManyToOne
    @JoinColumn(name = "pessoaId", nullable = false)
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "tipoSacramentoId", nullable = false)
    private TipoSacramento tipoSacramento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocalSacramento() {
        return localSacramento;
    }

    public void setLocalSacramento(String localSacramento) {
        this.localSacramento = localSacramento;
    }

    public LocalDateTime getDataHoraSacramento() {
        return dataHoraSacramento;
    }

    public void setDataHoraSacramento(LocalDateTime dataHoraSacramento) {
        this.dataHoraSacramento = dataHoraSacramento;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public TipoSacramento getTipoSacramento() {
        return tipoSacramento;
    }

    public void setTipoSacramento(TipoSacramento tipoSacramento) {
        this.tipoSacramento = tipoSacramento;
    }
}
