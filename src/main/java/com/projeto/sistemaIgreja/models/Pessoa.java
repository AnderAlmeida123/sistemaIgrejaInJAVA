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

    @Column(nullable = false) // Garante que o valor não seja nulo
    private String cpf;
    private String dataNascimento;
    private String sexo;

    public Long getId() {
        return id;
    }

}
