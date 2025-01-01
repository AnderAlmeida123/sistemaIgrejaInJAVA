package com.projeto.sistemaIgreja.repository;

import com.projeto.sistemaIgreja.models.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepositorio extends JpaRepository<Estoque, Long> {
}
