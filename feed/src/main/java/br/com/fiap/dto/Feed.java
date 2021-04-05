package br.com.fiap.model;

import java.util.List;

public class Feed {

	private List<Story> stories;
	private List<Agrupamento> agrupamentos;
	
	public List<Story> getStories() {
		return stories;
	}
	public void setStories(List<Story> stories) {
		this.stories = stories;
	}
	public List<Agrupamento> getAgrupamentos() {
		return agrupamentos;
	}
	public void setAgrupamentos(List<Agrupamento> agrupamentos) {
		this.agrupamentos = agrupamentos;
	}
	
	
}
