package br.com.fiap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.model.Agrupamento;
import br.com.fiap.model.Feed;
import br.com.fiap.model.Story;
import br.com.fiap.repository.AgrupamentoRepository;
import br.com.fiap.repository.PostRepository;
import br.com.fiap.repository.StoryRepository;

@RestController
@CrossOrigin
@RefreshScope
public class FeedController {

	@Autowired
	PostRepository postRepository;
	
	@Autowired
	StoryRepository storyRepository;
	
	@Autowired
	AgrupamentoRepository agrupamentoRepository;
	
	@Value("${max-results:10}")
    private int maxResults;
	
	@GetMapping("/feed/{idUsuario}")
	public Feed getFeedByUsuario(@PathVariable("idUsuario") long idUsuario) {
		
		Feed feed = new Feed();
		List<Story> stories = storyRepository.findAllStoriesByIdUsuario(idUsuario);
		List<Agrupamento> agrupamentos = agrupamentoRepository.findAll();
		
		feed.setAgrupamentos(agrupamentos);
		feed.setStories(stories);
		
		return feed;
		
	}
	
}