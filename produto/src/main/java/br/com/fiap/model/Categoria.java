package br.com.fiap.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categoria {
     
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String descricao;
    
    @OneToMany(mappedBy="categoria")
    private List<Subcategoria> subcategoria;
    
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

	public List<Subcategoria> getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(List<Subcategoria> subcategoria) {
		this.subcategoria = subcategoria;
	}
	
	
		
}
