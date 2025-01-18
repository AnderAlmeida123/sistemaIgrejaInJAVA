package com.projeto.sistemaIgreja.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "tesouraria")
public class Tesouraria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "O tipo de movimentação é obrigatório.")
    @Size(max = 50, message = "O tipo de movimentação deve ter no máximo 50 caracteres.")
    private String tipoMovimentacao;

    @NotBlank(message = "A descrição é obrigatória.")
    @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres.")
    private String descricao;

    @NotNull(message = "O valor é obrigatório.")
    @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero.")
    @Digits(integer = 10, fraction = 2, message = "O valor deve ter no máximo 10 dígitos inteiros e 2 casas decimais.")
    private BigDecimal valor;

    @NotBlank(message = "A data da movimentação é obrigatória.")
    @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "A data deve estar no formato dd/MM/yyyy.")
    private String dataMovimentacao;

    @NotNull(message = "O tipo de movimentação (entrada ou saída) é obrigatório.")
    private Boolean entradaSaida;

    @ManyToOne
    @NotNull(message = "O setor é obrigatório.")
    private Setor setor;

    @ManyToOne
    @NotNull(message = "A comunidade é obrigatória.")
    @JoinColumn(name = "comunidade_id")
    private Comunidade comunidade;

    // Getters e Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "O tipo de movimentação é obrigatório.") @Size(max = 50, message = "O tipo de movimentação deve ter no máximo 50 caracteres.") String getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(@NotBlank(message = "O tipo de movimentação é obrigatório.") @Size(max = 50, message = "O tipo de movimentação deve ter no máximo 50 caracteres.") String tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public @NotBlank(message = "A descrição é obrigatória.") @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres.") String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank(message = "A descrição é obrigatória.") @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres.") String descricao) {
        this.descricao = descricao;
    }

    public @NotNull(message = "O valor é obrigatório.") @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero.") @Digits(integer = 10, fraction = 2, message = "O valor deve ter no máximo 10 dígitos inteiros e 2 casas decimais.") BigDecimal getValor() {
        return valor;
    }

    public void setValor(@NotNull(message = "O valor é obrigatório.") @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero.") @Digits(integer = 10, fraction = 2, message = "O valor deve ter no máximo 10 dígitos inteiros e 2 casas decimais.") BigDecimal valor) {
        this.valor = valor;
    }

    public @NotBlank(message = "A data da movimentação é obrigatória.") @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "A data deve estar no formato dd/MM/yyyy.") String getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(@NotBlank(message = "A data da movimentação é obrigatória.") @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "A data deve estar no formato dd/MM/yyyy.") String dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public @NotNull(message = "O tipo de movimentação (entrada ou saída) é obrigatório.") Boolean getEntradaSaida() {
        return entradaSaida;
    }

    public void setEntradaSaida(@NotNull(message = "O tipo de movimentação (entrada ou saída) é obrigatório.") Boolean entradaSaida) {
        this.entradaSaida = entradaSaida;
    }

    public @NotNull(message = "O setor é obrigatório.") Setor getSetor() {
        return setor;
    }

    public void setSetor(@NotNull(message = "O setor é obrigatório.") Setor setor) {
        this.setor = setor;
    }

    public @NotNull(message = "A comunidade é obrigatória.") Comunidade getComunidade() {
        return comunidade;
    }

    public void setComunidade(@NotNull(message = "A comunidade é obrigatória.") Comunidade comunidade) {
        this.comunidade = comunidade;
    }
}
