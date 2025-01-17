package com.projeto.sistemaIgreja.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "tipoEvento") // Define explicitamente o nome da tabela

public class TipoEvento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @NotNull(message = "O nome do evento é obrigatório.")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]+$", message = "O nome deve conter apenas letras")
    private String nomeEvento;

    @OneToMany(mappedBy = "tipoEvento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Calendario> calendarios;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "O nome do evento é obrigatório.") @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]+$", message = "O nome deve conter apenas letras") String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(@NotNull(message = "O nome do evento é obrigatório.") @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]+$", message = "O nome deve conter apenas letras") String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public List<Calendario> getCalendarios() {
        return calendarios;
    }

    public void setCalendarios(List<Calendario> calendarios) {
        this.calendarios = calendarios;
    }
}
