package br.edu.unicesumar.example.service;



import org.springframework.data.domain.Pageable;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import br.edu.unicesumar.example.domain.Produtos;

import br.edu.unicesumar.example.repository.ProdutosRepository;

@Service

public class ProdutosService {

    @Autowired
    private ProdutosRepository Produtosrepository;

 

    // public Page<Produtos> findAll(Pageable pageable) {
    //     return this.Produtosrepository.findAll(pageable);
    // } 

    
    public Optional<Produtos> findById(Long id) {
		return this.Produtosrepository.findById(id);
	}


    public Page<Produtos> findAll(String Produto, Pageable pageable) {
        return this.Produtosrepository.findByProdutoIgnoreCaseContaining(Produto, pageable);
    }


    public Produtos save(Produtos produto) {
        if (this.Produtosrepository.existsById(produto.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Matrícula já utilizada!");
        }
        return this.Produtosrepository.save(produto);
    }

    public Produtos update(Produtos Produtos) {
        Produtos ProdutosBancoDeDados = this.Produtosrepository.findById(Produtos.getId()).orElse(null);
        if (ProdutosBancoDeDados == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado!");
        }

        if (ProdutosBancoDeDados.getId().equals(ProdutosBancoDeDados.getId())) {
            return this.Produtosrepository.save(Produtos);
        }

        return this.save(Produtos);
    }
    public void delete(Long id) {
        if (!this.Produtosrepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado!");
        }

        this.Produtosrepository.deleteById(id);
    }
}