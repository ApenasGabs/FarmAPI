package com.apenasgabs.farmapi.controller;

import com.apenasgabs.farmapi.model.Categoria;
import com.apenasgabs.farmapi.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

  @Autowired
  private CategoriaRepository categoriaRepository;

  @GetMapping
  public ResponseEntity<List<Categoria>> getTodasCategorias() {
    return ResponseEntity.ok(categoriaRepository.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Categoria> getCategoriaById(@PathVariable Long id) {
    return categoriaRepository.findById(id)
        .map(resposta -> ResponseEntity.ok(resposta))
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/nome/{nome}")
  public ResponseEntity<List<Categoria>> getCategoriasPorNome(@PathVariable String nome) {
    List<Categoria> categorias = categoriaRepository.findByNomeContainingIgnoreCase(nome);
    if (categorias.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(categorias);
  }

  @PostMapping
  public ResponseEntity<Categoria> criaCategoria(@Valid @RequestBody Categoria categoria) {
    return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Categoria> atualizaCategoria(@PathVariable Long id, @Valid @RequestBody Categoria categoria) {
    if (!categoriaRepository.existsById(id)) {
      return ResponseEntity.notFound().build();
    }
    categoria.setId(id);
    return ResponseEntity.ok(categoriaRepository.save(categoria));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletaCategoria(@PathVariable Long id) {
    if (!categoriaRepository.existsById(id)) {
      return ResponseEntity.notFound().build();
    }
    categoriaRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
