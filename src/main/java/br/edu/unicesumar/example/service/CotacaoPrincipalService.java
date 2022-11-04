package br.edu.unicesumar.example.service;

import org.springframework.data.domain.Pageable;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.unicesumar.example.domain.CotacaoPrincipal;

import br.edu.unicesumar.example.repository.CotacaoPrincipalRepository;


@Service
public class CotacaoPrincipalService {
    @Autowired
    private CotacaoPrincipalRepository CotacaoPrincipalRepository;

    public CotacaoPrincipal save(CotacaoPrincipal CotacaoPrincipal) {
        if (this.CotacaoPrincipalRepository.existsById(CotacaoPrincipal.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Matrícula já utilizada!");
        }
        return this.CotacaoPrincipalRepository.save(CotacaoPrincipal);
    }


    public Optional< CotacaoPrincipal> findById(Long id) {
		return this.CotacaoPrincipalRepository.findById(id);
	}



    public Page<CotacaoPrincipal> findAll(Pageable pageable) {
        return this.CotacaoPrincipalRepository.findAll(pageable);
    }


    public CotacaoPrincipal update(CotacaoPrincipal CotacaoPrincipal) {
        CotacaoPrincipal CotacaoPrincipalBancoDeDados = this.CotacaoPrincipalRepository.findById(CotacaoPrincipal.getId()).orElse(null);
        if (CotacaoPrincipalBancoDeDados == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado!");
        }

        if (CotacaoPrincipalBancoDeDados.getId().equals(CotacaoPrincipalBancoDeDados.getId())) {
            return this.CotacaoPrincipalRepository.save(CotacaoPrincipal);
        }

        return this.save(CotacaoPrincipal);
    }

    public void delete(Long id) {
        if (!this.CotacaoPrincipalRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado!");
        }

        this.CotacaoPrincipalRepository.deleteById(id);
    }
}
