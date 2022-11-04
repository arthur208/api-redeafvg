package br.edu.unicesumar.example.domain;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CotacaoParcial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private Long id_comerciante;

    @NotNull
    private String data_atual_cotacao;

    @NotNull
    private String data_final_cotacao;

    @NotNull
    private String preco_total;

    private String status_cotacao;

    private String status_cliente;

    @NotBlank
    private String CNPJ;
    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cotacao_id")
    private List<ProdutosCotacao> ProdutosCotacao = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name = "cotacaoparcial_id")
    private CotacaoPrincipal CotacaoPrincipal ;

}
