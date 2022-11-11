package br.edu.unicesumar.example.controller;

import java.net.URI;
import java.util.List;
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
import br.edu.unicesumar.example.domain.CotacaoParcial;
import br.edu.unicesumar.example.dto.sign.SignIn;
import br.edu.unicesumar.example.dto.sign.SignUp;
import br.edu.unicesumar.example.service.CotacaoParcialService;
import lombok.SneakyThrows;

@RestController
@CrossOrigin("*") 
@RequestMapping("/CotacaoParcial")
public class CotacãoParcialController {
    @Autowired
    private CotacaoParcialService service;

    @PostMapping
    @SneakyThrows
    public ResponseEntity<CotacaoParcial> salvar(@RequestBody CotacaoParcial CotacãoParcial) {
        CotacaoParcial CotaçãoParcial = service.save(CotacãoParcial);
        return ResponseEntity.created(new URI("/CotaçãoParcial/" + CotacãoParcial.getId())).body(CotaçãoParcial);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CotacaoParcial> buscarPorId(@PathVariable(name="id") Long id) {
        Optional<CotacaoParcial> CotacaoParcialOpt = this.service.findById(id);

        if(CotacaoParcialOpt.isPresent()) {
            return ResponseEntity.ok(CotacaoParcialOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

 


    @GetMapping("/cotacaoprincipal/{id}")
    public ResponseEntity<List<CotacaoParcial>> buscarCotacaoPrincipalID(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(this.service.findCotacaoPrincipal(id));
    }

    @GetMapping("/cotacaoprincipal")
    public ResponseEntity<List<CotacaoParcial>> buscarCotacaoPrincipal(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(this.service.findCotacaoPrincipal(id));
    }






    
    @GetMapping
    public ResponseEntity<Page<CotacaoParcial>> buscarTodosPorNome(@RequestParam(name = "CotacaoParcial", required = false, defaultValue = "") Long id, Pageable pageable) {
        return ResponseEntity.ok(service.findAll(id, pageable));
    }


    @PutMapping("/{id}")
    public ResponseEntity<CotacaoParcial> atualizar(@PathVariable("id") Long id, @RequestBody CotacaoParcial CotacaoParcial) {
        if (!id.equals(CotacaoParcial.getId())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(service.update(CotacaoParcial));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
