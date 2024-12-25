package com.projeto.sistemaIgreja.repository;

import com.projeto.sistemaIgreja.models.TipoEvento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoEventoRepositorio extends JpaRepository<TipoEvento, Long> {
}
