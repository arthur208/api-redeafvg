package br.edu.unicesumar.example.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.unicesumar.example.domain.Produtos;
public interface ProdutosRepository extends JpaRepository<Produtos, Long>{
    boolean existsById(Long Id);

    
    
     Page<Produtos> findByProdutoIgnoreCaseContaining(String Produto,Pageable pageable);
   
}
