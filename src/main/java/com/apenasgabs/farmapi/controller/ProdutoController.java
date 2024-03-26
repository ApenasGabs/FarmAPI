package com.apenasgabs.farmapi.controller;

import com.apenasgabs.farmapi.model.Produto;
import com.apenasgabs.farmapi.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

  @Autowired
  private ProdutoRepository produtoRepository;

  @GetMapping
  public ResponseEntity<List<Produto>> getAllProdutos() {
    return ResponseEntity.ok(produtoRepository.findAll());
  }

  @GetMapping("/nome")
  public ResponseEntity<List<Produto>> getProdutosPorNome(@RequestParam String nomeProduto) {
      List<Produto> produtos = produtoRepository.findByNomeContainingIgnoreCase(nomeProduto);
      if (produtos.isEmpty()) {
          return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok(produtos);
  }
  

  @GetMapping("/{id}")
  public ResponseEntity<Produto> getProdutoById(@PathVariable Long id) {
    return produtoRepository.findById(id)
        .map(response -> ResponseEntity.ok(response))
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/categoria/{categoriaNome}")
  public ResponseEntity<List<Produto>> getProdutosPorCategoriaNome(@PathVariable String categoriaNome) {
    List<Produto> produtos = produtoRepository.findByCategoriaNome(categoriaNome);
    if (produtos.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(produtos);
  }

  @PostMapping
  public ResponseEntity<Produto> createProduto(@Valid @RequestBody Produto produto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @Valid @RequestBody Produto produto) {
    if (!produtoRepository.existsById(id)) {
      return ResponseEntity.notFound().build();
    }
    produto.setId(id);
    return ResponseEntity.ok(produtoRepository.save(produto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
    if (!produtoRepository.existsById(id)) {
      return ResponseEntity.notFound().build();
    }
    produtoRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}