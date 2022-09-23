package br.edu.unicesumar.example.service;



import org.springframework.data.domain.Pageable;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import br.edu.unicesumar.example.domain.Fornecedor;

import br.edu.unicesumar.example.repository.FornecedorRepository;

@Service
public class FornecedorService {
    
    @Autowired
    private FornecedorRepository Fornecedorrepository;

    public Page<Fornecedor> findAll(String fornecedor, Pageable pageable) {
        return this.Fornecedorrepository.findByFornecedorIgnoreCaseContaining(fornecedor, pageable);
    }


    public Fornecedor save(Fornecedor Fornecedor) {
        if (this.Fornecedorrepository.existsById(Fornecedor.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Matrícula já utilizada!");
        }
        return this.Fornecedorrepository.save(Fornecedor);
    }

    public Fornecedor update(Fornecedor Fornecedor) {
        Fornecedor FornecedorBancoDeDados = this.Fornecedorrepository.findById(Fornecedor.getId()).orElse(null);
        if (FornecedorBancoDeDados == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado!");
        }

        if (FornecedorBancoDeDados.getId().equals(FornecedorBancoDeDados.getId())) {
            return this.Fornecedorrepository.save(Fornecedor);
        }

        return this.save(Fornecedor);
    }
    public void delete(Long id) {
        if (!this.Fornecedorrepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado!");
        }

        this.Fornecedorrepository.deleteById(id);
    }
}
