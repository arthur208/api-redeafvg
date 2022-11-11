package br.edu.unicesumar.example.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.unicesumar.example.domain.CotacaoParcial;
public interface CotacaoParcialRepository extends JpaRepository<CotacaoParcial, Long> {
    boolean existsById(Long id);

    Page<CotacaoParcial> findByidIgnoreCaseContaining(Long id,Pageable pageable);


    @Query(value = "select * from cotacao_parcial cp where cp.cotacaoprincipal_id = ?1", nativeQuery = true)
    List<CotacaoParcial> findCotacaoPrincipalId(Long id);
}
