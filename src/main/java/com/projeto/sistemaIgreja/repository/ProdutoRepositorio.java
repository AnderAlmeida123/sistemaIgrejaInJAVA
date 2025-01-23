package com.projeto.sistemaIgreja.repository;

import com.projeto.sistemaIgreja.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepositorio extends JpaRepository<Produto, Long> {
}
