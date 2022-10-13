package br.edu.unicesumar.example.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.unicesumar.example.domain.CotacaoParcial;
public interface CotacaoParcialRepository extends JpaRepository<CotacaoParcial, Long> {
    boolean existsById(Long id);

    Page<CotacaoParcial> findByidIgnoreCaseContaining(Long id,Pageable pageable);
}
