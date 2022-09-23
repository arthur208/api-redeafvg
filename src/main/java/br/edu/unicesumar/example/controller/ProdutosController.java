package br.edu.unicesumar.example.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import br.edu.unicesumar.example.config.auth.jwt.Jwt;
import br.edu.unicesumar.example.domain.Produtos;
import br.edu.unicesumar.example.domain.Users;
import br.edu.unicesumar.example.dto.sign.SignIn;
import br.edu.unicesumar.example.dto.sign.SignUp;
import br.edu.unicesumar.example.service.ProdutosService;
import lombok.SneakyThrows;


@RestController
@RequestMapping("/Produtos")
public class ProdutosController {

    @Autowired
    private ProdutosService service;

   
    // @GetMapping
    // public ResponseEntity<Page<Produtos>> buscarTodos(Pageable pageable) {
    //     return ResponseEntity.ok(this.service.findAll(pageable));
    // }

    @GetMapping
    public ResponseEntity<Page<Produtos>> buscarTodosPorNome(@RequestParam(name = "Produto", required = false, defaultValue = "") String Produto, Pageable pageable) {
        return ResponseEntity.ok(service.findAll(Produto, pageable));
    }

    @PostMapping
    @SneakyThrows
    public ResponseEntity<Produtos> salvar(@RequestBody Produtos Produto) {
        Produtos ProdutoSalvo = service.save(Produto);
        return ResponseEntity.created(new URI("/produto/" + Produto.getId())).body(ProdutoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produtos> atualizar(@PathVariable("id") Long id, @RequestBody Produtos produtos) {
        if (!id.equals(produtos.getId())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(service.update(produtos));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }


}
