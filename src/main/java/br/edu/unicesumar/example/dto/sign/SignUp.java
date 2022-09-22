package br.edu.unicesumar.example.dto.sign;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class SignUp {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotNull
    private LocalDate birthdate;

    @Email
    private String email;

    @NotNull
    private String username;

    @Length(min = 6, max = 20)
    private String password;

}
