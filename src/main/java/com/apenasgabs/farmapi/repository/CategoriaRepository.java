package com.apenasgabs.farmapi.repository;

import com.apenasgabs.farmapi.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
  List<Categoria> findByNomeContainingIgnoreCase(String nome);
}
