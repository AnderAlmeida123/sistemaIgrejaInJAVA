package com.projeto.sistemaIgreja.repository;

import com.projeto.sistemaIgreja.models.Comunidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComunidadeRepositorio extends JpaRepository<Comunidade, Long> {
}
