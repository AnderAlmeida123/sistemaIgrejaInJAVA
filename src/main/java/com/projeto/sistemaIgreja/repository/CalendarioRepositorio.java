package com.projeto.sistemaIgreja.repository;

import com.projeto.sistemaIgreja.models.Calendario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarioRepositorio extends JpaRepository<Calendario, Long> {
}
