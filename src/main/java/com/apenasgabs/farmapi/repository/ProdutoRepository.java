package com.apenasgabs.farmapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apenasgabs.farmapi.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
}
