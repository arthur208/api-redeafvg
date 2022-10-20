package br.edu.unicesumar.example.domain;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;



import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Fornecedor  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 

    @NotBlank
    private String RazaoSocial;

    @NotBlank
    private String nomeFantasia;

    @NotBlank
    private String fornecedor; 

    @NotBlank
    private String CNPJ;

    
    private String IE;

    @NotBlank
    private String Contato;

    @Email
    private String email;

    @NotBlank
    private String telefone;

    @NotBlank
    private String cep;

    @NotBlank
    private String cidade;

    @NotBlank
    private String uf;

    @NotBlank
    private String logradouro;

   
    private String numero;

    
    private String bairro;

    
    
    private String complemento;
}
