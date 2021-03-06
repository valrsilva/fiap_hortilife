package br.com.fiap.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.fiap.model.dto.Usuario;

@Entity
public class Produto {
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 60, message = "Nome deve possuir entre 3 e 60 caracteres.")
    @Column(length = 60)
    private String nome;
    
    @Column(length = 400)
    private String detalhes;
    
    @NotBlank(message = "Volume é obrigatório")
    @Size(min = 3, max = 30, message = "Volume deve possuir entre 3 e 30 caracteres.")
    @Column(length = 30)
    private String volume;
    
    private double valor;
    private boolean ativo;
    private long estoque;
    private Date dataInclusao;
    private Date dataAtualizacao;
    private long idUsuario;
    
    @Transient
    private Usuario usuario;
    
    @OneToOne
    private Categoria categoria;
    
    @OneToOne
    private Subcategoria subcategoria;
    
    private String imagemPrincipal;
    
    @OneToMany(mappedBy="produto")
    @JsonManagedReference
    private List<ImagemProduto> imagemProduto;
    
    @OneToOne
    private Promocao promocao;
    
    public Produto() {
    	
    }
    
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public long getEstoque() {
		return estoque;
	}

	public void setEstoque(long estoque) {
		this.estoque = estoque;
	}

	public Promocao getPromocao() {
		return promocao;
	}

	public void setPromocao(Promocao promocao) {
		this.promocao = promocao;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public List<ImagemProduto> getImagemProduto() {
		return imagemProduto;
	}

	public void setImagemProduto(List<ImagemProduto> imagemProduto) {
		this.imagemProduto = imagemProduto;
	}

	public Subcategoria getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}

	public String getImagemPrincipal() {
		return imagemPrincipal;
	}

	public void setImagemPrincipal(String imagemPrincipal) {
		this.imagemPrincipal = imagemPrincipal;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
