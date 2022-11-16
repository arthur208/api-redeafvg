package br.edu.unicesumar.example.service;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.unicesumar.example.domain.CotacaoParcial;
import br.edu.unicesumar.example.domain.Fornecedor;
import br.edu.unicesumar.example.repository.CotacaoParcialRepository;
import br.edu.unicesumar.example.repository.FornecedorRepository;

@Service
public class CotacaoParcialService {

    @Autowired
    private CotacaoParcialRepository CotaçãoParcialrepository;

    public CotacaoParcial save(CotacaoParcial CotaçãoParcial) {
        if (this.CotaçãoParcialrepository.existsById(CotaçãoParcial.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Matrícula já utilizada!");
        }
        return this.CotaçãoParcialrepository.save(CotaçãoParcial);
    }

    public List<CotacaoParcial> findCotacaoPrincipal(Long id) {
        return this.CotaçãoParcialrepository.findCotacaoPrincipalId(id);
    }


    public Optional< CotacaoParcial> findById(Long id) {
		return this.CotaçãoParcialrepository.findById(id);
	}
    public Page<CotacaoParcial> findAll(Long id, Pageable pageable) {
        return this.CotaçãoParcialrepository.findByidIgnoreCaseContaining(id, pageable);
    }

    public List<CotacaoParcial> findAll2() {
        return this.CotaçãoParcialrepository.findCotacaoPrincipal();
    }

   


    public CotacaoParcial update(CotacaoParcial CotacaoParcial) {
        CotacaoParcial CotacaoParcialBancoDeDados = this.CotaçãoParcialrepository.findById(CotacaoParcial.getId()).orElse(null);
        if (CotacaoParcialBancoDeDados == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado!");
        }

        if (CotacaoParcialBancoDeDados.getId().equals(CotacaoParcialBancoDeDados.getId())) {
            return this.CotaçãoParcialrepository.save(CotacaoParcial);
        }

        return this.save(CotacaoParcial);
    }

    public void delete(Long id) {
        if (!this.CotaçãoParcialrepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado!");
        }

        this.CotaçãoParcialrepository.deleteById(id);
    }

}
