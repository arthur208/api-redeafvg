package br.edu.unicesumar.example.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.unicesumar.example.domain.Fornecedor;
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>{
    boolean existsById(Long Id);

    
    
    Page<Fornecedor> findByFornecedorIgnoreCaseContaining(String fornecedor,Pageable pageable);
}
