package com.projeto.sistemaIgreja.repository;

import com.projeto.sistemaIgreja.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepositorio extends JpaRepository<Pessoa, Long> {
}
