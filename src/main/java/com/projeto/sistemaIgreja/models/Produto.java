package com.projeto.sistemaIgreja.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O nome do produto não pode ser nulo.")
    private String produtoNome;

    @NotNull(message = "A quantidade não pode ser nula.")
    private Integer quantidade;

    @NotNull(message = "A unidade de medida não pode ser nula.")
    private String medida;

    @NotNull(message = "Descreva uma Observação")
    private String observacao;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovimentacaoProduto> movimentacaoProduto;

    // Método para verificar se o produto pode ser movimentado
    public boolean podeMovimentar(int quantidade) {
        return this.quantidade >= quantidade;
    }

    // Getters e Setters

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", produtoNome='" + produtoNome + '\'' +
                ", quantidade=" + quantidade +
                ", medida='" + medida + '\'' +
                ", observacao='" + observacao + '\'' +
                '}';
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "O nome do produto não pode ser nulo.") String getProdutoNome() {
        return produtoNome;
    }

    public void setProdutoNome(@NotNull(message = "O nome do produto não pode ser nulo.") String produtoNome) {
        this.produtoNome = produtoNome;
    }

    public @NotNull(message = "A quantidade não pode ser nula.") Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(@NotNull(message = "A quantidade não pode ser nula.") Integer quantidade) {
        this.quantidade = quantidade;
    }

    public @NotNull(message = "A unidade de medida não pode ser nula.") String getMedida() {
        return medida;
    }

    public void setMedida(@NotNull(message = "A unidade de medida não pode ser nula.") String medida) {
        this.medida = medida;
    }

    public @NotNull(message = "Descreva uma Observação") String getObservacao() {
        return observacao;
    }

    public void setObservacao(@NotNull(message = "Descreva uma Observação") String observacao) {
        this.observacao = observacao;
    }

    public List<MovimentacaoProduto> getMovimentacaoProduto() {
        return movimentacaoProduto;
    }

    public void setMovimentacaoProduto(List<MovimentacaoProduto> movimentacaoProduto) {
        this.movimentacaoProduto = movimentacaoProduto;
    }
}
