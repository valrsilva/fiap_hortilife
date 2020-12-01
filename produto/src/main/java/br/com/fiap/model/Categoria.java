package br.com.fiap.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Categoria {
     
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String descricao;
    
    public Categoria() {
    	
    }

	public Categoria(long id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}

	public Categoria(String descricao) {
		super();
		this.descricao = descricao;
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

}
