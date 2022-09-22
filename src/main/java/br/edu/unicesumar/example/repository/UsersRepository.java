package br.edu.unicesumar.example.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unicesumar.example.domain.Users;

public interface UsersRepository extends JpaRepository<Users, UUID> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Users findUsersByUsername(String username);

}
