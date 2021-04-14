package br.com.fiap.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Subcategoria {
     
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String descricao;
    
    @JsonIgnore
    @ManyToOne
    private Categoria categoria;
    
    public Subcategoria() {
    	
    }

	public Subcategoria(long id, String descricao, Categoria categoria) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.categoria = categoria;
	}

	public Subcategoria(String descricao, Categoria categoria) {
		super();
		this.descricao = descricao;
		this.categoria = categoria;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	
}
