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
import br.edu.unicesumar.example.domain.CotacaoPrincipal;
import br.edu.unicesumar.example.dto.sign.SignIn;
import br.edu.unicesumar.example.dto.sign.SignUp;
import br.edu.unicesumar.example.service.CotacaoPrincipalService;
import lombok.SneakyThrows;

@RestController
@CrossOrigin("*") 
@RequestMapping("/cotacao")
public class CotacaoPrincipalController {
    @Autowired
    private CotacaoPrincipalService service;

    @PostMapping
    @SneakyThrows
    public ResponseEntity< CotacaoPrincipal> salvar(@RequestBody CotacaoPrincipal  CotacaoPrincipal) {
        CotacaoPrincipal  = service.save( CotacaoPrincipal);
        return ResponseEntity.created(new URI("/CotacaoPrincipal/" +  CotacaoPrincipal.getId())).body( CotacaoPrincipal);
    }

    @GetMapping("/{id}")
    public ResponseEntity< CotacaoPrincipal> buscarPorId(@PathVariable(name="id") Long id) {
        Optional< CotacaoPrincipal>  CotacaoPrincipalOpt = this.service.findById(id);

        if( CotacaoPrincipalOpt.isPresent()) {
            return ResponseEntity.ok( CotacaoPrincipalOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<Page< CotacaoPrincipal>> buscarTodos(Pageable pageable) {
        return ResponseEntity.ok(this.service.findAll(pageable));
    }


    @PutMapping("/{id}")
    public ResponseEntity< CotacaoPrincipal> atualizar(@PathVariable("id") Long id, @RequestBody  CotacaoPrincipal  CotacaoPrincipal) {
        if (!id.equals( CotacaoPrincipal.getId())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(service.update( CotacaoPrincipal));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
