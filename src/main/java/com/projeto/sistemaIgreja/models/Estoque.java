package com.projeto.sistemaIgreja.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
@Table(name = "estoque")
public class Estoque implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "O produto não pode ser nulo.")
    private String produto;

    // Alteração para Integer para representar a quantidade, com validação
    @NotNull(message = "A quantidade não pode ser nula.")
    @Min(value = 1, message = "A quantidade deve ser maior ou igual a 1.")
    private Integer quantidade;

    @NotNull(message = "A unidade de medida não pode ser nula.")
    private String medida;

    @ManyToOne
    @JoinColumn(name = "setor_id")
    private Setor setor;

    // Getter e Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    // Método para adicionar quantidade
    public void adicionarQuantidade(Integer quantidade) {
        // Validação para garantir que a quantidade a ser adicionada seja válida
        if (quantidade != null && quantidade > 0) {
            this.quantidade += quantidade;
        } else {
            throw new IllegalArgumentException("Quantidade a ser adicionada deve ser maior que zero.");
        }
    }

    // Método para remover quantidade
    public void removerQuantidade(Integer quantidade) {
        // Validação para garantir que a quantidade a ser removida seja válida
        if (quantidade != null && quantidade > 0) {
            if (this.quantidade >= quantidade) {
                this.quantidade -= quantidade;
            } else {
                throw new IllegalArgumentException("Quantidade insuficiente no estoque.");
            }
        } else {
            throw new IllegalArgumentException("Quantidade a ser removida deve ser maior que zero.");
        }
    }
}
