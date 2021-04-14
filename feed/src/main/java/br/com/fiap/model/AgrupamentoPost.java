package br.com.fiap.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class AgrupamentoPost {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@OneToOne
	private Post post;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
    private Agrupamento agrupamento;
	
	
	public AgrupamentoPost() {
		super();
	}

	public AgrupamentoPost(long id, Post post, Agrupamento agrupamento) {
		super();
		this.id = id;
		this.post = post;
		this.agrupamento = agrupamento;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Agrupamento getAgrupamento() {
		return agrupamento;
	}
	public void setAgrupamento(Agrupamento agrupamento) {
		this.agrupamento = agrupamento;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	
}
