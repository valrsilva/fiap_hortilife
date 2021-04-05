package br.com.fiap.dto;

import java.util.List;

import br.com.fiap.model.Agrupamento;
import br.com.fiap.model.Story;

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
