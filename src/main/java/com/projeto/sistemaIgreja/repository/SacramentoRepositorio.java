package com.projeto.sistemaIgreja.repository;

import com.projeto.sistemaIgreja.models.Sacramento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SacramentoRepositorio extends JpaRepository<Sacramento, Long> {
}
