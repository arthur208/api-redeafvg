package br.edu.unicesumar.example.domain;




import javax.persistence.Entity;

import javax.persistence.Id;

import javax.validation.constraints.NotBlank;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutosCotacao {
    @Id
    private Long id;

    @NotBlank
    private String produto;

    @NotBlank
    private Number quantidade;

    @NotBlank
    private String preco;

    
    private String observacao;
}
