package br.edu.unicesumar.example.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.unicesumar.example.config.auth.Roles;
import br.edu.unicesumar.example.config.auth.jwt.Jwt;
import br.edu.unicesumar.example.config.auth.jwt.JwtTool;
import br.edu.unicesumar.example.domain.Users;
import br.edu.unicesumar.example.dto.sign.SignIn;
import br.edu.unicesumar.example.dto.sign.SignUp;
import br.edu.unicesumar.example.repository.UsersRepository;

@Service
public class UsersService implements UserDetailsService {

    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTool jwtTokenTool;

    @Autowired
    private UsersRepository usersRepository;

    @Value("${escoladeti.auth.admin.username}")
    private String adminUsername;

    @Value("${escoladeti.auth.admin.password}")
    private String adminPassword;

    @Override
    public Users loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findUsersByUsername(username);
    }

    public Jwt signIn(SignIn signIn) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(signIn.getUsername(), signIn.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Users userDetails = (Users) authentication.getPrincipal();

        return jwtTokenTool.generateToken(userDetails);

    }

    public Users signUp(SignUp signUp) {
        if (usersRepository.existsByUsername(signUp.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is already taken!");
        }

        if (usersRepository.existsByEmail(signUp.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is already in use!");
        }

        Users users = Users.builder()
                .username(signUp.getUsername())
                .password(passwordEncoder.encode(signUp.getPassword()))
                .email(signUp.getEmail())
                .firstName(signUp.getFirstName())
                .lastName(signUp.getLastName())
                .birtdate(signUp.getBirthdate()).build();

        return usersRepository.save(users);
    }

    @PostConstruct
    public void registerAdminUser() {

        if (!usersRepository.existsByUsername(this.adminUsername)) {
            Users admin = Users.builder()
                    .username(this.adminUsername)
                    .password(passwordEncoder.encode(this.adminPassword))
                    .firstName("Admin").build();

            admin.getRoles().add(Roles.ROLE_ADMIN);

            usersRepository.save(admin);
        }

    }

}
