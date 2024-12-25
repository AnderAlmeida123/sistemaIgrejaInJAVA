package com.projeto.sistemaIgreja.models;


import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(name = "tipoEvento") // Define explicitamente o nome da tabela

public class TipoEvento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @Column(nullable = false) // Garante que o valor não seja nulo
    private String nomeEvento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }
}
