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
    private String RazaoSocial;

    @NotEmpty
    private String NomeFantasia;

    @NotNull
    private String CNPJ;

    @NotNull
    private String Logradouro;

    @NotNull
    private String Numero;

    @NotNull
    private String Complemento;

    private String IE;

    @Email
    private String email;

    @NotNull
    private String username;

    @Length(min = 6, max = 20)
    private String password;

}
