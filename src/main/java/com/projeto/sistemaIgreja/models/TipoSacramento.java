package com.projeto.sistemaIgreja.models;


import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(name = "tipoSacramento")
public class TipoSacramento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @Column(nullable = false) // Garante que o valor não seja nulo
    private String nomeSacramento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeSacramento() {
        return nomeSacramento;
    }

    public void setNomeSacramento(String nomeSacramento) {
        this.nomeSacramento = nomeSacramento;
    }
}
