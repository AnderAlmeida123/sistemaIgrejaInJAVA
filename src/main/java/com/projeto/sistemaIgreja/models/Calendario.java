package com.projeto.sistemaIgreja.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
@Table(name = "calendario")
public class Calendario implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Nome do Evento é Obrigatório.")
    private String tituloEvento;

    @NotBlank(message = "Data e Hora do Evento é Obrigatório.")
    private String dataHora;

    @NotBlank(message = "Descrição do Evento é Obrigatório.")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "comunidade_id", nullable = false)
    private Comunidade comunidade;

    @ManyToOne
    @JoinColumn(name = "tipo_evento_id", nullable = false)
    private TipoEvento tipoEvento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Nome do Evento é Obrigatório.") String getTituloEvento() {
        return tituloEvento;
    }

    public void setTituloEvento(@NotBlank(message = "Nome do Evento é Obrigatório.") String tituloEvento) {
        this.tituloEvento = tituloEvento;
    }

    public @NotBlank(message = "Data e Hora do Evento é Obrigatório.") String getDataHora() {
        return dataHora;
    }

    public void setDataHora(@NotBlank(message = "Data e Hora do Evento é Obrigatório.") String dataHora) {
        this.dataHora = dataHora;
    }

    public @NotBlank(message = "Descrição do Evento é Obrigatório.") String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank(message = "Descrição do Evento é Obrigatório.") String descricao) {
        this.descricao = descricao;
    }

    public @NotNull(message = "Comunidade é Obrigatória.") Comunidade getComunidade() {
        return comunidade;
    }

    public void setComunidade(@NotNull(message = "Comunidade é Obrigatória.") Comunidade comunidade) {
        this.comunidade = comunidade;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }
}
