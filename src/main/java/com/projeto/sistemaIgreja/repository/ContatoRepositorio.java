package com.projeto.sistemaIgreja.repository;

import com.projeto.sistemaIgreja.models.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepositorio extends JpaRepository<Contato, Long> {
}
