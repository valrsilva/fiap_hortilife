package br.com.fiap.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Produto {
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String detalhes;
    private double valor;
    
    @OneToOne
    private Categoria categoria;
    
    public Produto() {
    	
    }
    
	public Produto(String nome, String detalhes, double valor, Categoria categoria) {
		super();
		this.nome = nome;
		this.detalhes = detalhes;
		this.valor = valor;
		this.categoria = categoria;
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
	
	
	public Produto comNome(String nome) {
		this.nome = nome;
		return this;
	}
	public Produto comValor(double valor) {
		this.valor = valor;
		return this;
	}
	public Produto comCategoria(Categoria categoria) {
		this.categoria = categoria;
		return this;
	}
	public Produto comDetalhes(String detalhes) {
		this.detalhes = detalhes;
		return this;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", detalhes=" + detalhes + ", valor=" + valor + ", categoria="
				+ categoria + "]";
	}
	
	
	
}
