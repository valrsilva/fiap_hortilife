package br.com.fiap.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class Produto {
     
    private long id;
    private String nome;
    private String detalhes;
    private String volume;
    private double valor;
    private boolean ativo;
    private long estoque;
    private Date dataInclusao;
    private Date dataAtualizacao;
    //private Categoria categoria;
    //private List<ImagemProduto> imagemProduto;
    //private Promocao promocao;
	
}
