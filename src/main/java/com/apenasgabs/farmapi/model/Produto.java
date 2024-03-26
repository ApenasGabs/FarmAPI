package com.apenasgabs.farmapi.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_produto")
public class Produto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Nome é obrigatório!")
  @Size(min = 5, max = 100)
  private String nome;

  @NotBlank(message = "Descrição é obrigatório!")
  @Size(min = 5, max = 255)
  private String descricao;

  @NotNull(message = "Preço é obrigatório!")
  @DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser maior que 0")
  @Digits(integer = 6, fraction = 2, message = "O preço deve ter no máximo 6 dígitos inteiros e 2 fracionários")
  private BigDecimal preco;

  @NotNull(message = "A quantidade em estoque é obrigatória!")
  private Integer quantidadeEstoque;

  @ManyToOne
  @JsonIgnoreProperties("produto")
  private Categoria categoria;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public BigDecimal getPreco() {
    return preco;
  }

  public void setPreco(BigDecimal preco) {
    this.preco = preco;
  }

  public Integer getQuantidadeEstoque() {
    return quantidadeEstoque;
  }

  public void setQuantidadeEstoque(Integer quantidadeEstoque) {
    this.quantidadeEstoque = quantidadeEstoque;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }
}
