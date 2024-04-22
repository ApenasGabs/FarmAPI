package com.apenasgabs.farmapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.apenasgabs.farmapi.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

  List<Produto> findByCategoriaNome(@Param("nomeCategoria") String nomeCategoria);

  List<Produto> findByNomeContainingIgnoreCase(String nome);

}
