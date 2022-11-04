package br.edu.unicesumar.example.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.unicesumar.example.domain.CotacaoPrincipal;
public interface CotacaoPrincipalRepository extends JpaRepository<CotacaoPrincipal, Long> {
    boolean existsById(Long id);

    // Page<CotacaoPrincipal> findByidIgnoreCaseContaining(Pageable pageable);
}
