package com.projeto.sistemaIgreja.repository;

import com.projeto.sistemaIgreja.models.TipoSacramento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoSacramentoRepositorio extends JpaRepository<TipoSacramento, Long> {
}
