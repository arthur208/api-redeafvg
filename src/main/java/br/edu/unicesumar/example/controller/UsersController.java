package br.edu.unicesumar.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unicesumar.example.domain.Users;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @GetMapping("/me")
    public ResponseEntity<Users> getMe(@AuthenticationPrincipal Users user) {
        return ResponseEntity.ok(user);
    }

}
