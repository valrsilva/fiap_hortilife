package br.com.fiap.dto;

public class MidiaDto {

    private long id;
    private String tipo;
    private String titulo;
    private String descricao;
    private String genero;
    private Integer ano;
    private String diretor;
    private Integer visualizacoes;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public String getDiretor() {
		return diretor;
	}
	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}
	public Integer getVisualizacoes() {
		return visualizacoes;
	}
	public void setVisualizacoes(Integer visualizacoes) {
		this.visualizacoes = visualizacoes;
	}
    
    
    
}
