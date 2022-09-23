package br.edu.unicesumar.example.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import br.edu.unicesumar.example.domain.Fornecedor;
import br.edu.unicesumar.example.domain.Users;
import br.edu.unicesumar.example.dto.sign.SignIn;
import br.edu.unicesumar.example.dto.sign.SignUp;
import br.edu.unicesumar.example.service.FornecedorService;
import lombok.SneakyThrows;


@RestController
@RequestMapping("/Fornecedor")
public class FornecedorController {
    
    @Autowired
    private FornecedorService service;

   
    // @GetMapping
    // public ResponseEntity<Page<Fornecedor>> buscarTodos(Pageable pageable) {
    //     return ResponseEntity.ok(this.service.findAll(pageable));
    // }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public ResponseEntity<Page<Fornecedor>> buscarTodosPorNome(@RequestParam(name = "Fornecedor", required = false, defaultValue = "") String fornecedor, Pageable pageable) {
        return ResponseEntity.ok(service.findAll(fornecedor, pageable));
    }

    @PostMapping
    @SneakyThrows
    public ResponseEntity<Fornecedor> salvar(@RequestBody Fornecedor Fornecedor) {
        Fornecedor ProdutoSalvo = service.save(Fornecedor);
        return ResponseEntity.created(new URI("/Fornecedor/" + Fornecedor.getId())).body(ProdutoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fornecedor> atualizar(@PathVariable("id") Long id, @RequestBody Fornecedor Fornecedor) {
        if (!id.equals(Fornecedor.getId())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(service.update(Fornecedor));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
